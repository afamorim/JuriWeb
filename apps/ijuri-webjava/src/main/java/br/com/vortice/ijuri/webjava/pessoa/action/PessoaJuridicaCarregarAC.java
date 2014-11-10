/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
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
public class PessoaJuridicaCarregarAC extends BaseAction {
	
	private PessoaDelegate 		pessoaDelegate;
	private static final Logger LOG = Logger.getLogger(PessoaJuridicaCarregarAC.class);
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PessoaJuridicaForm pessoaJuridicaForm = (PessoaJuridicaForm)form;
		PessoaJuridicaVO vo = new PessoaJuridicaVO();
		BeanUtils.copyProperties(vo, pessoaJuridicaForm);
		LOG.debug("vo.getCodigo() " + vo.getCodigo());
		if (vo.getCodigo() != null && vo.getCodigo().intValue() > 0){
			vo = (PessoaJuridicaVO)pessoaDelegate.findByPrimaryKey(vo);
			LOG.debug("VO.GETCODIGO " +vo.getCodigo());
			BeanUtils.copyProperties(pessoaJuridicaForm, vo);
			if (vo.getUsuario() != null){
				pessoaJuridicaForm.setUsuarioCodigo(vo.getUsuario().getCodigo());
				pessoaJuridicaForm.setPerfilCodigo(vo.getUsuario().getPerfil().getCodigo());
				pessoaJuridicaForm.setSenha(vo.getUsuario().getSenha());
				pessoaJuridicaForm.setRsenha(vo.getUsuario().getSenha());
			}
		}
		return mapping.getInputForward();
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}
