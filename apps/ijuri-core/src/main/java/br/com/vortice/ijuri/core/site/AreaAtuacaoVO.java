package br.com.vortice.ijuri.core.site;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class AreaAtuacaoVO extends ValueObject {
    private Integer codigo;
    private String descricao;
    
    private static Map map = new TreeMap();
    
    static{
        AreaAtuacaoVO areaAtuacao = new AreaAtuacaoVO();
        areaAtuacao.setCodigo(new Integer(1));
        areaAtuacao.setDescricao("Condomínio");
        map.put(areaAtuacao.getCodigo(), areaAtuacao);
        
        areaAtuacao = new AreaAtuacaoVO();
        areaAtuacao.setCodigo(new Integer(2));
        areaAtuacao.setDescricao("Empresas");
        map.put(areaAtuacao.getCodigo(), areaAtuacao);
        
        areaAtuacao = new AreaAtuacaoVO();
        areaAtuacao.setCodigo(new Integer(3));
        areaAtuacao.setDescricao("Municípios");
        map.put(areaAtuacao.getCodigo(), areaAtuacao);
        
        
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
     * @param descricao The descricao to set.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static Collection findAll(){
        return map.values();
    } 
    
    public static AreaAtuacaoVO findByCodigo(Integer codigo){
        return (AreaAtuacaoVO)map.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
}
