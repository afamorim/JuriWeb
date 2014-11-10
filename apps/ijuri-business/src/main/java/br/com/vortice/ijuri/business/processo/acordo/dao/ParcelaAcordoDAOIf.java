/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.acordo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.business.pagamento.dao.PagamentoDAOIf;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoFiltroReportAssembler;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Amadeu
 */
public interface ParcelaAcordoDAOIf extends PagamentoDAOIf{
	
	public ParcelaAcordoVO findByPrimaryKey(ParcelaAcordoVO parcelaAcordoVO) throws AmbienteException;
    
    public Collection findByAcordo(AcordoVO acordoVO) throws AmbienteException;
    
    public void insert(Collection parcelas, AcordoVO acordoVO) throws AmbienteException;
    
    public void remove(AcordoVO acordoVO) throws AmbienteException,AplicacaoException;
    
    public Collection findRelatorioClienteByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) throws AmbienteException;
    
    public Collection findRelatorioByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) throws AmbienteException;
		
}
