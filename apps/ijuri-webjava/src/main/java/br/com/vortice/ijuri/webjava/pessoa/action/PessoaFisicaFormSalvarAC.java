/*
 * Created on 03/05/2005
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
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.pessoa.PessoaDelegate;
import br.com.vortice.ijuri.webjava.pessoa.form.PessoaFisicaForm;
import br.com.vortice.jvseguranca.core.Perfil;
import br.com.vortice.jvseguranca.core.Usuario;

/**
 * @author afamorim
 */
public class PessoaFisicaFormSalvarAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PessoaFisicaForm pessoaFisicaForm = (PessoaFisicaForm)form;
		PessoaFisicaVO vo = new PessoaFisicaVO();
		BeanUtils.copyProperties(vo, pessoaFisicaForm);
		if (!isCampoVazio(pessoaFisicaForm.getCodigo())){
			if (pessoaFisicaForm.getUsuarioCodigo() != null && pessoaFisicaForm.getUsuarioCodigo() > 0)
			{
				vo.setUsuario(new Usuario(pessoaFisicaForm.getUsuarioCodigo()));
				vo.getUsuario().setPerfil(new Perfil(pessoaFisicaForm.getPerfilCodigo()));
				vo.getUsuario().setSenha(pessoaFisicaForm.getSenha());
			}
			pessoaDelegate.update(vo);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
		}else{
			vo = (PessoaFisicaVO)pessoaDelegate.insert(vo);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
		}
		
        pessoaFisicaForm.limpar();
        
		return mapping.findForward("success");
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}
	
}