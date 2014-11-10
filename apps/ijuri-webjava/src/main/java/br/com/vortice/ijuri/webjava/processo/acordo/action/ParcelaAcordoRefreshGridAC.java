/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.ParcelaAcordoForm;

/**
 * @author Antonio Amadeu
 */
public class ParcelaAcordoRefreshGridAC extends BaseAction{
    
	private AcordoDelegate acordoDelegate;
	public ActionForward executar(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{    
        ParcelaAcordoForm parcelaAcordoForm = (ParcelaAcordoForm)form;
        
        AcordoVO acordoVO = new AcordoVO();
        acordoVO.setCodigo(Long.valueOf(parcelaAcordoForm.getAcordoCodigo()));
            
        acordoVO = acordoDelegate.findByPrimaryKey(acordoVO);
        
        ArrayList parcelas = (ArrayList)acordoDelegate.findParcelasByAcordo(acordoVO);
        
        //seta a cole��o de parcelas no request para serem exibidas na tela em grid
        request.setAttribute("parcelas", parcelas);
        
        parcelaAcordoForm.carregarComponentes(mapping, request,getWebApplicationContext());
        return mapping.getInputForward();	
	}
	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}
	
}