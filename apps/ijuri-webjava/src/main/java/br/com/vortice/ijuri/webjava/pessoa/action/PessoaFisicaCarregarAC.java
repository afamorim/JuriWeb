/*
 * Created on 03/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
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
public class PessoaFisicaCarregarAC extends BaseAction {
	
	private PessoaDelegate pessoaDelegate;
	private static final Logger LOG = Logger.getLogger(PessoaFisicaCarregarAC.class);
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PessoaFisicaForm pessoaFisicaForm = (PessoaFisicaForm)form;
		PessoaFisicaVO vo = new PessoaFisicaVO();
		BeanUtils.copyProperties(vo, pessoaFisicaForm);
		if (vo.getCodigo() != null && vo.getCodigo().intValue() > 0){
			LOG.debug("vo.getCodigo() " + vo.getCodigo());
			vo = (PessoaFisicaVO)pessoaDelegate.findByPrimaryKey(vo);
			LOG.debug("vo " + vo);
			BeanUtils.copyProperties(pessoaFisicaForm, vo);
			if (vo.getUsuario() != null){
				pessoaFisicaForm.setUsuarioCodigo(vo.getUsuario().getCodigo());
				pessoaFisicaForm.setPerfilCodigo(vo.getUsuario().getPerfil().getCodigo());
				pessoaFisicaForm.setSenha(vo.getUsuario().getSenha());
				pessoaFisicaForm.setRsenha(vo.getUsuario().getSenha());
			}
		}
		return mapping.getInputForward();
	}

	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

}