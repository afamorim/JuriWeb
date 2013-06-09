
package br.com.vortice.ijuri.core.pessoa;

public class PessoaFisicaVO extends PessoaVO {
	
   public PessoaFisicaVO(){
	   super();
   }
   
   public PessoaFisicaVO(Integer codigo){
	   super(codigo);
   }
	
    private String cpf; 
 
    public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
 } // end PessoaJuridicaVO






