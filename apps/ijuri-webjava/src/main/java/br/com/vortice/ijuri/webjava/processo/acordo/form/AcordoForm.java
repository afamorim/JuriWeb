package br.com.vortice.ijuri.webjava.processo.acordo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.processo.acordo.RegimeMultaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;

public class AcordoForm extends BaseActionForm {
	
	private String codigo;
    private String valor; 
    private String quantidadeParcelas; 
    private String dataVencimento; 
    private String observacao; 

    //Processo
    private String processoNumero;
    private Long processoCodigo;
    private String honorario;
    
    
    //Filtros
    private String periodoTaxaComumInicio;
    private String periodoTaxaComumFim; 
    private String periodoTaxaExtraInicio;
    private String periodoTaxaExtraFim;
    private Integer devedorCodigo;
    private String devedorNome;
    private Integer clienteCodigo;
    private String clienteNome;
    
    //Corre��o Monet�ria
    private String valorIndiceMulta;
    private String valorJurosMes;
    private String valorClausulaPenalMes;
	private String regimeMultaCodigo;
    private Integer indiceReajusteCodigo;
    
	/**
     * @return Returns the dataVencimento.
     */
    public String getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento The dataVencimento to set.
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return Returns the observacao.
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao The observacao to set.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return Returns the quantidadeParcelas.
     */
    public String getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    /**
     * @param quantidadeParcelas The quantidadeParcelas to set.
     */
    public void setQuantidadeParcelas(String quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    /**
     * @return Returns the valor.
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor The valor to set.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return Returns the processoNumero.
     */
    public String getProcessoNumero() {
        return processoNumero;
    }

    /**
     * @param processoNumero The processoNumero to set.
     */
    public void setProcessoNumero(String processoNumero) {
        this.processoNumero = processoNumero;
    }

    /**
     * @param codigo The codigo to set.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
		return codigo;
	}

	 public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
		 
         if (quantidadeParcelas != null){
             validarVazio(quantidadeParcelas, "N� de Parcelas");
             validarNumerico(quantidadeParcelas, "N� de Parcelas");
         }
         
         validarVazio(dataVencimento, "Data de Vencimento");
         validarData(dataVencimento, "Data de Vencimento");
         
         validarVazio(valorJurosMes, "Juros por M�s (%)");
         validarNumeroFloat(valorJurosMes, "Juros por M�s (%)");
         
         validarVazioNumerico(indiceReajusteCodigo, "�ndice de Reajuste");
         
         validarVazio(regimeMultaCodigo, "Regime Multa");
         if (regimeMultaCodigo != null && regimeMultaCodigo.trim().length()>0){
             
             if (regimeMultaCodigo.equals(RegimeMultaVO.CLAUSULA_PENAL.toString())){
                 validarVazio(valorClausulaPenalMes, "Cl�usula Penal por M�s (%)");
                 validarNumeroFloat(valorClausulaPenalMes, "Cl�usula Penal por M�s (%)");
             }else{
                 validarVazio(valorIndiceMulta, "Multa (%)");
                 validarNumeroFloat(valorIndiceMulta, "Multa (%)");
             }
         } 
         
         validarVazioNumerico(processoCodigo,"Processo");
         
         validarVazio(valor, "Valor da causa");
         
         return super.validate(mapping, request);
	 }
	 
	 public void carregarComponentes(ActionMapping mapping, 
	    		HttpServletRequest request, 
	    		WebApplicationContext webAppContext) throws Exception {
		 
		 CorrecaoMonetariaDelegate correcaoMonetariaDelegate 
		 	= (CorrecaoMonetariaDelegate) webAppContext.getBean("correcaoMonetariaDelegate");
		 request.setAttribute("indices",correcaoMonetariaDelegate.findAllIndiceReajuste());
		 
		 request.setAttribute("regimes",RegimeMultaVO.findAll());
                
     }

    /**
     * @return Returns the honorario.
     */
    public String getHonorario() {
        return honorario;
    }

    /**
     * @param honorario The honorario to set.
     */
    public void setHonorario(String honorario) {
        this.honorario = honorario;
    }

    /**
     * @return Returns the processoCodigo.
     */
    public Long getProcessoCodigo() {
        return processoCodigo;
    }

    /**
     * @param processoCodigo The processoCodigo to set.
     */
    public void setProcessoCodigo(Long processoCodigo) {
        this.processoCodigo = processoCodigo;
    }

    /**
     * @return Returns the clienteNome.
     */
    public String getClienteNome() {
        return clienteNome;
    }

    /**
     * @param clienteNome The clienteNome to set.
     */
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    /**
     * @return Returns the devedorNome.
     */
    public String getDevedorNome() {
        return devedorNome;
    }

    /**
     * @param devedorNome The devedorNome to set.
     */
    public void setDevedorNome(String devedorNome) {
        this.devedorNome = devedorNome;
    }

    /**
     * @return Returns the clienteCodigo.
     */
    public Integer getClienteCodigo() {
        return clienteCodigo;
    }

    /**
     * @param clienteCodigo The clienteCodigo to set.
     */
    public void setClienteCodigo(Integer clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    /**
     * @return Returns the devedorCodigo.
     */
    public Integer getDevedorCodigo() {
        return devedorCodigo;
    }

    /**
     * @param devedorCodigo The devedorCodigo to set.
     */
    public void setDevedorCodigo(Integer devedorCodigo) {
        this.devedorCodigo = devedorCodigo;
    }

    /**
     * @return Returns the periodoTaxaComumFim.
     */
    public String getPeriodoTaxaComumFim() {
        return periodoTaxaComumFim;
    }

    /**
     * @param periodoTaxaComumFim The periodoTaxaComumFim to set.
     */
    public void setPeriodoTaxaComumFim(String periodoTaxaComumFim) {
        this.periodoTaxaComumFim = periodoTaxaComumFim;
    }

    /**
     * @return Returns the periodoTaxaComumInicio.
     */
    public String getPeriodoTaxaComumInicio() {
        return periodoTaxaComumInicio;
    }

    /**
     * @param periodoTaxaComumInicio The periodoTaxaComumInicio to set.
     */
    public void setPeriodoTaxaComumInicio(String periodoTaxaComumInicio) {
        this.periodoTaxaComumInicio = periodoTaxaComumInicio;
    }

    /**
     * @return Returns the periodoTaxaExtraFim.
     */
    public String getPeriodoTaxaExtraFim() {
        return periodoTaxaExtraFim;
    }

    /**
     * @param periodoTaxaExtraFim The periodoTaxaExtraFim to set.
     */
    public void setPeriodoTaxaExtraFim(String periodoTaxaExtraFim) {
        this.periodoTaxaExtraFim = periodoTaxaExtraFim;
    }

    /**
     * @return Returns the periodoTaxaExtraInicio.
     */
    public String getPeriodoTaxaExtraInicio() {
        return periodoTaxaExtraInicio;
    }

    /**
     * @param periodoTaxaExtraInicio The periodoTaxaExtraInicio to set.
     */
    public void setPeriodoTaxaExtraInicio(String periodoTaxaExtraInicio) {
        this.periodoTaxaExtraInicio = periodoTaxaExtraInicio;
    }

    public String getValorClausulaPenalMes() {
        return valorClausulaPenalMes;
    }

    public void setValorClausulaPenalMes(String valorClausulaPenalMes) {
        this.valorClausulaPenalMes = valorClausulaPenalMes;
    }

    public String getValorIndiceMulta() {
        return valorIndiceMulta;
    }

    public void setValorIndiceMulta(String valorIndiceMulta) {
        this.valorIndiceMulta = valorIndiceMulta;
    }

    public String getValorJurosMes() {
        return valorJurosMes;
    }

    public void setValorJurosMes(String valorJurosMes) {
        this.valorJurosMes = valorJurosMes;
    }

    public String getRegimeMultaCodigo() {
        return regimeMultaCodigo;
    }

    public void setRegimeMultaCodigo(String regimeMultaCodigo) {
        this.regimeMultaCodigo = regimeMultaCodigo;
    }

    public Integer getIndiceReajusteCodigo() {
        return indiceReajusteCodigo;
    }

    public void setIndiceReajusteCodigo(Integer indiceReajusteCodigo) {
        this.indiceReajusteCodigo = indiceReajusteCodigo;
    }

}
