package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.localidade.MunicipioVO;
import br.com.vortice.ijuri.core.processo.ComarcaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ComarcaForm;

public class ComarcaFormSalvarAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		ComarcaForm comarcaForm = (ComarcaForm)form;
		ComarcaVO comarcaVO = new ComarcaVO();
		BeanUtils.copyProperties(comarcaVO, comarcaForm);
		comarcaVO.setMunicipio(new MunicipioVO(comarcaForm.getMunicipioCodigo()));
		if (comarcaForm.getCodigo().intValue() > 0)
		{
			processoDelegate.update(comarcaVO);
			request.setAttribute("msg", MensagemSucessoIf.UPDATE);
		}else
		{
			comarcaVO = processoDelegate.insert(comarcaVO);
			request.setAttribute("msg", MensagemSucessoIf.INSERT);
		}
		return mapping.findForward("success");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}