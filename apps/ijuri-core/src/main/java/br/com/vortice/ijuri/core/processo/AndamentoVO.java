
package br.com.vortice.ijuri.core.processo;

import java.lang.Long;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Date;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class AndamentoVO extends ValueObject {
	
	public AndamentoVO(){}
	
	public AndamentoVO(Long codigo){
		this.codigo = codigo;
	}

	private Date 			dataLancamento; 
    private Timestamp		dataHoraPrazo; 
    private Boolean			interno; 
    private String			observacao; 
    private Long			codigo; 
    private TipoAndamentoVO	tipoAndamento;
	private ProcessoVO 		processo;
	private Boolean			alarma;
	private Integer			diasAntecedencia;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Timestamp getDataHoraPrazo() {
        return dataHoraPrazo;
    }

    public void setDataHoraPrazo(Timestamp dataHoraPrazo) {
        this.dataHoraPrazo = dataHoraPrazo;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoAndamentoVO getTipoAndamento() {
        return tipoAndamento;
    }

    public void setTipoAndamento(TipoAndamentoVO tipoAndamentoVO) {
        this.tipoAndamento = tipoAndamentoVO;
    }

	public ProcessoVO getProcesso() {
		return processo;
	}

	public void setProcesso(ProcessoVO processo) {
		this.processo = processo;
	}

	public Boolean getInterno() {
		return interno;
	}

	public void setInterno(Boolean isInterno) {
		this.interno = isInterno;
	}
    
    public String getInternoString(){
        if (interno.booleanValue())
            return "Sim";
        else
            return "Não";
    }
    
    public String getDescricaoCompleta(){
        String descricao = null;
        
        if (this.tipoAndamento != null){
            StringBuffer buffer = new StringBuffer();
            
            buffer.append(this.tipoAndamento.getDescricao());
            
            if (this.observacao != null && this.observacao.trim().length() > 0){
                buffer.append(" - ");
                buffer.append(this.observacao);
            }
            
            descricao = buffer.toString();
        }
        
        return descricao;
    }

	public Boolean getAlarma() {
		return alarma;
	}

	public void setAlarma(Boolean alarma) {
		this.alarma = alarma;
	}

	public Integer getDiasAntecedencia() {
		return diasAntecedencia;
	}

	public void setDiasAntecedencia(Integer diasAntecedencia) {
		this.diasAntecedencia = diasAntecedencia;
	}
	
}