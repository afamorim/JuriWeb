
/*
 * Java class "ParcelaAcordoVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.processo.acordo;

import java.util.Date;

import br.com.vortice.ijuri.core.pagamento.PagamentoVO;


/**
 * <p></p>
 */
public class ParcelaAcordoVO extends PagamentoVO {

    private Float valor; 
    private Date dataVencimento; 
    private Integer numero; 
    private Date dataRepasse; 
    private Float valorRepasse; 
    private Float valorHonorario; 
    private String observacao; 
    private AcordoVO acordo;
    private Float valorCorrecao;
    private Float valorClausulaPenal;
    private String numeroFormatado; 
    private Boolean gerarAndamento;
    
    
    /**
     * @return Returns the dataRepasse.
     */
    public Date getDataRepasse() {
        return dataRepasse;
    }
    /**
     * @param dataRepasse The dataRepasse to set.
     */
    public void setDataRepasse(Date dataRepasse) {
        this.dataRepasse = dataRepasse;
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
     * @return Returns the valorHonorario.
     */
    public Float getValorHonorario() {
        return valorHonorario;
    }
    /**
     * @param valorHonorario The valorHonorario to set.
     */
    public void setValorHonorario(Float valorHonorario) {
        this.valorHonorario = valorHonorario;
    }
    /**
     * @return Returns the valorRepasse.
     */
    public Float getValorRepasse() {
        return valorRepasse;
    }
    /**
     * @param valorRepasse The valorRepasse to set.
     */
    public void setValorRepasse(Float valorRepasse) {
        this.valorRepasse = valorRepasse;
    }
    /**
     * @return Returns the acordo.
     */
    public AcordoVO getAcordo() {
        return acordo;
    }
    /**
     * @param acordo The acordo to set.
     */
    public void setAcordo(AcordoVO acordo) {
        this.acordo = acordo;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        ParcelaAcordoVO outraParcela = (ParcelaAcordoVO)other;
        if (this.numero.equals(outraParcela.getNumero())){
            return true;
        }
        return false;
    }
    
    public Float getValorAcrescimos(){
        return new Float(0f);
    }
    
    public void setValorClausulaPenal(Float valorClausulaPenal) {
        this.valorClausulaPenal = valorClausulaPenal;
    }
    
    public Float getValorClausulaPenal() {
        return valorClausulaPenal;
    }
    
    public Float getValorCorrecao() {
        return valorCorrecao;
    }
    
    public void setValorCorrecao(Float valorCorrigido) {
        this.valorCorrecao = valorCorrigido;
    }
    public String getNumeroFormatado() {
        return numeroFormatado;
    }
    public void setNumeroFormatado(String numeroFormatado) {
        this.numeroFormatado = numeroFormatado;
    }
    public Boolean getGerarAndamento() {
        return gerarAndamento;
    }
    public void setGerarAndamento(Boolean gerarAndamento) {
        this.gerarAndamento = gerarAndamento;
    }
    
    
 } // end ParcelaAcordoVO






