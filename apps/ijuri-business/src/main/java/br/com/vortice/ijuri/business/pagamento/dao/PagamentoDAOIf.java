/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.pagamento.PagamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Amadeu
 */
public interface PagamentoDAOIf {
	
	public PagamentoVO insert(PagamentoVO pagamentoVO) throws AmbienteException,AplicacaoException;
	
	public void update(PagamentoVO pagamentoVO) throws AmbienteException,AplicacaoException;
	
	public void remove(PagamentoVO pagamentoVO) throws AmbienteException,AplicacaoException;
	
	public PagamentoVO findByPrimaryKey(PagamentoVO pagamentoVO) throws AmbienteException;
    
    public void insert(Collection pagamentos) throws AmbienteException;
    
    public void remove(Collection pagamentos) throws AmbienteException,AplicacaoException;
		
}
