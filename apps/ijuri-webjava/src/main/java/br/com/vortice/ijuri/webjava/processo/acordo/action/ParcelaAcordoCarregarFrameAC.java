/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.pagamento.FormaPagamentoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.ParcelaAcordoForm;

/**
 * @author Antonio Amadeu
 */
public class ParcelaAcordoCarregarFrameAC extends BaseAction{
    
	private AcordoDelegate acordoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		ParcelaAcordoForm parcelaAcordoForm = (ParcelaAcordoForm)form;
        
        ParcelaAcordoVO parcela = new ParcelaAcordoVO();
        parcela.setCodigo(parcelaAcordoForm.getCodigo());
        parcela = acordoDelegate.findParcelaByPrimaryKey(parcela);
        
        if (parcela.isPago()){
            if (parcela.getFormaPagamento()!=null && parcela.getFormaPagamento().getCodigo()!=null){
                parcelaAcordoForm.setFormaPagamentoCodigo(parcela.getFormaPagamento().getCodigo().toString());
                
                if (parcela.getFormaPagamento().getCodigo().equals(FormaPagamentoVO.CHEQUE)){
                    parcelaAcordoForm.setNumeroAgencia(parcela.getCheque().getNumeroAgencia());
                    parcelaAcordoForm.setNumeroCheque(parcela.getCheque().getNumero());
                    parcelaAcordoForm.setBancoNumero(parcela.getCheque().getBanco().getNumeroString());
                    BeanUtils.copyProperty(parcelaAcordoForm, "dataCompensacaoCheque", parcela.getCheque().getDataCompensacao());
                    BeanUtils.copyProperty(parcelaAcordoForm, "dataRecebimentoCheque", parcela.getCheque().getDataRecebimento());
                }
            }
            parcelaAcordoForm.setParcelaQuitada(ParcelaAcordoForm.PARCELA_QUITADA);
        }else{
            //segest�o dos campos do novo pagamento da parcela
            parcela.setValorPago(parcela.getValorVencimento());
            
            float honorario = 0;
            if (!isCampoVazio(parcelaAcordoForm.getHonorario()))
            	honorario = Float.valueOf(parcelaAcordoForm.getHonorario()).floatValue()*0.01f; 
            float valorVencimento = parcela.getValorVencimento().floatValue();
            float valorHonorario = honorario*valorVencimento;
            parcela.setValorHonorario(new Float(valorHonorario));
            parcela.setValorRepasse(new Float(valorVencimento-valorHonorario));
            
            parcela.setDataPagamento(new Date());
            parcelaAcordoForm.setParcelaQuitada(ParcelaAcordoForm.PARCELA_EM_ABERTO);
        }
        
        parcelaAcordoForm.setStatusPagamentoCodigo(parcela.getStatus().getCodigo());
                
        BeanUtils.copyProperties(parcelaAcordoForm, parcela);
        
        parcelaAcordoForm.carregarComponentes(mapping, request, getWebApplicationContext());
        
        return mapping.getInputForward();
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}
	
}