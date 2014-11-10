package br.com.vortice.ijuri.webjava.monetario.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;

public class PeriodoIndiceRemoverAC extends BaseAction {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] codigos = request.getParameterValues("codigo");
		for (int i = 0; i < codigos.length; i++){
			correcaoMonetariaDelegate.remove(new PeriodoIndiceVO(new Integer(codigos[i])));
		}
		request.setAttribute("msg", MensagemSucessoIf.REMOVE);
		return mapping.findForward("success");
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}

}
