package br.com.vortice.ijuri.business.site.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.site.dao.ParceiroDAOIf;
import br.com.vortice.ijuri.core.site.ParceiroVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class ParceiroRN extends RegraNegocio {
	
    private ParceiroDAOIf dao;
    
    public ParceiroRN(){
    }
    
    public ParceiroVO insert(ParceiroVO parceiroVO) throws AmbienteException,AplicacaoException{
        return dao.insert(parceiroVO);
    }

    public void remove(ParceiroVO parceiro) throws AmbienteException,AplicacaoException{
        dao.remove(parceiro); 
    }
    
    public void update(ParceiroVO parceiroVO) throws AmbienteException,AplicacaoException{
        dao.update(parceiroVO);
    }
    
    public ParceiroVO findByPrimaryKey(ParceiroVO parceiroVO) throws AmbienteException{
        return dao.findByPrimaryKey(parceiroVO);
    }
    
    public Collection findByFilter(ParceiroVO parceiro) throws AmbienteException{
        return dao.findByFilter(parceiro);
    }
    
    public Collection findAll() throws AmbienteException {
        return dao.findAll();
    }

	public void setDao(ParceiroDAOIf dao) {
		this.dao = dao;
	}
}