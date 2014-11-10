/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pessoa.PessoaJuridicaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.pessoa.PessoaDelegate;
import br.com.vortice.ijuri.webjava.pessoa.form.PessoaJuridicaForm;

/**
 * @author afamorim
 */
public class PessoaJuridicaPesquisarAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PessoaJuridicaForm pessoaJuridicaForm = (PessoaJuridicaForm)form;
		PessoaJuridicaVO vo = new PessoaJuridicaVO();
		BeanUtils.copyProperties(vo, pessoaJuridicaForm);
		Collection collPessoa = pessoaDelegate.findByFilter(vo);
		request.setAttribute("collPessoa", collPessoa);
		return mapping.findForward("success");
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}