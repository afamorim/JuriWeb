package br.com.vortice.ijuri.core.processo;

import java.util.Collection;
import java.util.HashMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class TurnoVO extends ValueObject {
    private Integer codigo;
    private String descricao;
    
    private static HashMap tiposMap = new HashMap();
    
    static{
        TurnoVO turno = new TurnoVO();
        turno.setCodigo(new Integer(2));
        turno.setDescricao("Matutino");
        tiposMap.put(turno.getCodigo(), turno);
        
        turno = new TurnoVO();
        turno.setCodigo(new Integer(1));
        turno.setDescricao("Noturno");
        tiposMap.put(turno.getCodigo(), turno);
        
        turno = new TurnoVO();
        turno.setCodigo(new Integer(3));
        turno.setDescricao("Vespertino");
        tiposMap.put(turno.getCodigo(), turno);
        
        
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
        return tiposMap.values();
    } 
    
    public static TurnoVO findByCodigo(Integer codigo){
        return (TurnoVO)tiposMap.get(codigo);
    }
    
    public static String findDescricaoByCodigo(Integer codigo){
        return findByCodigo(codigo).getDescricao();
    }
}
