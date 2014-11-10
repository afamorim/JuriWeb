package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ComarcaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ComarcaForm;

public class ComarcaListAC extends BaseAction {
	
	private ProcessoDelegate 	processoDelegate;
	private LocalidadeDelegate	localidadeDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		ComarcaForm comarcaForm = (ComarcaForm)form;
		ComarcaVO comarcaVO = new ComarcaVO();
		BeanUtils.copyProperties(comarcaVO, comarcaForm);
		Collection collComarca = processoDelegate.findByFilter(comarcaVO);
		request.setAttribute("collComarca", collComarca);
        comarcaForm.carregarComponentes(mapping, request, getWebApplicationContext());
		return mapping.findForward("success");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

	public void setLocalidadeDelegate(LocalidadeDelegate localidadeDelegate) {
		this.localidadeDelegate = localidadeDelegate;
	}

}