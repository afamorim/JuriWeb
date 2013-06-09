package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class FiltroTipoAndamentoVO extends ValueObject {
	
	private String descricao;
	
	private JuizoVO juizo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public JuizoVO getJuizo() {
		return juizo;
	}

	public void setJuizo(JuizoVO juizo) {
		this.juizo = juizo;
	}
	
}
