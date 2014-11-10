package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.configuracao.dao.ConfiguracaoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.AndamentoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.ProcessoDAOIf;
import br.com.vortice.ijuri.core.configuracao.Configuracao;
import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class AndamentoRN extends RegraNegocio {
	
    private AndamentoDAOIf 		andamentoDAO;
    private ConfiguracaoDAOIf	configuracaoDAO;
    private String				templatePath;
    private ProcessoDAOIf		processoDAO;
    
    public AndamentoRN(){
    }
    
    public AndamentoVO insert(AndamentoVO andamento)  throws AmbienteException,AplicacaoException{
		return andamentoDAO.insert(andamento);
    }
    
    public void update(AndamentoVO andamento) throws AmbienteException,AplicacaoException{
		andamentoDAO.update(andamento);
    }
    
    public void delete(AndamentoVO andamento) throws AmbienteException,AplicacaoException{
		andamentoDAO.remove(andamento);
    }
	
	public AndamentoVO findByPrimaryKey(AndamentoVO andamentoVO)throws AmbienteException, AplicacaoException{
		return andamentoDAO.findByPrimaryKey(andamentoVO);
	}
    
    public Collection<AndamentoVO> findByProcesso(ProcessoVO processo) throws AmbienteException,AplicacaoException{
		return andamentoDAO.findByProcesso(processo);
    }
    
    public void informaVencimentoPrazo() throws AmbienteException, AplicacaoException{
    	Configuracao configuracao = configuracaoDAO.findByPrimaryKey(Configuracao.ANDAMENTO_SCHEDULE);
    	Collection<AndamentoVO> andamentos = andamentoDAO.findByDiasAntecedencia(new Integer(configuracao.getValor()));
    	if (andamentos != null && andamentos.size() > 0)
    	{
    		for (AndamentoVO andamento : andamentos){
    			andamento.getProcesso().setPartes(processoDAO.findPartesByProcesso(andamento.getProcesso()));
    		}
    	}
    }
    
	public void setAndamentoDAO(AndamentoDAOIf dao) {
		this.andamentoDAO = dao;
	}

	public void setConfiguracaoDAO(ConfiguracaoDAOIf configuracaoDAO) {
		this.configuracaoDAO = configuracaoDAO;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public void setProcessoDAO(ProcessoDAOIf processoDAO) {
		this.processoDAO = processoDAO;
	}
	
}