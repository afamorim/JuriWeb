package br.com.vortice.ijuri.core.monetario;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class PeriodoIndiceVO extends ValueObject {
	
	public PeriodoIndiceVO(){}
	
	public PeriodoIndiceVO(Integer codigo){
		this.codigo = codigo;
	}
	
	private Integer codigo;
	
	private IndiceReajusteVO indiceReajuste;
	
	private Integer mes;
	
	private Integer ano;
	
	private Float valor;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public IndiceReajusteVO getIndiceReajuste() {
		return indiceReajuste;
	}

	public void setIndiceReajuste(IndiceReajusteVO indiceReajuste) {
		this.indiceReajuste = indiceReajuste;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
}
