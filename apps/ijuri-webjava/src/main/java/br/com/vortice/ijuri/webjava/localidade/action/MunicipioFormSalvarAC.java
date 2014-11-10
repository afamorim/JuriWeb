package br.com.vortice.ijuri.webjava.localidade.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;
import br.com.vortice.ijuri.webjava.localidade.form.MunicipioForm;

public class MunicipioFormSalvarAC extends BaseAction {
	
	private LocalidadeDelegate localidadeDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MunicipioForm municipioForm = (MunicipioForm)form;
		MunicipioVO municipioVO = new MunicipioVO();
		BeanUtils.copyProperties(municipioVO, municipioForm);
		municipioVO.setEstadoVO(new EstadoVO(municipioForm.getEstadoCodigo()));
		if (municipioForm.getCodigo().intValue() > 0){
			localidadeDelegate.update(municipioVO);
		}else{
			municipioVO = localidadeDelegate.insert(municipioVO);
		}
		municipioForm.limpar();
        municipioForm.carregarComponentes(mapping, request, localidadeDelegate);
        
		return mapping.findForward("success");
	}

	public void setLocalidadeDelegate(LocalidadeDelegate localidadeDelegate) {
		this.localidadeDelegate = localidadeDelegate;
	}

}
