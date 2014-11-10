/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ProcessoForm;


/**
 * @author Antonio Amadeu
 */
public class ProcessoListAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{   
        ProcessoForm processoForm = (ProcessoForm)form;
        FiltroProcessoVO filtroProcesso = new FiltroProcessoVO();
        
        if (!isCampoVazio(processoForm.getNumero()))
            filtroProcesso.setNumero(processoForm.getNumero());
        
        OrgaoJudiciarioVO orgaoVO = new OrgaoJudiciarioVO();
        filtroProcesso.setOrgaoJudiciario(orgaoVO);
        if (!isCampoVazio(processoForm.getJuizoCodigo())){
            JuizoVO juizoVO = new JuizoVO();
            juizoVO.setCodigo(processoForm.getJuizoCodigo());
            orgaoVO.setJuizo(juizoVO);
        }
        
        if (!isCampoVazio(processoForm.getOrgaoJudiciarioCodigo()))
            orgaoVO.setCodigo(processoForm.getOrgaoJudiciarioCodigo());
        
        if (!isCampoVazio(processoForm.getClasseProcessoCodigo()))
            filtroProcesso.setClasseProcesso(new ClasseProcessoVO(processoForm.getClasseProcessoCodigo()));
        
        if (!isCampoVazio(processoForm.getPessoaCodigo()))
            filtroProcesso.setPessoa(new PessoaVO(processoForm.getPessoaCodigo()));
        
        Collection processos = processoDelegate.findByFilter(filtroProcesso);
        request.setAttribute("processos", processos);
        
        processoForm.carregarComponentes(mapping, request, getWebApplicationContext());
        
		return mapping.getInputForward();
		
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}