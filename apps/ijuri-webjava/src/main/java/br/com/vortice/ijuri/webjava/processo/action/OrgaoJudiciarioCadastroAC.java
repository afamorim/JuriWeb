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
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.OrgaoJudiciarioForm;

/**
 * @author Antonio Amadeu
 */
public class OrgaoJudiciarioCadastroAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{        
		OrgaoJudiciarioVO orgaoJudiciarioVO = new OrgaoJudiciarioVO();
		OrgaoJudiciarioForm orgaoJudiciarioForm = (OrgaoJudiciarioForm)form;
        BeanUtils.copyProperties(orgaoJudiciarioVO,form);
        
        if (orgaoJudiciarioForm.getCodigoJuizo().intValue()>0){
            JuizoVO juizo = new JuizoVO(orgaoJudiciarioForm.getCodigoJuizo());
            orgaoJudiciarioVO.setJuizo(juizo); 
        }
		
        if (orgaoJudiciarioForm.getCodigo()!=null && orgaoJudiciarioForm.getCodigo().intValue()>0){
        	processoDelegate.update(orgaoJudiciarioVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }else{    
        	processoDelegate.insert(orgaoJudiciarioVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }
        orgaoJudiciarioForm.limpar();
     	return (mapping.findForward("success"));
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}