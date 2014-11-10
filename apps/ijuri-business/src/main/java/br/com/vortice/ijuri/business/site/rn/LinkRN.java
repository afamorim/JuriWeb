package br.com.vortice.ijuri.business.site.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.site.dao.LinkDAOIf;
import br.com.vortice.ijuri.core.site.LinkVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class LinkRN extends RegraNegocio {
	
	private LinkDAOIf dao;
	
	public LinkRN(){
	}
	
	public LinkVO insert(LinkVO vo) throws AmbienteException, AplicacaoException{
		return dao.insert(vo);
	}
	
	public void update(LinkVO vo) throws AmbienteException, AplicacaoException{
		dao.update(vo);
	}
	
	public void remove(LinkVO vo) throws AmbienteException, AplicacaoException{
		dao.remove(vo);
	}
	
	public LinkVO findByPrimaryKey(LinkVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(vo);
	}
	
	public Collection findByFilter(LinkVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(vo);
	}

	public void setDao(LinkDAOIf dao) {
		this.dao = dao;
	}
	
}