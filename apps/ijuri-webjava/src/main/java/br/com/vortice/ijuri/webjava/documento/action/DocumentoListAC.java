package br.com.vortice.ijuri.webjava.documento.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.documento.DocumentoDelegate;

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