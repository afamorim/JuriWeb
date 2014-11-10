package br.com.vortice.ijuri.webjava.monetario.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.monetario.form.IndiceReajusteForm;

public class IndiceReajusteSalvarAC extends BaseAction {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndiceReajusteForm irForm = (IndiceReajusteForm)form;
		IndiceReajusteVO vo = new IndiceReajusteVO();
		BeanUtils.copyProperties(vo, irForm);
		if (irForm.getCodigo().intValue() > 0){
			correcaoMonetariaDelegate.update(vo);
			request.setAttribute("msg", MensagemSucessoIf.UPDATE);
		}else{
			vo = correcaoMonetariaDelegate.insert(vo);
			request.setAttribute("msg", MensagemSucessoIf.INSERT);
		}
		return mapping.findForward("success");
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}

}
