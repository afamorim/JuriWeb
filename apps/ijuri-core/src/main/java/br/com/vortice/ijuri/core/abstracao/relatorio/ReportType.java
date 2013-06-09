package br.com.vortice.ijuri.core.abstracao.relatorio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.vortice.ijuri.abstracao.ValueObject;

public class ReportType extends ValueObject {
    public static final int REPORT_TYPE_PDF = 1;
    public static final int REPORT_TYPE_HTML = 2;
    public static final int REPORT_TYPE_XLS = 3;
    public static final int REPORT_TYPE_TXT = 4;
    
    private Integer codigo;
    private String descricao;
    
    public static Map tiposMap = new HashMap();
    
    static{
        ReportType type = new ReportType();
        type.codigo = new Integer(REPORT_TYPE_PDF);
        type.descricao = "PDF";
        tiposMap.put(type.codigo, type);
        
        type = new ReportType();
        type.codigo = new Integer(REPORT_TYPE_HTML);
        type.descricao = "HTML";
        tiposMap.put(type.codigo, type);
        
        type = new ReportType();
        type.codigo = new Integer(REPORT_TYPE_XLS);
        type.descricao = "EXCEL";
        tiposMap.put(type.codigo, type);
        
        type = new ReportType();
        type.codigo = new Integer(REPORT_TYPE_TXT);
        type.descricao = "TXT";
        tiposMap.put(type.codigo, type);
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
    
    public static ReportType findByCodigo(Integer codigo){
        return (ReportType)tiposMap.get(codigo);
    }
    
    public static Collection findAll(){
        return tiposMap.values();
    }
}
