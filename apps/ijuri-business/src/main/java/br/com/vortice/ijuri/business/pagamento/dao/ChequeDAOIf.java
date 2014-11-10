/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.dao;

import br.com.vortice.ijuri.core.pagamento.ChequeVO;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Amadeu
 */
public interface ChequeDAOIf {
	
		
	public ChequeVO insert(ChequeVO cheque) throws AmbienteException;
    
    public void update(ChequeVO cheque) throws AmbienteException;
		
}
