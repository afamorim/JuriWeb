package br.com.vortice.ijuri.webjava.site.form;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class PropostaOnLineForm extends BaseActionForm{
	
	private String nomeCondominio;
	
	private String cnpjCpf;
	
	private String numeroUnidades;
	
	private String numeroEmpregados;
	
	private String valorTaxaCondominio;
	
	private String nomeSolicitante;
	
	private String cargo;
	
	private String telefone;
	
	private String email;
	
	private String observacao;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getNumeroUnidades() {
		return numeroUnidades;
	}

	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getValorTaxaCondominio() {
		return valorTaxaCondominio;
	}

	public void setValorTaxaCondominio(String valorTaxaCondominio) {
		this.valorTaxaCondominio = valorTaxaCondominio;
	}

	public String getNumeroEmpregados() {
		return numeroEmpregados;
	}

	public void setNumeroEmpregados(String numeroEmpregados) {
		this.numeroEmpregados = numeroEmpregados;
	}
	
}
