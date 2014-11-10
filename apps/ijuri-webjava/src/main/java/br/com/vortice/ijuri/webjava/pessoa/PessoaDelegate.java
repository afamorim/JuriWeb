/*
 * Created on 03/05/2005
 */
package br.com.vortice.ijuri.webjava.pessoa;

import java.util.Collection;

import br.com.vortice.ijuri.business.pessoa.rn.PessoaRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.jvseguranca.core.Usuario;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author afamorim
 */
public class PessoaDelegate extends BusinessDelegateAB {
	
	private PessoaRN pessoaRN;
	
	public PessoaDelegate() throws AmbienteException{
	}
	
	public PessoaVO insert(PessoaVO pessoaVO) throws AmbienteException, AplicacaoException{
		return pessoaRN.insert(pessoaVO);
	}

    
    public void update(PessoaVO pessoaVO) throws AmbienteException, AplicacaoException{
    	pessoaRN.update(pessoaVO);
    }
    
    public void delete(PessoaVO pessoaVO) throws AmbienteException, AplicacaoException{
    	pessoaRN.delete(pessoaVO);
    }
	
    public PessoaVO findByUsuario(Usuario usuario) throws AmbienteException, AplicacaoException{
    	return pessoaRN.findByUsuario(usuario);
    }
    
	public PessoaVO findByPrimaryKey(PessoaVO pessoaVO)throws AmbienteException, AplicacaoException{
		return pessoaRN.findByPrimaryKey(pessoaVO);
	}
	
	public Collection findByFilter(PessoaVO pessoaVO) throws AmbienteException, AplicacaoException{
		return pessoaRN.findByFilter(pessoaVO);
	}

	public void setPessoaRN(PessoaRN pessoaRN) {
		this.pessoaRN = pessoaRN;
	}
	
}