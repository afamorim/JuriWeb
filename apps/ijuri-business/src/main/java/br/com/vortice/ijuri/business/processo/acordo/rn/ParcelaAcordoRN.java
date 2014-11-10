/*
 * Created on 13/04/2005
 */
package br.com.vortice.ijuri.business.processo.acordo.rn;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.pagamento.dao.ChequeDAOIf;
import br.com.vortice.ijuri.business.processo.acordo.dao.ParcelaAcordoDAOIf;
import br.com.vortice.ijuri.business.processo.dao.AndamentoDAOIf;
import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.pagamento.ChequeVO;
import br.com.vortice.ijuri.core.pagamento.FormaPagamentoVO;
import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;
import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoFiltroReportAssembler;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author amadeu
 */
public class ParcelaAcordoRN extends RegraNegocio {
    
	private ParcelaAcordoDAOIf	dao;
    private ChequeDAOIf			chequeDAO;
    private AndamentoDAOIf		andamentoDAO;
    
    public ParcelaAcordoRN(){
       dao = getFabricaDAO().getParcelaAcordoDAO();
       chequeDAO = getFabricaDAO().getChequeDAO();
       andamentoDAO = getFabricaDAO().getAndamentoDAO();
    }
    
    public void update(ParcelaAcordoVO parcelaAcordo) throws AmbienteException,AplicacaoException{
        System.out.print("UPDATE - RN - 1");
    	if (parcelaAcordo.getFormaPagamento()!=null && 
                parcelaAcordo.getFormaPagamento().getCodigo().equals(FormaPagamentoVO.CHEQUE))
        {
            ChequeVO cheque = parcelaAcordo.getCheque();
            if (cheque.getCodigo() != null && cheque.getCodigo().intValue() > 0){
                chequeDAO.update(cheque);
            }else{
                cheque = chequeDAO.insert(cheque);
                parcelaAcordo.setCheque(cheque);
            }
        }
    	System.out.print("UPDATE - RN - 2");
        dao.update(parcelaAcordo);
        if ( (parcelaAcordo.getGerarAndamento()!=null && parcelaAcordo.getGerarAndamento().booleanValue()) && parcelaAcordo.isPago())
        {
            //Atualiza o andamento do processo
            AndamentoVO andamento = new AndamentoVO();
            andamento.setDataLancamento(new Date());
            andamento.setInterno(Boolean.FALSE);
            
            TipoAndamentoVO tipoAndamento = new TipoAndamentoVO();
            tipoAndamento.setCodigo(TipoAndamentoVO.PAGAMENTO_PARCELA);
            andamento.setTipoAndamento(tipoAndamento);
            
            NumberFormat formatter  = DecimalFormat.getCurrencyInstance(new Locale("pt","BR"));
            StringBuffer obs = new StringBuffer();
            obs.append("O pagamento no valor de ").append(formatter.format(parcelaAcordo.getValorPago().doubleValue()))
               .append("  da parcela de n� ").append(parcelaAcordo.getNumero())
               .append(" foi efetuado no dia ")
               .append(DateUtil.getInstance(new Locale("pt","BR")).convertDateToString(parcelaAcordo.getDataPagamento()))
               .append(".");
            if (parcelaAcordo.getStatus().getCodigo().equals(StatusPagamentoVO.AGUARDANDO_COMPENSACAO_CHEQUE)){
                obs.append("\n")
                   .append("O pagamento foi efetuado em cheque e a sua compensa��o se dar� em ")
                   .append(DateUtil.getInstance(new Locale("pt","BR")).convertDateToString(parcelaAcordo.getCheque().getDataCompensacao()))
                   .append(".");
            }
            andamento.setProcesso(parcelaAcordo.getAcordo().getProcesso());
            andamento.setObservacao(obs.toString());
            andamentoDAO.insert(andamento);
        }
    }
    
    public void remove(ParcelaAcordoVO parcelaAcordo) throws AmbienteException,AplicacaoException{
        dao.remove(parcelaAcordo);
    }
    
    public ParcelaAcordoVO findByPrimaryKey(ParcelaAcordoVO parcelaAcordo) throws AmbienteException,AplicacaoException{
        return dao.findByPrimaryKey(parcelaAcordo);
}
    
    public Collection findByAcordo(AcordoVO acordo) throws AmbienteException,AplicacaoException{
        return dao.findByAcordo(acordo);
    }
    
    public Collection findRelatorioClienteByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) 
      throws AmbienteException {
        return dao.findRelatorioClienteByFilter(filtroAssembler);
    }
    
    public Collection findRelatorioByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) 
      throws AmbienteException {
        return dao.findRelatorioByFilter(filtroAssembler);
    }

	public void setAndamentoDAO(AndamentoDAOIf andamentoDAO) {
		this.andamentoDAO = andamentoDAO;
	}

	public void setChequeDAO(ChequeDAOIf chequeDAO) {
		this.chequeDAO = chequeDAO;
	}

	public void setDao(ParcelaAcordoDAOIf dao) {
		this.dao = dao;
	}   
}