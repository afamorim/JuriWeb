package br.com.vortice.ijuri.webjava.localidade.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.localidade.MunicipioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;
import br.com.vortice.ijuri.webjava.localidade.form.MunicipioForm;

public class MunicipioCarregarAC extends BaseAction {
	
	private LocalidadeDelegate localidadeDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MunicipioForm municipioForm = (MunicipioForm)form;
		Collection collEstado = localidadeDelegate.findAllEstado();
		request.setAttribute("collEstado", collEstado);
        
        if (municipioForm.getCodigo()!=null && municipioForm.getCodigo().intValue()>0){
            MunicipioVO municipioVO = new MunicipioVO();
            BeanUtils.copyProperties(municipioVO,municipioForm);
            municipioVO = localidadeDelegate.findByPrimaryKey(municipioVO);
            BeanUtils.copyProperties(municipioForm,municipioVO);
            municipioForm.setEstadoCodigo(municipioVO.getEstadoVO().getCodigo());    
        }
        
		return mapping.findForward("success");
	}

	public void setLocalidadeDelegate(LocalidadeDelegate localidadeDelegate) {
		this.localidadeDelegate = localidadeDelegate;
	}

}
