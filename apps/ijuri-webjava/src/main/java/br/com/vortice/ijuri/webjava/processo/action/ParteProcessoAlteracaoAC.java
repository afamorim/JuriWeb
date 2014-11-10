/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.TipoParteVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagensErroIf;
import br.com.vortice.ijuri.webjava.processo.form.ParteProcessoForm;

/**
 * @author amadeu
 */
public class ParteProcessoAlteracaoAC extends BaseAction {
    
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
        //  Verifica dados em sess�o 
        HttpSession session = request.getSession();
        String nomeSessao = request.getParameter("idSessaoParte"); 
        
        ArrayList partes = (ArrayList) session.getAttribute(nomeSessao);
        
        ParteProcessoForm parteForm = (ParteProcessoForm)form;
        int pos = Integer.parseInt(parteForm.getPosition());
        
        request.setAttribute("tipos",TipoParteVO.findAll());
        
        // Verifica nova Parte 
        for (int i=0;i<partes.size();i++) {
            if (i!=pos){
                ParteVO parte = (ParteVO) partes.get(i);
                if (parte.getCodigo().equals(parteForm.getPessoaCodigo())){
                    return exibirMensagem(request, MensagensErroIf.PARTE_EXISTENTE, mapping, parteForm);
                }
            }
        }
        //Sincroniza VO que est� na sess�o com FORM
        ParteVO parteVO = (ParteVO)partes.get(pos); 
        parteVO.setCodigo(parteForm.getPessoaCodigo());
        parteVO.setTipoParte(TipoParteVO.findByCodigo(parteForm.getTipoParteCodigo()));
        parteVO.setNome(parteForm.getNome());
        
        if (!isCampoVazio(parteForm.getPessoaApto()))
        	parteVO.setApto(Integer.valueOf(parteForm.getPessoaApto()));
        if (!isCampoVazio(parteForm.getPessoaBloco()))
        	parteVO.setBloco(parteForm.getPessoaBloco());
        
        if (parteForm.getCliente()==null)
            parteVO.setSituacaoCliente(new Integer(ParteVO.PARTE_NAO_CLIENTE));
        else
            parteVO.setSituacaoCliente(new Integer(ParteVO.PARTE_CLIENTE));
        
        request.setAttribute("script", "<script> atualizarPartes('U');</script> ");

		return mapping.getInputForward();
	}

}