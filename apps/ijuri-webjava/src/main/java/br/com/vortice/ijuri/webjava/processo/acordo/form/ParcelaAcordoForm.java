package br.com.vortice.ijuri.webjava.processo.acordo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.pagamento.FormaPagamentoVO;
import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagensErroIf;
import br.com.vortice.ijuri.webjava.pagamento.PagamentoDelegate;

public class ParcelaAcordoForm extends BaseActionForm {
	public static Integer PARCELA_QUITADA = new Integer(1);
    public static Integer PARCELA_EM_ABERTO = new Integer(0);
    
    //Parcela
	private Integer numero;
    private String valorVencimento;
    private Integer parcelaQuitada;
    private String valorPago;
    private String dataPagamento;
    private String valorHonorario;
    private String dataVencimento;
    private String formaPagamentoCodigo;
    private String bancoNumero;
    private String numeroAgencia;
    private String numeroCheque;
    private String dataCompensacaoCheque;
    private String dataRecebimentoCheque;
    private String observacao;
    private String valorRepasse;
    private String dataRepasse;
    private String valorMulta; 
    private String valorJuros;
    private String valorCorrecao;
    private String valorClausulaPenal;
    private Long codigo;
    private Integer statusPagamentoCodigo;
    private Boolean gerarAndamento;
    
    private String parcelasTexto;
    
    //Acordo
    private String acordoCodigo;
    private String valorTotal; 
    private String quantidadeParcelas;
    private String valorTotalPago;
    private String valorTotalEmAberto;
    
    //Processo 
    private String processoCodigo;
    private String honorario;
    
    //Tela
    private String urlVoltar;
    
    private PagamentoDelegate pagamentoDelegate;
    	
    /**
     * @return Returns the numero.
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero The numero to set.
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return Returns the acordoCodigo.
     */
    public String getAcordoCodigo() {
        return acordoCodigo;
    }

    /**
     * @param acordoCodigo The acordoCodigo to set.
     */
    public void setAcordoCodigo(String acordoCodigo) {
        this.acordoCodigo = acordoCodigo;
    }

    /**
     * @return Returns the quantidadeParcelas.
     */
    public String getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    /**
     * @param quantidadeParcelas The quantidadeParcelas to set.
     */
    public void setQuantidadeParcelas(String quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    
    /**
     * @return Returns the valorTotalEmAberto.
     */
    public String getValorTotalEmAberto() {
        return valorTotalEmAberto;
    }

    /**
     * @param valorTotalEmAberto The valorTotalEmAberto to set.
     */
    public void setValorTotalEmAberto(String valorTotalEmAberto) {
        this.valorTotalEmAberto = valorTotalEmAberto;
    }

    /**
     * @return Returns the valorTotalPago.
     */
    public String getValorTotalPago() {
        return valorTotalPago;
    }

    /**
     * @param valorTotalPago The valorTotalPago to set.
     */
    public void setValorTotalPago(String valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }
    
    public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        if ("CALCULAR".equals(getAcao())){
            validarVazioData(dataVencimento, "Vencimento");
        }else{
        
            validarVazio(valorVencimento, "Valor da Parcela");
            validarNumeroFloat(valorVencimento, "Valor da Parcela");
            
            validarVazioData(dataVencimento, "Vencimento");
            
            if (StatusPagamentoVO.AGUARDANDO_COMPENSACAO_CHEQUE.equals(statusPagamentoCodigo) || 
                    StatusPagamentoVO.PAGO.equals(statusPagamentoCodigo)){
                
                validarVazio(valorPago, "Valor Pago");
                validarNumeroFloat(valorPago, "Valor Pago");
                
                validarVazioData(dataPagamento, "Pagamento");
                
                validarVazio(valorHonorario, "Valor Honor�rio");
                validarNumeroFloat(valorHonorario, "Valor Honor�rio");
                
                validarVazio(valorRepasse, "Valor Repasse");
                validarNumeroFloat(valorRepasse, "Valor Repasse");
                
                // Se a forma de pagamento foi escolhida deve olhar se � cheque  
                if (validarVazio(formaPagamentoCodigo, "Forma de Pagamento") == true){
                   if (formaPagamentoCodigo.equals(FormaPagamentoVO.CHEQUE.toString())){
                        validarVazio(bancoNumero, "Banco");
                        validarVazio(numeroAgencia, "N� Ag�ncia");
                        validarVazio(numeroCheque, "N� Cheque");
                   }
                }
                
                if ( StatusPagamentoVO.AGUARDANDO_COMPENSACAO_CHEQUE.equals(statusPagamentoCodigo) ){
                    if (formaPagamentoCodigo.equals(FormaPagamentoVO.DINHEIRO.toString())){
                        super.addErro(MensagensErroIf.FORMA_PAGAMENTO_INVALIDA);    
                    }
                }
            }
        }
        
        return super.validate(mapping, request);
    }
     
    public void carregarComponentes(ActionMapping mapping, 
    		HttpServletRequest request, 
    		WebApplicationContext webAppContext) throws Exception {
    	pagamentoDelegate = (PagamentoDelegate) webAppContext.getBean("pagamentoDelegate");
        request.setAttribute("bancos", pagamentoDelegate.findAllBancos());
        
        request.setAttribute("tiposPagamento", StatusPagamentoVO.findAll());
    }

    /**
     * @return Returns the valorTotal.
     */
    public String getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal The valorTotal to set.
     */
    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return Returns the processoCodigo.
     */
    public String getProcessoCodigo() {
        return processoCodigo;
    }

