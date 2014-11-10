/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pessoa.PessoaJuridicaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.pessoa.PessoaDelegate;
import br.com.vortice.ijuri.webjava.pessoa.form.PessoaJuridicaForm;
import br.com.vortice.jvseguranca.core.Perfil;
import br.com.vortice.jvseguranca.core.Usuario;

/**
 * @author afamorim
 */
public class PessoaJuridicaFormSalvarAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PessoaJuridicaForm pessoaJuridicaForm = (PessoaJuridicaForm)form;
		PessoaJuridicaVO vo = new PessoaJuridicaVO();
		BeanUtils.copyProperties(vo, pessoaJuridicaForm);
		if (pessoaJuridicaForm.getCodigo().intValue() > 0){
			if (pessoaJuridicaForm.getUsuarioCodigo() != null && pessoaJuridicaForm.getUsuarioCodigo() > 0)
			{
				vo.setUsuario(new Usuario(pessoaJuridicaForm.getUsuarioCodigo()));
				vo.getUsuario().setPerfil(new Perfil(pessoaJuridicaForm.getPerfilCodigo()));
				vo.getUsuario().setSenha(pessoaJuridicaForm.getSenha());
			}
			pessoaDelegate.update(vo);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
		}else{
			vo = (PessoaJuridicaVO)pessoaDelegate.insert(vo);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
		}
        
        pessoaJuridicaForm.limpar();
        
		return mapping.findForward("success");
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}
