package br.com.vortice.ijuri.business.localidade.rn;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.localidade.dao.MunicipioDAOIf;
import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class MunicipioRN extends RegraNegocio {
	
	private MunicipioDAOIf dao;
	private static final Logger LOG = Logger.getLogger(MunicipioRN.class);
	
	public MunicipioRN(){
	}
	
	public MunicipioVO insert(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException {
		return dao.insert(municipioVO);
	}
	
	public void update(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException {
		dao.update(municipioVO);
	}
	
	public void remove(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException {
		dao.remove(municipioVO);
	}
	
	public MunicipioVO findByPrimaryKey(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(municipioVO);
	}
	
	public Collection findByFilter(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(municipioVO);
	}
	
    public Collection findByUF(EstadoVO uf) throws AmbienteException, AplicacaoException{
    	Collection<MunicipioVO> collMunicipio = dao.findByUf(uf);
    	LOG.debug("collMunicipio " + collMunicipio.size());
        return collMunicipio;
    }
	
	public Collection findNComarcaByUf(EstadoVO estadoVO) throws AmbienteException, AplicacaoException{
		return dao.findNComarcaByUf(estadoVO);
	}

	public void setDao(MunicipioDAOIf dao) {
		this.dao = dao;
	}
}
