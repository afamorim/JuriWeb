package br.com.vortice.ijuri.business.monetario.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.monetario.dao.PeriodoIndiceDAOIf;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * Classe de regra de negocio do Periodo do Indice
 * Precisa de controle de transa��o
 * @author Fernando
 *
 */
public class PeriodoIndiceRN extends RegraNegocio {
	
	private PeriodoIndiceDAOIf dao;
	
	public PeriodoIndiceRN(){
	}
	
	public PeriodoIndiceVO insert(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		return dao.insert(vo);
	}
    
    public void insert(Collection variacoes) throws AmbienteException, AplicacaoException{
        dao.insert(variacoes);
    }
	
	public void update(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		dao.update(vo);
	}
	
	public void remove(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		dao.remove(vo);
	}
	
	public PeriodoIndiceVO findByPrimaryKey(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(vo);
	}
	
	public Collection findAll() throws AmbienteException, AplicacaoException{
		return dao.findAll();
	}
	
	/*public Collection findByPeriodo(PeriodoIndiceFiltroAssembler periodo) throws AmbienteException, AplicacaoException {
        return dao.findByPeriodo(periodo);
    }*/
	
	public Collection findByFilter(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(vo);
	}
	
	public PeriodoIndiceVO findLasPeriodo(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		return dao.findLasPeriodo(vo);
	}

	public void setDao(PeriodoIndiceDAOIf dao) {
		this.dao = dao;
	}
	
}
