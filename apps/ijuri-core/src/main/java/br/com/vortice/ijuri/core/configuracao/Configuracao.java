package br.com.vortice.ijuri.core.configuracao;

public enum Configuracao {
	ANDAMENTO_SCHEDULE("ANDAMENTO_SCHEDULE");
	
	private String sigla;
	private String valor;
	private String descricao;
	
	Configuracao(String sigla){
		this.sigla = sigla;
	}
	
	public static Configuracao getFromSigla(String sigla){
		if (ANDAMENTO_SCHEDULE.sigla.equals(sigla)){
			return ANDAMENTO_SCHEDULE;
		}
		return null;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}