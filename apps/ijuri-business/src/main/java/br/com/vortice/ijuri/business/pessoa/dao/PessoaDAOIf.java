/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pessoa.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.jvseguranca.core.Usuario;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface PessoaDAOIf {
	
	public PessoaVO insert(PessoaVO parteVO) throws AmbienteException;
	
	public void update(PessoaVO parteVO) throws AmbienteException;
	
	public void remove(PessoaVO parteVO) throws AmbienteException;
	
	public PessoaVO findByPrimaryKey(PessoaVO parteVO) throws AmbienteException;
	
	public PessoaVO findByUsuario(Usuario usuario) throws AmbienteException;
	
	public Collection findByFilter(PessoaVO parteVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
	
	/**
	 * Metodo responsavel por criar amarrar um usuario a uma pessoa do sistema juridico.
	 * @param pessoa
	 * @throws AmbienteException
	 * @throws AplicacaoException
	 */
	public void criarUsuario(PessoaVO pessoa) throws AmbienteException, AplicacaoException;
}
