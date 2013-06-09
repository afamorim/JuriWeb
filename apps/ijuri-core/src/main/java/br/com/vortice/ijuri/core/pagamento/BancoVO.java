
/*
 * Java class "BancoVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.pagamento;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.abstracao.util.StringUtil;


/**
 * <p></p>
 */
public class BancoVO extends ValueObject{ 

    private Integer numero; 
    private String nome; 
  
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
     * @return Returns the nome.
     */
    public String getNome() {
        return nome;
    }
    /**
     * @param nome The nome to set.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao(){
        return getNumeroString()+" - "+nome;
    }
    
    public String getNumeroString(){
        return StringUtil.preencherEsquerda(numero.toString(), 3, '0');
    }
    
 } // end BancoVO






