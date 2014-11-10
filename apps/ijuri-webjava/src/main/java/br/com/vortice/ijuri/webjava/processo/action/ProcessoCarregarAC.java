/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.StatusProcessoVO;
import br.com.vortice.ijuri.core.processo.TurnoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ProcessoForm;


/**
 * @author Antonio Amadeu
 */
public class ProcessoCarregarAC extends BaseAction{
	
    public static long			idSessaoParte = 1;
    public static final String	NOME_ATRIBUTO_SESSAO = "idSessaoParte";
    private ProcessoDelegate	processoDelegate;
    
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{    
		ProcessoVO processoVO = new ProcessoVO();
		ProcessoForm processoForm = (ProcessoForm)form;
		processoVO.setCodigo(processoForm.getCodigo());
        
		Collection classesProcesso = processoDelegate.findAllClasseProcesso();
		request.setAttribute("classesProcesso", classesProcesso);
		
		Collection comarcas = processoDelegate.findAllComarca();
		request.setAttribute("comarcas", comarcas);
		
		request.setAttribute("situacoes",StatusProcessoVO.findAll());
		
		request.setAttribute("turnos",TurnoVO.findAll());
		
		Collection juizos = processoDelegate.findAllJuizo();
		request.setAttribute("juizos", juizos);
		
		Collection orgaos = new ArrayList();
        if (processoVO.getCodigo()!=null && processoVO.getCodigo().intValue()>0)
        {
            processoVO = processoDelegate.findByPrimaryKey(processoVO);
            BeanUtils.copyProperties(processoForm,processoVO);
            processoForm.setClasseProcessoCodigo(processoVO.getClasseProcesso().getCodigo());
            processoForm.setComarcaCodigo(processoVO.getComarca().getCodigo());
            processoForm.setStatusCodigo(processoVO.getStatus().getCodigo());
            processoForm.setTurnoCodigo(processoVO.getTurno().getCodigo());
            
            processoForm.setJuizoCodigo(processoVO.getOrgaoJudiciario().getJuizo().getCodigo());
            processoForm.setOrgaoJudiciarioCodigo(processoVO.getOrgaoJudiciario().getCodigo());
            
            orgaos = processoDelegate.findOrgaoJudiciarioByJuizo(processoVO.getOrgaoJudiciario().getJuizo());
        }
        
        //Configura Sess�o das partes do processo
        HttpSession session = request.getSession();
        String nomeSessao = null; 
        if (request.getParameter(NOME_ATRIBUTO_SESSAO)!=null 
                && request.getParameter(NOME_ATRIBUTO_SESSAO).trim().length()>0){ 
            nomeSessao = request.getParameter(NOME_ATRIBUTO_SESSAO);
        }else{
            //O contador de id de sess�o zera quando chega a 100, para n�o crescer muito
            idSessaoParte = ((idSessaoParte+1)%100);
            nomeSessao = NOME_ATRIBUTO_SESSAO+(idSessaoParte);
        }
        
        request.setAttribute(NOME_ATRIBUTO_SESSAO, nomeSessao);
        request.setAttribute("orgaos", orgaos);
     	
        return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}