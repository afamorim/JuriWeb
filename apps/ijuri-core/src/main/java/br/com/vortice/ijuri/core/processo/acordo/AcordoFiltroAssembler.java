package br.com.vortice.ijuri.core.processo.acordo;

import java.util.Calendar;
import java.util.Date;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;

public class AcordoFiltroAssembler extends AcordoVO {
    private Date periodoTaxaComumInicio;
    private Date periodoTaxaComumFim; 
    
    private Date periodoTaxaExtraInicio;
    private Date periodoTaxaExtraFim;
    
    private PessoaVO devedor;
    private PessoaVO cliente;
    
    private ProcessoVO processo;
    
    public Integer getMesTaxaComumInicio(){
        if (periodoTaxaComumInicio!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaComumInicio);
            return new Integer(c.get(Calendar.MONTH)+1);
        }else{
            return null;
        }
    }
    
    public Integer getMesTaxaComumFim(){
        if (periodoTaxaComumFim!=null){ 
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaComumFim);
            return new Integer(c.get(Calendar.MONTH)+1);
        }else{
            return null;
        }
    }
    
    public Integer getAnoTaxaComumInicio(){
        if (periodoTaxaComumInicio!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaComumInicio);
            return new Integer(c.get(Calendar.YEAR));
        }else{
            return null;
        }
    }
    
    public Integer getAnoTaxaComumFim(){
        if (periodoTaxaComumFim!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaComumFim);
            return new Integer(c.get(Calendar.YEAR));
        }else{
            return null;
        }
    }
    
    public Integer getMesTaxaExtraInicio(){
        if (periodoTaxaExtraInicio!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaExtraInicio);
            return new Integer(c.get(Calendar.MONTH)+1);
        }else{
            return null;
        }
    }
    
    public Integer getMesTaxaExtraFim(){
        if (periodoTaxaExtraFim!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaExtraFim);
            return new Integer(c.get(Calendar.MONTH)+1);
        }else{
            return null;
        }
    }
    
    public Integer getAnoTaxaExtraInicio(){
        if (periodoTaxaExtraInicio!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaExtraInicio);
            return new Integer(c.get(Calendar.YEAR));
        }else{
            return null;
        }
    }
    
    public Integer getAnoTaxaExtraFim(){
        if (periodoTaxaExtraFim!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(periodoTaxaExtraFim);
            return new Integer(c.get(Calendar.YEAR));
        }else{
            return null;
        }
    }

    /**
     * @return Returns the periodoFinal.
     */
    public Date getPeriodoTaxaComumFim() {
        return periodoTaxaComumFim;
    }

    /**
     * @param periodoFinal The periodoFinal to set.
     */
    public void setPeriodoTaxaComumFim(Date periodoFinal) {
        this.periodoTaxaComumFim = periodoFinal;
    }

    /**
     * @return Returns the periodoInicial.
     */
    public Date getPeriodoTaxaComumInicio() {
        return periodoTaxaComumInicio;
    }

    /**
     * @param periodoInicial The periodoInicial to set.
     */
    public void setPeriodoTaxaComumInicio(Date periodoInicial) {
        this.periodoTaxaComumInicio = periodoInicial;
    }

    /**
     * @return Returns the periodoTaxaExtraComunFim.
     */
    public Date getPeriodoTaxaExtraFim() {
        return periodoTaxaExtraFim;
    }

    /**
     * @param periodoTaxaExtraComunFim The periodoTaxaExtraComunFim to set.
     */
    public void setPeriodoTaxaExtraFim(Date periodoTaxaExtraComunFim) {
        this.periodoTaxaExtraFim = periodoTaxaExtraComunFim;
    }

    /**
     * @return Returns the periodoTaxaExtraInicio.
     */
    public Date getPeriodoTaxaExtraInicio() {
        return periodoTaxaExtraInicio;
    }

    /**
     * @param periodoTaxaExtraInicio The periodoTaxaExtraInicio to set.
     */
    public void setPeriodoTaxaExtraInicio(Date periodoTaxaExtraInicio) {
        this.periodoTaxaExtraInicio = periodoTaxaExtraInicio;
    }

    /**
     * @return Returns the cliente.
     */
    public PessoaVO getCliente() {
        return cliente;
    }

    /**
     * @param cliente The cliente to set.
     */
    public void setCliente(PessoaVO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return Returns the devedor.
     */
    public PessoaVO getDevedor() {
        return devedor;
    }

    /**
     * @param devedor The devedor to set.
     */
    public void setDevedor(PessoaVO devedor) {
        this.devedor = devedor;
    }

    /**
     * @return Returns the processo.
     */
    public ProcessoVO getProcesso() {
        return processo;
    }

    /**
     * @param processo The processo to set.
     */
    public void setProcesso(ProcessoVO processo) {
        this.processo = processo;
    }
}
