
package br.com.vortice.ijuri.core.processo;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;

public class ParteVO extends PessoaVO implements Comparable{

    public static int PARTE_CLIENTE = 1;
    public static int PARTE_NAO_CLIENTE = 0;
    private Integer situacaoCliente; 
    private ProcessoVO processo;
    private TipoParteVO tipoParte;

    /**
     * @return Returns the tipoParte.
     */
    public TipoParteVO getTipoParte() {
        return tipoParte;
    }

    /**
     * @param tipoParte The tipoParte to set.
     */
    public void setTipoParte(TipoParteVO tipoParte) {
        this.tipoParte = tipoParte;
    }

    public boolean isCliente() {
        return (getSituacaoCliente().intValue() == PARTE_CLIENTE);
    }

    public ProcessoVO getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoVO processo) {
        this.processo = processo;
    }

    public void setSituacaoCliente(Integer situacaoCliente) {
        this.situacaoCliente = situacaoCliente;
    }

    public Integer getSituacaoCliente() {
        return situacaoCliente;
    }
    
    public String getNomeParte(){
        if (this.isCliente()){
            return getNome()+" (Cliente)";
        }
        return getNome();
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object outroObjeto) {
        ParteVO parteVO = (ParteVO)outroObjeto;
        return parteVO.getNome().compareTo(this.getNome());
    }
 }






