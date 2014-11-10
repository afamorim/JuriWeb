
package br.com.vortice.ijuri.core.pessoa;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.jvseguranca.core.Usuario;

public class PessoaVO extends ValueObject {
	
    public static Integer PESSOA_FISICA = new Integer(1);
	public static Integer PESSOA_JURIDICA = new Integer(2);
	
	public PessoaVO(){}
	
	public PessoaVO(Integer codigo){
		this.codigo = codigo;
	}
    
    private Integer 	apto; 
    private String 		bloco; 
	private Integer 	codigo;
    private String 		nome; 
    private String 		endereco; 
    private String 		cep;
    private String 		telefone;
    private Integer 	tipo;
    private String 		email;
    private Usuario	usuario;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getApto() {
        return apto;
    }

    public void setApto(Integer apto) {
        this.apto = apto;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    
    public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
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
    
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    /**
     * @return Returns the tipo.
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo The tipo to set.
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
 }