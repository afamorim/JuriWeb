/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.acordo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.acordo.AcordoFiltroAssembler;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Amadeu
 */
public interface AcordoDAOIf {
	
	public AcordoVO insert(AcordoVO acordoVO) throws AmbienteException,AplicacaoException;
	
	public void update(AcordoVO acordoVO) throws AmbienteException,AplicacaoException;
	
	public void remove(AcordoVO acordoVO) throws AmbienteException,AplicacaoException;
	
	public AcordoVO findByPrimaryKey(AcordoVO acordoVO) throws AmbienteException;
    
    public Collection findByFilter(AcordoFiltroAssembler acordoFiltro) throws AmbienteException;
		
}
