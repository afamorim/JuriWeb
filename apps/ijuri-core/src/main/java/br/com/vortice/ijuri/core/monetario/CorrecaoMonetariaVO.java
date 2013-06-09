package br.com.vortice.ijuri.core.monetario;

import java.util.Date;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class CorrecaoMonetariaVO extends ValueObject {
	
	private Integer codigo;
	
	private Double valorHistorico;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private Double juros;
	
	private Double multa;
	
	private Double honorarios;
	
	private IndiceReajusteVO indiceReajuste;
	
	private String cliente;
	
	private String devedor;
	
	private String jurosValor;
	
	private String multaValor;
	
	private String honorariosValor;
	
	private Double indiceReajusteValor;
	
	private String valor;

	public String getJurosValor() {
		return jurosValor;
	}

	public void setJurosValor(String jurosValor) {
		this.jurosValor = jurosValor;
	}

	public String getMultaValor() {
		return multaValor;
	}

	public void setMultaValor(String multaValor) {
		this.multaValor = multaValor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Double getHonorarios() {
		return honorarios;
	}

	public void setHonorarios(Double honorarios) {
		this.honorarios = honorarios;
	}

	public IndiceReajusteVO getIndiceReajuste() {
		return indiceReajuste;
	}

	public void setIndiceReajuste(IndiceReajusteVO indiceReajuste) {
		this.indiceReajuste = indiceReajuste;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	public Double getValorHistorico() {
		return valorHistorico;
	}

	public void setValorHistorico(Double valorHistorico) {
		this.valorHistorico = valorHistorico;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDevedor() {
		return devedor;
	}

	public void setDevedor(String devedor) {
		this.devedor = devedor;
	}

	public String getHonorariosValor() {
		return honorariosValor;
	}

	public void setHonorariosValor(String honorariosValor) {
		this.honorariosValor = honorariosValor;
	}

	public Double getIndiceReajusteValor() {
		return indiceReajusteValor;
	}

	public void setIndiceReajusteValor(Double indiceReajusteValor) {
		this.indiceReajusteValor = indiceReajusteValor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}