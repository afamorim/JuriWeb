package br.com.vortice.ijuri.documento.cliente.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
public class DocumentoRemoverAC extends BaseAction {

	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
    	DocumentoForm docForm = (DocumentoForm) form;
		DocumentoVO vo = new DocumentoVO();
		vo.setCodigo(docForm.getCodigo());
        
		boolean hasError = false;
		try{
			documentoDelegate.remove(vo);
		}catch(AplicacaoException ae){
			registrarMensagemSucesso(request,"Registro não pode ser excluído. Existe uma outra entidade refenciando a entidade atual.");
			hasError = true;
		}
		
		if (!hasError)
			registrarMensagemSucesso(request,MensagemSucessoIf.REMOVE);
		
		DiretorioVO diretorio = new DiretorioVO();
		diretorio.setCodigo(docForm.getDiretorioCodigo());
		request.setAttribute("children", documentoDelegate.findByDiretorio(diretorio));
		
		return mapping.getInputForward();
    }

	public void setDocumentoDelegate(DocumentoDelegate documentoDelegate) {
		this.documentoDelegate = documentoDelegate;
	}	
}