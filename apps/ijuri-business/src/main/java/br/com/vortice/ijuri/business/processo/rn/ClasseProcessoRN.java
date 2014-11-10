/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.ClasseProcessoDAOIf;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class ClasseProcessoRN extends RegraNegocio {
    private ClasseProcessoDAOIf dao;
    
    public ClasseProcessoRN(){
    }
    
    public ClasseProcessoVO insert(ClasseProcessoVO classeProcesso) 
        throws AmbienteException,AplicacaoException{
             return dao.insert(classeProcesso);   
    }
    
    public void update(ClasseProcessoVO classeProcesso) 
        throws AmbienteException,AplicacaoException{
            dao.update(classeProcesso);
    }
    
    public void remove(ClasseProcessoVO classeProcesso) 
        throws AmbienteException,AplicacaoException{
            dao.remove(classeProcesso);
    }
    
    public Collection findAll() 
        throws AmbienteException,AplicacaoException{
            return dao.findAll();
    }
    
    public ClasseProcessoVO findByPrimaryKey(ClasseProcessoVO classeProcesso) 
       throws AmbienteException,AplicacaoException{
            return dao.findByPrimaryKey(classeProcesso);
}
    
    public Collection findByFilter(ClasseProcessoVO classeProcesso) 
        throws AmbienteException,AplicacaoException{
            return dao.findByFilter(classeProcesso);
    }

	public void setDao(ClasseProcessoDAOIf dao) {
		this.dao = dao;
	}
    
}
