package br.com.vortice.ijuri.business.configuracao.dao;

import java.util.List;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

import br.com.vortice.ijuri.core.configuracao.Configuracao;

public interface ConfiguracaoDAOIf {
	
	/**
	 * M�todo que retorna todas as configura��es do sistema
	 * @param configuracao
	 * @return Collection de configura��es do sistema
	 * @throws AmbienteException
	 */
	public Configuracao findByPrimaryKey(Configuracao configuracao) throws AmbienteException;
	
	/**
	 * Retornar uma configura��o especifica.
	 * @return
	 * @throws AmbienteException
	 */
	public List<Configuracao> findAll() throws AmbienteException;
	
	/**
	 * M�todo responsavel por atualizar um valor de uma especifica��o espec�fica.
	 * @param configuracao
	 * @throws AmbienteException
	 * @throws AplicacaoException
	 */
	public void update(Configuracao configuracao) throws AmbienteException, AplicacaoException;
}