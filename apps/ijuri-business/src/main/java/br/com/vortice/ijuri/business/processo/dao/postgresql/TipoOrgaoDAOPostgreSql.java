/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.processo.dao.TipoOrgaoDAOIf;
import br.com.vortice.ijuri.core.processo.TipoOrgaoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class TipoOrgaoDAOPostgreSql extends PostGreSqlDAO implements TipoOrgaoDAOIf {

	public TipoOrgaoVO insert(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("INSERT INTO TIPO_ORGAO_JUDICIARIO (TIPO_ORGAO_JUDICIARIO_CODIGO)").toString();
		try{
			
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			return tipoOrgaoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}

	public void update(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("").toString();
		try{
			
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}

	public void remove(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("").toString();
		try{
			
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}

	public TipoOrgaoVO findByPrimaryKey(TipoOrgaoVO tipoOrgaoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("").toString();
		try{
			
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			return tipoOrgaoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}

	public Collection findAll() throws AmbienteException {
		return null;
	}

}
