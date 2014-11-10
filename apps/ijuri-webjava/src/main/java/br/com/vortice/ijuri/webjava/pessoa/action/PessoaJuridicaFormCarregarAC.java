/*
 * Created on 06/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;

/**
 * @author afamorim
 */
public class PessoaJuridicaFormCarregarAC extends BaseAction {

	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}

}
