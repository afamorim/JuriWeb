package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.TipoAndamentoForm;

public class TipoAndamentoCarregarAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TipoAndamentoForm tipoAndamentoForm = (TipoAndamentoForm)form;
		Collection collNJuizo = null;
		Collection collJuizo = null;
		if (request.getParameter("codigo") == null || request.getParameter("codigo").equals("")){
			collNJuizo = processoDelegate.findAllJuizo();
            collJuizo = new ArrayList();
		}else{
			TipoAndamentoVO vo = new TipoAndamentoVO(new Long(request.getParameter("codigo")));
			vo = processoDelegate.findByPrimaryKey(vo);
			collJuizo = vo.getCollJuizos();
			collNJuizo = processoDelegate.findNJuizoByTipoAndamento(vo);
			BeanUtils.copyProperties(tipoAndamentoForm, vo);
		}
		request.setAttribute("collNJuizo", collNJuizo);
		request.setAttribute("collJuizo", collJuizo);
		request.setAttribute("TipoAndamentoForm", tipoAndamentoForm);
		
        return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}