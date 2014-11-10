package br.com.vortice.ijuri.webjava.monetario.form;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class IndiceReajusteForm extends BaseActionForm {
	
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
