/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pagamento.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import br.com.vortice.ijuri.business.pagamento.dao.BancoDAOIf;
import br.com.vortice.ijuri.core.pagamento.BancoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class BancoDAOPostgreSql extends PostGreSqlDAO implements BancoDAOIf {

	public Collection findAll() throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = " SELECT NUM_BANCO, NOM_BANCO FROM BANCO ORDER BY NOM_BANCO ";
            
			stmt = conn.prepareStatement(sql);
			
            rs = stmt.executeQuery();
            
            return createCollection(rs,BancoVO.class, 
                    new String[]{"numero","nome"});
            
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

}