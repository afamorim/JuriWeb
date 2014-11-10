package br.com.vortice.ijuri.webjava.processo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.TipoParteVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class ParteProcessoForm extends BaseActionForm {
    private String position;
    private Integer cliente;
    private Integer pessoaCodigo;
    private Integer tipoParteCodigo;
    private Long processoCodigo;
    private String nome;
    private String pessoaBloco;
    private String pessoaApto;
    
    
     public String getPessoaApto() {
		return pessoaApto;
	}
	public void setPessoaApto(String pessoaApto) {
		this.pessoaApto = pessoaApto;
	}
	public String getPessoaBloco() {
		return pessoaBloco;
	}
	public void setPessoaBloco(String pessoaBloco) {
		this.pessoaBloco = pessoaBloco;
	}
	/**
     * @return Returns the position.
     */
    public String getPosition() {
        return position;
    }
    /**
     * @param position The position to set.
     */
    public void setPosition(String position) {
        this.position = position;
    }
    /**
     * @return Returns the pessoaCodigo.
     */
    public Integer getPessoaCodigo() {
        return pessoaCodigo;
    }
    /**
     * @param pessoaCodigo The pessoaCodigo to set.
     */
    public void setPessoaCodigo(Integer pessoaCodigo) {
        this.pessoaCodigo = pessoaCodigo;
    }
    /**
     * @return Returns the processoCodigo.
     */
    public Long getProcessoCodigo() {
        return processoCodigo;
    }
    /**
     * @param processoCodigo The processoCodigo to set.
     */
    public void setProcessoCodigo(Long processoCodigo) {
        this.processoCodigo = processoCodigo;
    }
    /**
     * @return Returns the tipoParteCodigo.
     */
    public Integer getTipoParteCodigo() {
        return tipoParteCodigo;
    }
    /**
     * @param tipoParteCodigo The tipoParteCodigo to set.
     */
    public void setTipoParteCodigo(Integer tipoParteCodigo) {
        this.tipoParteCodigo = tipoParteCodigo;
    }
    
    public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazioNumerico(pessoaCodigo,"Pessoa");
        validarVazioNumerico(tipoParteCodigo,"Tipo da parte");
        return super.validate(mapping, request);
    }
    /**
     * @return Returns the nome.
     */
    public String getNome() {
        return nome;
    }
    /**
     * @param nome The nome to set.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return Returns the cliente.
     */
    public Integer getCliente() {
        return cliente;
    }
    /**
     * @param cliente The cliente to set.
     */
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    protected void carregarComponentes(ActionMapping mapping, HttpServletRequest request) throws Exception {
        request.setAttribute("tipos", TipoParteVO.findAll());
        
    }
  
}
