package br.com.vortice.ijuri.business.documento.rn.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.documento.rn.dao.DocumentoDAOIf;
import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.core.documento.DocumentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

public class DocumentoDAOPostgreSql extends PostGreSqlDAO implements DocumentoDAOIf{ 
    
    
    public DocumentoVO insert(DocumentoVO documentoVO) throws AmbienteException, AplicacaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
    
        try{
            conn = getConexao();
            
            sql = new StringBuffer("INSERT INTO documento(")
                    .append("           documento_codigo, diretorio_codigo, nom_documento, blb_documento) ") 
                    .append("       VALUES(?, ?, ?, ?) ").toString();
                    
                   
            stmt = conn.prepareStatement(sql);
            
            documentoVO.setCodigo(new Long(this.getSequence("SEQ_DOCUMENTO").intValue()));
            setParameterValueStatement(stmt, documentoVO, 
                    new String[]{"codigo","diretorio.codigo","nome","bytes"});
            
            stmt.execute();
            
            return documentoVO;
        }catch(SQLException sqlEx){
            if (sqlEx.getMessage()!=null && sqlEx.getMessage().indexOf("could not convert")>0){
               throw new AplicacaoException("O nome do arquivo � inv�lido. Renomei o arquivo sem acentos e sem espaço."); 
            }  
        }finally{
            fechar(conn,stmt,null);
        }
        return null;
    }
    
    public void remove(DocumentoVO documentoVO) throws AmbienteException,AplicacaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
    
        try{
            conn = getConexao();
            
            sql = new StringBuffer("DELETE FROM documento")
                    .append("       WHERE documento_codigo = ? ").toString();
                    
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, documentoVO, 
                    new String[]{"codigo"});
            
            stmt.execute();
            
        }catch(SQLException sqlEx){
            tratarExcessaoRemove(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }

    public DocumentoVO findByPrimaryKey(DocumentoVO documentoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("     documento_codigo, diretorio_codigo, nom_documento, blb_documento ") 
                        .append("   FROM DOCUMENTO  ")
                        .append("   WHERE documento_codigo = ? ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, documentoVO.getCodigo().longValue());
            
            rs = stmt.executeQuery();
            
            return (DocumentoVO)createVO(rs,DocumentoVO.class, 
                    new String[]{"codigo","diretorio.codigo","nome","bytes"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
    }
    
    public Collection findByDiretorio(DiretorioVO diretorioVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
            .append("     documento_codigo, diretorio_codigo, nom_documento ") 
            .append("   FROM DOCUMENTO  ")
            .append("   WHERE diretorio_codigo = ? ")
            .append("   ORDER BY nom_documento ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, diretorioVO.getCodigo().longValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,DocumentoVO.class, 
                    new String[]{"codigo","diretorio.codigo","nome"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
    }
}
