/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.site.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.site.dao.ParceiroDAOIf;
import br.com.vortice.ijuri.core.site.ParceiroVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class ParceiroDAOPostgreSql extends PostGreSqlDAO implements ParceiroDAOIf{

	public ParceiroVO insert(ParceiroVO parceiroVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try{
            conn = getConexao();
			
            sql = new StringBuffer("INSERT INTO PARCEIRO(pessoa_codigo,des_servico) ")
                           .append(" VALUES (?,?) ").toString();
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, parceiroVO, 
                    new String[]{"codigo","resumoServico"});
			
            stmt.execute();
            
			return parceiroVO;
		}catch(SQLException sqlEx){
			tratarExcessaoUnique(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
        
        return null;
	}
    

    public void remove(ParceiroVO parceiro) throws AmbienteException,AplicacaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            sql = "DELETE FROM PARCEIRO WHERE PESSOA_CODIGO = ? ";
            
            conn = getConexao();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1,parceiro.getCodigo().longValue());
            
            stmt.execute();
            
        }catch(SQLException sqlEx){
            tratarExcessaoRemove(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }

	public void update(ParceiroVO parceiroVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("UPDATE PARCEIRO SET des_servico = ?")
                   .append("        WHERE pessoa_codigo = ? ").toString();
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, parceiroVO, 
                    new String[]{"resumoServico","codigo"});
            
            stmt.execute();
        }catch(SQLException sqlEx){
            tratarExcessaoUnique(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
	}


	public ParceiroVO findByPrimaryKey(ParceiroVO parceiroVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                                .append(" PE.PESSOA_CODIGO,PE.NOME,PE.STR_EMAIL,TELEFONE,DES_SERVICO ") 
                                .append(" FROM ")
                                .append("   PARCEIRO INNER JOIN PESSOA PE ")
                                .append("   ON (PARCEIRO.PESSOA_CODIGO = PE.PESSOA_CODIGO) ")
                                .append(" WHERE PE.PESSOA_CODIGO = ? ").toString();
            
			stmt = conn.prepareStatement(sql);
			
            stmt.setLong(1, parceiroVO.getCodigo().longValue());
			
            rs = stmt.executeQuery();
            
            return (ParceiroVO)createVO(rs,ParceiroVO.class, 
                    new String[]{"codigo","nome","email",
                                 "telefone","resumoServico"});
            
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

	public Collection findByFilter(ParceiroVO parceiro) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                            .append(" PE.PESSOA_CODIGO,PE.NOME,PE.STR_EMAIL,TELEFONE,DES_SERVICO ") 
                            .append(" FROM ")
                            .append("   PARCEIRO INNER JOIN PESSOA PE ")
                            .append("   ON (PARCEIRO.PESSOA_CODIGO = PE.PESSOA_CODIGO) ")
                            .append(" WHERE (? IS NULL OR PE.PESSOA_CODIGO = ?) ")
                            .append(" ORDER BY PE.NOME ").toString();

            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatementFilter(stmt, parceiro, new String[]{"codigo"});
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,ParceiroVO.class, 
                    new String[]{"codigo","nome","email",
                                 "telefone","resumoServico"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        } 
    }
    
    public Collection findAll() throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
            .append(" PE.PESSOA_CODIGO,PE.NOME,PE.STR_EMAIL,TELEFONE,DES_SERVICO ") 
            .append(" FROM ")
            .append("   PARCEIRO INNER JOIN PESSOA PE ")
            .append("   ON (PARCEIRO.PESSOA_CODIGO = PE.PESSOA_CODIGO) ")
            .append(" ORDER BY PE.NOME ").toString();
           
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,ParceiroVO.class, 
                new String[]{"codigo","nome","email",
                             "telefone","resumoServico"});

            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        } 
    }



}