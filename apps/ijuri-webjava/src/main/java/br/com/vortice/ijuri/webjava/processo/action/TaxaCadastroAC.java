/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.form.TaxaForm;


/**
 * @author Antonio Amadeu
 */
public class TaxaCadastroAC extends BaseAction{
    
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        TaxaForm taxaForm = (TaxaForm)form;    
        ArrayList taxas = (ArrayList)Base64Decoder.decodeToObject(taxaForm.getTaxas());
        
        TaxaVO taxa = new TaxaVO();
        BeanUtils.copyProperties(taxa,taxaForm);
        taxa.setTipo(TipoTaxaVO.findByCodigo(Integer.valueOf(taxaForm.getTipoCodigo())));

        if (!isCampoVazio(taxaForm.getPos()))
        {            
            int pos = Integer.parseInt(taxaForm.getPos());
            TaxaVO taxaRemovida = (TaxaVO)taxas.remove(pos);
            
            if (taxas.indexOf(taxa)>-1){
                registrarMensagemSucesso(request,"Per�odo j� existe.");
                taxaForm.carregarComponentes(mapping, request);
                return mapping.getInputForward();
            }else{
                taxa.setCodigo(taxaRemovida.getCodigo());
                taxas.add(taxa);
            }    
        }else{
            if (taxas.indexOf(taxa)>-1){
                registrarMensagemSucesso(request,"Per�odo j� existe.");
                taxaForm.carregarComponentes(mapping, request);
                return mapping.getInputForward();
            }else{
                taxas.add(taxa);
            }
        }
        
        Collections.sort(taxas);
        taxaForm.setTaxas(Base64Encoder.encode(taxas));
        
        request.setAttribute("script", "<script> reloadTaxas(); </script>");
        
        return mapping.getInputForward();
	}
}