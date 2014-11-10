package br.com.vortice.ijuri.webjava.pagamento;

import java.util.Collection;

import br.com.vortice.ijuri.business.pagamento.rn.BancoRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class PagamentoDelegate extends BusinessDelegateAB {
	
	private BancoRN bancoRN;
	
	public PagamentoDelegate() throws AmbienteException{
	}
	    
    public Collection findAllBancos() throws AmbienteException, AplicacaoException{
    	return bancoRN.findAll();
    }

	public void setBancoRN(BancoRN bancoRN) {
		this.bancoRN = bancoRN;
	}
    
}
