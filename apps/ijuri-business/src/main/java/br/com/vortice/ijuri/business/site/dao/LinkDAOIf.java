package br.com.vortice.ijuri.business.site.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.site.LinkVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public interface LinkDAOIf {
	
	public LinkVO insert(LinkVO vo) throws AmbienteException, AplicacaoException;
	
	public void update(LinkVO vo) throws AmbienteException, AplicacaoException;
	
	public void remove(LinkVO vo) throws AmbienteException, AplicacaoException;
	
	public LinkVO findByPrimaryKey(LinkVO vo) throws AmbienteException;
	
	public Collection findByFilter(LinkVO vo) throws AmbienteException;
}