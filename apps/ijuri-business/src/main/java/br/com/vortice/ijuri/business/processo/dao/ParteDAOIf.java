/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Fernando
 */
public interface ParteDAOIf {
	
	public void removeByProcesso(ProcessoVO processoVO) throws AmbienteException;
    
    public void insertPartesProcesso(ProcessoVO processoVO) throws AmbienteException;
	
	public ParteVO findByPrimaryKey(ParteVO parteVO) throws AmbienteException;
	
    public Collection findByProcesso(ProcessoVO processo) throws AmbienteException;
}
