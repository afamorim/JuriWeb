
package br.com.vortice.ijuri.core.processo;

import java.util.ArrayList;
import java.util.Collection;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class TipoAndamentoVO extends ValueObject{
	
    public static final Long PAGAMENTO_PARCELA = new Long(16); 
	
    public TipoAndamentoVO(){}
	
	public TipoAndamentoVO(Long c){
		codigo = c;
	}
	
    private String descricao; 

    private Long codigo; 

    private Collection collJuizos = new ArrayList();

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

	public Collection getCollJuizos() {
		return collJuizos;
	}

	public void setCollJuizos(Collection collJuizos) {
		this.collJuizos = collJuizos;
	}
	
	public void addJuizo(JuizoVO juizoVO){
		collJuizos.add(juizoVO);
	}
	
	public void removeJuizo(JuizoVO juizoVO){
		collJuizos.remove(juizoVO);
	}
 }






