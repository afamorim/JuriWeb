/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.TipoOrgaoVO;

import com.vortice.core.exception.AmbienteException;

public interface TipoOrgaoDAOIf {
	
	public TipoOrgaoVO insert(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException;
	
	public void update(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException;
	
	public void remove(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException;
	
	public TipoOrgaoVO findByPrimaryKey(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
}