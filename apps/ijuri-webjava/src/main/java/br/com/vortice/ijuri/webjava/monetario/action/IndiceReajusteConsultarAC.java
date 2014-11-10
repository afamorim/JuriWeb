package br.com.vortice.ijuri.webjava.monetario.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.monetario.form.IndiceReajusteForm;

public class IndiceReajusteConsultarAC extends BaseAction {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndiceReajusteForm irForm = (IndiceReajusteForm)form;
		IndiceReajusteVO vo = new IndiceReajusteVO();
		BeanUtils.copyProperties(vo, irForm);
		Collection collIndice = correcaoMonetariaDelegate.findByFilter(vo);
		request.setAttribute("collIndice", collIndice);
		return mapping.findForward("success");
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}

}
