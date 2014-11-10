/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Fernando
 */
public interface AndamentoDAOIf {
	
	public AndamentoVO insert(AndamentoVO andamentoVO) throws AmbienteException;
	
	public void update(AndamentoVO andamentoVO) throws AmbienteException;
	
	public void remove(AndamentoVO andamentoVO) throws AmbienteException;
	
	public AndamentoVO findByPrimaryKey(AndamentoVO andamentoVO) throws AmbienteException;
	
	public Collection<AndamentoVO> findByProcesso(ProcessoVO vo) throws AmbienteException;
	
	/**
	 * M�todo responsavel por retornar uma cole��o de andamento que estej�o para vencer a partir dos dias de antecedencia passados.
	 * @param diasAntecedencia
	 * @return
	 * @throws AmbienteException
	 */
	public Collection<AndamentoVO> findByDiasAntecedencia(Integer diasAntecedencia) throws AmbienteException;
}