/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.acordo.rn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.processo.acordo.dao.AcordoDAOIf;
import br.com.vortice.ijuri.business.processo.acordo.dao.ParcelaAcordoDAOIf;
import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoFiltroAssembler;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class AcordoRN extends RegraNegocio {
	
    private AcordoDAOIf dao;
    private ParcelaAcordoDAOIf parcelaAcordoDAO; 
    
    public AcordoRN(){
       dao = getFabricaDAO().getAcordoDAO();
       parcelaAcordoDAO = getFabricaDAO().getParcelaAcordoDAO();
    }
    public AcordoVO insert(AcordoVO acordo) throws AmbienteException,AplicacaoException{
    	dao.insert(acordo);
             
		//insere parcelas
		int numParcelas = acordo.getQuantidadeParcelas().intValue();
		float valor = acordo.getValor().floatValue()/numParcelas;
		Date data = acordo.getDataVencimento();
		DateUtil.getInstance(new Locale("pt","BR"));
		ArrayList parcelas = new ArrayList();
		for (int i=0 ;i<numParcelas;i++ )
		{ 
			ParcelaAcordoVO parcela = new ParcelaAcordoVO();
			parcela.setDataVencimento(data);
			parcela.setNumero(new Integer((int)(i+1)));
			parcela.setAcordo(acordo);
			parcela.setStatus(StatusPagamentoVO.findByCodigo(StatusPagamentoVO.EM_ABERTO));
			parcela.setValorVencimento(new Float(valor));
			parcelas.add(parcela);
			data =  DateUtil.getInstance().calcularProximoMes(data);
		}
		parcelaAcordoDAO.insert(parcelas,acordo);
		return acordo;
	}
    
    public void update(AcordoVO acordo) throws AmbienteException,AplicacaoException{
    	dao.update(acordo);
    }
    
    public void remove(AcordoVO acordo) throws AmbienteException,AplicacaoException{
        parcelaAcordoDAO.remove(acordo);
        dao.remove(acordo);
    }
    
    public AcordoVO findByPrimaryKey(AcordoVO acordo) throws AmbienteException,AplicacaoException{
            return dao.findByPrimaryKey(acordo);
    }
    
    public Collection findByFilter(AcordoFiltroAssembler acordo) throws AmbienteException,AplicacaoException{
            return dao.findByFilter(acordo);
    }
    
	public void setDao(AcordoDAOIf dao) {
		this.dao = dao;
	}
	
	public void setParcelaAcordoDAO(ParcelaAcordoDAOIf parcelaAcordoDAO) {
		this.parcelaAcordoDAO = parcelaAcordoDAO;
	}
    
}
