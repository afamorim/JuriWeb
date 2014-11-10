/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.vortice.ijuri.business.pagamento.dao.ChequeDAOIf;
import br.com.vortice.ijuri.core.pagamento.ChequeVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class ChequeDAOPostgreSql extends PostGreSqlDAO implements ChequeDAOIf {

	/**
     *  Insere os dados do cheque de um pagamento 
	 */
    public ChequeVO insert(ChequeVO cheque) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer().append("INSERT INTO CHEQUE(cheque_codigo,num_banco,num_cheque,")
                                    .append("num_agencia,dt_recebimento,dt_compensacao) VALUES(?,?,?,?,?,?) ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            cheque.setCodigo(getSequence("SEQ_CHEQUE"));
            
            setParameterValueStatement(stmt, cheque, 
                    new String[]{"codigo","banco.numero",
                                 "numero","numeroAgencia",
                                 "dataRecebimento","dataCompensacao"});
            
            stmt.execute();
            
            return cheque;
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
    }

    
     /**
     *  Atualiza os dados do cheque de um pagamento 
     */
    public void update(ChequeVO cheque) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer().append(" UPDATE CHEQUE SET num_banco = ?,num_cheque = ?,num_agencia = ?, ")
                                    .append("                  dt_recebimento = ?, dt_compensacao = ? ")    
                                    .append(" WHERE cheque_codigo = ? ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, cheque, 
                    new String[]{"codigo","banco.numero","numero",
                                 "numeroAgencia","dataRecebimento",
                                 "dataCompensacao","codigo"});
            
            stmt.execute();
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
        
    }
}