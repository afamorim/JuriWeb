/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceFiltroAssembler;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.ParcelaAcordoForm;


/**
 * @author Antonio Amadeu
 */
public class ParcelaAcordoCorrigirValoresAC extends BaseAction{
    
	private AcordoDelegate acordoDelegate;
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	public ActionForward executar(ActionMapping mapping, 
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response) throws Exception {
		
        
        ParcelaAcordoForm parcelaAcordoForm = (ParcelaAcordoForm)form;
        
        //Obt�m os dados do Acordo
        AcordoVO acordo = new AcordoVO(Long.valueOf(parcelaAcordoForm.getAcordoCodigo())); 
        acordo = acordoDelegate.findByPrimaryKey(acordo);
        
        //Obt�m os dados da Parcela
        ParcelaAcordoVO parcela = new ParcelaAcordoVO();
        parcela.setCodigo(parcelaAcordoForm.getCodigo());
        parcela = acordoDelegate.findParcelaByPrimaryKey(parcela);
        
        //Obt�m os dados da tela
        BeanUtils.copyProperties(parcela,parcelaAcordoForm);
        
        float valorVencimento = parcela.getValorVencimento().floatValue();
        float valorCorrigido = valorVencimento;
        
        float valorAcrescimos = 0.0f;
        DateUtil dateUtil = DateUtil.getInstance();
        if (parcela.getDataVencimento().getTime()<parcela.getDataPagamento().getTime()){
            
            
            //Corrigi o valor
            PeriodoIndiceFiltroAssembler periodoIndiceFiltro = new PeriodoIndiceFiltroAssembler();
            periodoIndiceFiltro.setIndiceReajuste(acordo.getIndiceReajuste());
            
            //Seta a primeira data de vencimento do Acordo
            periodoIndiceFiltro.setPeriodoIndiceInicio(parcela.getDataVencimento());  
            
            //Seta a data de pagamento da parcela atual
            periodoIndiceFiltro.setPeriodoIndiceFim(parcela.getDataPagamento());
            
            float indiceAcumluado =  correcaoMonetariaDelegate.findValorAcumladoIndiceByPeriodo(periodoIndiceFiltro).floatValue();
            valorCorrigido = valorVencimento * indiceAcumluado;
            if (indiceAcumluado > 1.0f )
                parcela.setValorCorrecao(new Float(valorCorrigido-valorVencimento));
            //fim da corre��o
            
            int dias = dateUtil.subtractDays(parcela.getDataVencimento(),parcela.getDataPagamento());
            //Calcula o juros
            float valorIndiceJurosDia = acordo.getValorJurosMes().floatValue()*0.01f/30f;
            float valorJuros = (dias*valorIndiceJurosDia)*valorCorrigido;  
            parcela.setValorJuros(new Float(valorJuros));
            
            valorAcrescimos += valorJuros;
            //fim do c�lculo de juros
            
            //Calcula a multa 
            if (acordo.getValorIndiceMulta()!=null && acordo.getValorIndiceMulta().floatValue()>0){
                
                float valorIndiceMulta = acordo.getValorIndiceMulta().floatValue()*0.01f;
                float valorMulta = valorIndiceMulta*valorCorrigido;
                parcela.setValorMulta(new Float(valorMulta));
                
                valorAcrescimos += valorMulta;
            
                //Fim do c�lculo de multa     
            //Calcula a cl�usula penal    
            }else if (acordo.getValorClausulaPenalMes()!=null && acordo.getValorClausulaPenalMes().floatValue()>0){
                
                float valorClausulaPenalDia = acordo.getValorClausulaPenalMes().floatValue()*0.01f/30;
                float valorClausulaPenal = (dias*valorClausulaPenalDia)*valorCorrigido;
                parcela.setValorClausulaPenal(new Float(valorClausulaPenal));
                
                valorAcrescimos += valorClausulaPenal;
                //Fim do c�lculo de cl�usula penal
            }
        }
        
        //Calcula o valor Pago
        float valorPago = valorCorrigido+valorAcrescimos;
        parcela.setValorPago(new Float(valorPago));
          
        //Calcula o valor do Honor�rio
        float honorario = Float.valueOf(parcelaAcordoForm.getHonorario()).floatValue()*0.01f; 
        float valorHonorario = honorario*valorPago;
        parcela.setValorHonorario(new Float(valorHonorario));
       
        //Calcula o valor de Repasse 
        parcela.setValorRepasse(new Float(valorPago-valorHonorario));
        
        BeanUtils.copyProperties(parcelaAcordoForm, parcela);
        
        parcelaAcordoForm.carregarComponentes(mapping, request,getWebApplicationContext());
        return mapping.getInputForward();
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}
	
}