/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.site.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.site.JurisprudenciaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.JurisprudenciaForm;

/**
 * @author amadeu
 */
public class JurisprudenciaRemoverAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		String[] codigos = request.getParameterValues("codigosExclusao");
		for (int i = 0; i < codigos.length; i++){
			JurisprudenciaVO vo = new JurisprudenciaVO(new Long(codigos[i]));
			siteDelegate.remove(vo);
		}
        
        JurisprudenciaForm frm = (JurisprudenciaForm)form;
        frm.carregarComponentes(mapping, request);
        registrarMensagemSucesso(request, MensagemSucessoIf.REMOVE);
		return mapping.findForward("success");
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}