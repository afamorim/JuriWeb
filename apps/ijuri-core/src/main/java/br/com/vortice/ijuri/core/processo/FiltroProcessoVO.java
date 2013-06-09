package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;

public class FiltroProcessoVO extends ProcessoVO {
	
    private PessoaVO pessoa;
    
    private Boolean interno;
    
    public PessoaVO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

	public Boolean getInterno() {
		return interno;
	}

	public void setInterno(Boolean interno) {
		this.interno = interno;
	}
}
