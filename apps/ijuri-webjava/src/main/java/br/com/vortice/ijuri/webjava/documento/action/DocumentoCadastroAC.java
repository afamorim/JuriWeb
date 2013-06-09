package br.com.vortice.ijuri.documento.cliente.web.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.vortice.ijuri.abstracao.view.BaseAction;
import br.com.vortice.ijuri.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.documento.DiretorioVO;
import br.com.vortice.ijuri.documento.DocumentoVO;
import br.com.vortice.ijuri.documento.cliente.web.DocumentoDelegate;
import br.com.vortice.ijuri.documento.cliente.web.form.DocumentoForm;
import com.vortice.exception.AplicacaoException;

/**
 * @author Amadeu
 *
 */
public class DocumentoCadastroAC extends BaseAction {
	
	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DocumentoForm docForm = (DocumentoForm)form;
        
        DocumentoVO documento = new DocumentoVO();
        
        DiretorioVO diretorio = new DiretorioVO();
        diretorio.setCodigo(docForm.getDiretorioCodigo());
        documento.setDiretorio(diretorio);
        
        //Zipa arquivo
        FormFile formFile = docForm.getArquivo();
        
        ByteArrayOutputStream streamZipado = new ByteArrayOutputStream();  
        ZipOutputStream zipStream = new ZipOutputStream(streamZipado);
        
        ZipEntry entry = new ZipEntry("file.zip");
        entry.setMethod(ZipEntry.DEFLATED);
        zipStream.putNextEntry(entry);
        
        //Read data from the source file and write it out to the zip file
        int count;
        ByteArrayInputStream inputStream = 
            new ByteArrayInputStream(formFile.getFileData(),0,formFile.getFileData().length);
        BufferedInputStream buffer = new BufferedInputStream(inputStream,DocumentoVO.BUFFER_SIZE);
        byte[] newBytes = new byte[DocumentoVO.BUFFER_SIZE];
        while( ( count = buffer.read(newBytes) ) != -1 )
        {
          zipStream.write(newBytes, 0, count);
        }
        
        streamZipado.close();
        buffer.close();
        zipStream.closeEntry();
        zipStream.flush();
        zipStream.finish(); 

        //documento.setBytes(Base64Encoder.encode(streamZipado.toByteArray()).getBytes("ISO-8859-1"));
        documento.setBytes(streamZipado.toByteArray());
        
        documento.setNome(formFile.getFileName());
        
        try{
        	documentoDelegate.insert(documento);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }catch (AplicacaoException e) {
            registrarMensagemSucesso(request,e.getMessage()); 
        }
        
        return mapping.getInputForward();
    }

	public void setDocumentoDelegate(DocumentoDelegate documentoDelegate) {
		this.documentoDelegate = documentoDelegate;
	}
    
}