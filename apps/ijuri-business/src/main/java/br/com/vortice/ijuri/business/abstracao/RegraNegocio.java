package br.com.vortice.ijuri.business.abstracao;

import br.com.vortice.ijuri.business.abstracao.persistencia.FabricaDAOPostgres;

import br.com.vortice.ijuri.business.abstracao.persistencia.FabricaDAOIf;

/**
 * @author Antonio Amadeu
 */
public abstract class RegraNegocio {
	
	protected FabricaDAOIf fabricaDAO;
	
	public RegraNegocio(){
		this.fabricaDAO = new FabricaDAOPostgres();
	}
	
	public RegraNegocio(FabricaDAOIf fabricaDAONova){
		this.fabricaDAO = fabricaDAONova;
	}
	
	/**
	 * @return Returns the fabricaDAO.
	 */
	public FabricaDAOIf getFabricaDAO() {
		return fabricaDAO;
	}
}	