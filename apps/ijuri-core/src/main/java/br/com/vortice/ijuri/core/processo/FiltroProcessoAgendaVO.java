package br.com.vortice.ijuri.core.processo;

import java.util.Date;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;

public class FiltroProcessoAgendaVO extends ProcessoVO {
	
    private PessoaVO pessoa;
    
    private Date prazoInicial;
    private Date prazoFinal;
    
    private TipoAndamentoVO tipoAndamento;
    
    public PessoaVO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

    public Date getPrazoFinal() {
        return prazoFinal;
    }

    public void setPrazoFinal(Date prazoFinal) {
        this.prazoFinal = prazoFinal;
    }

    public Date getPrazoInicial() {
        return prazoInicial;
    }

    public void setPrazoInicial(Date prazoInicial) {
        this.prazoInicial = prazoInicial;
    }

    public TipoAndamentoVO getTipoAndamento() {
        return tipoAndamento;
    }

    public void setTipoAndamento(TipoAndamentoVO tipoAndamento) {
        this.tipoAndamento = tipoAndamento;
    }

	
}
