package br.com.vortice.ijuri.webjava.documento.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.documento.DocumentoDelegate;
import br.com.vortice.ijuri.webjava.documento.form.DiretorioForm;

import com.jenkov.prizetags.tree.impl.Tree;

/**
 * @author Amadeu
 *
 */
public class DiretorioListAC extends BaseAction {
	
	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Tree tree = null;
        String treeCoded = request.getParameter("tree");
        if (treeCoded == null || treeCoded.trim().length() == 0){
            
            DiretorioForm diretorioForm = (DiretorioForm)form;
            diretorioForm.carregarComponentes(mapping, request, documentoDelegate);
            
        }else{
            tree = (Tree)Base64Decoder.decodeToObject(treeCoded);
            
            if (!isCampoVazio(request.getParameter("expand")))
                tree.expand(request.getParameter("expand"));
            else
                tree.collapse(request.getParameter("collapse"));
            
            String selectedNode = request.getParameter("idNodeSelected");
            if (!isCampoVazio(selectedNode)){
                tree.select(selectedNode);
            }
            
            request.setAttribute("treeBase64",Base64Encoder.encode(tree));
            request.setAttribute("tree.model", tree);
            
        }
        
        return mapping.getInputForward();
    }

	public void setDocumentoDelegate(DocumentoDelegate documentoDelegate) {
		this.documentoDelegate = documentoDelegate;
	}
    
}
