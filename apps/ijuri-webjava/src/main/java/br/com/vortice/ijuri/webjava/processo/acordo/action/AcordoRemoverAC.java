/*
 * Created on 07/05/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;

/**
 * @author amadeu
 */
public class AcordoRemoverAC extends BaseAction {

	private AcordoDelegate acordoDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] codigos = request.getParameterValues("codigos");
		for (int i = 0; i < codigos.length; i++){
			AcordoVO vo = new AcordoVO(new Long(codigos[i]));
			acordoDelegate.remove(vo);
		}
        registrarMensagemSucesso(request, MensagemSucessoIf.REMOVE);        
		return mapping.getInputForward();
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}

}