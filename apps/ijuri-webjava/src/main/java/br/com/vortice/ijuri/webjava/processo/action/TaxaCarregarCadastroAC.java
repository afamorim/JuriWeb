/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.form.TaxaForm;


/**
 * @author Antonio Amadeu
 */
public class TaxaCarregarCadastroAC extends BaseAction{
    
	public ActionForward executar(ActionMapping mapping, 
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response) throws Exception {
		
        
        TaxaForm taxaForm = (TaxaForm)form;
        
        taxaForm.carregarComponentes(mapping, request);
        
        return mapping.getInputForward();
		
	}
}
