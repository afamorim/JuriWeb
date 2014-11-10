/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.dao;

import java.util.Collection;

import com.vortice.core.exception.AmbienteException;

/**
 * @author Antonio Amadeu
 */
public interface BancoDAOIf {
	
		
	public Collection findAll() throws AmbienteException;
		
}
