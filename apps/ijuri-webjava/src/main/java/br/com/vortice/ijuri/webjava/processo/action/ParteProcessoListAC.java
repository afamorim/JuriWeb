/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ParteProcessoForm;

/**
 * @author Antonio Amadeu
 */
public class ParteProcessoListAC extends BaseAction{
	
    public static final String 	NOME_ATRIBUTO_SESSAO = "idSessaoParte";
    private ProcessoDelegate	processoDelegate;
    
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
        HttpSession session = request.getSession();
        String nomeSessao = null;
        nomeSessao = request.getParameter(NOME_ATRIBUTO_SESSAO);    
        ParteProcessoForm parteProcessoForm = (ParteProcessoForm)form;
        Collection partes = null;
        
        if ("clear".equals(request.getParameter("acao"))){
            session.removeAttribute(nomeSessao);
            partes = new ArrayList();
        }else if ("find".equals(request.getParameter("acao"))){
            ProcessoVO processoVO  = new ProcessoVO();
            processoVO.setCodigo(parteProcessoForm.getProcessoCodigo());
            partes = processoDelegate.findPartesByProcesso(processoVO);
        }else{ //refresh session
            partes = (Collection) session.getAttribute(nomeSessao);
            if (partes == null){
                partes = new ArrayList();
            }
        }
        request.setAttribute("partes", partes);
        
        request.setAttribute(NOME_ATRIBUTO_SESSAO, nomeSessao);
        
        request.getSession().setAttribute(nomeSessao, partes);

        return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}	
}