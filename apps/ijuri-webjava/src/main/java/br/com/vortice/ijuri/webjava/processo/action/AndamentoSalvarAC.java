package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.AndamentoForm;

public class AndamentoSalvarAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		AndamentoForm andamentoForm = (AndamentoForm)form;
		AndamentoVO vo = new AndamentoVO();
		BeanUtils.copyProperties(vo, andamentoForm);
		vo.setProcesso(new ProcessoVO(andamentoForm.getProcessoCodigo()));
		vo.setTipoAndamento(new TipoAndamentoVO(andamentoForm.getTipoAndamentoCodigo()));
        if (andamentoForm.getInterno()!=null && andamentoForm.getInterno().intValue() > 0)
            vo.setInterno(Boolean.TRUE);
        else vo.setInterno(Boolean.FALSE);
		
        if (andamentoForm.getCodigo() != null && andamentoForm.getCodigo().intValue()>0){
        	processoDelegate.update(vo);
            request.setAttribute("script", "<script> reloadAndamentos('U'); </script>");
		}else{
			vo = processoDelegate.insert(vo);
            request.setAttribute("script", "<script> reloadAndamentos('I'); </script>");
		}
        andamentoForm.carregarComponentes(mapping, request, this.getWebApplicationContext());
		return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}
