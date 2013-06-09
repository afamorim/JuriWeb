package br.com.vortice.ijuri.core.localidade;

import java.lang.String;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class EstadoVO extends ValueObject{
	
	public EstadoVO(){
		
	}
	
	public EstadoVO(Integer codigo){
		this.codigo = codigo;
	}
	
	private Integer codigo;
	
    private String sigla; 

    private String nome; 
    
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
 }






