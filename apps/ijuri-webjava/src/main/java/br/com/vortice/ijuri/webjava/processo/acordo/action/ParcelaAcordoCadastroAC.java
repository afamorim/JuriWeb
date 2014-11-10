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

import br.com.vortice.ijuri.core.pagamento.BancoVO;
import br.com.vortice.ijuri.core.pagamento.ChequeVO;
import br.com.vortice.ijuri.core.pagamento.FormaPagamentoVO;
import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.ParcelaAcordoForm;

/**
 * @author Antonio Amadeu
 */
public class ParcelaAcordoCadastroAC extends BaseAction{
    
	private AcordoDelegate acordoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ParcelaAcordoForm parcelaAcordoForm = (ParcelaAcordoForm)form;
        //Obt�m parcela selecionada
        ParcelaAcordoVO parcelaSelecionada = new ParcelaAcordoVO();
        parcelaSelecionada.setCodigo(parcelaAcordoForm.getCodigo());
        parcelaSelecionada = acordoDelegate.findParcelaByPrimaryKey(parcelaSelecionada);
        
        ParcelaAcordoVO parcelaVO = new ParcelaAcordoVO();
        //Se parcela n�o for quitada
        if (StatusPagamentoVO.EM_ABERTO.equals(parcelaAcordoForm.getStatusPagamentoCodigo()))
        {
            parcelaVO.setCodigo(parcelaSelecionada.getCodigo());
            parcelaVO.setObservacao(parcelaAcordoForm.getObservacao());
            BeanUtils.copyProperty(parcelaVO, "dataVencimento", parcelaAcordoForm.getDataVencimento());
            BeanUtils.copyProperty(parcelaVO, "valorVencimento", parcelaAcordoForm.getValorVencimento());
            parcelaVO.setStatus(StatusPagamentoVO.findByCodigo(StatusPagamentoVO.EM_ABERTO));
        }else{
            AcordoVO acordo = new AcordoVO();
            acordo.setProcesso(new ProcessoVO(Long.valueOf(parcelaAcordoForm.getProcessoCodigo())));
            acordo.setCodigo(Long.valueOf(parcelaAcordoForm.getAcordoCodigo()));
            parcelaVO.setAcordo(acordo);
            
            parcelaVO.setCodigo(parcelaSelecionada.getCodigo());
            BeanUtils.copyProperties(parcelaVO, parcelaAcordoForm);
           
            BeanUtils.copyProperty(parcelaVO, "dataPagamento", parcelaAcordoForm.getDataPagamento());
            BeanUtils.copyProperty(parcelaVO, "dataRepasse", parcelaAcordoForm.getDataRepasse());
            
            parcelaVO.setStatus(StatusPagamentoVO.findByCodigo(parcelaAcordoForm.getStatusPagamentoCodigo()));
            
            FormaPagamentoVO formaPagamento = 
                FormaPagamentoVO.findByCodigo(Integer.valueOf(parcelaAcordoForm.getFormaPagamentoCodigo()));
            parcelaVO.setFormaPagamento(formaPagamento);
            
            if (formaPagamento.getCodigo().equals(FormaPagamentoVO.CHEQUE)){
                ChequeVO cheque = new ChequeVO();
                cheque.setNumero(parcelaAcordoForm.getNumeroCheque());
                cheque.setNumeroAgencia(parcelaAcordoForm.getNumeroAgencia());
                BeanUtils.copyProperty(cheque, "dataCompensacao", parcelaAcordoForm.getDataCompensacaoCheque());
                BeanUtils.copyProperty(cheque, "dataRecebimento", parcelaAcordoForm.getDataRecebimentoCheque());
                
                BancoVO banco = new BancoVO();
                banco.setNumero(Integer.valueOf(parcelaAcordoForm.getBancoNumero()));
                
                cheque.setBanco(banco);
                parcelaVO.setCheque(cheque);    
            }
        }
        
        acordoDelegate.update(parcelaVO);
        registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        
       /* StringBuffer url = new StringBuffer(request.getContextPath()+"/admin/Processo/Acordo/Parcela/CarregarCadastro.do?");
        url.append("acordoCodigo="+parcelaAcordoForm.getAcordoCodigo().toString());
        url.append("&urlVoltar="+parcelaAcordoForm.getUrlVoltar().toString());
        registrarScript(request, "parent.location.href = '"+url.toString()+"';");
        */
        registrarScript(request,
                "parent.atualizarGridParcelas("+parcelaAcordoForm.getCodigo()+","+parcelaAcordoForm.getNumero()+");");
        parcelaAcordoForm.carregarComponentes(mapping, request,getWebApplicationContext());       
        return mapping.getInputForward();
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}
	
}