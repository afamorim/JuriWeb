package br.com.vortice.ijuri.core.site;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;

public class ParceiroVO extends PessoaVO {
    private String resumoServico;
    
    public ParceiroVO(Integer codigo) {
        super(codigo);
    }

    public ParceiroVO() {}

    public String getResumoServico() {
        return resumoServico;
    }

    public void setResumoServico(String resumoServico) {
        this.resumoServico = resumoServico;
    }
    
}