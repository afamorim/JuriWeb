/*
 * Created on 06/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pessoa.PessoaFisicaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.pessoa.PessoaDelegate;
import br.com.vortice.ijuri.webjava.pessoa.form.PessoaFisicaForm;

/**
 * @author afamorim
 */
public class PessoaFisicaDetalharAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PessoaFisicaForm pessoaFisicaForm = (PessoaFisicaForm)form;
		PessoaFisicaVO pessoaFisicaVO = new PessoaFisicaVO();
		BeanUtils.copyProperties(pessoaFisicaVO, pessoaFisicaForm);
		pessoaFisicaVO = (PessoaFisicaVO)pessoaDelegate.findByPrimaryKey(pessoaFisicaVO);
		BeanUtils.copyProperties(pessoaFisicaForm, pessoaFisicaVO);
		return mapping.findForward("success");
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}
