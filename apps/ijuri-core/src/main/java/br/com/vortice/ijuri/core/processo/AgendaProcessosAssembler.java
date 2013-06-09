package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class AgendaProcessosAssembler extends ValueObject {
	
	private Integer codigo;
	
	private String numero;
	
	private OrgaoJudiciarioVO orgaoJudiciario;
	
	private ClasseProcessoVO classeProcesso;
	
	private String reus;
    
    private String autores;
    
    private AndamentoVO andamento;
    
    private String advogadoResponsavel;
	
	public ClasseProcessoVO getClasseProcesso() {
		return classeProcesso;
	}

	public void setClasseProcesso(ClasseProcessoVO classeProcesso) {
		this.classeProcesso = classeProcesso;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public OrgaoJudiciarioVO getOrgaoJudiciario() {
		return orgaoJudiciario;
	}

	public void setOrgaoJudiciario(OrgaoJudiciarioVO orgaoJudiciario) {
		this.orgaoJudiciario = orgaoJudiciario;
	}

    public AndamentoVO getAndamento() {
        return andamento;
    }

    public void setAndamento(AndamentoVO andamento) {
        this.andamento = andamento;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getReus() {
        return reus;
    }

    public void setReus(String reus) {
        this.reus = reus;
    }

    public String getAdvogadoResponsavel() {
        return advogadoResponsavel;
    }

    public void setAdvogadoResponsavel(String advogadoResponsavel) {
        this.advogadoResponsavel = advogadoResponsavel;
    }
	
}
