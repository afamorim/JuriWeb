package br.com.vortice.ijuri.core.processo;

import java.util.Collection;
import java.util.HashMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class StatusProcessoVO extends ValueObject {
    private Integer codigo;
    private String descricao;
    
    private static HashMap tiposMap = new HashMap();
    
    static{
        StatusProcessoVO statusProcesso = new StatusProcessoVO();
        statusProcesso.setCodigo(new Integer(1));
        statusProcesso.setDescricao("Ativo");
        tiposMap.put(statusProcesso.getCodigo(), statusProcesso);
        
        statusProcesso = new StatusProcessoVO();
        statusProcesso.setCodigo(new Integer(2));
        statusProcesso.setDescricao("Inativo");
        tiposMap.put(statusProcesso.getCodigo(), statusProcesso);
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
    
    public static StatusProcessoVO findByCodigo(Integer codigo){
        return (StatusProcessoVO)tiposMap.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
}
