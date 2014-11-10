package br.com.vortice.ijuri.business.monetario.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;

import br.com.vortice.ijuri.business.monetario.dao.PeriodoIndiceDAOIf;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceFiltroAssembler;
import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

public class PeriodoIndiceDAOPostgre extends PostGreSqlDAO implements PeriodoIndiceDAOIf {

	public PeriodoIndiceVO insert(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("INSERT INTO PERIODO_INDICE (PERIODO_INDICE_CODIGO, INDICE_REAJUSTE_CODIGO, ")
		.append("MES, ANO, VALOR) VALUES (?, ?, ?, ?, ?)").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			vo.setCodigo(this.getSequence("SEQ_PERIODO_INDICE"));
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			int i = 0;
			stmt.setInt(++i, vo.getCodigo().intValue());
			stmt.setInt(++i, vo.getIndiceReajuste().getCodigo().intValue());
			stmt.setInt(++i, vo.getMes().intValue());
			stmt.setInt(++i, vo.getAno().intValue());
			stmt.setFloat(++i, vo.getValor().floatValue());
			stmt.execute();
			return vo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
		
	}
    
    public void insert(Collection valores) throws AmbienteException, AplicacaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = this.getConexao();
            String sql = new StringBuffer("INSERT INTO PERIODO_INDICE (PERIODO_INDICE_CODIGO, INDICE_REAJUSTE_CODIGO, ")
                                  .append("MES, ANO, VALOR) VALUES (?, ?, ?, ?, ?)").toString();
            stmt = conn.prepareStatement(sql);
            for (Iterator iter = valores.iterator(); iter.hasNext();) {
                PeriodoIndiceVO vo = (PeriodoIndiceVO) iter.next();
                vo.setCodigo(this.getSequence("SEQ_PERIODO_INDICE"));
                this.setParameterValueStatement(stmt, vo, new String[]{"codigo","indiceReajuste.codigo","mes","ano","valor"});
                stmt.addBatch();
            }
            stmt.executeBatch();
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            this.fechar(conn, stmt, null);
        }
        
    }

	public void update(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("UPDATE PERIODO_INDICE SET INDICE_REAJUSTE_CODIGO=?, MES=?, ANO=?, VALOR=? ")
		.append("WHERE PERIODO_INDICE_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			int i = 0;
			stmt.setInt(++i, vo.getIndiceReajuste().getCodigo().intValue());
			stmt.setInt(++i, vo.getMes().intValue());
			stmt.setInt(++i, vo.getAno().intValue());
			stmt.setFloat(++i, vo.getValor().floatValue());
			stmt.setInt(++i, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void remove(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("DELETE FROM PERIODO_INDICE WHERE PERIODO_INDICE_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = this.getConexao();
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

	public PeriodoIndiceVO findByPrimaryKey(PeriodoIndiceVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT PERIODO_INDICE_CODIGO, INDICE_REAJUSTE_CODIGO, MES, ANO, VALOR ")
		.append("FROM PERIODO_INDICE WHERE PERIODO_INDICE_CODIGO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			return (PeriodoIndiceVO)this.createVO(rs, PeriodoIndiceVO.class, new String[]{"codigo", 
				"indiceReajuste.codigo", "mes", "ano", "valor"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}

	public Collection findAll() throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT PERIODO_INDICE_CODIGO, INDICE_REAJUSTE_CODIGO, MES, ")
		.append("ANO, VALOR FROM PERIODO_INDICE").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			return this.createCollection(rs, PeriodoIndiceVO.class, new String[]{"codigo", "indiceReajuste", 
				"mes", "ano", "valor"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFilter(PeriodoIndiceVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT PI.PERIODO_INDICE_CODIGO, PI.INDICE_REAJUSTE_CODIGO, PI.MES, PI.ANO, ")
		.append("PI.VALOR, IR.STR_NOME FROM PERIODO_INDICE PI JOIN INDICE_REAJUSTE IR ")
		.append("ON PI.INDICE_REAJUSTE_CODIGO=IR.INDICE_REAJUSTE_CODIGO WHERE (? IS NULL OR ?=MES) ")
		.append("AND (? IS NULL OR ?=ANO) AND (? IS NULL OR ?=PI.INDICE_REAJUSTE_CODIGO) ")
		.append("ORDER BY ANO, MES").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = this.getConexao();
			stmt = conn.prepareStatement(sql);
			int i = 0;
			if (vo.getMes() == null || vo.getMes().intValue() == 0){
				stmt.setNull(++i, Types.INTEGER);
				stmt.setNull(++i, Types.INTEGER);
			}else{
				stmt.setInt(++i, vo.getMes().intValue());
				stmt.setInt(++i, vo.getMes().intValue());
			}
			if (vo.getAno() == null || vo.getAno().intValue() == 0){
				stmt.setNull(++i, Types.INTEGER);
				stmt.setNull(++i, Types.INTEGER);
			}else{
				stmt.setInt(++i, vo.getAno().intValue());
				stmt.setInt(++i, vo.getAno().intValue());
			}
			if (vo.getIndiceReajuste() == null || vo.getIndiceReajuste().getCodigo() == null || vo.getIndiceReajuste().getCodigo().intValue() == 0){
				stmt.setNull(++i, Types.INTEGER);
				stmt.setNull(++i, Types.INTEGER);
			}else{
				stmt.setInt(++i, vo.getIndiceReajuste().getCodigo().intValue());
				stmt.setInt(++i, vo.getIndiceReajuste().getCodigo().intValue());
			}
			rs = stmt.executeQuery();
			return this.createCollection(rs, PeriodoIndiceVO.class, new String[]{"codigo", "indiceReajuste.codigo", 
				"mes", "ano", "valor", "indiceReajuste.nome"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	/**
     * Retorna todas as varia��es do indice de reajuste em um intervalo de tempo.  
     * @param periodo
     * @return Cole��o de PeriodoIndiceVO
     * @throws AmbienteException
     * @throws AplicacaoException
     */
    public Collection findByPeriodo(PeriodoIndiceFiltroAssembler periodo) throws AmbienteException, AplicacaoException {
        String sql = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = this.getConexao();
            sql = new StringBuffer("SELECT  PERIODO_INDICE_CODIGO, INDICE_REAJUSTE_CODIGO, ") 
                     .append("              MES, ANO, VALOR ") 
                     .append("      FROM    PERIODO_INDICE PI ")
                     .append("      WHERE   PI.INDICE_REAJUSTE_CODIGO = ? ")
                     .append("              AND (PI.MES + (PI.ANO * 100) >= (? + (? * 100))) ")    
                     .append("              AND (PI.MES + (PI.ANO * 100) <= (? + (? * 100))) ")
                     .append("      ORDER BY ANO,MES ").toString();
            stmt = conn.prepareStatement(sql);
            this.setParameterValueStatement(stmt, periodo, 
                    new String[]{"indiceReajuste.codigo","mesPeriodoIndiceInicio",
                                 "anoPeriodoIndiceInicio","mesPeriodoIndiceFim",
                                 "anoPeriodoIndiceFim"});
            rs = stmt.executeQuery();
            return this.createCollection(rs, PeriodoIndiceVO.class, new String[]{"codigo", "indiceReajuste.codigo", 
            	"mes", "ano", "valor"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            this.fechar(conn, stmt, rs);
        }
    }
    
    public PeriodoIndiceVO findLasPeriodo(PeriodoIndiceVO vo) throws AmbienteException{
    	String sql = new StringBuffer("SELECT ANO, MES FROM PERIODO_INDICE WHERE INDICE_REAJUSTE_CODIGO=? ")
    	.append("ORDER BY ANO DESC, MES DESC").toString();
    	Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
        	conn = getConexao();
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, vo.getCodigo().intValue());
        	rs = stmt.executeQuery();
        	if (rs.next()){
        		PeriodoIndiceVO indiceVO = new PeriodoIndiceVO();
        		indiceVO.setMes(new Integer(rs.getInt("MES")));
        		indiceVO.setAno(new Integer(rs.getInt("ANO")));
        		return indiceVO;
        	}else{
        		return null;
        	}
        }catch(SQLException sqlEx){
        	throw new AmbienteException(sqlEx);
        }finally{
            this.fechar(conn, stmt, rs);
        }
    }
}
