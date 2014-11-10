/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.ComarcaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface ComarcaDAOIf {
	
	public ComarcaVO insert(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException;
	
	public void update(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException;
	
	public void remove(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException;
	
	public ComarcaVO findByPrimaryKey(ComarcaVO comarcaVO) throws AmbienteException;
	
	public Collection<ComarcaVO> findByFilter(ComarcaVO comarcaVO) throws AmbienteException;
	
	public Collection<ComarcaVO> findAll() throws AmbienteException;
}
