/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.pagamento.dao.BancoDAOIf;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class BancoRN extends RegraNegocio {
    private BancoDAOIf dao;
    
    public BancoRN(){
    }
    
    public Collection findAll() throws AmbienteException,AplicacaoException{
            return dao.findAll();
    }

	public void setDao(BancoDAOIf dao) {
		this.dao = dao;
	}
    
}
