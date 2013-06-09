package br.com.vortice.ijuri.core.processo;

import java.util.Collection;
import java.util.HashMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class TipoTaxaVO extends ValueObject {
    private Integer codigo;
    private String descricao;
    
    private static HashMap tiposMap = new HashMap();
    
    public static final Integer COMUN = new Integer(1);
    public static final Integer EXTRA = new Integer(2);
    
    static{
        TipoTaxaVO tipoTaxa = new TipoTaxaVO();
        tipoTaxa.setCodigo(COMUN);
        tipoTaxa.setDescricao("Comun");
        tiposMap.put(tipoTaxa.getCodigo(), tipoTaxa);
        
        tipoTaxa = new TipoTaxaVO();
        tipoTaxa.setCodigo(EXTRA);
        tipoTaxa.setDescricao("Extra");
        tiposMap.put(tipoTaxa.getCodigo(), tipoTaxa);
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
    
    public static TipoTaxaVO findByCodigo(Integer codigo){
        return (TipoTaxaVO)tiposMap.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
}
