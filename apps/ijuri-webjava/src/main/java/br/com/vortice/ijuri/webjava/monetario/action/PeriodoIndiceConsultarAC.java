package br.com.vortice.ijuri.webjava.monetario.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.monetario.form.PeriodoIndiceReajusteForm;

public class PeriodoIndiceConsultarAC extends BaseAction {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PeriodoIndiceReajusteForm irForm = (PeriodoIndiceReajusteForm)form;
		PeriodoIndiceVO vo = new PeriodoIndiceVO();
		BeanUtils.copyProperties(vo, irForm);
		vo.setIndiceReajuste(new IndiceReajusteVO(irForm.getIndiceReajusteCodigo()));
		if (irForm.getMes() != null && !irForm.getMes().equals("")) vo.setMes(new Integer(irForm.getMes()));
		if (irForm.getAno() != null && !irForm.getAno().equals("")) vo.setAno(new Integer(irForm.getAno()));
		Collection collIndice = correcaoMonetariaDelegate.findByFilter(vo);
		request.setAttribute("collPeriodoIndice", collIndice);
		return mapping.findForward("success");
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}
}