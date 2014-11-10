/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ProcessoForm;

/**
 * @author Antonio Amadeu
 */
public class ProcessoCarregarComboOrgaoAC extends BaseAction{
	
	private ProcessoDelegate	processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{    
        ProcessoVO processoVO = new ProcessoVO();
		ProcessoForm processoForm = (ProcessoForm)form;
        processoVO.setCodigo(processoForm.getCodigo());
        
        Collection orgaos = new ArrayList();
        if (processoForm.getJuizoCodigo()!=null && processoForm.getJuizoCodigo().intValue()>0)
        {
            orgaos = processoDelegate.findOrgaoJudiciarioByJuizo(new JuizoVO(processoForm.getJuizoCodigo()));
        }
        request.setAttribute("orgaos", orgaos);
        processoForm.carregarComponentes(mapping, request, getWebApplicationContext());
     	return mapping.getInputForward();	
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}	
}