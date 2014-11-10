/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ClasseProcessoForm;

/**
 * @author Antonio Amadeu
 */
public class ClasseProcessoListAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, 
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response) throws Exception {
		
       
        ClasseProcessoForm classeProcessoForm = (ClasseProcessoForm)form;
        ClasseProcessoVO classeProcessoVO  = new ClasseProcessoVO();
        BeanUtils.copyProperties(classeProcessoVO,classeProcessoForm);
        Collection classeProcessos = processoDelegate.findClasseProcessoByFilter(classeProcessoVO);
        
        request.setAttribute("classeProcessos", classeProcessos);
		return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}