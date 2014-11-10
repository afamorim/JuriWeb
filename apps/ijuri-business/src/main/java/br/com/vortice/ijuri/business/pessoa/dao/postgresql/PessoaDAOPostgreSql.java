/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.pessoa.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import br.com.vortice.ijuri.business.pessoa.dao.PessoaDAOIf;
import br.com.vortice.ijuri.core.pessoa.PessoaFisicaVO;
import br.com.vortice.ijuri.core.pessoa.PessoaJuridicaVO;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.jvseguranca.core.Perfil;
import br.com.vortice.jvseguranca.core.Usuario;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class PessoaDAOPostgreSql extends PostGreSqlDAO implements PessoaDAOIf {

	public PessoaVO insert(PessoaVO pessoaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO PESSOA (PESSOA_CODIGO, NOME, STR_TELEFONE, CEP, CPF_CNPJ, ENDERECO, ")
		.append("APTO, BLOCO, TIPO_PESSOA, OBSERVACAO, CONTATO, STR_EMAIL) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)").toString();
		try{
			pessoaVO.setCodigo(this.getSequence("SEQ_PESSOA"));
			conn = getConexao();
			int i = 0;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(++i, pessoaVO.getCodigo().intValue());
			stmt.setString(++i, pessoaVO.getNome());
			stmt.setString(++i, pessoaVO.getTelefone());
			stmt.setString(++i, pessoaVO.getCep());
			if (pessoaVO instanceof PessoaFisicaVO){
				PessoaFisicaVO pessoaFisicaVO = (PessoaFisicaVO)pessoaVO;
				stmt.setString(++i, pessoaFisicaVO.getCpf());
				stmt.setString(++i, pessoaFisicaVO.getEndereco());
                if (pessoaFisicaVO.getApto().intValue() > 0)
                    stmt.setInt(++i, pessoaFisicaVO.getApto().intValue());
                else
                    stmt.setNull(++i, Types.INTEGER);
				stmt.setString(++i, pessoaFisicaVO.getBloco());
				stmt.setInt(++i, 1);
				stmt.setNull(++i, Types.VARCHAR);
				stmt.setNull(++i, Types.VARCHAR);
                if (pessoaFisicaVO.getEmail()!=null && pessoaFisicaVO.getEmail().length() > 0)
                    stmt.setString(++i, pessoaFisicaVO.getEmail());
                else
                    stmt.setNull(++i, Types.VARCHAR);
                
			}
			else if (pessoaVO instanceof PessoaJuridicaVO){
				PessoaJuridicaVO pessoaJuridicaVo = (PessoaJuridicaVO)pessoaVO;
				stmt.setString(++i, pessoaJuridicaVo.getCnpj());
				stmt.setString(++i, pessoaJuridicaVo.getEndereco());
				stmt.setNull(++i, Types.INTEGER);
				stmt.setNull(++i, Types.VARCHAR);
				stmt.setInt(++i, 2);
				stmt.setNull(++i, Types.VARCHAR);
				stmt.setString(++i, pessoaJuridicaVo.getContato());
                if (pessoaJuridicaVo.getEmail()!=null && pessoaJuridicaVo.getEmail().length() > 0)
                    stmt.setString(++i, pessoaJuridicaVo.getEmail());
                else
                    stmt.setNull(++i, Types.VARCHAR);
			}
			stmt.execute();
			return pessoaVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void update(PessoaVO pessoaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE PESSOA SET NOME=?, STR_TELEFONE=?, CEP=?, CPF_CNPJ=?, ENDERECO=?, ")
		.append("APTO=?, BLOCO=?, OBSERVACAO=?, CONTATO=?, STR_EMAIL=? WHERE PESSOA_CODIGO=?").toString();
		try{
			conn = getConexao();
			int i = 0;
			stmt = conn.prepareStatement(sql);
			stmt.setString(++i, pessoaVO.getNome());
			stmt.setString(++i, pessoaVO.getTelefone());
			stmt.setString(++i, pessoaVO.getCep());
			if (pessoaVO instanceof PessoaFisicaVO){
				PessoaFisicaVO pessoaFisicaVO = (PessoaFisicaVO)pessoaVO;
				stmt.setString(++i, pessoaFisicaVO.getCpf());
				stmt.setString(++i, pessoaFisicaVO.getEndereco());
                if (pessoaFisicaVO.getApto().intValue() > 0)
                    stmt.setInt(++i, pessoaFisicaVO.getApto().intValue());
                else
                    stmt.setNull(++i, Types.INTEGER);
				stmt.setString(++i, pessoaFisicaVO.getBloco());
				stmt.setNull(++i, Types.VARCHAR);
				stmt.setNull(++i, Types.VARCHAR);
                if (pessoaFisicaVO.getEmail()!=null && pessoaFisicaVO.getEmail().length() > 0)
                    stmt.setString(++i, pessoaFisicaVO.getEmail());
                else
                    stmt.setNull(++i, Types.VARCHAR);
			}
			else if (pessoaVO instanceof PessoaJuridicaVO){
				PessoaJuridicaVO pessoaJuridicaVo = (PessoaJuridicaVO)pessoaVO;
				stmt.setString(++i, pessoaJuridicaVo.getCnpj());
				stmt.setString(++i, pessoaJuridicaVo.getEndereco());
				stmt.setNull(++i, Types.INTEGER);
				stmt.setNull(++i, Types.VARCHAR);
				stmt.setNull(++i, Types.VARCHAR);
				stmt.setString(++i, pessoaJuridicaVo.getContato());
                if (pessoaJuridicaVo.getEmail()!=null && pessoaJuridicaVo.getEmail().length() > 0)
                    stmt.setString(++i, pessoaJuridicaVo.getEmail());
                else
                    stmt.setNull(++i, Types.VARCHAR);
			}
			stmt.setInt(++i, pessoaVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void remove(PessoaVO pessoaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM PESSOA WHERE PESSOA_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pessoaVO.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public PessoaVO findByUsuario(Usuario usuario) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("SELECT p.pessoa_codigo, p.nome, p.str_telefone, p.cpf_cnpj, p.endereco, ");
		sql.append("p.apto, p.bloco, p.tipo_pessoa, p.observacao, p.contato, p.cep, p.str_email, p.tipo_pessoa, ");
		sql.append("p.usuario_codigo, u.perfil_codigo, pe.nome AS perfil, u.senha ");
		sql.append("FROM pessoa AS p LEFT JOIN usuario AS u ON p.usuario_codigo=u.codigo ");
		sql.append("LEFT JOIN perfil AS pe ON u.perfil_codigo=pe.codigo WHERE usuario_codigo=?");
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setLong(1, usuario.getCodigo());
			rs = stmt.executeQuery();
			if (rs.next()){
				if (PessoaVO.PESSOA_FISICA.equals(rs.getInt("tipo_pessoa"))){
					PessoaFisicaVO pessoaFisicaVO = new PessoaFisicaVO(rs.getInt("pessoa_codigo"));
					pessoaFisicaVO.setNome(rs.getString("nome"));
					pessoaFisicaVO.setTelefone(rs.getString("str_telefone"));
					pessoaFisicaVO.setCpf(rs.getString("cpf_cnpj"));
					pessoaFisicaVO.setEndereco(rs.getString("endereco"));
					pessoaFisicaVO.setApto(new Integer(rs.getInt("apto")));
					pessoaFisicaVO.setBloco(rs.getString("bloco"));
					pessoaFisicaVO.setCep(rs.getString("cep"));
                    pessoaFisicaVO.setEmail(rs.getString("str_email"));
                    if (rs.getInt("usuario_codigo") > 0)
                    {
                    	pessoaFisicaVO.setUsuario(new Usuario());
                    	pessoaFisicaVO.getUsuario().setCodigo(rs.getInt("usuario_codigo"));
                    	pessoaFisicaVO.getUsuario().setPerfil(new Perfil());
                    	pessoaFisicaVO.getUsuario().getPerfil().setCodigo(rs.getInt("perfil_codigo"));
                    	pessoaFisicaVO.getUsuario().getPerfil().setNome(rs.getString("perfil"));
                    	pessoaFisicaVO.getUsuario().setSenha(rs.getString("senha"));
                    }
					return pessoaFisicaVO;
				}else if (PessoaVO.PESSOA_JURIDICA.equals(rs.getInt("tipo_pessoa"))){
					PessoaJuridicaVO pessoaJuridicaVO = new PessoaJuridicaVO(rs.getInt("pessoa_codigo"));
					pessoaJuridicaVO.setNome(rs.getString("nome"));
					pessoaJuridicaVO.setTelefone(rs.getString("str_telefone"));
					pessoaJuridicaVO.setCnpj(rs.getString("cpf_cnpj"));
					pessoaJuridicaVO.setEndereco(rs.getString("endereco"));
					pessoaJuridicaVO.setContato(rs.getString("contato"));
					pessoaJuridicaVO.setCep(rs.getString("cep"));
                    pessoaJuridicaVO.setEmail(rs.getString("str_email"));
                    if (rs.getInt("usuario_codigo") > 0)
                    {
                    	pessoaJuridicaVO.getUsuario().setCodigo(rs.getInt("usuario_codigo"));
                    	pessoaJuridicaVO.getUsuario().setPerfil(new Perfil());
                    	pessoaJuridicaVO.getUsuario().getPerfil().setCodigo(rs.getInt("perfil_codigo"));
                    	pessoaJuridicaVO.getUsuario().setSenha(rs.getString("senha"));
                    }
					return pessoaJuridicaVO;
				}
			}
			return null;
		}catch(SQLException e){
			throw new AmbienteException(e);
		}
	}
	
	public PessoaVO findByPrimaryKey(PessoaVO pessoaVO)throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer("SELECT p.pessoa_codigo, p.nome, p.str_telefone, p.cpf_cnpj, p.endereco, " +
				"p.apto, p.bloco, p.tipo_pessoa, p.observacao, p.contato, p.cep, p.str_email, p.tipo_pessoa, " +
				"p.usuario_codigo, u.perfil_codigo, pe.nome AS perfil, u.senha " +
				"FROM pessoa AS p LEFT JOIN usuario AS u ON p.usuario_codigo=u.codigo " +
				"LEFT JOIN perfil AS pe ON u.perfil_codigo=pe.codigo WHERE pessoa_codigo=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pessoaVO.getCodigo().intValue());
			rs = stmt.executeQuery();
			if (rs.next()){
				if (PessoaVO.PESSOA_FISICA.equals(rs.getInt("tipo_pessoa"))){
					PessoaFisicaVO pessoaFisicaVO = new PessoaFisicaVO(rs.getInt("pessoa_codigo"));
					pessoaFisicaVO.setNome(rs.getString("NOME"));
					pessoaFisicaVO.setTelefone(rs.getString("STR_TELEFONE"));
					pessoaFisicaVO.setCpf(rs.getString("CPF_CNPJ"));
					pessoaFisicaVO.setEndereco(rs.getString("ENDERECO"));
					pessoaFisicaVO.setApto(new Integer(rs.getInt("APTO")));
					pessoaFisicaVO.setBloco(rs.getString("BLOCO"));
					pessoaFisicaVO.setCep(rs.getString("CEP"));
                    pessoaFisicaVO.setEmail(rs.getString("str_email"));
                    if (rs.getInt("usuario_codigo") > 0)
                    {
                    	pessoaFisicaVO.setUsuario(new Usuario());
                    	pessoaFisicaVO.getUsuario().setCodigo(rs.getInt("usuario_codigo"));
                    	pessoaFisicaVO.getUsuario().setPerfil(new Perfil());
                    	pessoaFisicaVO.getUsuario().getPerfil().setCodigo(rs.getInt("perfil_codigo"));
                    	pessoaFisicaVO.getUsuario().getPerfil().setNome(rs.getString("perfil"));
                    	pessoaFisicaVO.getUsuario().setSenha(rs.getString("senha"));
                    }
					return pessoaFisicaVO;
				}else if (PessoaVO.PESSOA_JURIDICA.equals(rs.getInt("tipo_pessoa"))){
					PessoaJuridicaVO pessoaJuridicaVO = new PessoaJuridicaVO(rs.getInt("pessoa_codigo"));
					pessoaJuridicaVO.setNome(rs.getString("NOME"));
					pessoaJuridicaVO.setTelefone(rs.getString("STR_TELEFONE"));
					pessoaJuridicaVO.setCnpj(rs.getString("CPF_CNPJ"));
					pessoaJuridicaVO.setEndereco(rs.getString("ENDERECO"));
					pessoaJuridicaVO.setContato(rs.getString("contato"));
					pessoaJuridicaVO.setCep(rs.getString("cep"));
                    pessoaJuridicaVO.setEmail(rs.getString("str_email"));
                    if (rs.getInt("usuario_codigo") > 0)
                    {
                    	pessoaJuridicaVO.setUsuario(new Usuario());
                    	pessoaJuridicaVO.getUsuario().setCodigo(rs.getInt("usuario_codigo"));
                    	pessoaJuridicaVO.getUsuario().setPerfil(new Perfil());
                    	pessoaJuridicaVO.getUsuario().getPerfil().setCodigo(rs.getInt("perfil_codigo"));
                    	pessoaJuridicaVO.getUsuario().getPerfil().setNome(rs.getString("perfil"));
                    	pessoaJuridicaVO.getUsuario().setSenha(rs.getString("senha"));
                    }
					return pessoaJuridicaVO;
				}else
					return null;
			}else return null;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFilter(PessoaVO pessoaVO) throws AmbienteException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.pessoa_codigo, p.nome, p.str_telefone, p.cpf_cnpj, p.endereco, p.apto, p.bloco, p.tipo_pessoa, p.observacao, " +
			"p.contato, p.cep, p.usuario_codigo, u.ativo, perfil.nome AS perfil, p.str_email, u.perfil_codigo " +
			"FROM pessoa AS p LEFT JOIN usuario AS u ON p.usuario_codigo=u.codigo " +
			"LEFT JOIN perfil ON u.perfil_codigo=perfil.codigo " +
			"WHERE (? IS NULL OR UPPER(p.nome) LIKE UPPER(?) || '%') " +
			"AND (? IS NULL OR UPPER(cpf_cnpj) LIKE '%' || UPPER(?) || '%') " +
			"AND tipo_pessoa=? ORDER BY p.nome";
		Collection collPessoa = new ArrayList();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			int i = 0;
            stmt.setString(++i, pessoaVO.getNome());
            stmt.setString(++i, pessoaVO.getNome());
            if (pessoaVO instanceof PessoaFisicaVO){
				PessoaFisicaVO pessoaFisicaVO = (PessoaFisicaVO)pessoaVO;
                stmt.setString(++i, pessoaFisicaVO.getCpf());
                stmt.setString(++i, pessoaFisicaVO.getCpf());
				stmt.setInt(++i, PessoaVO.PESSOA_FISICA.intValue());
			}else if (pessoaVO instanceof PessoaJuridicaVO){
				PessoaJuridicaVO pessoaJuridicaVO = (PessoaJuridicaVO)pessoaVO;
				stmt.setString(++i, pessoaJuridicaVO.getCnpj());
				stmt.setString(++i, pessoaJuridicaVO.getCnpj());
				stmt.setInt(++i, PessoaVO.PESSOA_JURIDICA.intValue());
			}else{
                stmt.setNull(++i, Types.VARCHAR);
                stmt.setNull(++i, Types.VARCHAR);
                stmt.setInt(++i, pessoaVO.getTipo().intValue());
            }
			rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getInt("tipo_pessoa")==PessoaVO.PESSOA_FISICA.intValue()){
					PessoaFisicaVO pf = new PessoaFisicaVO();
					pf.setCodigo(new Integer(rs.getInt("pessoa_codigo")));
					pf.setNome(rs.getString("nome"));
					pf.setTelefone(rs.getString("str_telefone"));
					pf.setCpf(rs.getString("cpf_cnpj"));
					pf.setEndereco(rs.getString("endereco"));
					pf.setApto(new Integer(rs.getInt("apto")));
					pf.setBloco(rs.getString("bloco"));
					pf.setCep(rs.getString("cep"));
					pf.setEmail(rs.getString("str_email"));
					if (rs.getInt("usuario_codigo") > 0){
						pf.setUsuario(new Usuario());
						pf.getUsuario().setCodigo(rs.getInt("usuario_codigo"));
						pf.getUsuario().setAtivo(rs.getBoolean("ativo"));
						pf.getUsuario().setPerfil(new Perfil());
						pf.getUsuario().getPerfil().setCodigo(rs.getInt("perfil_codigo"));
						pf.getUsuario().getPerfil().setNome(rs.getString("perfil"));
					}
					collPessoa.add(pf);
				}else if (rs.getInt("tipo_pessoa")==PessoaVO.PESSOA_JURIDICA.intValue()){
					PessoaJuridicaVO pj = new PessoaJuridicaVO();
					pj.setCodigo(new Integer(rs.getInt("pessoa_codigo")));
					pj.setNome(rs.getString("nome"));
					pj.setTelefone(rs.getString("str_telefone"));
					pj.setCnpj(rs.getString("cpf_cnpj"));
					pj.setEndereco(rs.getString("endereco"));
					pj.setContato(rs.getString("contato"));
					pj.setCep(rs.getString("cep"));
					pj.setEmail(rs.getString("str_email"));
					if (rs.getInt("usuario_codigo") > 0){
						pj.setUsuario(new Usuario());
						pj.getUsuario().setCodigo(rs.getInt("usuario_codigo"));
						pj.getUsuario().setAtivo(rs.getBoolean("ativo"));
						pj.getUsuario().setPerfil(new Perfil());
						pj.getUsuario().getPerfil().setCodigo(rs.getInt("perfil_codigo"));
						pj.getUsuario().setAtivo(rs.getBoolean("ativo"));
						pj.getUsuario().getPerfil().setNome(rs.getString("perfil"));
					}
					collPessoa.add(pj);
				}
			}
			return collPessoa;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public void criarUsuario(PessoaVO pessoa) throws AmbienteException, AplicacaoException{
		String sql = "UPDATE pessoa SET usuario_codigo=? WHERE pessoa_codigo=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, pessoa.getUsuario().getCodigo());
			stmt.setInt(2, pessoa.getCodigo());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}
	}
	
	public Collection findAll() throws AmbienteException {
		return null;
	}

}