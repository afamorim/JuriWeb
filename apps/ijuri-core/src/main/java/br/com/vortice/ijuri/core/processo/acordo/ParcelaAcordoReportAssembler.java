/**
 * 
 */
package br.com.vortice.ijuri.core.processo.acordo;

import br.com.vortice.ijuri.core.processo.ParteVO;

/**
 * @author Amadeu
 *
 */
public class ParcelaAcordoReportAssembler extends ParcelaAcordoVO{
    private ParteVO cliente;
    private ParteVO devedor;
    
    public ParteVO getCliente() {
        return cliente;
    }
    public void setCliente(ParteVO cliente) {
        this.cliente = cliente;
    }
    public ParteVO getDevedor() {
        return devedor;
    }
    public void setDevedor(ParteVO devedor) {
        this.devedor = devedor;
    }
}

