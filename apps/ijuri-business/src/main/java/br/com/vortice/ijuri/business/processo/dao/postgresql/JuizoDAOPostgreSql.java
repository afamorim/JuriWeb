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

import br.com.vortice.ijuri.business.processo.dao.JuizoDAOIf;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class JuizoDAOPostgreSql extends PostGreSqlDAO implements JuizoDAOIf {

	public JuizoVO insert(JuizoVO juizoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("INSERT INTO JUIZO (JUIZO_CODIGO, DES_JUIZO) VALUES (?, ?)").toString();
		try{
			juizoVO.setCodigo(this.getSequence("SEQ_JUIZO"));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, juizoVO.getCodigo().intValue());
			stmt.setString(2, juizoVO.getDescricao());
			stmt.execute();
			return juizoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx); 
		}finally{
            fechar(conn,stmt,null);
        }
        
	}

	public void update(JuizoVO juizoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("UPDATE JUIZO SET DES_JUIZO=? WHERE JUIZO_CODIGO=?)").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, juizoVO.getDescricao());
			stmt.setInt(2, juizoVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
	}

	public void remove(JuizoVO juizoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("DELETE FROM JUIZO WHERE JUIZO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, juizoVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
		    tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}

	public JuizoVO findByPrimaryKey(JuizoVO juizoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT JUIZO_CODIGO, DES_JUIZO FROM JUIZO WHERE JUIZO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, juizoVO.getCodigo().intValue());
			rs = stmt.executeQuery();
			if (rs.next()){
				juizoVO.setDescricao(rs.getString("DES_JUIZO"));
			}
			return juizoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}
    
    public Collection findByFilter(JuizoVO juizoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        Collection collJuizo = new ArrayList();
        try{
            conn = getConexao();
            
            sql = new StringBuffer();
            sql.append("SELECT JUIZO_CODIGO, DES_JUIZO FROM JUIZO ");
            sql.append("WHERE ((UPPER(DES_JUIZO) LIKE '%' || UPPER(?) || '%') OR (? IS NULL)) "); 
            
            stmt = conn.prepareStatement(sql.toString());
            
            stmt.setString(1, juizoVO.getDescricao());
            stmt.setString(2, juizoVO.getDescricao());
            
            rs = stmt.executeQuery();
            while (rs.next()){
                JuizoVO juizoNovo = new JuizoVO(new Integer(rs.getInt("JUIZO_CODIGO")));
                juizoNovo.setDescricao(rs.getString("DES_JUIZO"));
                collJuizo.add(juizoNovo);
            }
            
            return collJuizo;
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
		String sql = new StringBuffer("SELECT JUIZO_CODIGO, DES_JUIZO FROM JUIZO").toString();
		Collection collJuizo = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				JuizoVO juizoVO = new JuizoVO(new Integer(rs.getInt("JUIZO_CODIGO")));
				juizoVO.setDescricao(rs.getString("DES_JUIZO"));
				collJuizo.add(juizoVO);
			}
			return collJuizo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}
	
	public Collection findNJuizoByTipoAndamento(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT DISTINCT J.JUIZO_CODIGO, J.DES_JUIZO FROM JUIZO AS J ")
		.append("WHERE J.JUIZO_CODIGO NOT IN (SELECT JUIZO_CODIGO FROM TIPO_ANDAMENTO_JUIZO AS TAJ ")
		.append("WHERE TAJ.TIPO_ANDAMENTO_CODIGO=?)").toString();
		Collection collJuizo = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tipoAndamentoVO.getCodigo().intValue());
			rs = stmt.executeQuery();
			while (rs.next()){
				JuizoVO juizoVO = new JuizoVO(new Integer(rs.getInt("JUIZO_CODIGO")));
				juizoVO.setDescricao(rs.getString("DES_JUIZO"));
				collJuizo.add(juizoVO);
			}
			return collJuizo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}
}
