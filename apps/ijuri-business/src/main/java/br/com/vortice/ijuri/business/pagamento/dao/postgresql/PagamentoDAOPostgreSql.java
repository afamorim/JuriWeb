/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import br.com.vortice.ijuri.business.pagamento.dao.PagamentoDAOIf;
import br.com.vortice.ijuri.core.pagamento.PagamentoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class PagamentoDAOPostgreSql extends PostGreSqlDAO implements PagamentoDAOIf {

	public PagamentoVO insert(PagamentoVO pagamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try{
            conn = getConexao();
			
            sql = new StringBuffer(" INSERT INTO PAGAMENTO(pagamento_codigo,cheque_codigo,forma_pagamento_codigo, ")
                           .append("                      vl_vencimento,vl_pago,dt_pagamento, ")
                           .append("                      sts_pagamento,vl_multa,vl_juros,dt_vencimento ) ")
                           .append(" VALUES (?,?,?,?,?,?,?,?,?) ").toString();
            stmt = conn.prepareStatement(sql);
            
            pagamentoVO.setCodigo(new Long(this.getSequence("SEQ_PAGAMENTO").intValue()));
            setParameterValueStatement(stmt, pagamentoVO, 
                    new String[]{"codigo","cheque.codigo","formaPagamento.codigo",
                                 "valorVencimento","valorPago","dataPagamento",
                                 "valorMulta","valorJuros","dataVencimento"});
			
            stmt.execute();
            
			return pagamentoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
	}
    
    public void insert(Collection pagamentos) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        
        try{
            if (pagamentos!=null && !pagamentos.isEmpty()){
                sql = new StringBuffer(" INSERT INTO PAGAMENTO(pagamento_codigo,cheque_codigo,forma_pagamento_codigo, ")
                                .append("                      vl_vencimento,vl_pago,dt_pagamento, ")
                                .append("                      sts_pagamento,vl_multa,vl_juros,dt_vencimento,vl_correcao) ")
                                .append(" VALUES (?,?,?,?,?,?,?,?,?,?,?) ").toString();
                
                conn = getConexao();
                
                stmt = conn.prepareStatement(sql);
                
                Iterator it = pagamentos.iterator();
                while(it.hasNext()){    
                    PagamentoVO pagamentoVO = (PagamentoVO)it.next();
                    pagamentoVO.setCodigo(new Long(this.getSequence("SEQ_PAGAMENTO").intValue()));
                    setParameterValueStatement(stmt, pagamentoVO, 
                            new String[]{"codigo","cheque.codigo","formaPagamento.codigo",
                                         "valorVencimento","valorPago","dataPagamento","status.codigo",
                                         "valorMulta","valorJuros","dataVencimento","valorCorrecao"});
                    
                    stmt.addBatch();
                }
                
                stmt.executeBatch();
            }
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx.getNextException());
        }finally{
            fechar(conn,stmt,null);
        }
    }

	public void update(PagamentoVO pagamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
        String sql = null;
        try{
            sql = new StringBuffer( " UPDATE PAGAMENTO SET cheque_codigo = ?,forma_pagamento_codigo = ?, ")
                            .append("                      vl_vencimento = ?,vl_pago = ?,dt_pagamento = ?, ")
                            .append("                      sts_pagamento = ?,vl_multa = ?, ")
                            .append("                      vl_juros = ?,dt_vencimento = ?, vl_correcao = ?  ")
                            .append(" WHERE pagamento_codigo = ? ").toString();
            
            conn = getConexao();
            
            stmt = conn.prepareStatement(sql);                
            
            setParameterValueStatement(stmt, pagamentoVO, 
                 new String[]{"cheque.codigo","formaPagamento.codigo",
                              "valorVencimento","valorPago","dataPagamento","status.codigo",
                              "valorMulta","valorJuros","dataVencimento","valorCorrecao","codigo"});

            stmt.execute();
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
	}

	public void remove(PagamentoVO pagamentoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM PAGAMENTO WHERE PAGAMENTO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, pagamentoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}
    
    public void remove(Collection pagamentos) throws AmbienteException,AplicacaoException {
        PagamentoVO pagamentoVO = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            
            if (pagamentos!=null && !pagamentos.isEmpty()){
                sql = "DELETE FROM PAGAMENTO WHERE PAGAMENTO_CODIGO = ? ";
                
                conn = getConexao();
                
                stmt = conn.prepareStatement(sql);
                
                Iterator it = pagamentos.iterator();
                while(it.hasNext()){    
                    pagamentoVO = (ParcelaAcordoVO)it.next();
                    stmt.setLong(1,pagamentoVO.getCodigo().longValue());
                    stmt.addBatch();
                }
                
                stmt.executeBatch();
            }

            
        }catch(SQLException sqlEx){
            tratarExcessaoRemove(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }

	public PagamentoVO findByPrimaryKey(PagamentoVO pagamentoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("         pagamento_codigo,cheque_codigo, ")
                      .append("         forma_pagamento_codigo,vl_vencimento,vl_pago, ")
                      .append("         dt_pagamento,sts_pagamento,vl_multa,vl_juros,dt_vencimento,vl_correcao ")
                      .append("     FROM pagamento ")
                      .append("     WHERE pagamento_codigo = ? ").toString();
            
			stmt = conn.prepareStatement(sql);
			
            stmt.setLong(1, pagamentoVO.getCodigo().longValue());
			
            rs = stmt.executeQuery();
            
            return (PagamentoVO)createVO(rs,PagamentoVO.class, 
                    new String[]{"codigo","cheque.codigo","formaPagamento.codigo",
                            "valorVencimento","valorPago","dataPagamento",
                            "valorMulta","valorJuros","dataVencimento","valorCorrecao"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

}