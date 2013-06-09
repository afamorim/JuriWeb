
/*
 * Java class "FormaPagamentoVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.pagamento;

import java.util.Collection;
import java.util.HashMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

/**
 * <p></p>
 */
public class StatusPagamentoVO extends ValueObject{

    private Integer codigo;
    private String descricao;
    
    private static HashMap tiposMap = new HashMap();
    
    public static final Integer PAGO = new Integer(1);
    public static final Integer EM_ABERTO = new Integer(2);
    public static final Integer AGUARDANDO_COMPENSACAO_CHEQUE = new Integer(3);
    
    static{
        StatusPagamentoVO statusPagamento = new StatusPagamentoVO();
        statusPagamento.setCodigo(PAGO);
        statusPagamento.setDescricao("Pago");
        tiposMap.put(statusPagamento.getCodigo(), statusPagamento);
        
        statusPagamento = new StatusPagamentoVO();
        statusPagamento.setCodigo(EM_ABERTO);
        statusPagamento.setDescricao("Em aberto");
        tiposMap.put(statusPagamento.getCodigo(), statusPagamento);
        
        statusPagamento = new StatusPagamentoVO();
        statusPagamento.setCodigo(AGUARDANDO_COMPENSACAO_CHEQUE);
        statusPagamento.setDescricao("Cheque à compensar");
        tiposMap.put(statusPagamento.getCodigo(), statusPagamento);
    }

    /**
     * @return Returns the codigo.
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo The codigo to set.
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return Returns the descricao.
     */
    public String getDescricao() {
        if (descricao==null)
            return findDescricaoByCodigo(codigo);
        else
            return descricao;
    }
    
    /**
     * @return Returns the descricao.
     */
    public String getDescricaoList() {
        return findDescricaoByCodigo(getCodigo());
    }

    /**
     * @param descricao The descricao to set.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static Collection findAll(){
        return tiposMap.values();
    } 
    
    public static StatusPagamentoVO findByCodigo(Integer codigo){
        return (StatusPagamentoVO)tiposMap.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
} // end StatusPagamentoVO






