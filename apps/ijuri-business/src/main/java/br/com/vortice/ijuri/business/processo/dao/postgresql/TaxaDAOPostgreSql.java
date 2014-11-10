/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import br.com.vortice.ijuri.business.processo.dao.TaxaDAOIf;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TaxaVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class TaxaDAOPostgreSql extends PostGreSqlDAO implements TaxaDAOIf {

	public TaxaVO insert(TaxaVO taxaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try{
            conn = getConexao();
			
            sql = new StringBuffer("INSERT INTO TAXA(taxa_codigo,processo_codigo,tipo_taxa,num_mes,num_ano) ")
                   .append(" VALUES (?,?,?,?,?) ").toString();
            stmt = conn.prepareStatement(sql);
            
            taxaVO.setCodigo(new Long(this.getSequence("SEQ_TAXA").intValue()));
            setParameterValueStatement(stmt, taxaVO, 
                    new String[]{"codigo","processo.codigo","tipo.codigo",
                                 "mes","ano"});
			
            stmt.execute();
            
			return taxaVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
	}
    
    public void insert(Collection taxas,ProcessoVO processo) throws AmbienteException {
        TaxaVO taxaVO = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            if (taxas!=null && !taxas.isEmpty()){
                sql = new StringBuffer("INSERT INTO TAXA(taxa_codigo,processo_codigo,tipo_taxa,num_mes,num_ano) ")
                              .append(" VALUES (?,?,?,?,?) ").toString();
                conn = getConexao();
                
                stmt = conn.prepareStatement(sql);
                
                Iterator it = taxas.iterator();
                while(it.hasNext()){    
                    taxaVO = (TaxaVO)it.next();
                    taxaVO.setProcesso(processo);
                    taxaVO.setCodigo(new Long(getSequence("SEQ_TAXA").longValue()));
                    setParameterValueStatement(stmt, taxaVO, 
                            new String[]{"codigo","processo.codigo","tipo.codigo",
                                         "mes","ano"});
                    stmt.addBatch();
                }
                
                stmt.executeBatch();
            }

            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }
    
    public void remove(Collection taxas) throws AmbienteException {
        TaxaVO taxaVO = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            if (taxas!=null && !taxas.isEmpty()){
                sql = "DELETE FROM TAXA WHERE TAXA_CODIGO = ? ";
                
                conn = getConexao();
                
                stmt = conn.prepareStatement(sql);
                
                Iterator it = taxas.iterator();
                while(it.hasNext()){    
                    taxaVO = (TaxaVO)it.next();
                    stmt.setLong(1,taxaVO.getCodigo().longValue());
                    stmt.addBatch();
                }
                
                stmt.executeBatch();
            }

            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }
    
    public void removeByProcesso(ProcessoVO processo) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            sql = "DELETE FROM TAXA WHERE PROCESSO_CODIGO = ? ";
            
            conn = getConexao();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1,processo.getCodigo().longValue());
            
            stmt.execute();
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }

	public void update(TaxaVO taxaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("UPDATE TAXA SET processo_codigo = ?,tipo_taxa = ?, ")
                   .append("                        num_mes = ?,num_ano = ? ")
                   .append("        WHERE taxa_codigo = ? ").toString();
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, taxaVO, 
                    new String[]{"codigo","processo.codigo","tipo.codigo",
                                 "mes","ano"});
            
            stmt.execute();
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
	}

	public void remove(TaxaVO taxaVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM TAXA WHERE TAXA_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, taxaVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}

	public TaxaVO findByPrimaryKey(TaxaVO taxaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("         taxa_codigo,processo_codigo,tipo_taxa,num_mes,num_ano ")
                      .append("     FROM taxa ")
                      .append("     WHERE taxa_codigo = ? ").toString();
            
			stmt = conn.prepareStatement(sql);
			
            stmt.setLong(1, taxaVO.getCodigo().longValue());
			
            rs = stmt.executeQuery();
            
            return (TaxaVO)createVO(rs,TaxaVO.class, 
                    new String[]{"codigo","processo.codigo","tipo.codigo",
                                 "mes","ano"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

	public Collection findByProcesso(ProcessoVO processoVO,TipoTaxaVO tipo) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("         taxa_codigo,processo_codigo,tipo_taxa,num_mes,num_ano ")
                      .append("     FROM taxa ")
                      .append("     WHERE processo_codigo = ? ")
                      .append("           and tipo_taxa = ?  ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, processoVO.getCodigo().longValue());
            stmt.setInt(2, tipo.getCodigo().intValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,TaxaVO.class, 
                    new String[]{"codigo","processo.codigo","tipo.codigo",
                                 "mes","ano"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }    }


}