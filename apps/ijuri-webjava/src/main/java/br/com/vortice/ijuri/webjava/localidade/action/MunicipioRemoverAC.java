package br.com.vortice.ijuri.webjava.localidade.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.localidade.MunicipioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;
import br.com.vortice.ijuri.webjava.localidade.form.MunicipioForm;

public class MunicipioRemoverAC extends BaseAction {
	
	private LocalidadeDelegate localidadeDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] codigos = request.getParameterValues("codigo");
		for (int i = 0; i < codigos.length; i++){
			MunicipioVO municipioVO = new MunicipioVO(new Integer(codigos[i]));
			localidadeDelegate.remove(municipioVO);
		}
		MunicipioForm municipioForm = (MunicipioForm)form;
		municipioForm.carregarComponentes(mapping, request, localidadeDelegate);
		return mapping.findForward("success");
	}

	public void setLocalidadeDelegate(LocalidadeDelegate localidadeDelegate) {
		this.localidadeDelegate = localidadeDelegate;
	}

}
