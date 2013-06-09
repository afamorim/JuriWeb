package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;

public class ComarcaVO extends ValueObject {
	
	public ComarcaVO(){}
	
	public ComarcaVO(Long codigo){
		this.codigo = codigo;
	}
	
    private Long codigo; 

    private MunicipioVO municipio; 
    
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public MunicipioVO getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(MunicipioVO municipio) {
		this.municipio = municipio;
	}
	
 }