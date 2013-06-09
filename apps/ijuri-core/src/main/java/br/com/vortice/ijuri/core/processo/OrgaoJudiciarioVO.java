
package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class OrgaoJudiciarioVO extends ValueObject {
	
	public OrgaoJudiciarioVO(){}
	
	public OrgaoJudiciarioVO(Long codigo){
		this.codigo = codigo;
	}
	
	public OrgaoJudiciarioVO(String des){
		this.descricao = des;
	}
	
    private String descricao; 

    private Long codigo; 

    private JuizoVO juizo; 
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public JuizoVO getJuizo() {
        return juizo;
    }

    public void setJuizo(JuizoVO juizo) {
        this.juizo = juizo;
    }

 } 






