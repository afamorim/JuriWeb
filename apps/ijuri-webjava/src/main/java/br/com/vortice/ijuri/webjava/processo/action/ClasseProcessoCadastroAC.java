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

import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ClasseProcessoForm;

/**
 * @author Antonio Amadeu
 */
public class ClasseProcessoCadastroAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{    
		ClasseProcessoVO classeProcessoVO = new ClasseProcessoVO();
		ClasseProcessoForm classeProcessoForm = (ClasseProcessoForm)form;
        BeanUtils.copyProperties(classeProcessoVO,form);
        if (classeProcessoForm.getCodigo()!=null && classeProcessoForm.getCodigo().longValue()>0)
        {
        	processoDelegate.update(classeProcessoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }else
        {    
        	processoDelegate.insert(classeProcessoVO);
             registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }
        classeProcessoForm.limpar();
        return (mapping.findForward("success"));
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}