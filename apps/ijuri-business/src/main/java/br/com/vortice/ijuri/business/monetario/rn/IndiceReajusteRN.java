package br.com.vortice.ijuri.business.monetario.rn;

import java.util.Collection;
import java.util.Iterator;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.monetario.dao.IndiceReajusteDAOIf;
import br.com.vortice.ijuri.business.monetario.dao.PeriodoIndiceDAOIf;
import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceFiltroAssembler;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class IndiceReajusteRN extends RegraNegocio {
	
	private IndiceReajusteDAOIf dao;
	private PeriodoIndiceDAOIf periodoIndiceDAO;
	
	public IndiceReajusteRN(){
	}
	
	public IndiceReajusteVO insert(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		return dao.insert(vo);
	}
	
	public void update(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		dao.update(vo);
	}
	public void remove(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		dao.remove(vo);
	}
	
	public IndiceReajusteVO findByPrimaryKey(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(vo);
	}
	
	public Collection findAll() throws AmbienteException, AplicacaoException{
		return dao.findAll();
	}
	
	public Collection findByFilter(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(vo);
	}
	
	public Float findValorAcumladoByPeriodo(PeriodoIndiceFiltroAssembler periodo) 
        throws AmbienteException, AplicacaoException {
            
            //se a varia��o de per�odo deve ser de no m�nimo um m�s para o ac�mulo 
            if (periodo.getAnoPeriodoIndiceFim().equals(periodo.getAnoPeriodoIndiceInicio()) 
               && periodo.getMesPeriodoIndiceFim().equals(periodo.getMesPeriodoIndiceInicio()))
                    return new Float(1.0f); 
                     
            Collection variacoes = periodoIndiceDAO.findByPeriodo(periodo);
            
            float valorAcumulado = 1.0f;
            float valorAtual = 0.0f;
            for (Iterator iter = variacoes.iterator(); iter.hasNext();) {
                PeriodoIndiceVO variacao = (PeriodoIndiceVO) iter.next();
                valorAtual = (variacao.getValor().floatValue()*0.01f)+1;
                valorAcumulado *= valorAtual;
            }
            
            return new Float(valorAcumulado);
    }

	public void setDao(IndiceReajusteDAOIf dao) {
		this.dao = dao;
	}

	public void setPeriodoIndiceDAO(PeriodoIndiceDAOIf periodoIndiceDAO) {
		this.periodoIndiceDAO = periodoIndiceDAO;
	}
	
}
