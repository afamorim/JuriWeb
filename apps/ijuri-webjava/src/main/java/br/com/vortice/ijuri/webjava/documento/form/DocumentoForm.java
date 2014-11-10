package br.com.vortice.ijuri.webjava.documento.form;

import org.apache.struts.upload.FormFile;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class DocumentoForm extends BaseActionForm {
    private Long codigo;
    private String nome;
    private FormFile arquivo;
    private Long diretorioCodigo;
    
    public FormFile getArquivo() {
        return arquivo;
    }
    public void setArquivo(FormFile arquivo) {
        this.arquivo = arquivo;
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Long getDiretorioCodigo() {
        return diretorioCodigo;
    }
    public void setDiretorioCodigo(Long diretorioCodigo) {
        this.diretorioCodigo = diretorioCodigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
