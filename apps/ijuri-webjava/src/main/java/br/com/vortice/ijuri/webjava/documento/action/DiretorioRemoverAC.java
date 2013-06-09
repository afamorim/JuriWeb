package br.com.vortice.ijuri.documento.cliente.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.vortice.exception.AplicacaoException;

import br.com.vortice.ijuri.abstracao.view.BaseAction;
import br.com.vortice.ijuri.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.documento.DiretorioVO;
import br.com.vortice.ijuri.documento.cliente.web.DocumentoDelegate;
import br.com.vortice.ijuri.documento.cliente.web.form.DiretorioForm;

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