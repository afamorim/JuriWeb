/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.OrgaoJudiciarioDAOIf;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class OrgaoJudiciarioRN extends RegraNegocio {
	
    private OrgaoJudiciarioDAOIf dao;
    
    public OrgaoJudiciarioRN(){
    }
    
    public OrgaoJudiciarioVO insert(OrgaoJudiciarioVO orgaoJudiciario) 
        throws AmbienteException,AplicacaoException{
            return dao.insert(orgaoJudiciario);
    }
    
    public void update(OrgaoJudiciarioVO orgaoJudiciario) 
        throws AmbienteException,AplicacaoException{
            dao.update(orgaoJudiciario);
    }
    
    public void remove(OrgaoJudiciarioVO orgaoJudiciario) 
        throws AmbienteException,AplicacaoException{
            dao.remove(orgaoJudiciario);
    }
    
    public Collection findByJuizo(JuizoVO juizo) 
        throws AmbienteException,AplicacaoException{
            return dao.findByJuizo(juizo);
    }
    
    public Collection findByFilter(OrgaoJudiciarioVO orgaoJudiciario) 
        throws AmbienteException,AplicacaoException{
            return dao.findByFilter(orgaoJudiciario);
    }
    
    public OrgaoJudiciarioVO findByPrimaryKey(OrgaoJudiciarioVO orgaoJudiciario) 
        throws AmbienteException,AplicacaoException{
            return dao.findByPrimaryKey(orgaoJudiciario);
    }

	public void setDao(OrgaoJudiciarioDAOIf dao) {
		this.dao = dao;
	}
    
}
