package br.com.vortice.ijuri.webjava.localidade;

import java.util.Collection;

import br.com.vortice.ijuri.business.localidade.rn.EstadoRN;
import br.com.vortice.ijuri.business.localidade.rn.MunicipioRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;
import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class LocalidadeDelegate extends BusinessDelegateAB {
	
	private MunicipioRN municipioRN;
	private EstadoRN 	estadoRN;
	
	public LocalidadeDelegate() throws AmbienteException{
	}
	
	public MunicipioVO insert(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException {
		return municipioRN.insert(municipioVO);
	}
	
	public void update(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException {
		municipioRN.update(municipioVO);
	}
	
	public void remove(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException{
		municipioRN.remove(municipioVO);
	}
	
	public MunicipioVO findByPrimaryKey(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException{
		return municipioRN.findByPrimaryKey(municipioVO);
	}
	
	public Collection findByFilter(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException{
		return municipioRN.findByFilter(municipioVO);
	}
	
	public Collection findMunicipioByUF(EstadoVO uf) throws AmbienteException, AplicacaoException{
		return municipioRN.findByUF(uf);
	}
	
	public Collection findAllEstado() throws AmbienteException, AplicacaoException{
		return estadoRN.findAll();
	}
	
	public Collection findMunicipioNComarcaByUf(EstadoVO estadoVO) throws AmbienteException, AplicacaoException{
		return municipioRN.findNComarcaByUf(estadoVO);
	}

	public void setEstadoRN(EstadoRN estadoRN) {
		this.estadoRN = estadoRN;
	}

	public void setMunicipioRN(MunicipioRN municipioRN) {
		this.municipioRN = municipioRN;
	}
	
}