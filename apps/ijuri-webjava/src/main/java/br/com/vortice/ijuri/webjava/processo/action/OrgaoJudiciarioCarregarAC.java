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

import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.OrgaoJudiciarioForm;


/**
 * @author Antonio Amadeu
 */
public class OrgaoJudiciarioCarregarAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{    
	    OrgaoJudiciarioVO orgaoJudiciarioVO = new OrgaoJudiciarioVO();
		OrgaoJudiciarioForm orgaoJudiciarioForm = (OrgaoJudiciarioForm)form;
        BeanUtils.copyProperties(orgaoJudiciarioVO,form);
        
        Collection juizos = processoDelegate.findAllJuizo();
        request.setAttribute("juizos", juizos);
        
        if (orgaoJudiciarioVO.getCodigo()!=null && 
                orgaoJudiciarioVO.getCodigo().intValue()>0){
            orgaoJudiciarioVO = processoDelegate.findOrgaoJudiciarioByPrimaryKey(orgaoJudiciarioVO);
            BeanUtils.copyProperties(orgaoJudiciarioForm,orgaoJudiciarioVO);
            orgaoJudiciarioForm.setCodigoJuizo(orgaoJudiciarioVO.getJuizo().getCodigo());
        }
     	return mapping.getInputForward();		
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}