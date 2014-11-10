/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

/**
 * @author afamorim
 */
public class JuizoRemoverAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		String[] codigos = request.getParameterValues("codigosExclusao");
		for (int i = 0; i < codigos.length; i++){
			JuizoVO vo = new JuizoVO(new Integer(codigos[i]));
			processoDelegate.remove(vo);
		}
		return mapping.findForward("sucess");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}