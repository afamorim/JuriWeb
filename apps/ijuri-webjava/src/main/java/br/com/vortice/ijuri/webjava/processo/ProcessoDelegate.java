package br.com.vortice.ijuri.webjava.processo;

import java.util.Collection;

import br.com.vortice.ijuri.business.processo.rn.AndamentoRN;
import br.com.vortice.ijuri.business.processo.rn.ClasseProcessoRN;
import br.com.vortice.ijuri.business.processo.rn.ComarcaRN;
import br.com.vortice.ijuri.business.processo.rn.JuizoRN;
import br.com.vortice.ijuri.business.processo.rn.OrgaoJudiciarioRN;
import br.com.vortice.ijuri.business.processo.rn.ProcessoRN;
import br.com.vortice.ijuri.business.processo.rn.TaxaRN;
import br.com.vortice.ijuri.business.processo.rn.TipoAndamentoRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;
import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.core.processo.ComarcaVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoAgendaVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoVO;
import br.com.vortice.ijuri.core.processo.FiltroTipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class ProcessoDelegate extends BusinessDelegateAB {

	private AndamentoRN			andamentoRN;
    private ProcessoRN			processoRN;
    private JuizoRN				juizoRN;
    private OrgaoJudiciarioRN	orgaoJudiciarioRN;
    private ClasseProcessoRN	classeProcessoRN;
	private ComarcaRN			comarcaRN;
	private TipoAndamentoRN		tipoAndamentoRN;
    private TaxaRN				taxaRN;
    
    public ProcessoDelegate() throws AmbienteException{
    }
    
    public ProcessoVO insert(ProcessoVO processoVO) throws AmbienteException, AplicacaoException{
    	return processoRN.insert(processoVO);
    }
    
    public void update(ProcessoVO processoVO) throws AmbienteException, AplicacaoException{
    	processoRN.update(processoVO); 
    }
    
    public void remove(ProcessoVO processoVO) throws AmbienteException, AplicacaoException{
    	processoRN.remove(processoVO);
    }
    
    public ProcessoVO findByPrimaryKey(ProcessoVO processoVO) throws AmbienteException, AplicacaoException{
    	return processoRN.findByPrimaryKey(processoVO);
    }
    
    public Collection findByFilter(FiltroProcessoVO filtroProcessoVO) throws AmbienteException, AplicacaoException{
    	return processoRN.findByFilter(filtroProcessoVO);
      }
    
    public Collection findByFilterReport(FiltroProcessoVO filtro) throws AmbienteException, AplicacaoException{
    	return processoRN.findByFilterReport(filtro);
    }
    
    public Collection findByClienteFilterReport(FiltroProcessoVO filtro) throws AmbienteException, AplicacaoException{
    	return processoRN.findByClienteFilterReport(filtro);
    }
    
    public Collection findPartesByProcesso(ProcessoVO processoVO) throws AmbienteException,AplicacaoException{
    	return processoRN.findPartesByProcesso(processoVO);
    }
    
    public JuizoVO insert(JuizoVO juizoVO) throws AmbienteException, AplicacaoException{
    	return juizoRN.insert(juizoVO);
    }
    
    public void update(JuizoVO juizoVO) throws AmbienteException, AplicacaoException{
    	juizoRN.update(juizoVO);
    }
    public void remove(JuizoVO juizoVO) throws AmbienteException, AplicacaoException{
    	juizoRN.remove(juizoVO);
    }
    
    public Collection findAllJuizo() throws AmbienteException, AplicacaoException{
    	return juizoRN.findAll();
    }
    
    public JuizoVO findJuizoByPrimaryKey(JuizoVO juizo) throws AmbienteException, AplicacaoException{
    	return juizoRN.findByPrimaryKey(juizo);
    }
    
    public Collection findJuizoByFilter(JuizoVO juizo) throws AmbienteException, AplicacaoException{
    	return juizoRN.findByFilter(juizo);
    }
	
	public Collection findNJuizoByTipoAndamento(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException, AplicacaoException{
		return juizoRN.findNJuizoByTipoAndamento(tipoAndamentoVO);
	}
    
    public OrgaoJudiciarioVO insert(OrgaoJudiciarioVO orgaoJudiciario) throws AmbienteException,AplicacaoException{
    	return orgaoJudiciarioRN.insert(orgaoJudiciario);
    }
    
    public void update(OrgaoJudiciarioVO orgaoJudiciario) throws AmbienteException,AplicacaoException{
    	orgaoJudiciarioRN.update(orgaoJudiciario);
    }
    
    public void remove(OrgaoJudiciarioVO orgaoJudiciario) throws AmbienteException,AplicacaoException{
    	orgaoJudiciarioRN.remove(orgaoJudiciario);
    }
    
    public Collection finOrgaoJudiciariodByJuizo(JuizoVO juizo) throws AmbienteException,AplicacaoException{
    	return orgaoJudiciarioRN.findByJuizo(juizo);
    }
    
    public Collection findOrgaoJudiciarioByFilter(OrgaoJudiciarioVO orgaoJudiciario) throws AmbienteException,AplicacaoException{
    	return orgaoJudiciarioRN.findByFilter(orgaoJudiciario);
    }
    
    public OrgaoJudiciarioVO findOrgaoJudiciarioByPrimaryKey(OrgaoJudiciarioVO orgaoJudiciario) throws AmbienteException,AplicacaoException{
    	return orgaoJudiciarioRN.findByPrimaryKey(orgaoJudiciario);
    }
	
	public ComarcaVO insert(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		return comarcaRN.insert(comarcaVO);
	}
	
	public void update(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		comarcaRN.update(comarcaVO);
	}
	
	public void remove(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		comarcaRN.remove(comarcaVO);
	}
	
	public ComarcaVO findByPrimaryKey(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		return comarcaRN.findByPrimaryKey(comarcaVO);
	}
	
	public Collection findByFilter(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		return comarcaRN.findByFilter(comarcaVO);
	}
    
    public ClasseProcessoVO insert(ClasseProcessoVO classeProcessoVO) throws AmbienteException, AplicacaoException{
    	return classeProcessoRN.insert(classeProcessoVO);
    }
    
    public void update(ClasseProcessoVO classeProcessoVO) throws AmbienteException, AplicacaoException{
    	classeProcessoRN.update(classeProcessoVO);
        
    }
    public void remove(ClasseProcessoVO classeProcessoVO) throws AmbienteException, AplicacaoException{
    	classeProcessoRN.remove(classeProcessoVO);
    }
    
    public Collection findAllClasseProcesso() throws AmbienteException, AplicacaoException{
    	return classeProcessoRN.findAll(); 
    }
    
    public ClasseProcessoVO findClasseProcessoByPrimaryKey(ClasseProcessoVO classeProcesso) throws AmbienteException, AplicacaoException{
    	return classeProcessoRN.findByPrimaryKey(classeProcesso);
    }
    
    public Collection findClasseProcessoByFilter(ClasseProcessoVO classeProcesso) throws AmbienteException, AplicacaoException{
    	return classeProcessoRN.findByFilter(classeProcesso); 
    }
	
	public TipoAndamentoVO insert(TipoAndamentoVO tipoAndamento) throws AmbienteException,AplicacaoException{
		return tipoAndamentoRN.insert(tipoAndamento);
	}
	
	public void update(TipoAndamentoVO tipoAndamento) throws AmbienteException,AplicacaoException{
		tipoAndamentoRN.update(tipoAndamento);
	}
	
	public void delete(TipoAndamentoVO tipoAndamento)  throws AmbienteException,AplicacaoException{
		tipoAndamentoRN.delete(tipoAndamento);
	}
	
	public TipoAndamentoVO findByPrimaryKey(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException, AplicacaoException{
		return tipoAndamentoRN.findByPrimaryKey(tipoAndamentoVO);
	}
	
	public Collection findAllTipoAndamento() throws AmbienteException,AplicacaoException{
		return tipoAndamentoRN.findAll();
	}
	
	public Collection findByFilter(FiltroTipoAndamentoVO tipoAndamento) throws AmbienteException,AplicacaoException{
		return tipoAndamentoRN.findByFilter(tipoAndamento);
	}
    
	public Collection findTipoAndamentoByJuizo(JuizoVO vo) throws AmbienteException, AplicacaoException{
		return tipoAndamentoRN.findByJuizo(vo);
	}
	
    public Collection findAllComarca() throws AmbienteException,AplicacaoException{
    	return comarcaRN.findAll(); 
    }
    
    public Collection findOrgaoJudiciarioByJuizo(JuizoVO juizo) throws AmbienteException,AplicacaoException{
    	return orgaoJudiciarioRN.findByJuizo(juizo);
    }
    
	public AndamentoVO insert(AndamentoVO andamento)  throws AmbienteException, AplicacaoException{
		return andamentoRN.insert(andamento);
	}
	
	public void update(AndamentoVO andamento) throws AmbienteException, AplicacaoException{
		andamentoRN.update(andamento);
	}
    
    public void delete(AndamentoVO andamento) throws AmbienteException, AplicacaoException{
    	andamentoRN.delete(andamento);
    }
    
	public AndamentoVO findByPrimaryKey(AndamentoVO andamentoVO)throws AmbienteException, AplicacaoException{
		return andamentoRN.findByPrimaryKey(andamentoVO);
	}
	
    
	public Collection findAndamentosByProcesso(ProcessoVO processoVO) throws AmbienteException, AplicacaoException{
		return andamentoRN.findByProcesso(processoVO);
	}
    
    public TaxaVO findTaxaByPrimaryKey(TaxaVO taxaVO) throws AmbienteException, AplicacaoException{
    	return taxaRN.findByPrimaryKey(taxaVO);
    }
    
    public Collection findTaxasByProcesso(ProcessoVO processoVO,TipoTaxaVO tipo) throws AmbienteException, AplicacaoException{
    	return taxaRN.findByProcesso(processoVO,tipo);
    }
    
    public Collection findAgenda(FiltroProcessoAgendaVO filtro) throws AmbienteException{
    	return processoRN.findAgenda(filtro);
    }

	public void setAndamentoRN(AndamentoRN andamentoRN) {
		this.andamentoRN = andamentoRN;
	}

	public void setClasseProcessoRN(ClasseProcessoRN classeProcessoRN) {
		this.classeProcessoRN = classeProcessoRN;
	}

	public void setComarcaRN(ComarcaRN comarcaRN) {
		this.comarcaRN = comarcaRN;
	}

	public void setJuizoRN(JuizoRN juizoRN) {
		this.juizoRN = juizoRN;
	}

	public void setOrgaoJudiciarioRN(OrgaoJudiciarioRN orgaoJudiciarioRN) {
		this.orgaoJudiciarioRN = orgaoJudiciarioRN;
	}

	public void setProcessoRN(ProcessoRN processoRN) {
		this.processoRN = processoRN;
	}

	public void setTaxaRN(TaxaRN taxaRN) {
		this.taxaRN = taxaRN;
	}

	public void setTipoAndamentoRN(TipoAndamentoRN tipoAndamentoRN) {
		this.tipoAndamentoRN = tipoAndamentoRN;
	}
}