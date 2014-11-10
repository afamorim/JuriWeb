/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.usuario.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.vortice.ijuri.business.usuario.dao.UsuarioDAOIf;
import br.com.vortice.ijuri.core.seguranca.UsuarioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class UsuarioDAOPostgreSql extends PostGreSqlDAO implements UsuarioDAOIf {

    public UsuarioVO findByUsuario(UsuarioVO usuarioVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT U.USUARIO_CODIGO, PESSOA_CODIGO, LOGIN, SENHA, TIPO_USUARIO ")
		.append("FROM USUARIO U WHERE U.LOGIN=? AND U.SENHA=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			setParameterValueStatement(stmt, usuarioVO, new String[]{"login", "senha"});
			rs = stmt.executeQuery();
			return (UsuarioVO)createVO(rs, UsuarioVO.class, new String[]{"codigo", "pessoa.codigo", "login", "senha", 
				"tipoUsuario.codigo"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
    }
}