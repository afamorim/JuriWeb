/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.JuizoDAOIf;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class JuizoRN extends RegraNegocio {
    private JuizoDAOIf dao;
    
    public JuizoRN(){
    }
    public JuizoVO insert(JuizoVO juizo) 
        throws AmbienteException,AplicacaoException{
             return dao.insert(juizo);   
    }
    
    public void update(JuizoVO juizo) 
        throws AmbienteException,AplicacaoException{
            dao.update(juizo);
    }
    
    public void remove(JuizoVO juizo) 
        throws AmbienteException,AplicacaoException{
            dao.remove(juizo);
    }
    
    public Collection findAll() 
        throws AmbienteException,AplicacaoException{
            return dao.findAll();
    }
    
    public JuizoVO findByPrimaryKey(JuizoVO juizo) 
       throws AmbienteException,AplicacaoException{
            return dao.findByPrimaryKey(juizo);
}
    
    public Collection findByFilter(JuizoVO juizo) 
        throws AmbienteException,AplicacaoException{
            return dao.findByFilter(juizo);
    }
    
	public Collection findNJuizoByTipoAndamento(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException, AplicacaoException{
		return dao.findNJuizoByTipoAndamento(tipoAndamentoVO);
	}
	
	public void setDao(JuizoDAOIf dao) {
		this.dao = dao;
	}
	
}