package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.ComarcaDAOIf;
import br.com.vortice.ijuri.core.processo.ComarcaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.view.MensagensErroIf;

public class ComarcaRN extends RegraNegocio {
	
	private ComarcaDAOIf dao;
	
	public ComarcaRN(){
	}
	
	public ComarcaVO insert(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		if (dao.findByFilter(comarcaVO).size() > 0)
			throw new AplicacaoException(MensagensErroIf.CHAVE_DUPLICADA);
		return dao.insert(comarcaVO);
	}
	
	public void update(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		dao.update(comarcaVO);
	}
	
	public void remove(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		dao.remove(comarcaVO);
	}
	
	public ComarcaVO findByPrimaryKey(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(comarcaVO);
	}
	
	public Collection<ComarcaVO> findByFilter(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(comarcaVO);
	}
	
	public Collection<ComarcaVO> findAll() throws AmbienteException, AplicacaoException{
		return dao.findAll();
	}

	public void setDao(ComarcaDAOIf dao) {
		this.dao = dao;
	}
	
}
