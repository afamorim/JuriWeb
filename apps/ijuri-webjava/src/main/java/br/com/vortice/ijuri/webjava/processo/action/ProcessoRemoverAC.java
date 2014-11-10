package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ProcessoForm;

public class ProcessoRemoverAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, 
                                     ActionForm form, 
                                     HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
		String[] codigos = request.getParameterValues("codigos");
		if (codigos != null){
			for (int i = 0; i < codigos.length; i++){
				ProcessoVO vo = new ProcessoVO(new Long(codigos[i]));
				processoDelegate.remove(vo);
			}
		}
		
		((ProcessoForm)form).carregarComponentes(mapping, request, this.getWebApplicationContext());
		
		return mapping.findForward("sucess");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}