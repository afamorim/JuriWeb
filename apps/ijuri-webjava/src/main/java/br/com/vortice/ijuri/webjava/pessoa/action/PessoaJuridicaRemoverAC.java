/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pessoa.PessoaJuridicaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.pessoa.PessoaDelegate;

/**
 * @author afamorim
 */
public class PessoaJuridicaRemoverAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] codigos = request.getParameterValues("codigo");
		for (int i = 0; i < codigos.length; i++) {
			PessoaJuridicaVO vo = new PessoaJuridicaVO(new Integer(codigos[i]));
			pessoaDelegate.delete(vo);
		}
		return mapping.findForward("success");
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}
