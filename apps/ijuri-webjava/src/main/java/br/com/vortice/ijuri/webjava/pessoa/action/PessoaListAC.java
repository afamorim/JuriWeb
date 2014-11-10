/*
 * Created on 03/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.pessoa.PessoaDelegate;
import br.com.vortice.ijuri.webjava.pessoa.form.PessoaForm;

/**
 * @author amadeu
 */
public class PessoaListAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PessoaForm pessoaForm = (PessoaForm)form;
        if (pessoaForm.getAcao()!=null){
            PessoaVO vo = new PessoaVO();
            
            BeanUtils.copyProperties(vo, pessoaForm);
			if (isCampoVazio(pessoaForm.getTipo()))
                vo.setTipo(null);
            
            Collection pessoas = pessoaDelegate.findByFilter(vo);
			
            request.setAttribute("pessoas", pessoas);
        }else{
            pessoaForm.setTipo("1");
        }
		return mapping.getInputForward();
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}