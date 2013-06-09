package br.com.vortice.ijuri.core.processo;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import br.com.vortice.ijuri.core.abstracao.ValueObject;
import br.com.vortice.ijuri.core.abstracao.util.DateUtil;

public class TaxaVO extends ValueObject implements Comparable{

    private Integer mes; 
    private Integer ano; 
    private Long codigo;
    private ProcessoVO processo;
    private TipoTaxaVO tipo;

    public Integer getAno() {
        return ano;
    }
    /**
     * @param ano The ano to set.
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    /**
     * @return Returns the codigo.
     */
    public Long getCodigo() {
        return codigo;
    }
    /**
     * @param codigo The codigo to set.
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    /**
     * @return Returns the isComun.
     */
    public boolean isComun() {
        if (tipo!=null && tipo.getCodigo().equals(TipoTaxaVO.COMUN))
            return true;
        
        return false;
    }
    /**
     * @return Returns the mes.
     */
    public Integer getMes() {
        return mes;
    }
    /**
     * @param mes The mes to set.
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    /**
     * @return Returns the processo.
     */
    public ProcessoVO getProcesso() {
        return processo;
    }
    /**
     * @param processo The processo to set.
     */
    public void setProcesso(ProcessoVO processo) {
        this.processo = processo;
    }
    /**
     * @return Returns the tipo.
     */
    public TipoTaxaVO getTipo() {
        return tipo;
    }
    /**
     * @param tipo The tipo to set.
     */
    public void setTipo(TipoTaxaVO tipo) {
        this.tipo = tipo;
    }
    
    public Date getMesAno(){
        Calendar c = Calendar.getInstance();
        c.set( Calendar.DAY_OF_MONTH, 01);
        c.set( Calendar.MONTH, mes.intValue()-1 );
        c.set( Calendar.YEAR, ano.intValue() );
        return c.getTime();
    }
    
    public boolean equals(Object objeto){
        TaxaVO outraTaxa = (TaxaVO)objeto;
        if (this.mes.equals(outraTaxa.getMes()) 
                && this.ano.equals(outraTaxa.getAno())){
            return true;
        }
        return false;
        
    }
    public int compareTo(Object outroObjeto) {
        TaxaVO outraTaxa = (TaxaVO)outroObjeto;
        DateUtil.getInstance(new Locale("pt","BR"));
        Date periodoThis = DateUtil.getInstance().convertToDate(new Integer(01), getMes(), getAno());
        Date periodoOther = DateUtil.getInstance().convertToDate(new Integer(01), outraTaxa.getMes(), outraTaxa.getAno());
        return periodoThis.compareTo(periodoOther);
    }
    
    public String toString() {
        return  DateUtil.getInstance(new Locale("pt","BR")).formatarMesAnoDescricao(getMes(), getAno());
    }
    
    public static String taxasToString(Collection taxas){
        StringBuffer msg = new StringBuffer();    
        
        if (taxas.size() > 1){
            TaxaVO taxaAnterior = null;
            Calendar cal = Calendar.getInstance();
            int contTaxas =  0;
            for (Iterator iter = taxas.iterator(); iter.hasNext();) {
                TaxaVO taxa = (TaxaVO) iter.next();
                //se for o primeiro
                if (taxaAnterior == null){
                    msg.append(taxa.toString());
                }else{
                     cal.setTime(taxaAnterior.getMesAno());
                     cal.add(Calendar.MONTH,1);
                     // se for o útimo
                     if (!iter.hasNext()){
                         if (!cal.getTime().equals(taxa.getMesAno())){
                             if (contTaxas > 1){
                                 msg.append(" à ");
                                 msg.append(taxaAnterior.toString());
                             }
                             
                             msg.append(", ");
                             msg.append(taxa.toString());
                         }else{
                             if (contTaxas >= 1)
                                 msg.append(" à ");
                             else
                                 msg.append(", ");
                             
                             msg.append(taxa.toString());
                         }
                     //se o não está em sequência
                     }else if (!cal.getTime().equals(taxa.getMesAno())){
                         if (contTaxas > 1){
                             msg.append(" à ");
                             msg.append(taxaAnterior.toString());
                         }else if (contTaxas == 1){
                             msg.append(", ");
                             msg.append(taxaAnterior.toString());
                         }
                         
                         msg.append(", ");
                         msg.append(taxa.toString());
                         contTaxas = 0;
                     //se está em sequência
                     }else{
                         contTaxas++;
                     }
                     
                }
                taxaAnterior = taxa;
            }
            int posVirgulaFinal = msg.lastIndexOf(",");
            if (posVirgulaFinal > 0){
                msg.deleteCharAt(posVirgulaFinal);
                msg.insert(posVirgulaFinal, " e");
            }
        }else{
            msg.append(taxas.toString());
            
            msg.deleteCharAt(msg.indexOf("["));
            msg.deleteCharAt(msg.indexOf("]"));
        }
        
        return msg.toString();
        
        
    }
    
    
 } // end TaxaVO





