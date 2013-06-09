package br.com.vortice.ijuri.documento.cliente.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.abstracao.view.BaseAction;
import br.com.vortice.ijuri.documento.DiretorioVO;
import br.com.vortice.ijuri.documento.cliente.web.DocumentoDelegate;

/**
 * @author Amadeu
 *
 */
public class DocumentoListAC extends BaseAction {

	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DiretorioVO diretorio = new DiretorioVO();
        String idNodeSelected =  request.getParameter("idNodeSelected");
        diretorio.setCodigo(Long.valueOf(idNodeSelected));
        
        request.setAttribute("children", documentoDelegate.findByDiretorio(diretorio));
        
        return mapping.getInputForward();
    }

	public void setDocumentoDelegate(DocumentoDelegate documentoDelegate) {
		this.documentoDelegate = documentoDelegate;
	}
}