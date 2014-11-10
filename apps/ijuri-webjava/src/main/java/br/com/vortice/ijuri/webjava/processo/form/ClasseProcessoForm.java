package br.com.vortice.ijuri.webjava.processo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class ClasseProcessoForm extends BaseActionForm {
    private Long codigo;
    private String descricao;
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
    
    public ActionErrors validate (ActionMapping mapping, HttpServletRequest request){
        validarVazio(descricao,"Nome da Classe do Processo"); 
        return super.validate(mapping, request);
    }
}
