package br.com.vortice.ijuri.business.processo.rn;

import java.util.ArrayList;
import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.ProcessoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.TaxaDAOIf;
import br.com.vortice.ijuri.core.processo.FiltroProcessoAgendaVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 * Regra de negocio de processo
 * precisa de controle de transa��o.
 */
public class ProcessoRN extends RegraNegocio {
	
    private ProcessoDAOIf processoDAO;
    private TaxaDAOIf taxaDAO;
    
    public ProcessoRN(){     
    }
    
    public ProcessoVO insert(ProcessoVO processo) 
        throws AmbienteException,AplicacaoException{
 
        processo = processoDAO.insert(processo);
 
        //insere Partes Processo
        processoDAO.insertPartesProcesso(processo);
        
        //insere Taxas Comuns
        taxaDAO.insert(processo.getTaxasComuns(), processo);
        
        //insere Taxas Extras
        taxaDAO.insert(processo.getTaxasExtras(), processo);
            
        return processo;
    }
    
    public void update(ProcessoVO processo)throws AmbienteException,AplicacaoException{
        
        //Atualiza processo
        processoDAO.update(processo);
        
        //Atualiza partes se necess�rio
        ArrayList partesAntiga = (ArrayList)processoDAO.findPartesByProcesso(processo);
        if (!partesAntiga.equals(processo.getPartes())){
            processoDAO.removePartesByProcesso(processo);
            processoDAO.insertPartesProcesso(processo);
        }
        
        //Se as taxas guardadas s�o iguais, n�o � necess�rio cadastrar de novo
        Collection taxasAntigas = taxaDAO.findByProcesso(processo, TipoTaxaVO.findByCodigo(TipoTaxaVO.COMUN));
        if (!taxasAntigas.equals(processo.getTaxasComuns())){
            if (!taxasAntigas.isEmpty())
                taxaDAO.remove(taxasAntigas);
            
            taxaDAO.insert(processo.getTaxasComuns(), processo);
        }
        //Faz-se a mesma coisa p taxas extras
        taxasAntigas = taxaDAO.findByProcesso(processo, TipoTaxaVO.findByCodigo(TipoTaxaVO.EXTRA));
        if (!taxasAntigas.equals(processo.getTaxasExtras())){
            if (!taxasAntigas.isEmpty())
                taxaDAO.remove(taxasAntigas);
            
            taxaDAO.insert(processo.getTaxasExtras(), processo);
        }
    }
    
    public void remove(ProcessoVO processo) throws AmbienteException,AplicacaoException{
        
        taxaDAO.removeByProcesso(processo);
        
        processoDAO.removePartesByProcesso(processo);
        
        processoDAO.remove(processo);
    }
    
    public Collection findByFilter(FiltroProcessoVO filtro) 
        throws AmbienteException,AplicacaoException{
            return processoDAO.findByFilter(filtro);
    }
    
    public ProcessoVO findByPrimaryKey(ProcessoVO processo) throws AmbienteException,AplicacaoException{
        return processoDAO.findByPrimaryKey(processo);
}
    
    public Collection findPartesByProcesso(ProcessoVO processo) 
        throws AmbienteException,AplicacaoException{
            return processoDAO.findPartesByProcesso(processo);
    } 
    
    public Collection findByFilterReport(FiltroProcessoVO filtro) throws AmbienteException, AplicacaoException{
    	return processoDAO.findByFilterReport(filtro);
    }
    
    public Collection findByClienteFilterReport(FiltroProcessoVO filtro) throws AmbienteException, AplicacaoException{
    	return processoDAO.findByClienteFilterReport(filtro);
    }
    
    public Collection findAgenda(FiltroProcessoAgendaVO filtro) throws AmbienteException{
        return processoDAO.findAgenda(filtro);
    }
    
	public void setProcessoDAO(ProcessoDAOIf processoDAO) {
		this.processoDAO = processoDAO;
	}
	
	public void setTaxaDAO(TaxaDAOIf taxaDAO) {
		this.taxaDAO = taxaDAO;
	}
}