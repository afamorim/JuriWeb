package br.com.vortice.ijuri.webjava.abstracao.view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vortice.ijuri.documento.DocumentoVO;
import br.com.vortice.ijuri.documento.cliente.web.DocumentoDelegate;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;

public abstract class BaseDownloadAction extends HttpServlet {
	
	protected DocumentoDelegate documentoDelegate;
	
    public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse)
    throws ServletException, IOException {
        this.doGet(pRequest, pResponse);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException { 
        
        ServletOutputStream outputStream = null;
        
        try{
            
            DocumentoVO documento = getDocumento(request, response);
            
            response.resetBuffer();
            //Informa que o arquivo deve ser baixado
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Disposition", "attachment; filename="+documento.getNome());
            
                        
            //Seta o tipo do conteudo do documento
            String contentType = getContentType(documento.getNome());
            if (contentType != null)
                response.setContentType(contentType);
            
            //Seta o tamanho do documneto que será enviado
            byte[] bytes = documento.getBytes();
            response.setContentLength(bytes.length);
            
            // Escreve o arquivo para outputStream do response para se exibido no browser
            outputStream = response.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            outputStream.write(bytes, 0, bytes.length);
            
            try{
                outputStream.flush();
            }catch(SocketException se){//Caso o usuário não queira baixar o documento}
                se.printStackTrace();
            }
            outputStream.close();
            
        }catch(Exception e){
            e.printStackTrace();
            throw new ServletException("Erro ao efetuar download do arquivo.",e);
        }finally{
            if (outputStream!=null)
                outputStream.close();
        }    
        
    }
    
    protected abstract DocumentoVO getDocumento(HttpServletRequest request, HttpServletResponse response)
        throws AmbienteException,AplicacaoException;
    
    protected String getContentType(String fileName){
        if (fileName.indexOf(".txt") > 0){
            return "text/plain";
        }else if (fileName.indexOf(".doc") > 0){
            return "application/msword";
        }else if (fileName.indexOf(".xls") > 0){
            return "application/vnd.ms-excel";
        }else if (fileName.indexOf(".ppt") > 0){
            return "application/vnd.ms-powerpoint";
        }else if (fileName.indexOf(".htm") > 0){
            return "text/html";
        }else if (fileName.indexOf(".pdf") > 0){
            return "application/pdf";
        }else if (fileName.indexOf(".zip") > 0){
            return "application/zip";
        }else if (fileName.indexOf(".rtf") > 0){
            return "application/rtf";
        }else if (fileName.indexOf(".gif") > 0){
            return "image/gif";
        }else if (fileName.indexOf(".jpg") > 0 || fileName.indexOf(".jpeg") > 0 || fileName.indexOf(".jpe") > 0){
            return "image/jpeg";
        }
        return null; 
    }
    

}