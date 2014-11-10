/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Fernando
 */
public interface OrgaoJudiciarioDAOIf {
	
	public OrgaoJudiciarioVO insert(OrgaoJudiciarioVO  orgaoJudiciarioVO) throws AmbienteException;
	
	public void update(OrgaoJudiciarioVO  orgaoJudiciarioVO) throws AmbienteException;
	
	public void remove(OrgaoJudiciarioVO  orgaoJudiciarioVO) throws AmbienteException,AplicacaoException;
	
	public OrgaoJudiciarioVO findByPrimaryKey(OrgaoJudiciarioVO  orgaoJudiciarioVO) throws AmbienteException;
	
	public Collection findByJuizo(JuizoVO juizo) throws AmbienteException;
    
    public Collection findByFilter(OrgaoJudiciarioVO orgao) throws AmbienteException;
}
