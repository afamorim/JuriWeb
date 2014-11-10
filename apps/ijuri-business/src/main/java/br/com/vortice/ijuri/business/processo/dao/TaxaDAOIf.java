/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Amadeu
 */
public interface TaxaDAOIf {
	
	public TaxaVO insert(TaxaVO taxaVO) throws AmbienteException,AplicacaoException;
	
	public void update(TaxaVO taxaVO) throws AmbienteException,AplicacaoException;
	
	public void remove(TaxaVO taxaVO) throws AmbienteException,AplicacaoException;
    
    public void removeByProcesso(ProcessoVO processoVO) throws AmbienteException,AplicacaoException;
    
    public void remove(Collection taxas) throws AmbienteException,AplicacaoException;
	
	public TaxaVO findByPrimaryKey(TaxaVO taxaVO) throws AmbienteException;
    
    public Collection findByProcesso(ProcessoVO processoVO,TipoTaxaVO tipo) throws AmbienteException;
    
    public void insert(Collection taxas,ProcessoVO processo) throws AmbienteException;
		
}
