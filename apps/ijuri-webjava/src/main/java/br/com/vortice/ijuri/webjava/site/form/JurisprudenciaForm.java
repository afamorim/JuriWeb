package br.com.vortice.ijuri.webjava.site.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class JurisprudenciaForm extends BaseActionForm {
    private Long codigo;
    private String titulo;
    private Long documentoCodigo;
    private String documentoNome;
    private Integer areaAtuacaoCodigo;
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Long getDocumentoCodigo() {
        return documentoCodigo;
    }
    public void setDocumentoCodigo(Long documentoCodigo) {
        this.documentoCodigo = documentoCodigo;
    }
    public String getDocumentoNome() {
        return documentoNome;
    }
    public void setDocumentoNome(String documentoNome) {
        this.documentoNome = documentoNome;
    }
    public Integer getAreaAtuacaoCodigo() {
        return areaAtuacaoCodigo;
    }
    public void setAreaAtuacaoCodigo(Integer areaAtuacaoCodigo) {
        this.areaAtuacaoCodigo = areaAtuacaoCodigo;
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        validarVazioNumerico(this.documentoCodigo, "Documento");
        validarVazio(this.titulo, "T�tulo");
        
        return super.validate(mapping, request);
    }
    
    public void carregarComponentes(ActionMapping mapping, HttpServletRequest request) throws Exception {
        request.setAttribute("areas",AreaAtuacaoVO.findAll());
    }
    
    
}
