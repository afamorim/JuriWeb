/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.localidade.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.vortice.ijuri.business.localidade.dao.MunicipioDAOIf;
import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class MunicipioDAOPostgreSql extends PostGreSqlDAO implements MunicipioDAOIf {
	
	private static final Logger LOG = Logger.getLogger(MunicipioDAOPostgreSql.class);
	
	public MunicipioVO insert(MunicipioVO municipioVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO MUNICIPIO (MUNICIPIO_CODIGO, ESTADO_CODIGO, NOM_MUNICIPIO) VALUES ")
		.append("(?, ?, ?)").toString();
		try{
			municipioVO.setCodigo(this.getSequence("SEQ_MUNICIPIO"));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, municipioVO.getCodigo().intValue());
			stmt.setInt(2, municipioVO.getEstadoVO().getCodigo().intValue());
			stmt.setString(3, municipioVO.getNome());
			stmt.execute();
			return municipioVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void update(MunicipioVO municipioVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE MUNICIPIO SET ESTADO_CODIGO=?, NOM_MUNICIPIO=? WHERE MUNICIPIO_CODIGO=?")
		.toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, municipioVO.getEstadoVO().getCodigo().intValue());
			stmt.setString(2, municipioVO.getNome());
			stmt.setInt(3, municipioVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void remove(MunicipioVO municipioVO) throws AmbienteException, AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM MUNICIPIO WHERE MUNICIPIO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, municipioVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}

	}

	public MunicipioVO findByPrimaryKey(MunicipioVO municipioVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT M.MUNICIPIO_CODIGO, M.ESTADO_CODIGO, M.NOM_MUNICIPIO, E.NOM_ESTADO ")
			.append("FROM MUNICIPIO AS M JOIN ESTADO AS E ON M.ESTADO_CODIGO=E.ESTADO_CODIGO AND M.MUNICIPIO_CODIGO=?")
			.toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, municipioVO.getCodigo().intValue());
			rs = stmt.executeQuery();
			if (rs.next()){
				municipioVO.setEstadoVO(new EstadoVO(new Integer(rs.getInt("ESTADO_CODIGO"))));
				municipioVO.getEstadoVO().setNome(rs.getString("NOM_ESTADO"));
                municipioVO.setNome(rs.getString("NOM_MUNICIPIO"));
                municipioVO.setCodigo(new Integer(rs.getInt("MUNICIPIO_CODIGO")));
				return municipioVO;
			} else return null;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFilter(MunicipioVO municipioVO) throws AmbienteException{
		String sql = new StringBuffer("SELECT  M.MUNICIPIO_CODIGO, M.ESTADO_CODIGO, M.NOM_MUNICIPIO, E.NOM_ESTADO ")
		.append("FROM MUNICIPIO AS M JOIN ESTADO AS E ON M.ESTADO_CODIGO=E.ESTADO_CODIGO ")
		.append("WHERE (? IS NULL OR E.ESTADO_CODIGO=?) AND (? IS NULL OR M.NOM_MUNICIPIO LIKE '%' || ? || '?')")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collMunicipio = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			if (municipioVO.getEstadoVO() != null && municipioVO.getEstadoVO().getCodigo() != null){
				stmt.setInt(1, municipioVO.getEstadoVO().getCodigo().intValue());
				stmt.setInt(2, municipioVO.getEstadoVO().getCodigo().intValue());
			}else{
				stmt.setNull(1, Types.INTEGER);
				stmt.setNull(2, Types.INTEGER);
			}
			stmt.setString(3, municipioVO.getNome());
			stmt.setString(4, municipioVO.getNome());
			rs = stmt.executeQuery();
			while(rs.next()){
				MunicipioVO vo = new MunicipioVO();
				vo.setCodigo(new Integer(rs.getInt("MUNICIPIO_CODIGO")));
				vo.setEstadoVO(new EstadoVO(new Integer(rs.getInt("ESTADO_CODIGO"))));
				vo.getEstadoVO().setNome(rs.getString("NOM_ESTADO"));
				vo.setNome(rs.getString("NOM_MUNICIPIO"));
				collMunicipio.add(vo);
			}
			return collMunicipio;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			fechar(conn, stmt, rs);
		}
	}
	
	public Collection findAll() throws AmbienteException {
		String sql = new StringBuffer("SELECT  M.MUNICIPIO_CODIGO, M.ESTADO_CODIGO, M.NOM_MUNICIPIO, E.NOM_ESTADO ")
			.append("FROM MUNICIPIO AS M JOIN ESTADO AS E ON M.ESTADO_CODIGO=E.ESTADO_CODIGO").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collMunicipio = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				MunicipioVO municipioVO = new MunicipioVO();
				municipioVO.setCodigo(new Integer(rs.getInt("MUNICIPIO_CODIGO")));
				municipioVO.setEstadoVO(new EstadoVO(new Integer(rs.getInt("ESTADO_CODIGO"))));
				municipioVO.getEstadoVO().setNome(rs.getString("NOM_ESTADO"));
				municipioVO.setNome(rs.getString("NOM_MUNICIPIO"));
				collMunicipio.add(municipioVO);
			}
			return collMunicipio;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByUf(EstadoVO estadoVO) throws AmbienteException{
		String sql = new StringBuffer("SELECT  M.MUNICIPIO_CODIGO, M.ESTADO_CODIGO, M.NOM_MUNICIPIO, E.NOM_ESTADO ")
		.append("FROM MUNICIPIO AS M JOIN ESTADO AS E ON M.ESTADO_CODIGO=E.ESTADO_CODIGO AND E.ESTADO_CODIGO=? ORDER BY M.NOM_MUNICIPIO")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collMunicipio = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estadoVO.getCodigo().intValue());
			rs = stmt.executeQuery();
			
			while(rs.next()){
				MunicipioVO municipioVO = new MunicipioVO();
				municipioVO.setCodigo(new Integer(rs.getInt("MUNICIPIO_CODIGO")));
				municipioVO.setEstadoVO(new EstadoVO(new Integer(rs.getInt("ESTADO_CODIGO"))));
				municipioVO.getEstadoVO().setNome(rs.getString("NOM_ESTADO"));
				municipioVO.setNome(rs.getString("NOM_MUNICIPIO"));
				collMunicipio.add(municipioVO);
			}
			LOG.debug("collMunicipio " + collMunicipio);
			return collMunicipio;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			fechar(conn, stmt, rs);
		}
	}
	
	public Collection findNComarcaByUf(EstadoVO estadoVO) throws AmbienteException{
		String sql = new StringBuffer("SELECT M.MUNICIPIO_CODIGO, M.NOM_MUNICIPIO, M.ESTADO_CODIGO ")
		.append("FROM MUNICIPIO AS M WHERE M.ESTADO_CODIGO=? AND M.MUNICIPIO_CODIGO NOT IN ")
		.append("(SELECT MUNICIPIO_CODIGO FROM COMARCA)").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collMunicipio = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estadoVO.getCodigo().intValue());
			rs = stmt.executeQuery();
			while(rs.next()){
				MunicipioVO municipioVO = new MunicipioVO();
				municipioVO.setCodigo(new Integer(rs.getInt("MUNICIPIO_CODIGO")));
				municipioVO.setEstadoVO(new EstadoVO(new Integer(rs.getInt("ESTADO_CODIGO"))));
				municipioVO.setNome(rs.getString("NOM_MUNICIPIO"));
				collMunicipio.add(municipioVO);
			}
			return collMunicipio;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			fechar(conn, stmt, rs);
		}
	}
}
