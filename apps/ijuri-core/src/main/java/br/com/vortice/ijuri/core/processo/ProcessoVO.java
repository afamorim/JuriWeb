
/*
 * Java class "ProcessoVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.processo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import br.com.vortice.ijuri.core.abstracao.ValueObject;


/**
 * <p></p>
 */
public class ProcessoVO extends ValueObject {
	
	public ProcessoVO(){}
	
	public ProcessoVO(Long codigo){
		this.codigo = codigo;
	}
	
    private String numero; 
    private StatusProcessoVO status; 
    private Float valorCausa; 
    private Long codigo; 
    private Date dataDistribuicao; 
    private Date dataAutuacao;
    private TurnoVO turno; 
    private Collection reus; // of type ReuVO
    private ClasseProcessoVO classeProcesso; 
    private OrgaoJudiciarioVO orgaoJudiciario; 
    private Collection andamentos; // of type AndamentoVO
    private ComarcaVO comarca; 
    private Collection autores; // of type AutorVO
    private Float honorario;
    private Collection partes; // of type ParteVO
    private String observacao;
    private Collection taxasComuns; // of type TaxaVO
    private Collection taxasExtras; // of type TaxaVO
    private Integer apto; 
    private String bloco; 
    
        /**
     * @return Returns the partes.
     */
    public Collection getPartes() {
        return partes;
    }
    /**
     * @param partes The partes to set.
     */
    public void setPartes(Collection partes) {
        this.partes = partes;
    }
        /**
         * @return Returns the andamentos.
         */
        public Collection getAndamentos() {
            return andamentos;
        }
        /**
         * @param andamentos The andamentos to set.
         */
        public void setAndamentos(Collection andamentos) {
            this.andamentos = andamentos;
        }
        /**
         * @return Returns the autores.
         */
        public Collection getAutores() {
            return autores;
        }
        /**
         * @param autores The autores to set.
         */
        public void setAutores(Collection autores) {
            this.autores = autores;
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
         * @return Returns the comarcaVO.
         */
        public ComarcaVO getComarca() {
            return comarca;
        }
        /**
         * @param comarcaVO The comarcaVO to set.
         */
        public void setComarca(ComarcaVO comarcaVO) {
            this.comarca = comarcaVO;
        }
        /**
         * @return Returns the dataDistribuicao.
         */
        public Date getDataDistribuicao() {
            return dataDistribuicao;
        }
        /**
         * @param dataDistribuicao The dataDistribuicao to set.
         */
        public void setDataDistribuicao(Date dataDistribuicao) {
            this.dataDistribuicao = dataDistribuicao;
        }
        /**
         * @return Returns the numero.
         */
        public String getNumero() {
            return numero;
        }
        /**
         * @param numero The numero to set.
         */
        public void setNumero(String numero) {
            this.numero = numero;
        }
        /**
         * @return Returns the orgaoJudiciarioVO.
         */
        public OrgaoJudiciarioVO getOrgaoJudiciario() {
            return orgaoJudiciario;
        }
        /**
         * @param orgaoJudiciarioVO The orgaoJudiciarioVO to set.
         */
        public void setOrgaoJudiciario(OrgaoJudiciarioVO orgaoJudiciarioVO) {
            this.orgaoJudiciario = orgaoJudiciarioVO;
        }
        /**
         * @return Returns the reus.
         */
        public Collection getReus() {
            return reus;
        }
        /**
         * @param reus The reus to set.
         */
        public void setReus(Collection reus) {
            this.reus = reus;
        }
        /**
         * @return Returns the status.
         */
        public StatusProcessoVO getStatus() {
            return status;
        }
        /**
         * @param status The status to set.
         */
        public void setStatus(StatusProcessoVO status) {
            this.status = status;
        }
        /**
         * @return Returns the tipoAcaoVO.
         */
        public ClasseProcessoVO getClasseProcesso() {
            return classeProcesso;
        }
        /**
         * @param tipoAcaoVO The tipoAcaoVO to set.
         */
        public void setClasseProcesso(ClasseProcessoVO classeProcessoVO) {
            this.classeProcesso = classeProcessoVO;
        }
        /**
         * @return Returns the turno.
         */
        public TurnoVO getTurno() {
            return turno;
        }
        /**
         * @param turno The turno to set.
         */
        public void setTurno(TurnoVO turno) {
            this.turno = turno;
        }
        /**
         * @return Returns the valorCausa.
         */
        public Float getValorCausa() {
            return valorCausa;
        }
        /**
         * @param valorCausa The valorCausa to set.
         */
        public void setValorCausa(Float valorCausa) {
            this.valorCausa = valorCausa;
        }
    /**
     * @return Returns the honorario.
     */
    public Float getHonorario() {
        return honorario;
    }
    /**
     * @param honorario The honorario to set.
     */
    public void setHonorario(Float honorario) {
        this.honorario = honorario;
    }
    /**
     * @return Returns the dataAutuacao.
     */
    public Date getDataAutuacao() {
        return dataAutuacao;
    }
    /**
     * @param dataAutuacao The dataAutuacao to set.
     */
    public void setDataAutuacao(Date dataAutuacao) {
        this.dataAutuacao = dataAutuacao;
    }

    /**
     * @return Returns the observacao.
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao The observacao to set.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return Returns the taxasComuns.
     */
    public Collection getTaxasComuns() {
        return taxasComuns;
    }

    /**
     * @param taxasComuns The taxasComuns to set.
     */
    public void setTaxasComuns(Collection taxasComuns) {
        this.taxasComuns = taxasComuns;
    }

    /**
     * @return Returns the taxasExtras.
     */
    public Collection getTaxasExtras() {
        return taxasExtras;
    }

    /**
     * @param taxasExtras The taxasExtras to set.
     */
    public void setTaxasExtras(Collection taxasExtras) {
        this.taxasExtras = taxasExtras;
    }
    
    /**
     * Retorna as taxas extras separadas por virgula
     * @return 
     */
    public String getTaxasExtrasDescricao(){
        return TaxaVO.taxasToString(getTaxasExtras());
    }

   /**
     * Retorna as taxas comuns separadas por virgula
     * @return 
     */
    public String getTaxasComunsDescricao(){
        return TaxaVO.taxasToString(getTaxasComuns());
        
    }

public Integer getApto() {
	return apto;
}

public void setApto(Integer apto) {
	this.apto = apto;
}

public String getBloco() {
	return bloco;
}

public void setBloco(String bloco) {
	this.bloco = bloco;
}
    
 } // end ProcessoVO






