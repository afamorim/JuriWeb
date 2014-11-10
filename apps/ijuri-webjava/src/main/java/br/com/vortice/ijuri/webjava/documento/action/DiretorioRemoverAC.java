package br.com.vortice.ijuri.webjava.documento.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.documento.DocumentoDelegate;
import br.com.vortice.ijuri.webjava.documento.form.DiretorioForm;

import com.vortice.core.exception.AplicacaoException;

/**
 * @author Amadeu
 *
 */
public class DiretorioRemoverAC extends BaseAction {
	
	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DiretorioForm diretorioForm = (DiretorioForm)form;
        
        DiretorioVO diretorio = new DiretorioVO();
        diretorio.setCodigo(diretorioForm.getCodigo());
        
        try{
            documentoDelegate.remove(diretorio);
        }catch(AplicacaoException ae){
            registrarMensagemSucesso(request, ae.getMessage());
            return mapping.getInputForward();
        }
        
        registrarMensagemSucesso(request, MensagemSucessoIf.REMOVE);
        
        return mapping.getInputForward();
    }

	public void setDocumentoDelegate(DocumentoDelegate documentoDelegate) {
		this.documentoDelegate = documentoDelegate;
	}   
}