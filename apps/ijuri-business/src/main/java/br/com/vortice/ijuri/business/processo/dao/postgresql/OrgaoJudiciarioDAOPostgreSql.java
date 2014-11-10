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

import br.com.vortice.ijuri.business.processo.dao.OrgaoJudiciarioDAOIf;
import br.com.vortice.ijuri.core.processo.JuizoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class OrgaoJudiciarioDAOPostgreSql extends PostGreSqlDAO implements OrgaoJudiciarioDAOIf {

	public OrgaoJudiciarioVO insert(OrgaoJudiciarioVO orgaoJudiciarioVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("INSERT INTO ORGAO_JUDICIARIO (ORGAO_JUDICIARIO_CODIGO, ")
		.append("JUIZO_CODIGO, DES_ORGAO_JUDICIARIO) VALUES (?, ?, ?)").toString();
		try{
			orgaoJudiciarioVO.setCodigo(new Long(this.getSequence("SEQ_ORGAO_JUDICIARIO").longValue()));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, orgaoJudiciarioVO.getCodigo().longValue());
			stmt.setInt(2, orgaoJudiciarioVO.getJuizo().getCodigo().intValue());
			stmt.setString(3, orgaoJudiciarioVO.getDescricao());
			stmt.execute();
			return orgaoJudiciarioVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn, stmt, rs);
        }
	}

	public void update(OrgaoJudiciarioVO orgaoJudiciarioVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE ORGAO_JUDICIARIO SET ")
		.append("JUIZO_CODIGO=?, DES_ORGAO_JUDICIARIO=? WHERE ORGAO_JUDICIARIO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orgaoJudiciarioVO.getJuizo().getCodigo().intValue());
			stmt.setString(2, orgaoJudiciarioVO.getDescricao());
			stmt.setLong(3, orgaoJudiciarioVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn, stmt, null);
        }
	}

	public void remove(OrgaoJudiciarioVO orgaoJudiciarioVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM ORGAO_JUDICIARIO WHERE ORGAO_JUDICIARIO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, orgaoJudiciarioVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn, stmt, null);
        }
	}

	public OrgaoJudiciarioVO findByPrimaryKey(OrgaoJudiciarioVO orgaoJudiciarioVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT JUIZO_CODIGO, ORGAO_JUDICIARIO_CODIGO, DES_ORGAO_JUDICIARIO ")
		.append("FROM ORGAO_JUDICIARIO WHERE ORGAO_JUDICIARIO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, orgaoJudiciarioVO.getCodigo().longValue());
			rs = stmt.executeQuery();
			
			return (OrgaoJudiciarioVO)createVO(rs, OrgaoJudiciarioVO.class, 
                    new String[]{"juizo.codigo","codigo","descricao"});
			
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn, stmt, rs);
        }
	}

	public Collection findByJuizo(JuizoVO juizo) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT ORGAO_JUDICIARIO_CODIGO, JUIZO_CODIGO, ")
		.append(" DES_ORGAO_JUDICIARIO FROM ORGAO_JUDICIARIO")
		.append(" WHERE JUIZO_CODIGO = ? ").toString();
		Collection collOrgaoJuduciario = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
            stmt.setInt(1,juizo.getCodigo().intValue());
			rs = stmt.executeQuery();
			
            return createCollection(rs, OrgaoJudiciarioVO.class, 
                    new String[]{"codigo","juizo.codigo","descricao"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn, stmt, rs);
        }
	}
    
    public Collection findByFilter(OrgaoJudiciarioVO orgao) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = new StringBuffer("SELECT ORGAO_JUDICIARIO_CODIGO, DES_JUIZO, JUIZO.JUIZO_CODIGO, ")
        .append(" DES_ORGAO_JUDICIARIO FROM ORGAO_JUDICIARIO,JUIZO ")
        .append(" WHERE ORGAO_JUDICIARIO.JUIZO_CODIGO = JUIZO.JUIZO_CODIGO ")
        .append("   AND ( (? IS NULL) OR (ORGAO_JUDICIARIO.JUIZO_CODIGO = ?) )  ")
        .append("   AND ( (? IS NULL) OR (UPPER(ORGAO_JUDICIARIO.DES_ORGAO_JUDICIARIO) LIKE '%'||UPPER(?)||'%') ) ").toString();
        try{
            conn = getConexao();
            stmt = conn.prepareStatement(sql);
          
            setParameterValueStatementFilter(stmt, orgao,new String[]{"juizo.codigo","descricao"});
            
            rs = stmt.executeQuery();
            
            return createCollection(rs, OrgaoJudiciarioVO.class, 
                        new String[]{"codigo","juizo.descricao","juizo.codigo","descricao"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn, stmt, rs);
        }
    }
    
    private OrgaoJudiciarioVO createVO(ResultSet rs) throws SQLException{ 
       try{
            OrgaoJudiciarioVO orgaoJudiciarioVO = new OrgaoJudiciarioVO(new Long(rs.getLong("ORGAO_JUDICIARIO_CODIGO")));
            orgaoJudiciarioVO.setDescricao(rs.getString("DES_ORGAO_JUDICIARIO"));
            
            JuizoVO juizo = new JuizoVO(new Integer(rs.getInt("JUIZO_CODIGO")));
            orgaoJudiciarioVO.setJuizo(juizo);
            
            return orgaoJudiciarioVO;
            
       }catch(SQLException e){
           throw e;
       } 
    }

}
