
package br.com.vortice.ijuri.core.pagamento;

import java.util.Date;

import br.com.vortice.ijuri.core.abstracao.ValueObject;


/**
 * <p></p>
 */
public class PagamentoVO extends ValueObject{

    private Float valorPago; 
    private Date dataVencimento;
    private Date dataPagamento; 
    private Long codigo; 
    private Float valorMulta; 
    private Float valorJuros; 
    private FormaPagamentoVO formaPagamento;
    private ChequeVO cheque;
    private Float valorVencimento;
    private StatusPagamentoVO status;

    public Long getCodigo() {
        return codigo;
    }
    /**
     * @param codigo The codigo to set.
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    /**
     * @return Returns the formaPagamentoVO.
     */
    public FormaPagamentoVO getFormaPagamento() {
        return formaPagamento;
    }
    /**
     * @param formaPagamentoVO The formaPagamentoVO to set.
     */
    public void setFormaPagamento(FormaPagamentoVO formaPagamentoVO) {
        this.formaPagamento = formaPagamentoVO;
    }
    /**
     * @return Returns the isPaga.
     */
    public boolean isPago() {
        return (StatusPagamentoVO.PAGO.equals(status.getCodigo()) || StatusPagamentoVO.AGUARDANDO_COMPENSACAO_CHEQUE.equals(status.getCodigo()));
    }
    
    /**
     * @return Returns the valorJuros.
     */
    public Float getValorJuros() {
        return valorJuros;
    }
    /**
     * @param valorJuros The valorJuros to set.
     */
    public void setValorJuros(Float valorJuros) {
        this.valorJuros = valorJuros;
    }
    /**
     * @return Returns the valorMulta.
     */
    public Float getValorMulta() {
        return valorMulta;
    }
    /**
     * @param valorMulta The valorMulta to set.
     */
    public void setValorMulta(Float valorMulta) {
        this.valorMulta = valorMulta;
    }
    /**
     * @return Returns the valorPago.
     */
    public Float getValorPago() {
        return valorPago;
    }
    /**
     * @param valorPago The valorPago to set.
     */
    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }
    /**
     * @return Returns the cheque.
     */
    public ChequeVO getCheque() {
        return cheque;
    }
    /**
     * @param cheque The cheque to set.
     */
    public void setCheque(ChequeVO cheque) {
        this.cheque = cheque;
    }
    /**
     * @return Returns the valor.
     */
    public Float getValorVencimento() {
        return valorVencimento;
    }
    /**
     * @param valor The valor to set.
     */
    public void setValorVencimento(Float valor) {
        this.valorVencimento = valor;
    }
    /**
     * @return Returns the dataPagamento.
     */
    public Date getDataPagamento() {
        return dataPagamento;
    }
    /**
     * @param dataPagamento The dataPagamento to set.
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    /**
     * @return Returns the dataVencimento.
     */
    public Date getDataVencimento() {
        return dataVencimento;
    }
    /**
     * @param dataVencimento The dataVencimento to set.
     */
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    /**
     * @return Returns the status.
     */
    public StatusPagamentoVO getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(StatusPagamentoVO status) {
        this.status = status;
    }
 } // end PagamentoVO






