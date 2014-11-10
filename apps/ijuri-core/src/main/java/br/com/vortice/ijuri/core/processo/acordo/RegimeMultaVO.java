package br.com.vortice.ijuri.core.processo.acordo;

import java.util.Collection;
import java.util.HashMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class RegimeMultaVO extends ValueObject {
    private Integer codigo;
    private String descricao;
    
    private static HashMap map = new HashMap();
    
    public static Integer MULTA =  new Integer(1);
    public static Integer CLAUSULA_PENAL =  new Integer(2);
    
    static{
        RegimeMultaVO regimeMulta = new RegimeMultaVO();
        regimeMulta.setCodigo(new Integer(1));
        regimeMulta.setDescricao("Multa (%)");
        map.put(regimeMulta.getCodigo(), regimeMulta);
        
        regimeMulta = new RegimeMultaVO();
        regimeMulta.setCodigo(new Integer(2));
        regimeMulta.setDescricao("Cláusula Penal por Mês (%)");
        map.put(regimeMulta.getCodigo(), regimeMulta);
        
        
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
    
    public static RegimeMultaVO findByCodigo(Integer codigo){
        return (RegimeMultaVO)map.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
}
