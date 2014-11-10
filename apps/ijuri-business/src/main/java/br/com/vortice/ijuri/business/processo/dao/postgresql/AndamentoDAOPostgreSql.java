/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.processo.dao.AndamentoDAOIf;
import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class AndamentoDAOPostgreSql extends PostGreSqlDAO implements AndamentoDAOIf {

	public AndamentoVO insert(AndamentoVO andamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO ANDAMENTO (ANDAMENTO_CODIGO, TIPO_ANDAMENTO_CODIGO, ")
		.append("PROCESSO_CODIGO, DES_ANDAMENTO, DTH_PRAZO, DT_LANCAMENTO, STS_INTERNO) VALUES (?, ?, ?, ?, ?, ?, ?)")
		.toString();
		try{
			andamentoVO.setCodigo(new Long(this.getSequence("SEQ_ANDAMENTO").intValue()));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
		    setParameterValueStatement(stmt, andamentoVO, new String[]{"codigo","tipoAndamento.codigo",
                    "processo.codigo","observacao","dataHoraPrazo","dataLancamento","interno"});
			stmt.execute();
			return andamentoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void update(AndamentoVO andamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE ANDAMENTO SET TIPO_ANDAMENTO_CODIGO=?, DES_ANDAMENTO=?, ")
		.append("DTH_PRAZO=?, DT_LANCAMENTO=?, STS_INTERNO=? WHERE ANDAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
            setParameterValueStatement(stmt, andamentoVO, new String[]{"tipoAndamento.codigo","observacao",
                    "dataHoraPrazo","dataLancamento","interno","codigo"});
            stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void remove(AndamentoVO andamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM ANDAMENTO WHERE ANDAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, andamentoVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public AndamentoVO findByPrimaryKey(AndamentoVO andamentoVO)throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        String sql = new StringBuffer("SELECT ANDAMENTO_CODIGO, TIPO_ANDAMENTO_CODIGO, DES_ANDAMENTO, ")
        .append(" DTH_PRAZO, STS_INTERNO, DT_LANCAMENTO FROM ANDAMENTO WHERE ")
        .append(" ANDAMENTO_CODIGO=? ").toString();
		try{
			
			conn = getConexao();
           
            stmt = conn.prepareStatement(sql);
            setParameterValueStatement(stmt, andamentoVO, new String[]{"codigo"});
            
            rs = stmt.executeQuery();
            
            return (AndamentoVO)createVO(rs, AndamentoVO.class,new String[]{"codigo","tipoAndamento.codigo",
                                                                       "observacao","dataHoraPrazo",
                                                                       "interno","dataLancamento"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}

	public Collection<AndamentoVO> findByProcesso(ProcessoVO vo) throws AmbienteException{
		Connection 			conn = null;
        PreparedStatement	stmt = null;
        ResultSet			rs = null;
		
        String sql = new StringBuffer("SELECT andamento_codigo, tipo_andamento.des_tipo_andamento, des_andamento, ")
		.append(" dth_prazo, sts_interno, dt_lancamento FROM andamento,tipo_andamento WHERE ")
        .append(" andamento.tipo_andamento_codigo = tipo_andamento.tipo_andamento_codigo ")
		.append(" AND processo_codigo = ? ")
		.append(" ORDER BY dt_lancamento DESC ").toString();
		try{
			conn = getConexao();
			
            stmt = conn.prepareStatement(sql);
			setParameterValueStatement(stmt, vo, new String[]{"codigo"});
			
            rs = stmt.executeQuery();
            
            return createCollection(rs, AndamentoVO.class,new String[]{"codigo","tipoAndamento.descricao",
                                                                       "observacao","dataHoraPrazo",
                                                                       "interno","dataLancamento"});
        }catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection<AndamentoVO> findByDiasAntecedencia(Integer diasAntecedencia) throws AmbienteException{
		StringBuilder sql = new StringBuilder()
		.append("SELECT andamento.andamento_codigo, andamento.tipo_andamento_codigo, andamento.processo_codigo, andamento.des_andamento, ")
		.append("	andamento.dt_lancamento, andamento.sts_interno, andamento.dth_prazo, tipo_andamento.des_tipo_andamento ")
		.append("	processo.str_numero_processo ")
		.append("FROM andamento ")
		.append("	JOIN tipo_andamento ON andamento.tipo_andamento_codigo=tipo_andamento.tipo_andamento_codigo ")
		.append("	JOIN processo ON andamento.processo_codigo=processo.processo_codigo ")
		.append("WHERE TO_DATE(dth_prazo, 'YYYY-MM-DD')-?=CURRENT_DATE");
		
		Connection 			conn = null;
        PreparedStatement	stmt = null;
        ResultSet			rs = null;
        
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, diasAntecedencia);
			rs = stmt.executeQuery();
			return createCollection(rs, AndamentoVO.class, new String[]{"codigo", "tipoAndamento.codigo", "processo.codigo", "descricao", 
				"dataLancamento", "interno", "dataHoraPrazo", "tipoAndamento.descricao", "processo.numero"});
		}catch (SQLException e) {
			throw new AmbienteException(e);
		}
	}
}