package br.com.vortice.ijuri.webjava.site.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.site.ParceiroVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.ParceiroForm;

public class ParceiroPesquisarAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception 
	{
        ParceiroForm parceiroForm = (ParceiroForm)form;        
        ParceiroVO parceiroVO = new ParceiroVO();
        
        if (!isCampoVazio(parceiroForm.getCodigo())){
            parceiroVO.setCodigo(parceiroForm.getCodigo());
        }
        
        Collection parceiros = siteDelegate.findParceiroByFilter(parceiroVO);
        request.setAttribute("parceiros", parceiros);
        return mapping.getInputForward();
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}