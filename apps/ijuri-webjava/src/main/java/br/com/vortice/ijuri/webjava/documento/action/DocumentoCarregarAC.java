package br.com.vortice.ijuri.webjava.documento.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.documento.form.DocumentoForm;

/**
 * @author Amadeu
 *
 */
public class DocumentoCarregarAC extends BaseAction {

    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DocumentoForm docForm = (DocumentoForm)form;
        
        return mapping.getInputForward();
    }
    
}
