package br.com.vortice.ijuri.webjava.pessoa.form;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;

public class PessoaForm extends BaseActionForm {
    private String nome;
    private String tipo;
    /**
     * @return Returns the nome.
     */
    public String getNome() {
        return nome;
    }
    /**
     * @param nome The nome to set.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return Returns the tipo.
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * @param tipo The tipo to set.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
