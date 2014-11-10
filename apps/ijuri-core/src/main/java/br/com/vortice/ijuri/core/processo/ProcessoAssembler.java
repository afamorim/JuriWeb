package br.com.vortice.ijuri.core.processo;

import java.sql.Timestamp;
import java.util.Collection;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class ProcessoAssembler extends ValueObject {
	
	private Integer codigo;
	
	private String numero;
	
	private OrgaoJudiciarioVO orgaoJudiciario;
	
	private ClasseProcessoVO classeProcesso;
	
	private String partes;
	
	private String apts;
	
	private String blocos;
	
	private AndamentoVO andamento;
    
    private String andamentoAtual;
	
	private String tipoAndamento;
	
	private Timestamp prazo;
	
	private String nomeCliente;
    
    private Float valorCausa;
	
	private Collection taxasComuns; // of type TaxaVO
	
	private Collection taxasExtras; // of type TaxaVO

	public AndamentoVO getAndamento() {
		return andamento;
	}

	public void setAndamento(AndamentoVO andamentoAtual) {
		this.andamento = andamentoAtual;
	}

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

	public String getPartes() {
		return partes;
	}

	public void setPartes(String partes) {
		this.partes = partes;
	}

	public Timestamp getPrazo() {
		return prazo;
	}

	public void setPrazo(Timestamp prazo) {
		this.prazo = prazo;
	}

	public String getTipoAndamento() {
		return tipoAndamento;
	}

	public void setTipoAndamento(String tipoAndamento) {
		this.tipoAndamento = tipoAndamento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Collection getTaxasComuns() {
		return taxasComuns;
	}

	public void setTaxasComuns(Collection taxasComuns) {
		this.taxasComuns = taxasComuns;
	}
	
	public Collection getTaxasExtras() {
		return taxasExtras;
	}

	public void setTaxasExtras(Collection taxasExtras) {
		this.taxasExtras = taxasExtras;
	}

	public String getApts() {
		return apts;
	}

	public void setApts(String apts) {
		this.apts = apts;
	}

	public String getBlocos() {
		return blocos;
	}

	public void setBlocos(String blocos) {
		this.blocos = blocos;
	}

	/**
     * Retorna as taxas comuns separadas por vírgula
     * @return 
     */
    public String getTaxasComunsDescricao(){
    	if (taxasComuns != null){
	        return TaxaVO.taxasToString(taxasComuns);
    	}else{
    		return null;
    	}
    }
    
    /**
     * Retorna as taxas extras separadas por vírgula
     * @return 
     */
    public String getTaxasExtrasDescricao(){
    	if (taxasExtras != null){
            return TaxaVO.taxasToString(taxasExtras);
    	}else return null;
    }

    public Float getValorCausa() {
        return valorCausa;
    }

    public void setValorCausa(Float valorCausa) {
        this.valorCausa = valorCausa;
    }

    public String getAndamentoAtual() {
        return andamentoAtual;
    }

    public void setAndamentoAtual(String andamentoAtual) {
        this.andamentoAtual = andamentoAtual;
    }
    
}
