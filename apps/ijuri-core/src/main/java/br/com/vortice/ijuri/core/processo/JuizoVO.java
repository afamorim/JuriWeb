package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class JuizoVO extends ValueObject {
	
    public static Integer EXTRA_JUDICIAL = new Integer(6); 
    
	public JuizoVO(){}
	
	public JuizoVO(Integer codigo){
		this.codigo = codigo;
	}
	
    private Integer codigo; 
    
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 } 






