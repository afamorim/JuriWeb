/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.FiltroProcessoAgendaVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoVO;
import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface ProcessoDAOIf {
	
	public ProcessoVO insert(ProcessoVO processoVO) throws AmbienteException,AplicacaoException;
	
	public void update(ProcessoVO processoVO) throws AmbienteException,AplicacaoException;
	
	public void remove(ProcessoVO processoVO) throws AmbienteException,AplicacaoException;
	
	public ProcessoVO findByPrimaryKey(ProcessoVO processoVO) throws AmbienteException;
	
	public Collection findByFilter(FiltroProcessoVO processo) throws AmbienteException;
    
    public void removePartesByProcesso(ProcessoVO processoVO) throws AmbienteException;
    
    public void insertPartesProcesso(ProcessoVO processoVO) throws AmbienteException;
    
    public ParteVO findParteByPrimaryKey(ParteVO parteVO) throws AmbienteException;
    
    public Collection findPartesByProcesso(ProcessoVO processo) throws AmbienteException;
    
    public Collection findByFilterReport(FiltroProcessoVO filtro) throws AmbienteException;
    
    public Collection findByClienteFilterReport(FiltroProcessoVO filtro) throws AmbienteException;
    
    public Collection findAgenda(FiltroProcessoAgendaVO filtro) throws AmbienteException;
}
