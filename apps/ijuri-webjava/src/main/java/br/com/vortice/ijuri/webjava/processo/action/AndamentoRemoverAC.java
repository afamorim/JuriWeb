package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

public class AndamentoRemoverAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		String[] codigos = request.getParameterValues("codigo");
		if (codigos != null){
			for (int i = 0; i < codigos.length; i++){
				AndamentoVO vo = new AndamentoVO(new Long(codigos[i]));
				processoDelegate.delete(vo);
			}
		}
		return new ActionForward(mapping.getInput()+"?processoCodigo="+request.getParameter("processoCodigo"));
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}
