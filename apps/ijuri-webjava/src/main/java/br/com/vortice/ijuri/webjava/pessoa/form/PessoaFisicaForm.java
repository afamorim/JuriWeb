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
public class PessoaFisicaForm extends BaseActionForm {
	
	private Integer codigo;
    private String 	nome; 
    private String 	endereco; 
    private String 	cep;
    private String 	telefone;
    private String 	apto; 
    private String 	bloco; 
    private String 	cpf;
    private String 	email;
    private Integer	usuarioCodigo;
    private Integer	perfilCodigo;
    private String	senha;
    private String	rsenha;
    
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
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazio(nome,"Nome da Pessoa"); 
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

	public Integer getUsuarioCodigo() {
		return usuarioCodigo;
	}

	public void setUsuarioCodigo(Integer usuarioCodigo) {
		this.usuarioCodigo = usuarioCodigo;
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

	public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, WebApplicationContext webAppContext) throws Exception {
		PerfilRN perfilRN = (PerfilRN)webAppContext.getBean("perfilRN");
		request.setAttribute("perfis", perfilRN.findAll());
	}
}