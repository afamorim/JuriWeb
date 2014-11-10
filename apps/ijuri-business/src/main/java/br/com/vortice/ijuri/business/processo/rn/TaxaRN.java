/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.TaxaDAOIf;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class TaxaRN extends RegraNegocio {
    private TaxaDAOIf dao;
    
    public TaxaRN(){
    }
    public TaxaVO insert(TaxaVO taxa) 
        throws AmbienteException,AplicacaoException{
             return dao.insert(taxa);   
    }
    
    public void update(TaxaVO taxa) 
        throws AmbienteException,AplicacaoException{
            dao.update(taxa);
    }
    
    public void remove(TaxaVO taxa) 
        throws AmbienteException,AplicacaoException{
            dao.remove(taxa);
    }
    
    public TaxaVO findByPrimaryKey(TaxaVO taxa) 
       throws AmbienteException,AplicacaoException{
            return dao.findByPrimaryKey(taxa);
}
    
    public Collection findByProcesso(ProcessoVO processo,TipoTaxaVO tipo) 
        throws AmbienteException,AplicacaoException{
            return dao.findByProcesso(processo,tipo);
    }
	public void setDao(TaxaDAOIf dao) {
		this.dao = dao;
	}
    
}
