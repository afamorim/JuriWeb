/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface ClasseProcessoDAOIf {
	
	public ClasseProcessoVO insert(ClasseProcessoVO classeProcessoVO) throws AmbienteException,AplicacaoException;
	
	public void update(ClasseProcessoVO classeProcessoVO) throws AmbienteException,AplicacaoException;
	
	public void remove(ClasseProcessoVO classeProcessoVO) throws AmbienteException,AplicacaoException;
	
	public ClasseProcessoVO findByPrimaryKey(ClasseProcessoVO classeProcessoVO) throws AmbienteException;
    
    public Collection findByFilter(ClasseProcessoVO classeProcessoVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
}
