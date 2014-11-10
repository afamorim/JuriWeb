package br.com.vortice.ijuri.webjava.processo.action;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.AndamentoForm;

public class AndamentoCarregarAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		AndamentoForm andamentoForm = (AndamentoForm)form;
		Collection collTipoAndamento = processoDelegate.findTipoAndamentoByJuizo(new JuizoVO(andamentoForm.getJuizoCodigo()) );
		if (andamentoForm.getCodigo() != null && andamentoForm.getCodigo().intValue()>0){
            AndamentoVO vo = new AndamentoVO();
            vo.setCodigo(andamentoForm.getCodigo());
            vo = processoDelegate.findByPrimaryKey(vo);
            BeanUtils.copyProperties(andamentoForm, vo);
			andamentoForm.setTipoAndamentoCodigo(vo.getTipoAndamento().getCodigo());
            if (vo.getInterno().booleanValue()){
                andamentoForm.setInterno(new Integer(1));
            }
		}else{
			Date hoje = new Date();
			SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
			andamentoForm.setDataLancamento(formata.format(hoje));
		}
		request.setAttribute("collTipoAndamento", collTipoAndamento);
		return mapping.findForward("success");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}

}
