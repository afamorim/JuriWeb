package br.com.vortice.ijuri.webjava.processo.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;

public class ComarcaForm extends BaseActionForm {
	
	private Integer codigo;
	
	private Integer estadoCodigo;
	
	private Integer municipioCodigo;

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

	public Integer getMunicipioCodigo() {
		return municipioCodigo;
	}

	public void setMunicipioCodigo(Integer municipioCodigo) {
		this.municipioCodigo = municipioCodigo;
	}
	
	public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
		validarVazioNumerico(estadoCodigo, "Estado");
		validarVazioNumerico(municipioCodigo, "Municipio");
		return super.validate(mapping, request);
	}
	
	public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, WebApplicationContext webAppContext) throws Exception {
		LocalidadeDelegate localidadeDelegate = (LocalidadeDelegate) webAppContext.getBean("localidadeDelegate");
		Collection collEstado = localidadeDelegate.findAllEstado();
		Collection collMunicipio = null;
		if (this.estadoCodigo == null || this.estadoCodigo.intValue() == 0){
			collMunicipio = new ArrayList();
		}else{
			collMunicipio = localidadeDelegate.findMunicipioByUF(new EstadoVO(this.estadoCodigo));
		}
		request.setAttribute("collEstado", collEstado);
		request.setAttribute("collMunicipio", collMunicipio);
	}
}
