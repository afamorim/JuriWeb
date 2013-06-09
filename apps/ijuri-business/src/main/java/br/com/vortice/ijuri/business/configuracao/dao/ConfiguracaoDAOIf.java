package br.com.vortice.ijuri.business.configuracao.dao;

import java.util.List;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

import br.com.vortice.ijuri.core.configuracao.Configuracao;

public interface ConfiguracaoDAOIf {
	
	/**
	 * Método que retorna todas as configurações do sistema
	 * @param configuracao
	 * @return Collection de configurações do sistema
	 * @throws AmbienteException
	 */
	public Configuracao findByPrimaryKey(Configuracao configuracao) throws AmbienteException;
	
	/**
	 * Retornar uma configuração especifica.
	 * @return
	 * @throws AmbienteException
	 */
	public List<Configuracao> findAll() throws AmbienteException;
	
	/**
	 * Método responsavel por atualizar um valor de uma especificação específica.
	 * @param configuracao
	 * @throws AmbienteException
	 * @throws AplicacaoException
	 */
	public void update(Configuracao configuracao) throws AmbienteException, AplicacaoException;
}