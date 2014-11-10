package br.com.vortice.ijuri.webjava.site.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class ParceiroForm extends BaseActionForm {
	
	private Integer codigo;
	private String nome;
	private String resumoServico;
    
    
    public String getResumoServico() {
        return resumoServico;
    }
    public void setResumoServico(String resumoServico) {
        this.resumoServico = resumoServico;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        validarVazioNumerico(this.codigo, "Pessoa");
        validarVazio(this.resumoServico, "Resumo do Servi�o");
        
        return super.validate(mapping, request);
    }
   

}
