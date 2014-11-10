package br.com.vortice.ijuri.webjava.site.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.site.ParceiroVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.ParceiroForm;

public class ParceiroSalvarAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception 
	{
        ParceiroForm parceiroForm = (ParceiroForm)form;
        ParceiroVO parceiroVO = new ParceiroVO();
        BeanUtils.copyProperties(parceiroVO, parceiroForm);
        
        if (parceiroForm.getAcao()!=null && parceiroForm.getAcao().equals("U")){
            siteDelegate.update(parceiroVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }else{
            siteDelegate.insert(parceiroVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }
        parceiroForm.limpar();
        return mapping.findForward("success");
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}