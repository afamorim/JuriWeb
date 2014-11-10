/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.localidade.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.localidade.EstadoVO;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Fernando
 */
public interface EstadoDAOIf {
	
	public EstadoVO insert(EstadoVO estadoVO) throws AmbienteException;
	
	public void update(EstadoVO estadoVO) throws AmbienteException;
	
	public void remove(EstadoVO estadoVO) throws AmbienteException;
	
	public EstadoVO finByPrimaryKey(EstadoVO estadoVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
}
