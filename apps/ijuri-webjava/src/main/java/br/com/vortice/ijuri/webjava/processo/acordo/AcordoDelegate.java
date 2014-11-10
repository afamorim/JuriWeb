package br.com.vortice.ijuri.webjava.processo.acordo;

import java.util.Collection;

import br.com.vortice.ijuri.business.processo.acordo.rn.AcordoRN;
import br.com.vortice.ijuri.business.processo.acordo.rn.ParcelaAcordoRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;
import br.com.vortice.ijuri.core.processo.acordo.AcordoFiltroAssembler;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoFiltroReportAssembler;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class AcordoDelegate extends BusinessDelegateAB {
	
	private AcordoRN 		acordoRN;
    private ParcelaAcordoRN parcelaAcordoRN;
	
	public AcordoDelegate() throws AmbienteException{

	}
	
	public AcordoVO insert(AcordoVO acordoVO) throws AmbienteException, AplicacaoException {
		return acordoRN.insert(acordoVO);
	}
	
	public void update(AcordoVO acordoVO) throws AmbienteException, AplicacaoException {
		acordoRN.update(acordoVO);
	}
	
	public void remove(AcordoVO acordoVO) throws AmbienteException, AplicacaoException{
		acordoRN.remove(acordoVO);
	}
    
    public void update(ParcelaAcordoVO parcelaAcordoVO) throws AmbienteException, AplicacaoException {
    	System.out.println("parcelaAcordoRN->"+parcelaAcordoRN);
    	parcelaAcordoRN.update(parcelaAcordoVO);
    }
    
    public void remove(ParcelaAcordoVO parcelaAcordoVO) throws AmbienteException, AplicacaoException{
    	parcelaAcordoRN.remove(parcelaAcordoVO);
    }
	
	public AcordoVO findByPrimaryKey(AcordoVO acordoVO) throws AmbienteException, AplicacaoException{
		return acordoRN.findByPrimaryKey(acordoVO);
	}
	
	public Collection findByFilter(AcordoFiltroAssembler filtro) throws AmbienteException, AplicacaoException{
		return acordoRN.findByFilter(filtro);
	}
	
    public Collection findParcelasByAcordo(AcordoVO acordoVO) throws AmbienteException, AplicacaoException{
    	return parcelaAcordoRN.findByAcordo(acordoVO);
    }
    
    public ParcelaAcordoVO findParcelaByPrimaryKey(ParcelaAcordoVO parcelaVO) throws AmbienteException, AplicacaoException{
    	return parcelaAcordoRN.findByPrimaryKey(parcelaVO);
    
    }
    
    public Collection findRelatorioClienteByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) throws AmbienteException {
    	return parcelaAcordoRN.findRelatorioClienteByFilter(filtroAssembler);
    }
    
    public Collection findRelatorioByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) throws AmbienteException {
    	return parcelaAcordoRN.findRelatorioByFilter(filtroAssembler);
    }

	public void setAcordoRN(AcordoRN acordoRN) {
		this.acordoRN = acordoRN;
	}

	public void setParcelaAcordoRN(ParcelaAcordoRN parcelaAcordoRN) {
		this.parcelaAcordoRN = parcelaAcordoRN;
	}
    
}