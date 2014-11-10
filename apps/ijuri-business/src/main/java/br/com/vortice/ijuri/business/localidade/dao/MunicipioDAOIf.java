/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.localidade.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface MunicipioDAOIf {
	
	public MunicipioVO insert(MunicipioVO municipioVO) throws AmbienteException;
	
	public void update(MunicipioVO municipioVO) throws AmbienteException;
	
	public void remove(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException;
	
	public MunicipioVO findByPrimaryKey(MunicipioVO municipioVO) throws AmbienteException;
	
	public Collection findByFilter(MunicipioVO municipioVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
	
	public Collection findByUf(EstadoVO estadoVO) throws AmbienteException;
	
	public Collection findNComarcaByUf(EstadoVO estadoVO) throws AmbienteException;
}
