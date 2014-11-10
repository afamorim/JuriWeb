/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;

/**
 * @author amadeu
 */
public class ParteProcessoRemoverAC extends BaseAction {

	protected ActionForward executar(ActionMapping mapping,               
                                     ActionForm form, 
                                     HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
		
        String[] positions = request.getParameterValues("positions");
        
        String nomeSessao = request.getParameter("idSessaoParte");
        ArrayList partes = (ArrayList)request.getSession().getAttribute(nomeSessao);
        
		for (int i = 0; i < positions.length; i++){
            partes.remove(Integer.parseInt(positions[i]));
		}
        
        request.getSession().setAttribute(nomeSessao,partes);
        
        request.setAttribute("idSessaoParte",nomeSessao);
        
        request.setAttribute("partes", partes);
        
		return mapping.getInputForward();
	}

}
