/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.site.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.site.dao.JurisprudenciaDAOIf;
import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.core.site.JurisprudenciaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class JurisprudenciaDAOPostgreSql extends PostGreSqlDAO implements JurisprudenciaDAOIf{

	public JurisprudenciaVO insert(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try{
            conn = getConexao();
			
            sql = new StringBuffer("INSERT INTO JURISPRUDENCIA(jurisprudencia_codigo,")
                   .append("                            documento_codigo,str_titulo,area_atuacao_codigo) ")
                   .append(" VALUES (?,?,?,?) ").toString();
            stmt = conn.prepareStatement(sql);
            
            jurisprudenciaVO.setCodigo(new Long(this.getSequence("SEQ_JURISPRUDENCIA").intValue()));
            setParameterValueStatement(stmt, jurisprudenciaVO, 
                    new String[]{"codigo","documento.codigo","titulo","areaAtuacao.codigo"});
			
            stmt.execute();
            
			return jurisprudenciaVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
	}

    public void remove(JurisprudenciaVO jurisprudencia) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            sql = "DELETE FROM JURISPRUDENCIA WHERE JURISPRUDENCIA_CODIGO = ? ";
            
            conn = getConexao();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1,jurisprudencia.getCodigo().longValue());
            
            stmt.execute();
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }

	public void update(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("UPDATE JURISPRUDENCIA SET documento_codigo = ?, str_titulo = ?,")
                   .append("                                  area_atuacao_codigo=? ")
                   .append("        WHERE jurisprudencia_codigo = ? ").toString();
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, jurisprudenciaVO, 
                    new String[]{"documento.codigo","titulo","areaAtuacao.codigo","codigo"});
            
            stmt.execute();
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
	}

	public JurisprudenciaVO findByPrimaryKey(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
            .append("                   jurisprudencia_codigo, str_titulo, ") 
            .append("                   d.documento_codigo,d.nom_documento,area_atuacao_codigo,d.blb_documento ") 
            .append("               FROM jurisprudencia j ")
            .append("                   INNER JOIN documento d ")
            .append("                   ON (j.documento_codigo = d.documento_codigo) ")
            .append("               WHERE jurisprudencia_codigo = ? ").toString();
            
			stmt = conn.prepareStatement(sql);
			
            stmt.setLong(1, jurisprudenciaVO.getCodigo().longValue());
			
            rs = stmt.executeQuery();
            
            return (JurisprudenciaVO)createVO(rs,JurisprudenciaVO.class, 
                    new String[]{"codigo","titulo","documento.codigo",
                                 "documento.nome","areaAtuacao.codigo","documento.bytes"});
            
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

	public Collection findByFilter(JurisprudenciaVO jurisprudencia) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
            .append("                   jurisprudencia_codigo, str_titulo, ") 
            .append("                   d.documento_codigo,d.nom_documento,area_atuacao_codigo ") 
            .append("               FROM jurisprudencia j ")
            .append("                   INNER JOIN documento d ")
            .append("                   ON (j.documento_codigo = d.documento_codigo) ")
            .append("               WHERE (? is null  OR d.documento_codigo = ?) ") 
            .append("                     AND (? is null  OR area_atuacao_codigo = ? ) ")
            .append("                     AND (? is null  OR str_titulo LIKE ? ||'%' ) ").toString();
  
            
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatementFilter(stmt, jurisprudencia, 
                    new String[]{"documento.codigo","areaAtuacao.codigo","titulo"});
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,JurisprudenciaVO.class, 
                    new String[]{"codigo","titulo","documento.codigo","documento.nome","areaAtuacao.codigo"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        } 
    }
    
    public Collection findByAreaAtuacao(AreaAtuacaoVO area) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
            .append("                   jurisprudencia_codigo, str_titulo, ") 
            .append("                   d.documento_codigo,d.nom_documento,area_atuacao_codigo ") 
            .append("               FROM jurisprudencia j ")
            .append("                   INNER JOIN documento d ")
            .append("                   ON (j.documento_codigo = d.documento_codigo) ")
            .append("               WHERE area_atuacao_codigo = ? ").toString();
  
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, area.getCodigo().intValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,JurisprudenciaVO.class, 
                    new String[]{"codigo","titulo","documento.codigo","documento.nome","areaAtuacao.codigo"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        } 
    }



}