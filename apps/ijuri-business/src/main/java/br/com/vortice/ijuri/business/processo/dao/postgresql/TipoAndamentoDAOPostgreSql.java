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

import br.com.vortice.ijuri.business.processo.dao.TipoAndamentoDAOIf;
import br.com.vortice.ijuri.core.processo.FiltroTipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class TipoAndamentoDAOPostgreSql extends PostGreSqlDAO implements TipoAndamentoDAOIf {

	public TipoAndamentoVO insert(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO TIPO_ANDAMENTO (TIPO_ANDAMENTO_CODIGO, DES_TIPO_ANDAMENTO) ")
		.append("VALUES (?, ?)").toString();
		try{
			tipoAndamentoVO.setCodigo(new Long(this.getSequence("SEQ_TIPO_ANDAMENTO").longValue()));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, tipoAndamentoVO.getCodigo().longValue());
			stmt.setString(2, tipoAndamentoVO.getDescricao());
			stmt.execute();
			return tipoAndamentoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void insertJuizoInTipoAndamento(TipoAndamentoVO tipoAndamentoVO, JuizoVO juizoVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO TIPO_ANDAMENTO_JUIZO (TIPO_ANDAMENTO_CODIGO, JUIZO_CODIGO) ")
		.append("VALUES (?, ?)").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, tipoAndamentoVO.getCodigo().longValue());
			stmt.setInt(2, juizoVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void removeAllJuizo(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM TIPO_ANDAMENTO_JUIZO WHERE TIPO_ANDAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, tipoAndamentoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void update(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE TIPO_ANDAMENTO SET DES_TIPO_ANDAMENTO=? ")
		.append("WHERE TIPO_ANDAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tipoAndamentoVO.getDescricao());
			stmt.setLong(2, tipoAndamentoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	
	public void remove(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM TIPO_ANDAMENTO WHERE TIPO_ANDAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, tipoAndamentoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public TipoAndamentoVO findByPrimaryKey(TipoAndamentoVO tipoAndamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT TAJ.JUIZO_CODIGO, J.DES_JUIZO, TA.DES_TIPO_ANDAMENTO ")
		.append("FROM TIPO_ANDAMENTO AS TA JOIN TIPO_ANDAMENTO_JUIZO AS TAJ ")
		.append("ON TA.TIPO_ANDAMENTO_CODIGO=TAJ.TIPO_ANDAMENTO_CODIGO ")
		.append("JOIN JUIZO AS J ON J.JUIZO_CODIGO=TAJ.JUIZO_CODIGO ")
		.append("WHERE TA.TIPO_ANDAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, tipoAndamentoVO.getCodigo().longValue());
			rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()){
				if (i == 0) tipoAndamentoVO.setDescricao(rs.getString("DES_TIPO_ANDAMENTO"));
				++i;
				JuizoVO juizo = new JuizoVO(new Integer(rs.getInt("JUIZO_CODIGO")));
				juizo.setDescricao(rs.getString("DES_JUIZO"));
				tipoAndamentoVO.addJuizo(juizo);
			}
			return tipoAndamentoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}

	public Collection findAll() throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT TIPO_ANDAMENTO_CODIGO, DES_TIPO_ANDAMENTO FROM TIPO_ANDAMENTO ORDER BY DES_TIPO_ANDAMENTO ").toString();
		Collection collTipoAndamento = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				TipoAndamentoVO tipoAndamentoVO = new TipoAndamentoVO(new Long(rs.getLong("TIPO_ANDAMENTO_CODIGO")));
				tipoAndamentoVO.setDescricao(rs.getString("DES_TIPO_ANDAMENTO"));
				collTipoAndamento.add(tipoAndamentoVO);
			}
			return collTipoAndamento;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFilter(FiltroTipoAndamentoVO filtro) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT DISTINCT TA.TIPO_ANDAMENTO_CODIGO, TA.DES_TIPO_ANDAMENTO ")
		.append("FROM TIPO_ANDAMENTO AS TA JOIN TIPO_ANDAMENTO_JUIZO TAJ ")
		.append("ON TA.TIPO_ANDAMENTO_CODIGO=TAJ.TIPO_ANDAMENTO_CODIGO WHERE ")
		.append("(? IS NULL OR UPPER(TA.DES_TIPO_ANDAMENTO) LIKE '%' || UPPER(?) || '%') ")
		.append("AND (? IS NULL OR TAJ.JUIZO_CODIGO=?)")
		.toString();
		Collection collTipoAndamento = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			int i = 0;
			stmt.setString(++i, filtro.getDescricao());
			stmt.setString(++i, filtro.getDescricao());
			if (filtro.getJuizo() == null || filtro.getJuizo().getCodigo() == null){
				stmt.setNull(++i, Types.INTEGER);
				stmt.setNull(++i, Types.INTEGER);
			}else{
				stmt.setInt(++i, filtro.getJuizo().getCodigo().intValue());
				stmt.setInt(++i, filtro.getJuizo().getCodigo().intValue());
			}
			rs = stmt.executeQuery();
			while(rs.next()){
				TipoAndamentoVO tipoAndamentoVO = new TipoAndamentoVO(new Long(rs.getLong("TIPO_ANDAMENTO_CODIGO")));
				tipoAndamentoVO.setDescricao(rs.getString("DES_TIPO_ANDAMENTO"));
				collTipoAndamento.add(tipoAndamentoVO);
			}
			return collTipoAndamento;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
    
    public Collection findByJuizo(JuizoVO juizoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = new StringBuffer("SELECT TA.TIPO_ANDAMENTO_CODIGO, TA.DES_TIPO_ANDAMENTO ") 
                                .append(" FROM TIPO_ANDAMENTO_JUIZO TAJ,TIPO_ANDAMENTO TA ")
                                .append(" WHERE TAJ.JUIZO_CODIGO = ? ")
                                .append("       AND TA.TIPO_ANDAMENTO_CODIGO = TAJ.TIPO_ANDAMENTO_CODIGO ")
                                .append(" ORDER BY DES_TIPO_ANDAMENTO ").toString();
        try{
            conn = getConexao();
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, juizoVO,new String[]{"codigo"});
            
            rs = stmt.executeQuery();
            
            return createCollection(rs, TipoAndamentoVO.class, new String[]{"codigo","descricao"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            this.fechar(conn, stmt, rs);
        }
    }

}
