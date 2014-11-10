package br.com.vortice.ijuri.webjava.documento.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vortice.ijuri.core.documento.DocumentoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseDownloadAction;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class DocumentoDownloadAC extends BaseDownloadAction {
	
    protected DocumentoVO getDocumento(HttpServletRequest request, HttpServletResponse response) 
        throws AmbienteException,AplicacaoException {
        
        DocumentoVO documento = new DocumentoVO();
        try{
            documento.setCodigo(Long.valueOf(request.getParameter("codigo")));
            
            documento = documentoDelegate.findByPrimaryKey(documento);
            ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(documento.getBytes()));
            zipInputStream.getNextEntry();
            
            ByteArrayOutputStream stream = 
                new ByteArrayOutputStream();
            BufferedOutputStream buffer = new BufferedOutputStream(stream,DocumentoVO.BUFFER_SIZE);
            byte[] newBytes = new byte[DocumentoVO.BUFFER_SIZE];
            int count;
            while( ( count = zipInputStream.read(newBytes, 0, DocumentoVO.BUFFER_SIZE ) ) != -1 )
            {
              buffer.write(newBytes, 0, count);
            }
            buffer.flush();
            buffer.close();
            
            zipInputStream.close();
            
            documento.setBytes(stream.toByteArray());
            
        }catch (IOException e) {
            e.printStackTrace(); 
        }
        return documento;   
    }
}