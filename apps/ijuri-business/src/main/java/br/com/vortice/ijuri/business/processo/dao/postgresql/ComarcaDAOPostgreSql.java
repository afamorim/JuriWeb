/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import br.com.vortice.ijuri.business.processo.dao.ComarcaDAOIf;
import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.localidade.MunicipioVO;
import br.com.vortice.ijuri.core.processo.ComarcaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class ComarcaDAOPostgreSql extends PostGreSqlDAO implements ComarcaDAOIf {

	public ComarcaVO insert(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO COMARCA (COMARCA_CODIGO, MUNICIPIO_CODIGO) VALUES (?, ?)")
		.toString();
		try{
			comarcaVO.setCodigo(new Long(this.getSequence("SEQ_COMARCA").longValue()));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, comarcaVO.getCodigo().longValue());
			stmt.setInt(2, comarcaVO.getMunicipio().getCodigo().intValue());
			stmt.execute();
			return comarcaVO;
		}catch(SQLException sqlEx){
			tratarExcessaoUnique(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void update(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE COMARCA SET MUNICIPIO_CODIGO=? WHERE COMARCA_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comarcaVO.getMunicipio().getCodigo());
			stmt.setLong(2, comarcaVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoUnique(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void remove(ComarcaVO comarcaVO) throws AmbienteException, AplicacaoException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM COMARCA WHERE COMARCA_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, comarcaVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public ComarcaVO findByPrimaryKey(ComarcaVO comarcaVO)throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.COMARCA_CODIGO, c.MUNICIPIO_CODIGO, m.estado_codigo " +
				"FROM COMARCA AS C JOIN MUNICIPIO AS M ON C.MUNICIPIO_CODIGO=M.MUNICIPIO_CODIGO " +
				"WHERE COMARCA_CODIGO=?";
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, comarcaVO.getCodigo().longValue());
			rs = stmt.executeQuery();
			if (rs.next())
				comarcaVO.setMunicipio(new MunicipioVO(new Integer(rs.getInt("MUNICIPIO_CODIGO"))));
				comarcaVO.getMunicipio().setEstadoVO(new EstadoVO(rs.getInt("estado_codigo")));
			return comarcaVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection<ComarcaVO> findByFilter(ComarcaVO comarcaVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<ComarcaVO> collComarca = new ArrayList<ComarcaVO>();
		String sql = new StringBuffer("SELECT C.COMARCA_CODIGO, C.MUNICIPIO_CODIGO, M.NOM_MUNICIPIO, E.ESTADO_CODIGO, ")
		.append("E.NOM_ESTADO FROM COMARCA AS C JOIN MUNICIPIO AS M ON C.MUNICIPIO_CODIGO=M.MUNICIPIO_CODIGO ")
		.append("JOIN ESTADO AS E ON M.ESTADO_CODIGO=E.ESTADO_CODIGO ")
		.append("WHERE (? IS NULL OR E.ESTADO_CODIGO=?) AND (? IS NULL OR M.MUNICIPIO_CODIGO=?) ORDER BY M.NOM_MUNICIPIO")
		.toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			if (comarcaVO.getMunicipio() != null && comarcaVO.getMunicipio().getEstadoVO() != null){
				stmt.setInt(1, comarcaVO.getMunicipio().getEstadoVO().getCodigo().intValue());
				stmt.setInt(2, comarcaVO.getMunicipio().getEstadoVO().getCodigo().intValue());
			}else{
				stmt.setNull(1, Types.INTEGER);
				stmt.setNull(2, Types.INTEGER);
			}
			if (comarcaVO.getMunicipio() != null){
				stmt.setInt(3, comarcaVO.getMunicipio().getCodigo().intValue());
				stmt.setInt(4, comarcaVO.getMunicipio().getCodigo().intValue());
			}else{
				stmt.setNull(3, Types.INTEGER);
				stmt.setNull(4, Types.INTEGER);
			}
			rs = stmt.executeQuery();
			while (rs.next()){
				ComarcaVO vo = new ComarcaVO(new Long(rs.getLong("COMARCA_CODIGO")));
				vo.setMunicipio(new MunicipioVO(new Integer(rs.getInt("MUNICIPIO_CODIGO"))));
				vo.getMunicipio().setNome(rs.getString("NOM_MUNICIPIO"));
				vo.getMunicipio().setEstadoVO(new EstadoVO(new Integer(rs.getInt("ESTADO_CODIGO"))));
				vo.getMunicipio().getEstadoVO().setNome(rs.getString("NOM_ESTADO"));
				collComarca.add(vo);
			}
			return collComarca;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection<ComarcaVO> findAll() throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT COMARCA_CODIGO, M.MUNICIPIO_CODIGO,M.NOM_MUNICIPIO FROM COMARCA C,MUNICIPIO M ")
                              .append("WHERE C.MUNICIPIO_CODIGO = M.MUNICIPIO_CODIGO ").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			return createCollection(rs, ComarcaVO.class, new String[]{"codigo","municipio.codigo","municipio.nome"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}

}
