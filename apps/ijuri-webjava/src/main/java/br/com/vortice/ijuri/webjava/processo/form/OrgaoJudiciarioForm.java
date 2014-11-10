package br.com.vortice.ijuri.webjava.processo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

public class OrgaoJudiciarioForm extends BaseActionForm {
    private Long codigo;
    private String descricao;
    private Integer codigoJuizo;
 
    /**
     * @return Returns the codigo.
     */
    public Long getCodigo() {
        return codigo;
    }
    /**
     * @param codigo The codigo to set.
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    /**
     * @return Returns the descricao.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * @param descricao The descricao to set.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * @return Returns the codigoJuizo.
     */
    public Integer getCodigoJuizo() {
        return codigoJuizo;
    }
    /**
     * @param codigoJuizo The codigoJuizo to set.
     */
    public void setCodigoJuizo(Integer codigoJuizo) {
        this.codigoJuizo = codigoJuizo;
    }
    
    public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazio(descricao,"Nome do Org�o Judici�rio");
        validarVazioNumerico(codigoJuizo,"Ju�zo");
        return super.validate(mapping, request);
    }
   
    /**
     * M�todo chamado pela abstra��o para carregar combo entre outros 
     * se ocorrer erro de aplica��o.
     */
    protected void carregarComponentes(ActionMapping mapping, HttpServletRequest request, ProcessoDelegate delegate) throws Exception{
        request.setAttribute("juizos", delegate.findAllJuizo());
    }
}
