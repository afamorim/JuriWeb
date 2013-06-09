
/*
 * Java class "TipoUsuarioVO.java" generated from Poseidon for UML.
 * Poseidon for UML is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 */
package br.com.vortice.ijuri.core.seguranca;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import br.com.vortice.ijuri.core.abstracao.ValueObject;

public class TipoUsuarioVO extends ValueObject {

    private Long codigo; 
   
    private String descricao;
    
    private static Map tipos = new TreeMap();

    public TipoUsuarioVO() {
    }
    
    static{
            TipoUsuarioVO vo;
                                            
            vo = new TipoUsuarioVO();
            vo.setCodigo(new Long("1"));
            vo.setDescricao("Administrador");
            tipos.put(vo.getCodigo(),vo);
            
            vo = new TipoUsuarioVO();
            vo.setCodigo(new Long("2"));
            vo.setDescricao("Cliente");
            tipos.put(vo.getCodigo(),vo);
    }

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
    
    public static Collection obterTodos(){
        return tipos.values();   
    }
    
    public static TipoUsuarioVO obterPorCodigo(Long codigo){
        return (TipoUsuarioVO) tipos.get(codigo);
    }
    
    public static String obterNomePorCodigo(Long codigo){
        return ((TipoUsuarioVO) tipos.get(codigo)).getDescricao();
    }
 } 






