package br.com.vortice.ijuri.webjava.processo.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.StatusProcessoVO;
import br.com.vortice.ijuri.core.processo.TurnoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.action.ParteProcessoListAC;

/**
 * @author amadeu
 */
public class ProcessoForm extends BaseActionForm {
    private Long codigo;
    private String numero;
    private String dataDistribuicao;
    private String dataAutuacao;
    private Long orgaoJudiciarioCodigo;
    private Long classeProcessoCodigo;
    private Integer juizoCodigo;
    private Integer statusCodigo;
    private String honorario;
    private String valorCausa;
    private Long comarcaCodigo; 
    private Integer turnoCodigo;
    private Integer pessoaCodigo;
    private String pessoaNome;
    private String observacao;
    
    //Taxas
    private String taxasComunsCode;
    private String taxasExtrasCode;
    
    //Frame
    private Integer devedorCodigo;
    private String devedorNome;
    private Integer clienteCodigo;
    private String clienteNome;
   
    //Apartamento
    private String apto;
    private String bloco;
    
    //
    private Integer interno;
    
    //Tela
    private String urlVoltar;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazio(numero,"N�mero do Processo");
        validarVazioNumerico(orgaoJudiciarioCodigo,"�rg�o Judici�rio");
        validarVazioNumerico(classeProcessoCodigo, "Classe do Processo");
        validarVazioNumerico(statusCodigo, "Status do Processo");
        validarVazioNumerico(comarcaCodigo, "Comarca");
        validarVazioNumeroFloat(valorCausa, "Valor da Causa");
        
        validarData(dataAutuacao,"Autua��o");
        validarData(dataDistribuicao,"Distribui��o");
        
        return super.validate(mapping, request);
    }

    public Long getClasseProcessoCodigo() {
        return classeProcessoCodigo;
    }

    public void setClasseProcessoCodigo(Long classeProcessoCodigo) {
        this.classeProcessoCodigo = classeProcessoCodigo;
    }

    public String getDataAutuacao() {
        return dataAutuacao;
    }
 
    public void setDataAutuacao(String dataAutuacao) {
        this.dataAutuacao = dataAutuacao;
    }

    public String getDataDistribuicao() {
        return dataDistribuicao;
    }

    public void setDataDistribuicao(String dataDistribuicao) {
        this.dataDistribuicao = dataDistribuicao;
    }
    
    public Integer getJuizoCodigo() {
        return juizoCodigo;
    }

    public void setJuizoCodigo(Integer juizoCodigo) {
        this.juizoCodigo = juizoCodigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getOrgaoJudiciarioCodigo() {
        return orgaoJudiciarioCodigo;
    }

    public void setOrgaoJudiciarioCodigo(Long orgaoJudicicarioCodigo) {
        this.orgaoJudiciarioCodigo = orgaoJudicicarioCodigo;
    }

    public Integer getStatusCodigo() {
        return statusCodigo;
    }

    public void setStatusCodigo(Integer status) {
        this.statusCodigo = status;
    }

    public Long getComarcaCodigo() {
        return comarcaCodigo;
    }

    public void setComarcaCodigo(Long comarcaCodigo) {
        this.comarcaCodigo = comarcaCodigo;
    }

    public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, WebApplicationContext webAppContext) throws Exception {
    	ProcessoDelegate delegate = (ProcessoDelegate) webAppContext.getBean("processoDelegate");
        Collection classesProcesso = delegate.findAllClasseProcesso();
        request.setAttribute("classesProcesso", classesProcesso);
        
        Collection juizos = delegate.findAllJuizo();
        request.setAttribute("juizos", juizos);
        
        Collection comarcas = delegate.findAllComarca();
        request.setAttribute("comarcas", comarcas);
        
        request.setAttribute("situacoes",StatusProcessoVO.findAll());
        
        request.setAttribute("turnos",TurnoVO.findAll());
        
        Collection orgaos = new ArrayList();
        if (getJuizoCodigo()!=null && 
                getJuizoCodigo().intValue()>0){
            orgaos = delegate.findOrgaoJudiciarioByJuizo(new JuizoVO(getJuizoCodigo()));
        }
        request.setAttribute("orgaos", orgaos);
        
        // Mantem nome de Sess�o das partes
        String nomeSessao = request.getParameter(ParteProcessoListAC.NOME_ATRIBUTO_SESSAO);
        request.setAttribute(ParteProcessoListAC.NOME_ATRIBUTO_SESSAO, nomeSessao);
        
        // Mantem as taxas na sess�o 
        if (getTaxasComunsCode()!=null && getTaxasComunsCode().length()>0) 
            request.getSession().setAttribute("TAXAS_COMUNS",getTaxasComunsCode());
        
        if (getTaxasExtrasCode()!=null && getTaxasExtrasCode().length()>0) 
            request.getSession().setAttribute("TAXAS_EXTRAS",getTaxasExtrasCode());
    }

    public String getHonorario() {
        return honorario;
    }

    public void setHonorario(String honorario) {
        this.honorario = honorario;
    }

    public String getValorCausa() {
        return valorCausa;
    }

    public void setValorCausa(String valorCausa) {
        this.valorCausa = valorCausa;
    }

    public Integer getTurnoCodigo() {
        return turnoCodigo;
    }

    public void setTurnoCodigo(Integer turnoCodigo) {
        this.turnoCodigo = turnoCodigo;
    }

    public Integer getPessoaCodigo() {
        return pessoaCodigo;
    }

    public void setPessoaCodigo(Integer pessoaCodigo) {
        this.pessoaCodigo = pessoaCodigo;
    }
 
    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public Integer getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(Integer clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public Integer getDevedorCodigo() {
        return devedorCodigo;
    }

    public void setDevedorCodigo(Integer devedorCodigo) {
        this.devedorCodigo = devedorCodigo;
    }

    public String getDevedorNome() {
        return devedorNome;
    }

    public void setDevedorNome(String devedorNome) {
        this.devedorNome = devedorNome;
    }

	public Integer getInterno() {
		return interno;
	}

	public void setInterno(Integer interno) {
		this.interno = interno;
	}

    /**
     * @return Returns the observacao.
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao The observacao to set.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return Returns the taxasComunsCode.
     */
    public String getTaxasComunsCode() {
        return taxasComunsCode;
    }

    /**
     * @param taxasComunsCode The taxasComunsCode to set.
     */
    public void setTaxasComunsCode(String taxasComunsCode) {
        this.taxasComunsCode = taxasComunsCode;
    }

    /**
     * @return Returns the taxasExtrasCode.
     */
    public String getTaxasExtrasCode() {
        return taxasExtrasCode;
    }

    /**
     * @param taxasExtrasCode The taxasExtrasCode to set.
     */
    public void setTaxasExtrasCode(String taxasExtrasCode) {
        this.taxasExtrasCode = taxasExtrasCode;
    }

    /**
     * @return Returns the urlVoltar.
     */
    public String getUrlVoltar() {
        return urlVoltar;
    }

    /**
     * @param urlVoltar The urlVoltar to set.
     */
    public void setUrlVoltar(String urlVoltar) {
        this.urlVoltar = urlVoltar;
    }

	public String getApto() {
		return apto;
	}

	public void setApto(String apto) {
		this.apto = apto;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}    
}
