package br.com.vortice.ijuri.webjava.processo.form;

import java.text.DateFormatSymbols;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class TaxaForm extends BaseActionForm {
	
    private String mes; 
    private String ano; 
    private String processoCodigo;
    private String tipoCodigo;
    private String codigo;
    private String taxas;
    private String periodoInicio;
    private String periodoFim;
    private String pos;
    
	
	public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
		 return super.validate(mapping, request);
	}
	 
	public void carregarComponentes(ActionMapping mapping, HttpServletRequest request) throws Exception {
        request.setAttribute("meses",new DateFormatSymbols(getLocale()).getMonths());
	}

    /**
     * @return Returns the acordoCodigo.
     */
    public String getProcessoCodigo() {
        return processoCodigo;
    }

    /**
     * @param acordoCodigo The acordoCodigo to set.
     */
    public void setProcessoCodigo(String acordoCodigo) {
        this.processoCodigo = acordoCodigo;
    }

    /**
     * @return Returns the ano.
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano The ano to set.
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return Returns the codigo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo The codigo to set.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return Returns the mes.
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes The mes to set.
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return Returns the taxas.
     */
    public String getTaxas() {
        return taxas;
    }

    /**
     * @param taxas The taxas to set.
     */
    public void setTaxas(String taxas) {
        this.taxas = taxas;
    }

    /**
     * @return Returns the periodoFim.
     */
    public String getPeriodoFim() {
        return periodoFim;
    }

    /**
     * @param periodoFim The periodoFim to set.
     */
    public void setPeriodoFim(String periodoFim) {
        this.periodoFim = periodoFim;
    }

    /**
     * @return Returns the periodoInicio.
     */
    public String getPeriodoInicio() {
        return periodoInicio;
    }

    /**
     * @param periodoInicio The periodoInicio to set.
     */
    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    /**
     * @return Returns the tipoCodigo.
     */
    public String getTipoCodigo() {
        return tipoCodigo;
    }

    /**
     * @param tipoCodigo The tipoCodigo to set.
     */
    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    /**
     * @return Returns the pos.
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos The pos to set.
     */
    public void setPos(String pos) {
        this.pos = pos;
    }
}