    /**
     * @param processoCodigo The processoCodigo to set.
     */
    public void setProcessoCodigo(String processoCodigo) {
        this.processoCodigo = processoCodigo;
    }

    /**
     * @return Returns the parcelasTexto.
     */
    public String getParcelasTexto() {
        return parcelasTexto;
    }

    /**
     * @param parcelasTexto The parcelasTexto to set.
     */
    public void setParcelasTexto(String parcelasTexto) {
        this.parcelasTexto = parcelasTexto;
    }

    /**
     * @return Returns the parcelaQuitada.
     */
    public Integer getParcelaQuitada() {
        return parcelaQuitada;
    }

    /**
     * @param parcelaQuitada The parcelaQuitada to set.
     */
    public void setParcelaQuitada(Integer parcelaQuitada) {
        this.parcelaQuitada = parcelaQuitada;
    }

    /**
     * @return Returns the valorVencimento.
     */
    public String getValorVencimento() {
        return valorVencimento;
    }

    /**
     * @param valorVencimento The valorVencimento to set.
     */
    public void setValorVencimento(String valorVencimento) {
        this.valorVencimento = valorVencimento;
    }

    /**
     * @return Returns the dataPagamento.
     */
    public String getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento The dataPagamento to set.
     */
    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return Returns the valorHonorario.
     */
    public String getValorHonorario() {
        return valorHonorario;
    }

    /**
     * @param valorHonorario The valorHonorario to set.
     */
    public void setValorHonorario(String valorHonorario) {
        this.valorHonorario = valorHonorario;
    }

    /**
     * @return Returns the valorPago.
     */
    public String getValorPago() {
        return valorPago;
    }

    /**
     * @param valorPago The valorPago to set.
     */
    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    /**
     * @return Returns the dataVencimento.
     */
    public String getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento The dataVencimento to set.
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return Returns the formaPagamentoCodigo.
     */
    public String getFormaPagamentoCodigo() {
        return formaPagamentoCodigo;
    }

    /**
     * @param formaPagamentoCodigo The formaPagamentoCodigo to set.
     */
    public void setFormaPagamentoCodigo(String formaPagamentoCodigo) {
        this.formaPagamentoCodigo = formaPagamentoCodigo;
    }

    /**
     * @return Returns the numeroAgencia.
     */
    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    /**
     * @param numeroAgencia The numeroAgencia to set.
     */
    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    /**
     * @return Returns the numeroCheque.
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque The numeroCheque to set.
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * @return Returns the bancoNumero.
     */
    public String getBancoNumero() {
        return bancoNumero;
    }

    /**
     * @param bancoNumero The bancoNumero to set.
     */
    public void setBancoNumero(String bancoNumero) {
        this.bancoNumero = bancoNumero;
    }

    /**
     * @return Returns the observacao.
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao The observacao to set.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return Returns the dataRepasse.
     */
    public String getDataRepasse() {
        return dataRepasse;
    }

    /**
     * @param dataRepasse The dataRepasse to set.
     */
    public void setDataRepasse(String dataRepasse) {
        this.dataRepasse = dataRepasse;
    }

    /**
     * @return Returns the valorRepasse.
     */
    public String getValorRepasse() {
        return valorRepasse;
    }

    /**
     * @param valorRepasse The valorRepasse to set.
     */
    public void setValorRepasse(String valorRepasse) {
        this.valorRepasse = valorRepasse;
    }

    /**
     * @return Returns the honorario.
     */
    public String getHonorario() {
        return honorario;
    }

    /**
     * @param honorario The honorario to set.
     */
    public void setHonorario(String honorario) {
        this.honorario = honorario;
    }

    /**
     * @return Returns the urlVoltar.
     */
    public String getUrlVoltar() {
        return urlVoltar;
    }

    /**
     * @param urlVoltar The urlVoltar to set.
     */
    public void setUrlVoltar(String urlVoltar) {
        this.urlVoltar = urlVoltar;
    }

    public String getValorClausulaPenal() {
        return valorClausulaPenal;
    }

    public void setValorClausulaPenal(String valorClausulaPenal) {
        this.valorClausulaPenal = valorClausulaPenal;
    }

    public String getValorCorrecao() {
        return valorCorrecao;
    }

    public void setValorCorrecao(String valorCorrecao) {
        this.valorCorrecao = valorCorrecao;
    }

    public String getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(String valorJuros) {
        this.valorJuros = valorJuros;
    }

    public String getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(String valorMulta) {
        this.valorMulta = valorMulta;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDataCompensacaoCheque() {
        return dataCompensacaoCheque;
    }

    public void setDataCompensacaoCheque(String dataCompensacaoCheque) {
        this.dataCompensacaoCheque = dataCompensacaoCheque;
    }

    public String getDataRecebimentoCheque() {
        return dataRecebimentoCheque;
    }

    public void setDataRecebimentoCheque(String dataRecebimentoCheque) {
        this.dataRecebimentoCheque = dataRecebimentoCheque;
    }

    public Integer getStatusPagamentoCodigo() {
        return statusPagamentoCodigo;
    }

    public void setStatusPagamentoCodigo(Integer statusPagamentoCodigo) {
        this.statusPagamentoCodigo = statusPagamentoCodigo;
    }

    public Boolean getGerarAndamento() {
        return gerarAndamento;
    }

    public void setGerarAndamento(Boolean gerarAndamento) {
        this.gerarAndamento = gerarAndamento;
    }

	public void setPagamentoDelegate(PagamentoDelegate pagamentoDelegate) {
		this.pagamentoDelegate = pagamentoDelegate;
	}
    
}     