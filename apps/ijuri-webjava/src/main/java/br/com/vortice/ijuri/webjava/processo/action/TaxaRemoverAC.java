/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.form.TaxaForm;


/**
 * @author Antonio Amadeu
 */
public class TaxaRemoverAC extends BaseAction{
    
	public ActionForward executar(ActionMapping mapping, 
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response) throws Exception {
		
        
        TaxaForm taxaForm = (TaxaForm)form;
        
        ArrayList taxas = (ArrayList)Base64Decoder.decodeToObject(taxaForm.getTaxas());
        
        String[] codigosTaxa = request.getParameterValues("checks");
        int pos = 0;
        ArrayList taxasSelecionadas = new ArrayList(codigosTaxa.length); 
        for (int i=0;i<codigosTaxa.length;i++){
            pos = Integer.parseInt(codigosTaxa[i]);
            taxasSelecionadas.add(taxas.get(pos));
        }
        
        for (Iterator iter = taxasSelecionadas.iterator(); iter.hasNext();) {
            TaxaVO taxa = (TaxaVO) iter.next();
            taxas.remove(taxa);
        }
        
        taxaForm.setTaxas(Base64Encoder.encode(taxas));
        
        request.setAttribute("taxas", taxas);
        
        return mapping.getInputForward();
		
	}
}
