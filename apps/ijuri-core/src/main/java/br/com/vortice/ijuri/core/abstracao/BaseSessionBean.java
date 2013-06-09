/*
 * Created on 13/03/2005
 */
package br.com.vortice.ijuri.core.abstracao;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;

/**
 * @author Antonio Amadeu
 */
public class BaseSessionBean implements SessionBean{
	
	/**
	 * Contexto da sessão.
	 */
	protected SessionContext sessionContext;

	/**
	* Padrão de um EJB SessionBean.
	*/
	public void ejbCreate() {
	}

	/**
	* Padrão de um EJB SessionBean.
	*/
	public void ejbActivate() {}

  	/**
   	* Padrão de um EJB SessionBean.
   	*/
  	public void ejbPassivate() {}

  	/**
   	* Padrão de um EJB SessionBean.
   	*/
	public void ejbRemove() {}

  	/**
  	* Padrão de um EJB SessionBean.
  	*/
  	public void setSessionContext(SessionContext sessionContext) {
  		this.sessionContext = sessionContext;
  	}
  	
    /**
     * M�todo para tratar excess�o de insert-update-delete
     * 
     * @param excessao
     * @return
     */
    
  	protected void tratarExcessaoFacade(Exception excessao) throws AplicacaoException,AmbienteException{
  		
        sessionContext.setRollbackOnly();
        
        if (excessao instanceof AplicacaoException){
            throw (AplicacaoException)excessao;
        }else if (excessao instanceof AmbienteException){
            throw (AmbienteException)excessao;
        }else{
            throw new AmbienteException("Erro n�o tratado:", excessao);
        }
        
        
    }
  	
}
