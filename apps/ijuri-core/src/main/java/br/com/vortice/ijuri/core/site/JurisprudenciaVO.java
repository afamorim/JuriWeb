package br.com.vortice.ijuri.core.site;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.documento.DocumentoVO;

public class JurisprudenciaVO extends ValueObject {
    private Long codigo;
    private String titulo;
    private DocumentoVO documento;
    private AreaAtuacaoVO areaAtuacao;

    public JurisprudenciaVO() {}
    
    public JurisprudenciaVO(Long codigo) {
        this.codigo = codigo;
    }
    
    public AreaAtuacaoVO getAreaAtuacao() {
        return areaAtuacao;
    }
    public void setAreaAtuacao(AreaAtuacaoVO areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public DocumentoVO getDocumento() {
        return documento;
    }
    public void setDocumento(DocumentoVO documento) {
        this.documento = documento;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    } 
}
