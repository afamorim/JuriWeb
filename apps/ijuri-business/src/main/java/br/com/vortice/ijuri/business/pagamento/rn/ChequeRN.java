/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.rn;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.pagamento.dao.ChequeDAOIf;
import br.com.vortice.ijuri.core.pagamento.ChequeVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class ChequeRN extends RegraNegocio {
    private ChequeDAOIf dao;
    
    public ChequeRN(){
    }
    
    public ChequeVO insert(ChequeVO cheque) throws AmbienteException,AplicacaoException{
            return dao.insert(cheque);
    }
    
    public void update(ChequeVO cheque) throws AmbienteException,AplicacaoException{
         dao.update(cheque);
    }

	public void setDao(ChequeDAOIf dao) {
		this.dao = dao;
	}
    
}
