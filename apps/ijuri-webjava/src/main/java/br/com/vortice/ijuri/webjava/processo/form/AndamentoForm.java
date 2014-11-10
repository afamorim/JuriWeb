package br.com.vortice.ijuri.webjava.processo.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

public class AndamentoForm extends BaseActionForm {
	
	private Long codigo;
	
	private Long processoCodigo;
	
	private Long tipoAndamentoCodigo;
	
	private String observacao;
	
	private String dataLancamento;
	
	private String dataHoraPrazo;
	
	private Integer interno;
    
    private Integer juizoCodigo;

	public Integer getInterno() {
		return interno;
	}

	public void setInterno(Integer interno) {
		this.interno = interno;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDataHoraPrazo() {
		return dataHoraPrazo;
	}

	public void setDataHoraPrazo(String dtHoraPrazo) {
		this.dataHoraPrazo = dtHoraPrazo;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dtLancamento) {
		this.dataLancamento = dtLancamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getTipoAndamentoCodigo() {
		return tipoAndamentoCodigo;
	}

	public void setTipoAndamentoCodigo(Long tipoAndamentoCodigo) {
		this.tipoAndamentoCodigo = tipoAndamentoCodigo;
	}

	public Long getProcessoCodigo() {
		return processoCodigo;
	}

	public void setProcessoCodigo(Long processoCodigo) {
		this.processoCodigo = processoCodigo;
	}

    public void carregarComponentes(ActionMapping mapping, 
    		HttpServletRequest request, 
    		WebApplicationContext webAppContext) throws Exception {
        
    	ProcessoDelegate delegate = (ProcessoDelegate) webAppContext.getBean("processoDelegate");
    	Collection tipos = delegate.findTipoAndamentoByJuizo(new JuizoVO(this.juizoCodigo));
        request.setAttribute("collTipoAndamento", tipos);
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        validarVazioNumerico(tipoAndamentoCodigo, "Tipo do Andamento");
        validarVazio(dataLancamento,"Data do Lan�amento");
        //validarVazio(dataHoraPrazo,"Prazo");
                     
        validarData(dataLancamento, "Lan�amento");
        validarDataHora(dataHoraPrazo, "Prazo");
        
        return super.validate(mapping, request);
    }

    public Integer getJuizoCodigo() {
        return juizoCodigo;
    }

    public void setJuizoCodigo(Integer juizoCodigo) {
        this.juizoCodigo = juizoCodigo;
    }
}
