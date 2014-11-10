package br.com.vortice.ijuri.webjava.processo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.TipoAndamentoForm;

public class TipoAndamentoFormSalvarAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TipoAndamentoForm tipoAndamentoForm = (TipoAndamentoForm)form;
		TipoAndamentoVO tipoAndamentoVO = new TipoAndamentoVO();
		BeanUtils.copyProperties(tipoAndamentoVO, tipoAndamentoForm);
		for (int i = 0; i < tipoAndamentoForm.getJuizoCodigosDireita().length; i++){
			tipoAndamentoVO.addJuizo(new JuizoVO(new Integer(tipoAndamentoForm.getJuizoCodigosDireita()[i])));
		}
		if (tipoAndamentoForm.getCodigo().intValue() > 0){
			processoDelegate.update(tipoAndamentoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
		}else{
			processoDelegate.insert(tipoAndamentoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
		}
        
        tipoAndamentoForm.limpar();
        
		return mapping.findForward("success");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}
