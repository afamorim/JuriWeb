package br.com.vortice.ijuri.business.monetario.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.monetario.PeriodoIndiceFiltroAssembler;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public interface PeriodoIndiceDAOIf {
	
	public PeriodoIndiceVO insert(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException;
	
	public void update(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException;
	
	public void remove(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException;
	
	public PeriodoIndiceVO findByPrimaryKey(PeriodoIndiceVO vo) throws AmbienteException;
	
	public Collection findAll() throws AmbienteException, AplicacaoException;
	
	public Collection findByPeriodo(PeriodoIndiceFiltroAssembler periodo) throws AmbienteException, AplicacaoException;
    
    public void insert(Collection valores) throws AmbienteException, AplicacaoException;
    
    public Collection findByFilter(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException;
    
    public PeriodoIndiceVO findLasPeriodo(PeriodoIndiceVO vo) throws AmbienteException;
}
