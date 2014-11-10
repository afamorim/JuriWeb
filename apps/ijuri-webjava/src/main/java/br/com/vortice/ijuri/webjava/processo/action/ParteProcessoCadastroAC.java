/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
public class ParteProcessoCadastroAC extends BaseAction {
    
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
        //  Verifica dados em sess�o 
        HttpSession session = request.getSession();
        String nomeSessao = request.getParameter("idSessaoParte"); 
        
        Collection partes = (Collection) session.getAttribute(nomeSessao);
        
        //Sincroniza VO com FORM
        ParteProcessoForm parteForm = (ParteProcessoForm)form;
        ParteVO parteVO = new ParteVO();
        parteVO.setCodigo(parteForm.getPessoaCodigo());
        parteVO.setTipoParte(TipoParteVO.findByCodigo(parteForm.getTipoParteCodigo()));
        parteVO.setNome(parteForm.getNome());
        
        if (!isCampoVazio(parteForm.getPessoaApto()))
        	parteVO.setApto(Integer.valueOf(parteForm.getPessoaApto()));
        if (!isCampoVazio(parteForm.getPessoaBloco()))
        	parteVO.setBloco(parteForm.getPessoaBloco());
        
        if (parteForm.getCliente()!=null && parteForm.getCliente().intValue()==ParteVO.PARTE_CLIENTE)
            parteVO.setSituacaoCliente(new Integer(ParteVO.PARTE_CLIENTE));
        else
            parteVO.setSituacaoCliente(new Integer(ParteVO.PARTE_NAO_CLIENTE));
        
        request.setAttribute("tipos",TipoParteVO.findAll());
        
        // Inclui nova Parte em sess�o
        if (partes!=null){
            Iterator iter = null;
            for (iter = partes.iterator(); iter.hasNext();) {
                ParteVO parte = (ParteVO) iter.next();
                if (parte.getCodigo().equals(parteForm.getPessoaCodigo())){
                    return exibirMensagem(request, MensagensErroIf.PARTE_EXISTENTE,mapping,parteForm);
                }
            }
        }else{
            partes = new ArrayList();
        }   
        partes.add(parteVO);
        session.setAttribute(nomeSessao,partes);
        request.setAttribute("script", "<script> atualizarPartes();</script> ");
		return mapping.getInputForward();
	}

}