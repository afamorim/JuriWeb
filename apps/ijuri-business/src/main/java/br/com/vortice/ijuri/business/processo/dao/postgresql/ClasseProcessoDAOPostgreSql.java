/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.vortice.ijuri.business.processo.dao.ClasseProcessoDAOIf;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class ClasseProcessoDAOPostgreSql extends PostGreSqlDAO implements ClasseProcessoDAOIf {

	public ClasseProcessoVO insert(ClasseProcessoVO classeProcessoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO CLASSE_PROCESSO (CLASSE_PROCESSO_CODIGO, DES_CLASSE_PROCESSO) VALUES (?, ?)")
		.toString();
		try{
            classeProcessoVO.setCodigo(new Long(this.getSequence("SEQ_CLASSE_PROCESSO").intValue()));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, classeProcessoVO.getCodigo().longValue());
			stmt.setString(2, classeProcessoVO.getDescricao());
			stmt.execute();
			return classeProcessoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
	}

	public void update(ClasseProcessoVO classeProcessoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE CLASSE_PROCESSO SET DES_CLASSE_PROCESSO=? WHERE CLASSE_PROCESSO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, classeProcessoVO.getDescricao());
			stmt.setLong(2, classeProcessoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}

	public void remove(ClasseProcessoVO classeProcessoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM CLASSE_PROCESSO WHERE CLASSE_PROCESSO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, classeProcessoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}

	public ClasseProcessoVO findByPrimaryKey(ClasseProcessoVO classeProcessoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT CLASSE_PROCESSO_CODIGO,DES_CLASSE_PROCESSO FROM CLASSE_PROCESSO WHERE CLASSE_PROCESSO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, classeProcessoVO.getCodigo().longValue());
			rs = stmt.executeQuery();
            return (ClasseProcessoVO)createVO(rs,ClasseProcessoVO.class, new String[]{"codigo","descricao"});
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
		Collection collClasseProc = new ArrayList();
		String sql = new StringBuffer("SELECT CLASSE_PROCESSO_CODIGO, DES_CLASSE_PROCESSO FROM CLASSE_PROCESSO").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
            ClasseProcessoVO classeProcessoVO = null;
			while (rs.next()){
                classeProcessoVO = new ClasseProcessoVO(new Long(rs.getLong("CLASSE_PROCESSO_CODIGO")));
				classeProcessoVO.setDescricao(rs.getString("DES_CLASSE_PROCESSO"));
				collClasseProc.add(classeProcessoVO);
			}
			return collClasseProc;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}
    
    public Collection findByFilter(ClasseProcessoVO classeProcessoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        Collection collClasseProcesso = new ArrayList();
        try{
            conn = getConexao();
            
            sql = new StringBuffer();
            sql.append("SELECT CLASSE_PROCESSO_CODIGO, DES_CLASSE_PROCESSO FROM CLASSE_PROCESSO ");
            sql.append("WHERE ((UPPER(DES_CLASSE_PROCESSO) LIKE '%' || UPPER(?) || '%') OR (? IS NULL)) "); 
            
            stmt = conn.prepareStatement(sql.toString());
            
            stmt.setString(1, classeProcessoVO.getDescricao());
            stmt.setString(2, classeProcessoVO.getDescricao());
            
            rs = stmt.executeQuery();
            while (rs.next()){
                ClasseProcessoVO classeProcessoNovo = new ClasseProcessoVO(new Long(rs.getInt("CLASSE_PROCESSO_CODIGO")));
                classeProcessoNovo.setDescricao(rs.getString("DES_CLASSE_PROCESSO"));
                collClasseProcesso.add(classeProcessoNovo);
            }
            
            return collClasseProcesso;
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
    }


}