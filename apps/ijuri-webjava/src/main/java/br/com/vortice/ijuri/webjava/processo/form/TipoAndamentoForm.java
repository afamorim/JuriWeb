package br.com.vortice.ijuri.webjava.processo.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

public class TipoAndamentoForm extends BaseActionForm {
	
	private Long codigo;
	
	private String descricao;
	
	private String[] juizoCodigosDireita;
	
	private String[] juizoCodigosEsquerda;
	
	private Integer juizoCodigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    public String[] getJuizoCodigosDireita() {
        return juizoCodigosDireita;
    }

    public void setJuizoCodigosDireita(String[] juizoCodigosDireita) {
        this.juizoCodigosDireita = juizoCodigosDireita;
    }

    public String[] getJuizoCodigosEsquerda() {
        return juizoCodigosEsquerda;
    }

    public void setJuizoCodigosEsquerda(String[] juizoCodigosEsquerda) {
        this.juizoCodigosEsquerda = juizoCodigosEsquerda;
    }

	public Integer getJuizoCodigo() {
		return juizoCodigo;
	}

	public void setJuizoCodigo(Integer juizoCodigo) {
		this.juizoCodigo = juizoCodigo;
	}
	
	public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazio(descricao,"Descri��o do Tipo de Andamento");
		validarVazio(juizoCodigosDireita, "Juizos Selecionados");
        return super.validate(mapping, request);
    }
	
	public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, ProcessoDelegate delegate) throws Exception {
		Collection collNJuizo = null;
		Collection collJuizo = null;
		collNJuizo = delegate.findAllJuizo();
		collJuizo = new ArrayList();
		request.setAttribute("collNJuizo", collNJuizo);
		request.setAttribute("collJuizo", collJuizo);
	}
}
