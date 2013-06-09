package br.com.vortice.ijuri.core.seguranca;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;

public class UsuarioVO extends ValueObject {

    private Long codigo; 

    private String login; 

    private String senha;
	
	private PessoaVO pessoa;

    private TipoUsuarioVO tipoUsuario; 

    private PessoaVO pessoaVO; 

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PessoaVO getPessoaVO() {
        return pessoaVO;
    }

    public void setPessoaVO(PessoaVO pessoaVO) {
        this.pessoaVO = pessoaVO;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public TipoUsuarioVO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioVO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public PessoaVO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}
	
 }






