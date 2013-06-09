
package br.com.vortice.ijuri.core.localidade;

import java.lang.String;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class MunicipioVO extends ValueObject {
	
	public MunicipioVO(){}
	
	public MunicipioVO(Integer codigo){
		this.codigo = codigo;
	}
	
	private Integer codigo;
	
    private String nome; 

    public EstadoVO estadoVO;
    
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public EstadoVO getEstadoVO() {
		return estadoVO;
	}
	
	public void setEstadoVO(EstadoVO estadoVO) {
		this.estadoVO = estadoVO;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
 }






