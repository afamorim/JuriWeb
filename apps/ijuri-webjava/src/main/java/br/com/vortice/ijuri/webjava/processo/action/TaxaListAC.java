/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.TaxaForm;


/**
 * @author Antonio Amadeu
 */
public class TaxaListAC extends BaseAction{
    
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        TaxaForm taxaForm = (TaxaForm)form;
        ArrayList taxas = null;
        
        if (taxaForm.getAcao()!=null && taxaForm.getAcao().equals("getSessao"))
        {
            String nomeSessao = null;
            if (taxaForm.getTipoCodigo().equals(TipoTaxaVO.COMUN))
                nomeSessao = "TAXAS_COMUNS";
            else
                nomeSessao = "TAXAS_EXTRAS";
            
            String taxasCodificadas = (String)request.getSession().getAttribute(nomeSessao);
            if (taxasCodificadas!=null)
                taxaForm.setTaxas(taxasCodificadas);
            
            request.getSession().removeAttribute(nomeSessao);
        }
        else if (!isCampoVazio(taxaForm.getProcessoCodigo()))
        {
            ProcessoVO processoVO  = new ProcessoVO(Long.valueOf(taxaForm.getProcessoCodigo()));
            taxas = (ArrayList)processoDelegate.findTaxasByProcesso(processoVO,TipoTaxaVO.findByCodigo(Integer.valueOf(taxaForm.getTipoCodigo())));
            taxaForm.setTaxas(Base64Encoder.encode(taxas));
        }
        if (taxaForm.getTaxas() != null){
            taxas = (ArrayList)Base64Decoder.decodeToObject(taxaForm.getTaxas());
        }else{
            taxas = new ArrayList();
        }
        
        Collections.sort(taxas);    
        request.setAttribute("taxas", taxas);
        
        return mapping.getInputForward();		
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
}