package br.com.vortice.ijuri.webjava.monetario.action;

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

public class IndiceReajusteCarregarAC extends BaseAction {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndiceReajusteForm irForm = (IndiceReajusteForm)form;
		if (irForm.getCodigo() != null && irForm.getCodigo().intValue() > 0){
			IndiceReajusteVO vo = new IndiceReajusteVO();
			vo.setCodigo(irForm.getCodigo());
			vo = correcaoMonetariaDelegate.findByPrimaryKey(vo);
			BeanUtils.copyProperties(irForm, vo);
		}
		return new ActionForward(mapping.getInput());
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}

}
