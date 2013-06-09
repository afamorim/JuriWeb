
/*
 * Java class "ChequeVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.pagamento;

import java.util.Date;

public class ChequeVO extends FormaPagamentoVO {

     private String numero; 
     private Date dataCompensacao;
     private Date dataRecebimento;
     private String numeroAgencia; 
     private BancoVO banco;

    /**
     * @return Returns the bancoVO.
     */
    public BancoVO getBanco() {
        return banco;
    }
    /**
     * @param bancoVO The bancoVO to set.
     */
    public void setBanco(BancoVO bancoVO) {
        this.banco = bancoVO;
    }
    /**
     * @return Returns the numero.
     */
    public String getNumero() {
        return numero;
    }
    /**
     * @param numero The numero to set.
     */
    public void setNumero(String numero) {
        this.numero = numero;
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
    public Date getDataCompensacao() {
        return dataCompensacao;
    }
    public void setDataCompensacao(Date dataCompensacao) {
        this.dataCompensacao = dataCompensacao;
    }
    public Date getDataRecebimento() {
        return dataRecebimento;
    }
    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }
 } // end ChequeVO






