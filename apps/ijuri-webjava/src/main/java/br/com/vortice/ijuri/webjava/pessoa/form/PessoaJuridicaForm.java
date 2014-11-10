/*
 * Created on 03/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.jvseguranca.business.PerfilRN;

/**
 * @author afamorim
 */
public class PessoaJuridicaForm extends BaseActionForm {
	
	private Integer codigo;
	private String	nome;
	private String	cep;
	private String	cnpj;
	private String	endereco;
	private String	contato;
	private String	telefone;
    private String	email;
    private Integer perfilCodigo;
    private String 	senha;
    private String 	rsenha;
    private Integer	usuarioCodigo;
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getContato() {
		return contato;
	}
	
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazio(nome,"Nome da Empresa"); 
        return super.validate(mapping, request);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Integer getPerfilCodigo() {
		return perfilCodigo;
	}

	public void setPerfilCodigo(Integer perfilCodigo) {
		this.perfilCodigo = perfilCodigo;
	}
    
	public String getRsenha() {
		return rsenha;
	}

	public void setRsenha(String rsenha) {
		this.rsenha = rsenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getUsuarioCodigo() {
		return usuarioCodigo;
	}

	public void setUsuarioCodigo(Integer usuarioCodigo) {
		this.usuarioCodigo = usuarioCodigo;
	}

	public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, WebApplicationContext webAppContext) throws Exception {
		PerfilRN perfilRN = (PerfilRN)webAppContext.getBean("perfilRN");
		request.setAttribute("perfis", perfilRN.findAll());
	}
}