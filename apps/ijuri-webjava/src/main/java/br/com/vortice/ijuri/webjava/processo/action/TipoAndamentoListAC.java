package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.FiltroTipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.TipoAndamentoForm;

public class TipoAndamentoListAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TipoAndamentoForm tipoAndamentoForm = (TipoAndamentoForm)form;
		FiltroTipoAndamentoVO filtro = new FiltroTipoAndamentoVO();
		BeanUtils.copyProperties(filtro, tipoAndamentoForm);
		if (!isCampoVazio(tipoAndamentoForm.getJuizoCodigo()))
			filtro.setJuizo(new JuizoVO(tipoAndamentoForm.getJuizoCodigo()));
		Collection collTipoAndamento = processoDelegate.findByFilter(filtro);
		request.setAttribute("collTipoAndamento", collTipoAndamento);
		return mapping.findForward("success");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}
