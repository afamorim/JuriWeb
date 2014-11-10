package br.com.vortice.ijuri.webjava.site.bean;

import br.com.vortice.ijuri.core.site.ContatoVO;

import com.vortice.core.view.BasePageBean;

public class ContatoPageBean extends BasePageBean {

	private ContatoVO contato;
	
	public ContatoPageBean(){
		contato = new ContatoVO();
	}
	
	public ContatoVO getContato() {
		return contato;
	}

	public void setContato(ContatoVO contato) {
		this.contato = contato;
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}

	@Override
	protected void sort(String column, boolean ascending) {
	}
}