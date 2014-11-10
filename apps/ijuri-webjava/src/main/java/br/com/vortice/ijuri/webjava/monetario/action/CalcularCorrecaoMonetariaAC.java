package br.com.vortice.ijuri.webjava.monetario.action;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.core.monetario.CorrecaoMonetariaVO;
import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;


public class CalcularCorrecaoMonetariaAC extends FrameworkServlet {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String valorHistorico = request.getParameter("valorHistorico");
		String dataInicial = request.getParameter("dataInicial");
		String dataFinal = request.getParameter("dataFinal");
		String juros = request.getParameter("juros");
		String honorarios = request.getParameter("honorarios");
		String multa = request.getParameter("multa");
		String codigoIndice = request.getParameter("indiceReajusteCodigo");
		String cliente = request.getParameter("cliente");
		String devedor = request.getParameter("devedor");
		
		ArrayList collCorrecao = null;
		if (request.getParameter("arrayCorrecao") != null && !request.getParameter("arrayCorrecao").trim().equals(""))
			collCorrecao = (ArrayList)Base64Decoder.decodeToObject(request.getParameter("arrayCorrecao"));
		else collCorrecao = new ArrayList();

		CorrecaoMonetariaVO vo = new CorrecaoMonetariaVO();
		
		try{
            NumberFormat decimalFmt = DecimalFormat.getInstance(getLocale());
			vo.setValorHistorico(new Double(decimalFmt.parse(valorHistorico).doubleValue()));
			vo.setJuros(new Double(decimalFmt.parse(juros).doubleValue()));
			vo.setHonorarios(new Double(decimalFmt.parse(honorarios).doubleValue()));
			vo.setIndiceReajuste(new IndiceReajusteVO(new Integer(codigoIndice)));
			vo.setMulta(new Double(decimalFmt.parse(multa).doubleValue()));
			
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			vo.setDataInicial(formato.parse(dataInicial));
			vo.setDataFinal(formato.parse(dataFinal));
			CorrecaoMonetariaVO correcao = correcaoMonetariaDelegate.calcularCorrecao(vo);
			correcao.setCliente(cliente);
			correcao.setDevedor(devedor);
			correcao.setJurosValor(juros);
			correcao.setMultaValor(multa);
			correcao.setHonorariosValor(honorarios);
			correcao.setValor(valorHistorico);
			//correcao.setCodigo(new Integer(collCorrecao.size()));
			collCorrecao.add(correcao);
			request.setAttribute("arrayCorrecao", Base64Encoder.encode(collCorrecao));
			request.setAttribute("collCorrecao", collCorrecao);
			
			//System.out.println("DEVEDOR:>>> " + cliente);
			//System.out.println("CLIENTE:>>> " + devedor);
			
			request.getRequestDispatcher("/admin/correcaoMonetaria/tabelaCorrecaoMonetaria.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    @Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		correcaoMonetariaDelegate = (CorrecaoMonetariaDelegate)context.getBean("correcaoMonetariaDelegate");
	}

    protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
    
	/**
     * Retorna o locale configurado no web.xml. (LOCALE_LANGUAGE & LOCALE_COUNTRY)
     * @return java.util.Locale da Aplicação
     */
    protected Locale getLocale(){
        String linguagem = getServletConfig().getServletContext().getInitParameter("LOCALE_LANGUAGE");
        String pais = getServletConfig().getServletContext().getInitParameter("LOCALE_COUNTRY");
        return new Locale(linguagem,pais); 
        
    }
}
