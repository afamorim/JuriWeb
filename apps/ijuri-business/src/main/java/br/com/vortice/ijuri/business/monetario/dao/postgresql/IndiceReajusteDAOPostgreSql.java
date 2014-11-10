package br.com.vortice.ijuri.business.monetario.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.monetario.dao.IndiceReajusteDAOIf;
import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

public class IndiceReajusteDAOPostgreSql extends PostGreSqlDAO implements IndiceReajusteDAOIf{
	
	public IndiceReajusteVO insert(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("INSERT INTO INDICE_REAJUSTE (INDICE_REAJUSTE_CODIGO, STR_NOME, ")
		.append("DES_INDICE_REAJUSTE) VALUES (?, ?, ?)").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			vo.setCodigo(this.getSequence("SEQ_INDICE_REAJUSTE"));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			stmt.setString(2, vo.getNome());
			stmt.setString(3, vo.getDescricao());
			stmt.execute();
			return vo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
		
	}
	
	public void update(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("UPDATE INDICE_REAJUSTE SET STR_NOME=?, DES_INDICE_REAJUSTE=? ")
		.append("WHERE INDICE_REAJUSTE_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getNome());
			stmt.setString(2, vo.getDescricao());
			stmt.setInt(3, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void remove(IndiceReajusteVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("DELETE FROM INDICE_REAJUSTE WHERE INDICE_REAJUSTE_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public IndiceReajusteVO findByPrimaryKey(IndiceReajusteVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT INDICE_REAJUSTE_CODIGO, STR_NOME, DES_INDICE_REAJUSTE ")
		.append("FROM INDICE_REAJUSTE WHERE INDICE_REAJUSTE_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			vo = (IndiceReajusteVO)this.createVO(rs, IndiceReajusteVO.class, new String[]{"codigo", "nome", 
				"descricao"});
			return vo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findAll() throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConexao();
			
            String sql = new StringBuffer("SELECT INDICE_REAJUSTE_CODIGO, STR_NOME, ")
                                  .append("       DES_INDICE_REAJUSTE ")
                                  .append("FROM INDICE_REAJUSTE ORDER BY STR_NOME").toString();
            stmt = conn.prepareStatement(sql);
			
            rs = stmt.executeQuery();
			
			return this.createCollection(rs, IndiceReajusteVO.class, new String[]{"codigo", "nome", "descricao"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFilter(IndiceReajusteVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT INDICE_REAJUSTE_CODIGO, STR_NOME, DES_INDICE_REAJUSTE ")
		.append("FROM INDICE_REAJUSTE WHERE (? IS NULL OR UPPER(STR_NOME) LIKE  '%' || UPPER(?) || '%') ")
		.append("AND (? IS NULL OR UPPER(DES_INDICE_REAJUSTE) LIKE  '%' || UPPER(?) || '%')")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getNome());
			stmt.setString(2, vo.getNome());
			stmt.setString(3, vo.getDescricao());
			stmt.setString(4, vo.getDescricao());
			rs = stmt.executeQuery();
			return this.createCollection(rs, IndiceReajusteVO.class, new String[]{"codigo", "nome", "descricao"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
}
