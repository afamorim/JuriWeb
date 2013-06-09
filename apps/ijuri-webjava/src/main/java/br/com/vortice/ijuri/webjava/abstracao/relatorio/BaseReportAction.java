package br.com.vortice.ijuri.webjava.abstracao.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;
import org.springframework.web.servlet.FrameworkServlet;

import br.com.vortice.ijuri.abstracao.view.MensagemIf;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;

public abstract class BaseReportAction extends FrameworkServlet {

    public static final String REPORT_TYPE_PARAM = "reportType";
    public static final int REPORT_PRINT = 5;
    private static final Logger LOG = Logger.getLogger(BaseReportAction.class);
    

	
    protected void doService(HttpServletRequest pRequest, HttpServletResponse pResponse) 
    	throws IOException, ServletException {    
        try{
		        if (pRequest.getParameter(REPORT_TYPE_PARAM)==null){
		            throw new AmbienteException(new StringBuffer()
		                    .append("O tipo do relatório")
		                    .append(" gerado deve ser informado via URL: reportType ")
		                    .append("não existe.").toString()); 
		        
		        }
		        
		        /*
		         * Obtém os parâmetros da URL e converte-os para um MAP de parâmetros  
		         * para o relatório.
		         */
		        Map parametros = new HashMap();
		        String item = null;
		        String valor = null;
		        
		        for (Iterator iter = pRequest.getParameterMap().keySet().iterator(); iter.hasNext(); ) {
		            item = (String) iter.next();
		            if (!item.equals(REPORT_TYPE_PARAM)){
		                valor = pRequest.getParameter(item);
		                parametros.put(item,valor);
		            }
		                
		        }
		        
		        String reportName = getReport(pRequest);
		        
		        if (reportName==null || reportName.trim().equals("")){
		            throw new AmbienteException(
		            		new StringBuffer()
								.append(" O nome do arquivo do relatório deve ")
								.append(" ser informado na classe filha.")
								.toString());
				      }
		        
		        /*
		         * Obtém o caminho físico do contexto WEB para recuperar o arquivo compilado 
		         * (.jasper) e gerar o relatório.
		         */    
		        String nomeRelatorio = getServletContext().getRealPath(reportName);
		        
		        LOG.debug("reportName " + reportName);
		        LOG.debug("getWebApplicationContext().getServletContext().getRealPath() " + getWebApplicationContext().getServletContext().getRealPath(""));
		        
		        Object retorno = configurationReport(pRequest,pResponse,parametros);
                JRDataSource dataSource = null;
		        if (retorno != null) {
		            /*
                     * Constrói uma fonte de dados(JRDataSource) para o jasper reports  
                     * a partir de uma collection.
                     */
                    if (retorno instanceof Collection){
                        dataSource = new JavaBeanReportDataSource((Collection)retorno);
                    }else{
                        Collection col = new ArrayList();
                        col.add(retorno);
                        dataSource = new JavaBeanReportDataSource(col);
                    }
                    
		        }
		        LOG.debug("nomeRelatorio " + nomeRelatorio);
		        JasperReport report = (JasperReport)JRLoader.loadObject(nomeRelatorio);
		        
		        //Seta o LOCALE para o relatório
                parametros.put("REPORT_LOCALE", getLocale());
                
                //Seta o endereço do cliente
                MessageResources resource = MessageResources.getMessageResources("clienteResource");
                parametros.put("ENDERECO_CLIENTE",resource.getMessage(MensagemIf.ENDERECO_CLIENTE));
                
		        JasperPrint jasperPrint = JasperFillManager.fillReport(report,parametros,dataSource);
		        
		        JRAbstractExporter exporter = null;
		        
		        int type = Integer.parseInt(pRequest.getParameter(REPORT_TYPE_PARAM));
		        switch (type) {
		        	case ReportType.REPORT_TYPE_PDF:	   
		    	   		    		exporter = new JRPdfExporter();
		    	   		    		pResponse.setContentType("application/pdf");
		    	   		    	break;
		    	   		    	
		        	case ReportType.REPORT_TYPE_HTML:
		        		    		exporter = new JRHtmlExporter();

		        		    		//Map criado de uso interno para controle de imagens do jasper Reports
		        		    		Map imagesMap = new HashMap();
		        		    		pRequest.getSession().setAttribute("IMAGES_MAP", imagesMap);
		        		    		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		        		    		
		        		    		/*
		        		    		 * Indica ao exporter a ImageServlet do relatório HTML para ser feito o mapeamento.
		        		    		 * No caso, é utilizado uma Servlet JCImageReportServlet com mapeamento no web.xml
		        		    		 * da aplicacao - imageReport   
		        		    		 */ 
		        		    		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, pRequest.getContextPath()+"/imageReport?image=");
		        		    		pResponse.setContentType("text/html");
		    				    break;
		    				    
		        	case ReportType.REPORT_TYPE_XLS:
                    				exporter = new JRXlsExporter();
                    				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                    				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
                    				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.TRUE);
                    				pResponse.setContentType("application/vnd.ms-excel");
		    				    break;
		    				    
		        	case ReportType.REPORT_TYPE_TXT:
                    				exporter = new JRCsvExporter();
		        					exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,";");
        				   		break;
        			default:
        					throw new AmbienteException("O valor do parâmetro 'reportType' é inválido. ");
              }
		        
			        
			    ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
			        
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutput);
		        exporter.exportReport();
		        
		        byte[] bytes = byteArrayOutput.toByteArray();
		        pResponse.setContentLength(bytes.length);
				        

		        // Escreve o arquivo para outputStream do response para se exibido no browser
		        ServletOutputStream outputStream = pResponse.getOutputStream();
		        outputStream.write(bytes, 0, bytes.length);
		        outputStream.flush();
		        outputStream.close();

        }catch(Exception jre){
            tratarExcecao(jre,pRequest,pResponse);
        }
    }
    
    private void tratarExcecao(Exception e,HttpServletRequest request,HttpServletResponse response)
    	throws IOException,ServletException{
	    
        if (!(e instanceof AplicacaoException)){
            gerarTelaDeErro(e, request, response);
        }else{
            
            response.setContentType("text/html");
            
            ServletOutputStream stream = response.getOutputStream();
            stream.println("<html>");
            stream.println("<head>");
            stream.println("</head>");
            stream.println("<script language='javascript'>");
            stream.println("opener.fecharPopUp(window,'"+e.getMessage()+"');");
            stream.println("</script>");
            stream.println("</html>");
            
            stream.flush();
            stream.close();
        }  
        
    }
    
    
    /**
     * @return nome do arquivo(.jasper) do relatório copilado com o caminho relativo.
     */
    protected abstract String getReport(HttpServletRequest pRequest);
    
    /**
     * Método responsável pela configuração do relatório - ajustes de parâmetros 
     * e obtenção do objeto ou coleção de objetos para preenchimento do relatório.    
     * @return Um objeto ou uma coleção do objeto que representa cada registro(linha do relatório).   
     */
    protected abstract Object configurationReport(HttpServletRequest pRequest, 
            HttpServletResponse pResponse, Map pMap) throws AmbienteException,AplicacaoException;
   
    
    /**
     * Método através do qual o usuário pode inserir uma imágem a ser apresentada no relatório que 
     * está sendo gerado
     * @param parametros
     * @param parameterName
     * @param path
     * @throws JCAmbienteException
     */
    public void setParameterImagePath(Map parametros, String parameterName,String path){
        if (parameterName==null){
            throw new IllegalArgumentException("Nome do parâmetro deve ser informado.");
        }
        if (path==null){
            throw new IllegalArgumentException("O caminho relativo da imagem deve ser informado.");
        }
        parametros.put(parameterName,getServletContext().getRealPath(path));
    }
    
    private void fecharJanela(HttpServletResponse aResponse) throws AmbienteException{
   	   try{
   			    aResponse.setContentType("text/html");
   			    PrintWriter out = aResponse.getWriter();
   					out.println("<html>");
   					out.println("<head>");
   					out.println("<body>");
   					out.print("<script> ");
   					out.print(" window.close(); ");
   					out.print("</script>");
   					out.println("</body>");
   					out.println("</html>");
   					out.flush();
   					out.close();
   	   }catch(IOException e){
   	       throw new AmbienteException(e.getMessage(),e);
   	   }
   	}
    
    protected void addSubReport(String name,String path,Collection list,Map params)
    	throws AmbienteException{
	      try{
	        if (name==null)
	            throw new IllegalArgumentException("O nome do sub-report deve ser informado.");
	        
	        if (path==null)
	            throw new IllegalArgumentException("O caminho relativo do sub-report deve ser informado.");
	        
	        if (list==null)
	            throw new IllegalArgumentException("Uma colecão de elementos para o sub-report deve ser informado.");
	        // getServletContext().getRealPath(
	        String realPathSubReport = this.getWebApplicationContext().getServletContext().getRealPath(path);
	        
	        JasperReport subReport = (JasperReport)JRLoader.loadObject(realPathSubReport);
	        params.put(name+"SubReport",subReport);
	  
	        params.put(name+"DS", new JavaBeanReportDataSource(list));
	      }catch(JRException e){
	          e.printStackTrace();
	          throw new AmbienteException("Erro ao adicionar o subreport: "+name,e);
	      }   
    }
    
    protected Locale getLocale(){
        String linguagem = getServletConfig().getServletContext().getInitParameter("LOCALE_LANGUAGE");
        String pais = getServletConfig().getServletContext().getInitParameter("LOCALE_COUNTRY");
        return new Locale(linguagem,pais);
    }
    
    public void gerarTelaDeErro(Throwable e,            
            HttpServletRequest pRequest,
            HttpServletResponse pResponse) throws IOException,ServletException{
		
    	e.printStackTrace();  
		
    	ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		PrintStream printStream=new PrintStream(outputStream);
		e.printStackTrace(printStream);
		String stackTrace = outputStream.toString();
		
		pRequest.setAttribute("STACK_TRACE",stackTrace);
		
		pRequest.setAttribute("MESSAGE",e.getMessage());
		
		pRequest.setAttribute("CLASS_NAME",e.getClass());
		
		pRequest.getRequestDispatcher("/erro.jsp").forward(pRequest, pResponse);

    }
}
