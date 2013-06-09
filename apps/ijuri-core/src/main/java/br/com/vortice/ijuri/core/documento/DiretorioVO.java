
/*
 * Java class "Diretorio.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.documento;

import java.util.Collection;

import br.com.vortice.ijuri.core.abstracao.ValueObject;


public class DiretorioVO extends ValueObject {

   private Long codigo; 
   private String nome;
   private String sortKey;
   private Integer level;
   private DiretorioVO parent;
   private Collection documentos;

   public Long getCodigo() {
        return codigo;
   }
   public void setCodigo(Long codigo) {
        this.codigo = codigo;
   }
   public Collection getDocumentos() {
        return documentos;
   }
   public void setDocumentos(Collection documentos) {
        this.documentos = documentos;
   }
   public DiretorioVO getParent() {
       return parent;
   }
   public void setParent(DiretorioVO parent) {
        this.parent = parent;
   }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSortKey() {
        return sortKey;
    }
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

 } 






