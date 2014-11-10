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

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.OrgaoJudiciarioForm;


/**
 * @author Antonio Amadeu
 */
public class OrgaoJudiciarioListAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    	throws Exception 
    {        
        OrgaoJudiciarioForm orgaoJudiciarioForm = (OrgaoJudiciarioForm)form;
        OrgaoJudiciarioVO orgaoJudiciarioVO  = new OrgaoJudiciarioVO();
        BeanUtils.copyProperties(orgaoJudiciarioVO,orgaoJudiciarioForm);
        
        Collection juizos = processoDelegate.findAllJuizo();
        request.setAttribute("juizos", juizos);
       
        if (orgaoJudiciarioForm.getCodigoJuizo()!=null && 
                orgaoJudiciarioForm.getCodigoJuizo().intValue()>0){
            JuizoVO juizo = new JuizoVO(orgaoJudiciarioForm.getCodigoJuizo()); 
            orgaoJudiciarioVO.setJuizo(juizo);
        }  
        Collection orgaoJudiciarios = processoDelegate.findOrgaoJudiciarioByFilter(orgaoJudiciarioVO);
        request.setAttribute("orgaoJudiciarios", orgaoJudiciarios);
        
        return mapping.getInputForward();
    }

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
    
}