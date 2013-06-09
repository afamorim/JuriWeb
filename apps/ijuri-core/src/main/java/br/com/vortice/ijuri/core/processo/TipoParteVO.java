package br.com.vortice.ijuri.core.processo;

import java.util.Collection;
import java.util.HashMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class TipoParteVO extends ValueObject {
    private Integer codigo;
    private String descricao;
    
    public static int REU = 1;
    public static int AUTOR = 2;
    public static int ADVOGADO = 3;
    
    private static HashMap tiposMap = new HashMap();
    
    static{
        TipoParteVO tipoParte = new TipoParteVO();
        tipoParte.setCodigo(new Integer(REU));
        tipoParte.setDescricao("Réu");
        tiposMap.put(tipoParte.getCodigo(), tipoParte);
        
        tipoParte = new TipoParteVO();
        tipoParte.setCodigo(new Integer(AUTOR));
        tipoParte.setDescricao("Autor");
        tiposMap.put(tipoParte.getCodigo(), tipoParte);
        
        tipoParte = new TipoParteVO();
        tipoParte.setCodigo(new Integer(ADVOGADO));
        tipoParte.setDescricao("Advogado");
        tiposMap.put(tipoParte.getCodigo(), tipoParte);
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
        return descricao;
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
    
    public static TipoParteVO findByCodigo(Integer codigo){
        return (TipoParteVO)tiposMap.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
}
