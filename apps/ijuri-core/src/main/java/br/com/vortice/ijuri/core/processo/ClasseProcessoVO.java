package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class ClasseProcessoVO extends ValueObject{
	
	public ClasseProcessoVO(){}
	
	public ClasseProcessoVO(String des){
		descricao = des;
	}
	
	public ClasseProcessoVO(Long codigo){
		this.codigo = codigo;
	}
	
    private String descricao; 

    private Long codigo; 

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 }






