package br.com.vortice.ijuri.webjava.monetario.form;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class PeriodoIndiceReajusteForm extends BaseActionForm {
	
	private Integer codigo;
	
	private String mes;
	
	private String ano;
	
	private String valor;
	
	private Integer indiceReajusteCodigo;

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getIndiceReajusteCodigo() {
		return indiceReajusteCodigo;
	}

	public void setIndiceReajusteCodigo(Integer indiceReajusteCodigo) {
		this.indiceReajusteCodigo = indiceReajusteCodigo;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}