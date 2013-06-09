package br.com.vortice.ijuri.core.site;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class LinkVO extends ValueObject {
	
	public LinkVO(){}
	
	public LinkVO(Long codigo){
		this.codigo = codigo;
	}
	
    private Long codigo;
    
    private String nome;
    
    private String endereco;
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
