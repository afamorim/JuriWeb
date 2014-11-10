package br.com.vortice.ijuri.webjava.monetario;

import java.util.Collection;

import br.com.vortice.ijuri.business.monetario.rn.CorrecaoMonetariaRN;
import br.com.vortice.ijuri.business.monetario.rn.IndiceReajusteRN;
import br.com.vortice.ijuri.business.monetario.rn.PeriodoIndiceRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;
import br.com.vortice.ijuri.core.monetario.CorrecaoMonetariaVO;
import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceFiltroAssembler;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class CorrecaoMonetariaDelegate extends BusinessDelegateAB {
	
    private IndiceReajusteRN	indiceReajusteRN;
    private PeriodoIndiceRN		periodoIndiceRN;
    private CorrecaoMonetariaRN	correcaoMonetariaRN;
    
    public CorrecaoMonetariaDelegate() throws AmbienteException{
    }
    
    public IndiceReajusteVO insert(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
    	return indiceReajusteRN.insert(vo);
	}
	
    public void update(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
    	indiceReajusteRN.update(vo);
	}
	
    public void remove(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
    	indiceReajusteRN.remove(vo);
	}
	
	public IndiceReajusteVO findByPrimaryKey(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		return indiceReajusteRN.findByPrimaryKey(vo);
	}
	
	public Collection findAllIndiceReajuste() throws AmbienteException, AplicacaoException{
		return indiceReajusteRN.findAll();
	}
    
    public Float findValorAcumladoIndiceByPeriodo(PeriodoIndiceFiltroAssembler periodo) throws AmbienteException, AplicacaoException{
    	return indiceReajusteRN.findValorAcumladoByPeriodo(periodo);
    }
	
    public Collection findByFilter(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
    	return indiceReajusteRN.findByFilter(vo);
	}
    
    public PeriodoIndiceVO insert(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
    	return periodoIndiceRN.insert(vo);
	}
	
    public void update(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
    	periodoIndiceRN.update(vo);
	}
	
    public void remove(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
    	periodoIndiceRN.remove(vo);
	}
	
    public PeriodoIndiceVO findByPrimaryKey(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
    	return periodoIndiceRN.findByPrimaryKey(vo);
	}
	
	public Collection findAllPeriodoIndice() throws AmbienteException, AplicacaoException{
		return periodoIndiceRN.findAll();
	}
	
	public Collection findByFilter(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
		return periodoIndiceRN.findByFilter(vo);
	}
    
    public void insertVaricoesIndice(Collection variacoes) throws AmbienteException, AplicacaoException{
    	periodoIndiceRN.insert(variacoes);   
    }
    
    public CorrecaoMonetariaVO calcularCorrecao(CorrecaoMonetariaVO vo) throws AmbienteException, AplicacaoException{
    	return correcaoMonetariaRN.calcularCorrecao(vo);
    }
    
    public PeriodoIndiceVO findLasPeriodo(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException{
    	return periodoIndiceRN.findLasPeriodo(vo);
    }

	public void setCorrecaoMonetariaRN(CorrecaoMonetariaRN correcaoMonetariaRN) {
		this.correcaoMonetariaRN = correcaoMonetariaRN;
	}

	public void setIndiceReajusteRN(IndiceReajusteRN indiceReajusteRN) {
		this.indiceReajusteRN = indiceReajusteRN;
	}

	public void setPeriodoIndiceRN(PeriodoIndiceRN periodoIndiceRN) {
		this.periodoIndiceRN = periodoIndiceRN;
	}
    
}