package br.com.vortice.ijuri.webjava.site.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.site.LinkVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.LinkForm;

public class LinkSalvarAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		LinkForm linkForm = (LinkForm)form;
		LinkVO vo = new LinkVO();
		BeanUtils.copyProperties(vo, linkForm);
		if (linkForm.getCodigo().intValue() > 0){
			siteDelegate.update(vo);
			request.setAttribute("msg", "Link atualizado com sucesso.");
		}else{
			vo = siteDelegate.insert(vo);
			request.setAttribute("msg", "Link inserido com sucesso.");
		}
		return mapping.findForward("success");
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}
