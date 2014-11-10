/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.form.TaxaForm;


/**
 * @author Antonio Amadeu
 */
public class TaxaGerarAC extends BaseAction{
    
	public ActionForward executar(ActionMapping mapping, 
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response) throws Exception {
		
        
        TaxaForm taxaForm = (TaxaForm)form;
        
        String[] tokens = taxaForm.getPeriodoInicio().split("/");
        int mes = Integer.parseInt(tokens[0]);
        int ano = Integer.parseInt(tokens[1]);
        Date dataInicial = DateUtil.getInstance(getLocale()).convertToDate(null, new Integer(mes), new Integer(ano));
        
        tokens = taxaForm.getPeriodoFim().split("/");
        mes = Integer.parseInt(tokens[0]);
        ano = Integer.parseInt(tokens[1]);
        Date dataFinal = DateUtil.getInstance(getLocale()).convertToDate(null, new Integer(mes), new Integer(ano));
        
        Collection taxas = new ArrayList();
        TaxaVO taxa = null; 
        int i = 0;
        Calendar c = Calendar.getInstance();
        while (dataInicial.getTime()<=dataFinal.getTime()){
            c.setTime(dataInicial);
            taxa = new TaxaVO();
            taxa.setMes(new Integer(c.get(Calendar.MONTH)+1));
            taxa.setAno(new Integer(c.get(Calendar.YEAR)));
            taxa.setTipo(TipoTaxaVO.findByCodigo(Integer.valueOf(taxaForm.getTipoCodigo())));
            taxas.add(taxa);
            dataInicial = DateUtil.getInstance().calcularProximoMes(dataInicial);
        }
        
        taxaForm.setTaxas(Base64Encoder.encode(taxas));
        
        request.setAttribute("taxas", taxas);
        
        return mapping.getInputForward();
		
	}
}
