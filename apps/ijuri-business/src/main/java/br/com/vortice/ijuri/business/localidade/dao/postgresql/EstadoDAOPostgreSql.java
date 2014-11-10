/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.localidade.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.vortice.ijuri.business.localidade.dao.EstadoDAOIf;
import br.com.vortice.ijuri.core.localidade.EstadoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class EstadoDAOPostgreSql extends PostGreSqlDAO implements EstadoDAOIf {
	
	public EstadoVO insert(EstadoVO estadoVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO ESTADO (ESTADO_CODIGO, NOM_ESTADO, SIG_ESTADO) VALUES (?, ?, ?)")
		.toString();
		try{
			estadoVO.setCodigo(this.getSequence("SEQ_ESTADO"));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estadoVO.getCodigo().intValue());
			stmt.setString(2, estadoVO.getNome());
			stmt.setString(3, estadoVO.getSigla());
			stmt.execute();
			return estadoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}
	
	public void update(EstadoVO estadoVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE ESTADO SET NOM_ESTADO=?, SIG_ESTADO=? WHERE ESTADO_CODIGO=?")
		.toString();
		try{	
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, estadoVO.getNome());
			stmt.setString(2, estadoVO.getSigla());
			stmt.setInt(3, estadoVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}
	
	public void remove(EstadoVO estadoVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM ESTADO WHERE ESTADO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estadoVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}
	
	public EstadoVO finByPrimaryKey(EstadoVO estadoVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT NOM_ESTADO, SIG_ESTADO FROM ESTADO WHERE ESTADO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()){
				estadoVO.setNome(rs.getString("NOM_ESTADO"));
				estadoVO.setSigla((rs.getString("SIG_ESTADO")));
				return estadoVO;
			}else return null;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}
	
	public Collection findAll() throws AmbienteException{
		String sql = "SELECT ESTADO_CODIGO, NOM_ESTADO, SIG_ESTADO FROM ESTADO";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collEstado = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				EstadoVO estadoVO = new EstadoVO();
				estadoVO.setCodigo(new Integer(rs.getInt("ESTADO_CODIGO")));
				estadoVO.setNome(rs.getString("NOM_ESTADO"));
				estadoVO.setSigla(rs.getString("SIG_ESTADO"));
				collEstado.add(estadoVO);
			}
			return collEstado;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			fechar(conn, stmt, rs);
		}
	}
}
