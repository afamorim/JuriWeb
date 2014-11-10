package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.AndamentoForm;

public class AndamentoListAC extends BaseAction {

	private ProcessoDelegate processoDelegate;
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		AndamentoForm andamentoForm = (AndamentoForm)form;
		
        ProcessoVO processoVO = new ProcessoVO(andamentoForm.getProcessoCodigo());
		Collection collAndamento = processoDelegate.findAndamentosByProcesso(processoVO);
        
        request.setAttribute("andamentos", collAndamento);
		return mapping.getInputForward(); 
	}
	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}
