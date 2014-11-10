package br.com.vortice.ijuri.webjava.monetario.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.monetario.form.PeriodoIndiceReajusteForm;

public class PeriodoIndiceSalvarAC extends BaseAction {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PeriodoIndiceReajusteForm piForm = (PeriodoIndiceReajusteForm)form;
		piForm.setIndiceReajusteCodigo(new Integer(request.getParameter("indiceReajusteCodigo")));
		PeriodoIndiceVO vo = new PeriodoIndiceVO();
		BeanUtils.copyProperties(vo, piForm);
		vo.setIndiceReajuste(new IndiceReajusteVO(piForm.getIndiceReajusteCodigo()));
		if (piForm.getCodigo().intValue() > 0){
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