/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.FiltroTipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface TipoAndamentoDAOIf {
	
	public TipoAndamentoVO insert(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException;
	
	public void insertJuizoInTipoAndamento(TipoAndamentoVO tipoAndamentoVO, JuizoVO juizoVO) throws AmbienteException;
	
	public void update(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException;
	
	public void removeAllJuizo(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException;
	
	public void remove(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException,AplicacaoException;
	
	public TipoAndamentoVO findByPrimaryKey(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException;
	
	public Collection findByFilter(FiltroTipoAndamentoVO tipoAndamento) throws AmbienteException;
    
    public Collection findByJuizo(JuizoVO juizoVO) throws AmbienteException;
}