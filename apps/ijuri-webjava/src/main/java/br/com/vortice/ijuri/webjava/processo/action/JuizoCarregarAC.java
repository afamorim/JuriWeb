/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.JuizoForm;

/**
 * @author Antonio Amadeu
 */
public class JuizoCarregarAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
        JuizoForm juizoForm = (JuizoForm)form;
        JuizoVO juizoVO  = new JuizoVO();
        BeanUtils.copyProperties(juizoVO,juizoForm);
      
        if (juizoForm.getCodigo()!=null && juizoForm.getCodigo().intValue()>0)
        {
           juizoVO =  processoDelegate.findJuizoByPrimaryKey(juizoVO);
           BeanUtils.copyProperties(juizoForm,juizoVO);
        }
		return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}