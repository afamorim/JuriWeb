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
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.JuizoForm;

/**
 * @author Antonio Amadeu
 */
public class JuizoCadastroAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{        
		JuizoVO juizoVO = new JuizoVO();
		JuizoForm juizoForm = (JuizoForm)form;
        BeanUtils.copyProperties(juizoVO,form);
        if (juizoForm.getCodigo().intValue() > 0)
        {
        	processoDelegate.update(juizoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }else
        {    
        	processoDelegate.insert(juizoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }
        juizoForm.limpar();
		return (mapping.findForward("success"));
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}