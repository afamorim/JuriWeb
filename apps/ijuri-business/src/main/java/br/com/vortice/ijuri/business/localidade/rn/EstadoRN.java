package br.com.vortice.ijuri.business.localidade.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.localidade.dao.EstadoDAOIf;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class EstadoRN extends RegraNegocio {
	
	private EstadoDAOIf dao;
	
	public EstadoRN(){
	}
	
    public Collection findAll() throws AmbienteException, AplicacaoException{
        return dao.findAll();
    }

	public void setDao(EstadoDAOIf dao) {
		this.dao = dao;
	}
    
}
