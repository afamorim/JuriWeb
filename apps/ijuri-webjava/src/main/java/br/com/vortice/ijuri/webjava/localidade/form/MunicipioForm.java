package br.com.vortice.ijuri.webjava.localidade.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;

public class MunicipioForm extends BaseActionForm {
	
	private Integer codigo;
	
	private Integer estadoCodigo;
	
	private String nome;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getEstadoCodigo() {
		return estadoCodigo;
	}

	public void setEstadoCodigo(Integer estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	 public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
		 validarVazio(nome, "Nome do Municipio");
		 validarVazioNumerico(estadoCodigo, "Estado");
		 return super.validate(mapping, request);
	 }
	 
	 public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, LocalidadeDelegate delegate) throws Exception {
		 Collection collEstado = delegate.findAllEstado();
		 request.setAttribute("collEstado", collEstado);
	 }
}
