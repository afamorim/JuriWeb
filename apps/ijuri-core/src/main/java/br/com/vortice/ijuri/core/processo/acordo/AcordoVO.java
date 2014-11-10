
/*
 * Java class "AcordoVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.processo.acordo;

import java.util.Date;
import java.util.Iterator;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;


/**
 * <p></p>
 */
public class AcordoVO extends ValueObject{

    private Float valor; 
    private Integer quantidadeParcelas; 
    private Date dataVencimento; 
    private String observacao; 
    private Long codigo; 
    
    private Float valorTotalPago;
    private Float valorIndiceMulta;
    private Float valorJurosMes;
    private Float valorClausulaPenalMes;
    private IndiceReajusteVO indiceReajuste;
    
    private ProcessoVO processo; 
    private java.util.Collection parcelas; // of type ParcelaAcordoVO
    private java.util.Collection taxasComuns; // of type TaxaVO
    private java.util.Collection taxasExtras; // of type TaxaVO

    public AcordoVO(Long codigo) {
        super();
        this.codigo = codigo;
    }
    
    public AcordoVO(){
        
    }
    
    /**
     * @return Returns the codigo.
     */
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
     * @return Returns the parcelas.
     */
    public java.util.Collection getParcelas() {
        return parcelas;
    }
    /**
     * @param parcelas The parcelas to set.
     */
    public void setParcelas(java.util.Collection parcelas) {
        this.parcelas = parcelas;
        setValorTotalPago();
    }
    /**
     * @return Returns the processoVO.
     */
    public ProcessoVO getProcesso() {
        return processo;
    }
    /**
     * @param processoVO The processoVO to set.
     */
    public void setProcesso(ProcessoVO processoVO) {
        this.processo = processoVO;
    }
    /**
     * @return Returns the quantidadeParcelas.
     */
    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }
    /**
     * @param quantidadeParcelas The quantidadeParcelas to set.
     */
    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
    /**
     * @return Returns the taxas.
     */
    public java.util.Collection getTaxasComuns() {
        return taxasComuns;
    }
    /**
     * @param taxas The taxas to set.
     */
    public void setTaxasComuns(java.util.Collection taxas) {
        this.taxasComuns = taxas;
    }
    /**
     * @return Returns the valor.
     */
    public Float getValor() {
        return valor;
    }
    /**
     * @param valor The valor to set.
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }
    /**
     * @return Returns the taxasExtras.
     */
    public java.util.Collection getTaxasExtras() {
        return taxasExtras;
    }
    /**
     * @param taxasExtras The taxasExtras to set.
     */
    public void setTaxasExtras(java.util.Collection taxasExtras) {
        this.taxasExtras = taxasExtras;
    }
    
    public void setValorTotalPago()
    {
         if (this.getParcelas()==null || this.getParcelas().size()==0){
             setValorTotalPago(new Float(0));
             return;
         }    
             
         Iterator it = this.getParcelas().iterator();
         
         float valorPago = 0;
         
         while(it.hasNext()){
            ParcelaAcordoVO parcela = (ParcelaAcordoVO)it.next();
            if(parcela.getStatus().getCodigo().equals(StatusPagamentoVO.PAGO)){
                 valorPago += parcela.getValorPago().floatValue(); 
            }
         }
         
         setValorTotalPago(new Float(valorPago));
    }
    
    public ParcelaAcordoVO getPrimeiraParcelaEmAberto(){
        if (this.getParcelas()==null || this.getParcelas().size()==0){
              return null;
        }    
             
        Iterator it = this.getParcelas().iterator();
                
        while(it.hasNext() ){
            ParcelaAcordoVO parcela = (ParcelaAcordoVO)it.next();
            if(parcela.getStatus().getCodigo().equals(StatusPagamentoVO.EM_ABERTO)){
                  return parcela;
            }
        }
         
        return null;
    }
    

    public Boolean getConcluido(){
         Iterator it = this.getParcelas().iterator();
         
         while(it.hasNext()){
            ParcelaAcordoVO parcela = (ParcelaAcordoVO)it.next();
            if(parcela.getStatus().getCodigo().equals(StatusPagamentoVO.EM_ABERTO)){
                 return Boolean.FALSE;
            }
         }
         
         return Boolean.TRUE;
    }
    
    public Float getValorTotalEmAberto(){
        if (getValorTotalPago()!=null){
            return new Float(getValor().floatValue() - getValorTotalPago().floatValue());
        }else{
            return getValor();
        }
    }

    /**
     * @return Returns the valorTotalPago.
     */
    public Float getValorTotalPago() {
        return valorTotalPago;
    }

    /**
     * @param valorTotalPago The valorTotalPago to set.
     */
    public void setValorTotalPago(Float valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public IndiceReajusteVO getIndiceReajuste() {
        return indiceReajuste;
    }

    public void setIndiceReajuste(IndiceReajusteVO indiceReajuste) {
        this.indiceReajuste = indiceReajuste;
    }

    public Float getValorClausulaPenalMes() {
        return valorClausulaPenalMes;
    }

    public void setValorClausulaPenalMes(Float valorClausulaPenalMes) {
        this.valorClausulaPenalMes = valorClausulaPenalMes;
    }

    public Float getValorIndiceMulta() {
        return valorIndiceMulta;
    }

    public void setValorIndiceMulta(Float valorIndiceMulta) {
        this.valorIndiceMulta = valorIndiceMulta;
    }

    public Float getValorJurosMes() {
        return valorJurosMes;
    }

    public void setValorJurosMes(Float valorJurosMes) {
        this.valorJurosMes = valorJurosMes;
    }
   
 } // end AcordoVO
