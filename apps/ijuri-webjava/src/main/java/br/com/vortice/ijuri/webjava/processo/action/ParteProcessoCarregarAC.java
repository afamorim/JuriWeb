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

import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.TipoParteVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.form.ParteProcessoForm;

/**
 * @author amadeu
 */
public class ParteProcessoCarregarAC extends BaseAction {

	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
        String nomeSessao = request.getParameter("idSessaoParte");
        ArrayList partes = (ArrayList)request.getSession().getAttribute(nomeSessao);
        
        request.setAttribute("tipos",TipoParteVO.findAll());
        
        if (request.getParameter("position")!=null){
            ParteProcessoForm parteForm = (ParteProcessoForm)form; 
            ParteVO parte = (ParteVO)partes.get(Integer.parseInt(parteForm.getPosition()));
            parteForm.setPessoaCodigo(parte.getCodigo());
            parteForm.setNome(parte.getNome());
            parteForm.setTipoParteCodigo(parte.getTipoParte().getCodigo());
            parteForm.setCliente(parte.getSituacaoCliente());
            request.setAttribute("position", parteForm.getPosition());
        }
        
        request.setAttribute("idSessaoParte",nomeSessao);
        
        return mapping.getInputForward();
	}

}