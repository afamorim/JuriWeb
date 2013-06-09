/**
 * 
 */
package br.com.vortice.ijuri.core.processo.acordo;

import java.util.Date;

import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;

/**
 * @author Amadeu
 *
 */
public class ParcelaAcordoFiltroReportAssembler extends AcordoFiltroAssembler {
	
    private Date dataRepasseInicio;
    private Date dataRepasseFinal;
    
    private Date dataPagamentoInicio;
    private Date dataPagamentoFinal;
    
    private Date dataVencimentoInicio;
    private Date dataVencimentoFinal;
    
    private StatusPagamentoVO statusPagamento;

    public Date getDataRepasseFinal() {
        return dataRepasseFinal;
    }
    public void setDataRepasseFinal(Date dataRepasseFinal) {
        this.dataRepasseFinal = dataRepasseFinal;
    }
    public Date getDataRepasseInicio() {
        return dataRepasseInicio;
    }
    public void setDataRepasseInicio(Date dataRepasseInicio) {
        this.dataRepasseInicio = dataRepasseInicio;
    }
    public Date getDataPagamentoFinal() {
        return dataPagamentoFinal;
    }
    public void setDataPagamentoFinal(Date dataPagamentoFinal) {
        this.dataPagamentoFinal = dataPagamentoFinal;
    }
    public Date getDataPagamentoInicio() {
        return dataPagamentoInicio;
    }
    public void setDataPagamentoInicio(Date dataPagamentoInicio) {
        this.dataPagamentoInicio = dataPagamentoInicio;
    }
    public Date getDataVencimentoFinal() {
        return dataVencimentoFinal;
    }
    public void setDataVencimentoFinal(Date dataVencimentoFinal) {
        this.dataVencimentoFinal = dataVencimentoFinal;
    }
    public Date getDataVencimentoInicio() {
        return dataVencimentoInicio;
    }
    public void setDataVencimentoInicio(Date dataVencimentoInicio) {
        this.dataVencimentoInicio = dataVencimentoInicio;
    }
    public StatusPagamentoVO getStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(StatusPagamentoVO statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
    
}
