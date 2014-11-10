package br.com.vortice.ijuri.business.site.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import br.com.vortice.ijuri.business.site.dao.LinkDAOIf;
import br.com.vortice.ijuri.core.site.LinkVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

public class LinkDAOPostgreSql extends PostGreSqlDAO implements LinkDAOIf{
	
	public LinkVO insert(LinkVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("INSERT INTO LINK (LINK_CODIGO, NOM_LINK, STR_ENDERECO) VALUES (?, ?, ?)")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			vo.setCodigo(new Long(this.getSequence("SEQ_LINK").intValue()));
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			stmt.setString(2, vo.getNome());
			stmt.setString(3, vo.getEndereco());
			stmt.execute();
			return vo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void update(LinkVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("UPDATE LINK SET NOM_LINK=?, STR_ENDERECO=? WHERE LINK_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getNome());
			stmt.setString(2, vo.getEndereco());
			stmt.setInt(3, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void remove(LinkVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("DELETE FROM LINK WHERE LINK_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public LinkVO findByPrimaryKey(LinkVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT NOM_LINK, STR_ENDERECO FROM LINK WHERE LINK_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			if (rs.next()){
				vo.setNome(rs.getString("NOM_LINK"));
				vo.setEndereco(rs.getString("STR_ENDERECO"));
				return vo;
			}else{
				return null;
			}
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFilter(LinkVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT LINK_CODIGO, NOM_LINK, STR_ENDERECO FROM LINK ")
		.append(" WHERE (? IS NULL OR NOM_LINK LIKE '%' || ? || '%')")
        .append(" ORDER BY NOM_LINK ").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			if (vo.getNome() != null){
				stmt.setString(1, vo.getNome());
				stmt.setString(2, vo.getNome());
			}else{
				stmt.setNull(1, Types.VARCHAR);
				stmt.setNull(2, Types.VARCHAR);
			}
			
			rs = stmt.executeQuery();
			return createCollection(rs, LinkVO.class, new String[]{"codigo", "nome", "endereco"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
}