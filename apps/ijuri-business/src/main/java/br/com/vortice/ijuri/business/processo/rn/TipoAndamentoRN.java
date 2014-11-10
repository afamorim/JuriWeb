/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.rn;

import java.util.Collection;
import java.util.Iterator;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.dao.TipoAndamentoDAOIf;
import br.com.vortice.ijuri.core.processo.FiltroTipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.view.MensagensErroIf;

/**
 * @author amadeu
 * Regra de negocio de tipo Andamento
 * Precisa de controle de transação.
 */
public class TipoAndamentoRN extends RegraNegocio {
    private TipoAndamentoDAOIf dao;
    
    public TipoAndamentoRN(){
    }
    
	private void insertJuizo(TipoAndamentoVO tipoAndamento) throws AmbienteException, AplicacaoException{
		if (tipoAndamento.getCollJuizos().size() > 0) {
			Iterator iterator = tipoAndamento.getCollJuizos().iterator();
			while (iterator.hasNext()){
				JuizoVO juizoVO = (JuizoVO)iterator.next();
				dao.insertJuizoInTipoAndamento(tipoAndamento, juizoVO);
			}
		}
	}
	
    public TipoAndamentoVO insert(TipoAndamentoVO tipoAndamento) throws AmbienteException,AplicacaoException{
		tipoAndamento = dao.insert(tipoAndamento);
		this.insertJuizo(tipoAndamento);
		return tipoAndamento;
	}

    public void update(TipoAndamentoVO tipoAndamento) throws AmbienteException,AplicacaoException{
	    dao.removeAllJuizo(tipoAndamento);
        this.insertJuizo(tipoAndamento);
        
        dao.update(tipoAndamento);
    }
    
    public void delete(TipoAndamentoVO tipoAndamento)  throws AmbienteException,AplicacaoException{
		validarTipoAndamento(tipoAndamento);
        
        dao.removeAllJuizo(tipoAndamento);
        
		dao.remove(tipoAndamento);
    }
	
	public TipoAndamentoVO findByPrimaryKey(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(tipoAndamentoVO);
	}
    
    public Collection findAll() throws AmbienteException,AplicacaoException{
		return dao.findAll();
    }
    
    public Collection findByFilter(FiltroTipoAndamentoVO tipoAndamento) throws AmbienteException,AplicacaoException{
            return dao.findByFilter(tipoAndamento);
    }
    
    public Collection findByJuizo(JuizoVO juizo) throws AmbienteException{
        return dao.findByJuizo(juizo);
    }
    
    public void validarTipoAndamento(TipoAndamentoVO tipoAndamento) throws AplicacaoException{
        if (TipoAndamentoVO.PAGAMENTO_PARCELA.equals(tipoAndamento.getCodigo())){
            throw new AplicacaoException("error.aplicacao.UPDATE_PARCELA_QUITADA");
        }
    }

	public void setDao(TipoAndamentoDAOIf dao) {
		this.dao = dao;
	}
    
}
