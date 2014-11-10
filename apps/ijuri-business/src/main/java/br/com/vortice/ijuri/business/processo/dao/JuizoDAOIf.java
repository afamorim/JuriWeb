/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface JuizoDAOIf {
	
	public JuizoVO insert(JuizoVO juizoVO) throws AmbienteException,AplicacaoException;
	
	public void update(JuizoVO juizoVO) throws AmbienteException,AplicacaoException;
	
	public void remove(JuizoVO juizoVO) throws AmbienteException,AplicacaoException;
	
	public JuizoVO findByPrimaryKey(JuizoVO juizoVO) throws AmbienteException;
    
    public Collection findByFilter(JuizoVO juizoVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
	
	public Collection findNJuizoByTipoAndamento(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException;
}
