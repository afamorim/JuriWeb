package br.com.vortice.ijuri.business.configuracao.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

import br.com.vortice.ijuri.core.configuracao.Configuracao;
import br.com.vortice.ijuri.business.configuracao.dao.ConfiguracaoDAOIf;

public class ConfiguracaoDAOPostgreSql extends PostGreSqlDAO implements ConfiguracaoDAOIf {

	public List<Configuracao> findAll() throws AmbienteException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT sigla, valor, descricao FROM configuracao");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Configuracao> configuracoes = new ArrayList<Configuracao>();
		try {
			conn = getConexao();
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while (rs.next()){
				Configuracao configuracao = Configuracao.getFromSigla(rs.getString(1));
				configuracao.setValor(rs.getString(2));
				configuracao.setDescricao(rs.getString(3));
				configuracoes.add(configuracao);
			}
			return configuracoes;
		} catch (SQLException e) {
			throw new AmbienteException(e);
		}
	}

	public Configuracao findByPrimaryKey(Configuracao configuracao)	throws AmbienteException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT sigla, valor, descricao FROM configuracao WHERE sigla=?");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConexao();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, configuracao.getSigla());
			rs = stmt.executeQuery();
			if (rs.next()){
				configuracao = Configuracao.getFromSigla(rs.getString(1));
				configuracao.setValor(rs.getString(2));
				configuracao.setDescricao(rs.getString(3));
				return configuracao;
			}
			return null;
		} catch (SQLException e) {
			throw new AmbienteException(e);
		}
	}

	public void update(Configuracao configuracao) throws AmbienteException,	AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		StringBuilder sql = new StringBuilder()
		.append("UPDATE configuracao SET valor=? WHERE sigla=?");
		try {
			conn = getConexao();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, configuracao.getValor());
			stmt.setString(2, configuracao.getSigla());
			stmt.execute();
		} catch (SQLException e) {
			throw new AmbienteException(e);
		}
	}
}