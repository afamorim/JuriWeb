package br.com.vortice.ijuri.business.monetario.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public interface IndiceReajusteDAOIf {
	
	public IndiceReajusteVO insert(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException;
	
	public void update(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException;
	
	public void remove(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException;
	
	public IndiceReajusteVO findByPrimaryKey(IndiceReajusteVO vo) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
	
	public Collection findByFilter(IndiceReajusteVO vo) throws AmbienteException;
}
