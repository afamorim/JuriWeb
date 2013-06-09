package br.com.vortice.ijuri.core.pessoa;

public class PessoaJuridicaVO extends PessoaVO {
	
	public PessoaJuridicaVO(){
		super();
	}
	
	public PessoaJuridicaVO(Integer codigo){
		super();
		this.setCodigo(codigo);
	}
	
    private String cnpj; 

    private String contato; 

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
 }





