package br.com.vortice.ijuri.core.monetario;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class IndiceReajusteVO extends ValueObject {
	
	public IndiceReajusteVO(){}
	
	public IndiceReajusteVO(Integer codigo){
		this.codigo = codigo;
	}
	
	private Integer codigo;
	
	private String nome;
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
