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
import java.util.Iterator;

import org.apache.log4j.Logger;

import br.com.vortice.ijuri.business.processo.dao.ProcessoDAOIf;
import br.com.vortice.ijuri.core.processo.AgendaProcessosAssembler;
import br.com.vortice.ijuri.core.processo.AndamentoVO;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoAgendaVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.ProcessoAssembler;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.core.processo.TipoParteVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Fernando
 */
public class ProcessoDAOPostgreSql extends PostGreSqlDAO implements ProcessoDAOIf {
	
	private Logger log = Logger.getLogger(ProcessoDAOPostgreSql.class);
	
	public ProcessoVO insert(ProcessoVO processoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("INSERT INTO PROCESSO (PROCESSO_CODIGO, ORGAO_JUDICIARIO_CODIGO, ")
		.append("CLASSE_PROCESSO_CODIGO, COMARCA_CODIGO, STR_NUMERO_PROCESSO, STS_PROCESSO, VL_CAUSA, DT_DISTRIBUICAO, ")
		.append("TURNO_CODIGO, PERCENTAGEM_HONORARIO,DT_AUTUACAO,STR_OBSERVACAO, APTO, BLOCO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)").toString();
		try{
			processoVO.setCodigo(new Long(this.getSequence("SEQ_PROCESSO").longValue()));
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
            setParameterValueStatement(stmt, processoVO, 
                    new String[]{"codigo","orgaoJudiciario.codigo","classeProcesso.codigo",
                                 "comarca.codigo","numero",
                                 "status.codigo","valorCausa",
                                 "dataDistribuicao","turno.codigo",
                                 "honorario","dataAutuacao","observacao",
                                 "apto","bloco"});
            stmt.execute();
			return processoVO;
		}catch(SQLException sqlEx){
			tratarExcessaoUnique(sqlEx);
		}finally{
            fechar(conn, stmt, null);
        }
        return null;
	}

	public void update(ProcessoVO processoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("UPDATE PROCESSO SET ORGAO_JUDICIARIO_CODIGO=?, CLASSE_PROCESSO_CODIGO=?, ")
		.append("COMARCA_CODIGO=?, STR_NUMERO_PROCESSO=?, STS_PROCESSO=?, VL_CAUSA=?, DT_DISTRIBUICAO=?, ")
		.append("TURNO_CODIGO=?, PERCENTAGEM_HONORARIO=?,DT_AUTUACAO=?,STR_OBSERVACAO=?, APTO=?, BLOCO=? WHERE PROCESSO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
            setParameterValueStatement(stmt, processoVO, 
                    new String[]{"orgaoJudiciario.codigo","classeProcesso.codigo",
                                 "comarca.codigo","numero",
                                 "status.codigo","valorCausa",
                                 "dataDistribuicao","turno.codigo",
                                 "honorario","dataAutuacao","observacao",
                                 "apto","bloco",
            					 "codigo"});
			stmt.execute();
		}catch(SQLException sqlEx){
            tratarExcessaoUnique(sqlEx);
		}finally{
            fechar(conn, stmt, null);
        }
	}

	public void remove(ProcessoVO processoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM PROCESSO WHERE PROCESSO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, processoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn, stmt, null);
        }
	}

	public ProcessoVO findByPrimaryKey(ProcessoVO processoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer(" SELECT PROCESSO_CODIGO,OJ.ORGAO_JUDICIARIO_CODIGO, ")
                .append(" OJ.DES_ORGAO_JUDICIARIO , OJ.JUIZO_CODIGO, J.DES_JUIZO, ")
                .append(" CP.CLASSE_PROCESSO_CODIGO, CP.DES_CLASSE_PROCESSO, C.COMARCA_CODIGO,M.NOM_MUNICIPIO, ")
                .append(" STR_NUMERO_PROCESSO, STS_PROCESSO, VL_CAUSA, DT_DISTRIBUICAO,  ")
        		.append(" TURNO_CODIGO, PERCENTAGEM_HONORARIO,DT_AUTUACAO, PROCESSO.STR_OBSERVACAO, APTO, BLOCO ")
        		.append(" FROM PROCESSO,ORGAO_JUDICIARIO OJ,COMARCA C,")
        		.append("      CLASSE_PROCESSO CP,JUIZO J,MUNICIPIO M ") 
        		.append(" WHERE PROCESSO_CODIGO = ? ")
                .append(" AND PROCESSO.ORGAO_JUDICIARIO_CODIGO = OJ.ORGAO_JUDICIARIO_CODIGO ")
                .append(" AND OJ.JUIZO_CODIGO = J.JUIZO_CODIGO ")
                .append(" AND PROCESSO.CLASSE_PROCESSO_CODIGO = CP.CLASSE_PROCESSO_CODIGO ")
                .append(" AND M.MUNICIPIO_CODIGO = C.MUNICIPIO_CODIGO ")
                .append(" AND PROCESSO.COMARCA_CODIGO = C.COMARCA_CODIGO ").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, processoVO.getCodigo().longValue());
			rs = stmt.executeQuery();
			return (ProcessoVO)createVO(rs, ProcessoVO.class,    
                    new String[]{"codigo","orgaoJudiciario.codigo","orgaoJudiciario.descricao",
                                "orgaoJudiciario.juizo.codigo","orgaoJudiciario.juizo.descricao",
                                "classeProcesso.codigo","classeProcesso.descricao",
                                "comarca.codigo","comarca.municipio.nome","numero",
                                "status.codigo","valorCausa",
                                "dataDistribuicao","turno.codigo",
                                "honorario","dataAutuacao","observacao",
                                "apto","bloco",});
				 
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn, stmt, rs);
        }
	}

	public Collection findByFilter(FiltroProcessoVO filtro) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = new StringBuffer(" SELECT DISTINCT PROCESSO.PROCESSO_CODIGO, OJ.DES_ORGAO_JUDICIARIO, ")
                              .append("       CP.DES_CLASSE_PROCESSO, STR_NUMERO_PROCESSO,")
                              .append("       STS_PROCESSO, DT_DISTRIBUICAO,VL_CAUSA,PERCENTAGEM_HONORARIO ")
                              .append(" FROM PROCESSO,PARTE,CLASSE_PROCESSO CP,ORGAO_JUDICIARIO OJ,JUIZO J ")
                              .append(" WHERE (? IS NULL OR OJ.ORGAO_JUDICIARIO_CODIGO = ? ) AND ") 
                              .append("     (? IS NULL OR CP.CLASSE_PROCESSO_CODIGO = ? ) AND ")
                              .append("     (? IS NULL OR STR_NUMERO_PROCESSO = ? ) AND ")
                              .append("     PROCESSO.PROCESSO_CODIGO = PARTE.PROCESSO_CODIGO AND ")
                              //.append("     PARTE.PESSOA_CODIGO = PESSOA.PESSOA_CODIGO AND ")   
                              .append("     (? IS NULL OR PARTE.PESSOA_CODIGO = ?) AND ")
                              .append("     (? IS NULL OR J.JUIZO_CODIGO = ?) AND ")
                              .append("     CP.CLASSE_PROCESSO_CODIGO = PROCESSO.CLASSE_PROCESSO_CODIGO AND ")
                              .append("     OJ.JUIZO_CODIGO = J.JUIZO_CODIGO AND ")
                              .append("     OJ.ORGAO_JUDICIARIO_CODIGO = PROCESSO.ORGAO_JUDICIARIO_CODIGO ").toString();
        try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
            setParameterValueStatementFilter(stmt, filtro, 
                    new String[]{"orgaoJudiciario.codigo",
                                 "classeProcesso.codigo",
                                 "numero",
                                 "pessoa.codigo","orgaoJudiciario.juizo.codigo"});
			
            rs = stmt.executeQuery();
            return createCollection(rs, ProcessoVO.class,    
                    new String[]{"codigo","orgaoJudiciario.descricao",
                                "classeProcesso.descricao","numero",
                                "status.codigo","dataDistribuicao",
                                "valorCausa","honorario"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn, stmt, rs);
        }
	}

    public void insertPartesProcesso(ProcessoVO processoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        StringBuffer sql = null;
        try{
            Collection partes = processoVO.getPartes();
            if (partes!=null && !partes.isEmpty()){
                sql = new StringBuffer("INSERT INTO PARTE (PROCESSO_CODIGO, PESSOA_CODIGO, TIPO_PARTE, STS_CLIENTE) ")
                    .append("VALUES (?, ?, ?, ?)");
                conn = getConexao();
                stmt = conn.prepareStatement(sql.toString());
                Iterator it = partes.iterator();
                while(it.hasNext()){    
                    stmt.setLong(1, processoVO.getCodigo().longValue());
                    ParteVO parte  = (ParteVO)it.next();
                    stmt.setInt(2, parte.getCodigo().intValue());
                    stmt.setInt(3, parte.getTipoParte().getCodigo().intValue());
                    stmt.setInt(4,parte.getSituacaoCliente().intValue());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }        
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn, stmt, null);
        }
    }
    
    public void removePartesByProcesso(ProcessoVO processoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = new StringBuffer("DELETE FROM PARTE WHERE PROCESSO_CODIGO=? ").toString();
        try{
            conn = getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, processoVO.getCodigo().longValue());
            stmt.execute();
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn, stmt, null);
        }
    }

    public ParteVO findParteByPrimaryKey(ParteVO parteVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = new StringBuffer("SELECT TIPO_PARTE, STS_CLIENTE FROM PARTE WHERE PROCESSO_CODIGO=? AND ")
        .append("PESSOA_CODIGO=?").toString();
        try{
            
            conn = getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, parteVO.getProcesso().getCodigo().longValue());
            stmt.setInt(2, parteVO.getCodigo().intValue());
            rs = stmt.executeQuery();
            if (rs.next()){
                parteVO.setTipoParte(TipoParteVO.findByCodigo(new Integer(rs.getInt("TIPO_PARTE"))));
                parteVO.setSituacaoCliente(new Integer(rs.getInt("STS_CLIENTE")));
                return parteVO;
            }else return null;
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn, stmt, rs);
        }
    }

    public Collection findPartesByProcesso(ProcessoVO processo) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        Collection partesProcesso = new ArrayList();
        try{
            conn = getConexao();
            
            sql = new StringBuffer();
            sql.append(" SELECT TIPO_PARTE, STS_CLIENTE, ") 
               .append("        PESSOA.NOME, PESSOA.PESSOA_CODIGO, PESSOA.APTO, PESSOA.BLOCO ")
               .append(" FROM   PARTE,PESSOA ")
               .append(" WHERE  PROCESSO_CODIGO=? AND ") 
               .append("        PESSOA.PESSOA_CODIGO = PARTE.PESSOA_CODIGO ");
            
            stmt = conn.prepareStatement(sql.toString());
            stmt.setLong(1, processo.getCodigo().longValue());
            
            rs = stmt.executeQuery();
            
            ParteVO parte = null;
            while (rs.next()){
                parte = new ParteVO();
                parte.setCodigo(new Integer(rs.getInt("PESSOA_CODIGO")));
                parte.setNome(rs.getString("NOME"));
                if (rs.getInt("APTO") > 0) 
                    parte.setApto(new Integer(rs.getInt("APTO")));
                parte.setBloco(rs.getString("BLOCO"));
                parte.setProcesso(processo);
                parte.setTipoParte(TipoParteVO.findByCodigo(new Integer(rs.getInt("TIPO_PARTE"))));
                parte.setSituacaoCliente(new Integer(rs.getInt("STS_CLIENTE")));
                partesProcesso.add(parte);
            }
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn, stmt, rs);
        }
        return partesProcesso;
    }
    
    public Collection findByFilterReport(FiltroProcessoVO filtro) throws AmbienteException{
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	String sql = new StringBuffer("SELECT DISTINCT P.PROCESSO_CODIGO, P.STR_NUMERO_PROCESSO, ")
    	.append("OJ.DES_ORGAO_JUDICIARIO, CP.DES_CLASSE_PROCESSO, VL_CAUSA FROM PROCESSO AS P JOIN ORGAO_JUDICIARIO AS OJ ON ")
    	.append("P.ORGAO_JUDICIARIO_CODIGO=OJ.ORGAO_JUDICIARIO_CODIGO JOIN CLASSE_PROCESSO AS CP ON ")
    	.append("P.CLASSE_PROCESSO_CODIGO=CP.CLASSE_PROCESSO_CODIGO JOIN PARTE PA ON ")
    	.append("P.PROCESSO_CODIGO=PA.PROCESSO_CODIGO WHERE (? IS NULL OR OJ.ORGAO_JUDICIARIO_CODIGO = ? ) AND ") 
    	.append("(? IS NULL OR CP.CLASSE_PROCESSO_CODIGO = ? ) AND (? IS NULL OR STR_NUMERO_PROCESSO = ? ) AND ")
    	.append("(? IS NULL OR PA.PESSOA_CODIGO = ?) AND (? IS NULL OR P.STS_PROCESSO=?)").toString();
    	String sqlParte = "SELECT NOME FROM PARTE P JOIN PESSOA PE ON P.PESSOA_CODIGO=PE.PESSOA_CODIGO " + 
		"WHERE PROCESSO_CODIGO=?";
    	String sqlAnd = new StringBuffer("SELECT A.ANDAMENTO_CODIGO, A.DES_ANDAMENTO, TA.DES_TIPO_ANDAMENTO, ")
    	.append("A.DTH_PRAZO FROM ANDAMENTO A, TIPO_ANDAMENTO TA ")
    	.append("WHERE A.TIPO_ANDAMENTO_CODIGO=TA.TIPO_ANDAMENTO_CODIGO AND A.ANDAMENTO_CODIGO IN ") 
    	.append("(SELECT MAX(ANDAMENTO_CODIGO) FROM ANDAMENTO AS A WHERE A.PROCESSO_CODIGO=? AND A.STS_INTERNO=?)")
    	.toString();
    	Collection collAssembler = new ArrayList();
    	try{
    		conn = getConexao();
    		stmt = conn.prepareStatement(sql);
    		setParameterValueStatementFilter(stmt, filtro, new String[]{"orgaoJudiciario.codigo", 
    				"classeProcesso.codigo", "numero", "pessoa.codigo", "status.codigo"});
    		rs = stmt.executeQuery();
    		while(rs.next()){
    			ProcessoAssembler assembler = new ProcessoAssembler();
    			assembler.setCodigo(new Integer(rs.getInt("PROCESSO_CODIGO")));
    			assembler.setNumero(rs.getString("STR_NUMERO_PROCESSO"));
    			assembler.setOrgaoJudiciario(new OrgaoJudiciarioVO(rs.getString("DES_ORGAO_JUDICIARIO")));
    			assembler.setClasseProcesso(new ClasseProcessoVO(rs.getString("DES_CLASSE_PROCESSO")));
                if (rs.getFloat("VL_CAUSA")>0)
                    assembler.setValorCausa(new Float(rs.getFloat("VL_CAUSA")));
    			
    			stmt = conn.prepareStatement(sqlParte);
    			stmt.setInt(1, rs.getInt("PROCESSO_CODIGO"));
    			ResultSet res = stmt.executeQuery();
    			String partes = "";
    			while(res.next()){
    				if (partes.equals("")){
    					partes = res.getString("NOME");
    				}else{
    					partes += ", " + res.getString("NOME");
    				}
    			}
    			assembler.setPartes(partes);
    			
    			stmt = conn.prepareStatement(sqlAnd);
    			stmt.setInt(1, rs.getInt("PROCESSO_CODIGO"));
    			if (filtro.getInterno().booleanValue()) stmt.setInt(2, 1);
    			else stmt.setInt(2, 0);
    			res = stmt.executeQuery();
                AndamentoVO andamento = new AndamentoVO();
                if (res.next()){
    				andamento.setObservacao(res.getString("DES_ANDAMENTO"));
                    andamento.setDataHoraPrazo(res.getTimestamp("DTH_PRAZO"));
    				
                    TipoAndamentoVO tipo = new TipoAndamentoVO();
                    tipo.setDescricao(res.getString("DES_TIPO_ANDAMENTO"));
                    andamento.setTipoAndamento(tipo);
    			}
                assembler.setAndamento(andamento);
    			collAssembler.add(assembler);
    			res.close();
    		}
    		return collAssembler;
    	}catch(SQLException sqlEx){
    		throw new AmbienteException(sqlEx);
    	}finally{
    		this.fechar(conn, stmt, rs);
    	}
    }
    
    public Collection findByClienteFilterReport(FiltroProcessoVO filtro) throws AmbienteException{
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	String sql = new StringBuffer("SELECT DISTINCT P.PROCESSO_CODIGO, P.STR_NUMERO_PROCESSO, ")
    	.append("OJ.DES_ORGAO_JUDICIARIO, CP.DES_CLASSE_PROCESSO, VL_CAUSA FROM PROCESSO AS P JOIN ORGAO_JUDICIARIO AS OJ ON ")
    	.append("P.ORGAO_JUDICIARIO_CODIGO=OJ.ORGAO_JUDICIARIO_CODIGO JOIN CLASSE_PROCESSO AS CP ON ")
    	.append("P.CLASSE_PROCESSO_CODIGO=CP.CLASSE_PROCESSO_CODIGO JOIN PARTE PA ON ")
    	.append("P.PROCESSO_CODIGO=PA.PROCESSO_CODIGO WHERE (? IS NULL OR OJ.ORGAO_JUDICIARIO_CODIGO = ? ) AND ") 
    	.append("(? IS NULL OR CP.CLASSE_PROCESSO_CODIGO = ? ) AND (? IS NULL OR STR_NUMERO_PROCESSO = ? ) AND ")
    	.append("(? IS NULL OR PA.PESSOA_CODIGO = ?) AND P.STS_PROCESSO=1").toString();
    	String sqlParte = "SELECT NOME, PE.APTO, PE.BLOCO, P.TIPO_PARTE FROM PARTE P JOIN PESSOA PE " + 
		"ON P.PESSOA_CODIGO=PE.PESSOA_CODIGO WHERE PROCESSO_CODIGO=? AND P.TIPO_PARTE <> ?";
    	String sqlAnd = new StringBuffer("SELECT A.ANDAMENTO_CODIGO, A.DES_ANDAMENTO, TA.DES_TIPO_ANDAMENTO, ")
    	.append("A.DTH_PRAZO FROM ANDAMENTO A, TIPO_ANDAMENTO TA ")
    	.append("WHERE A.TIPO_ANDAMENTO_CODIGO=TA.TIPO_ANDAMENTO_CODIGO AND A.ANDAMENTO_CODIGO IN ") 
    	.append("(SELECT MAX(ANDAMENTO_CODIGO) FROM ANDAMENTO AS A WHERE A.PROCESSO_CODIGO=? AND A.STS_INTERNO=0)")
    	.toString();
    	Collection collAssembler = new ArrayList();
    	try{
    		conn = getConexao();
    		stmt = conn.prepareStatement(sql);
    		setParameterValueStatementFilter(stmt, filtro, new String[]{"orgaoJudiciario.codigo", 
    				"classeProcesso.codigo", "numero", "pessoa.codigo"});
    		rs = stmt.executeQuery();
    		while(rs.next()){
    			ProcessoAssembler assembler = new ProcessoAssembler();
    			assembler.setNomeCliente(filtro.getPessoa().getNome());
    			assembler.setCodigo(new Integer(rs.getInt("PROCESSO_CODIGO")));
    			assembler.setNumero(rs.getString("STR_NUMERO_PROCESSO"));
    			assembler.setOrgaoJudiciario(new OrgaoJudiciarioVO(rs.getString("DES_ORGAO_JUDICIARIO")));
    			assembler.setClasseProcesso(new ClasseProcessoVO(rs.getString("DES_CLASSE_PROCESSO")));
                if (rs.getFloat("VL_CAUSA")>0)
                    assembler.setValorCausa(new Float(rs.getFloat("VL_CAUSA")));
    			
    			stmt = conn.prepareStatement(sqlParte);
    			stmt.setInt(1, rs.getInt("PROCESSO_CODIGO"));
    			stmt.setInt(2, TipoParteVO.ADVOGADO);
    			ResultSet res = stmt.executeQuery();
    			String partes = "";
    			String apts = "";
    			String blocos = "";
    			while(res.next()){
    				if (partes.equals("")){
    					partes = res.getString("NOME");
    				}else{
    					partes += ", " + res.getString("NOME");
    				}
                    
                    if (res.getInt("TIPO_PARTE") == TipoParteVO.REU){
                        apts = (res.getString("APTO") != null && apts.equals("")) ? res.getString("APTO") : apts;
                        blocos = (res.getString("BLOCO") != null && blocos.equals("")) ? res.getString("BLOCO") : blocos;
                    }
    			}
    			if (apts.equals("")) apts = null;
    			if (blocos.equals("")) blocos = null;
    			assembler.setPartes(partes);
    			assembler.setApts(apts);
    			assembler.setBlocos(blocos);
    			
    			stmt = conn.prepareStatement(sqlAnd);
    			stmt.setInt(1, rs.getInt("PROCESSO_CODIGO"));
    			res = stmt.executeQuery();
    			if (res.next()){
    				assembler.setAndamentoAtual(res.getString("DES_ANDAMENTO"));
    				assembler.setTipoAndamento(res.getString("DES_TIPO_ANDAMENTO"));
    				assembler.setPrazo(res.getTimestamp("DTH_PRAZO"));
    			}
    			collAssembler.add(assembler);
    			res.close();
    		}
    		return collAssembler;
    	}catch(SQLException sqlEx){
    		throw new AmbienteException(sqlEx);
    	}finally{
    		this.fechar(conn, stmt, rs);
    	}
    }
    
    public Collection findAgenda(FiltroProcessoAgendaVO filtro) throws AmbienteException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet resPartes = null;
        StringBuffer sql = null;
        String sqlReu   = "SELECT NOME FROM VW_REU WHERE PROCESSO_CODIGO=?";
        String sqlAutor = "SELECT NOME FROM VW_AUTOR WHERE PROCESSO_CODIGO=?";
        
        Collection agendamentos = new ArrayList();
        try{
            conn = getConexao();
            sql = new StringBuffer(" SELECT  PROCESSO.PROCESSO_CODIGO,OJ.ORGAO_JUDICIARIO_CODIGO, ") 
                .append("    OJ.DES_ORGAO_JUDICIARIO , OJ.JUIZO_CODIGO, J.DES_JUIZO,  ")
                .append("    STR_NUMERO_PROCESSO,VW_ADV.NOME AS NOME_RESPOSNSAVEL, ") 
                .append("    ANDAMENTO.DES_ANDAMENTO,ANDAMENTO.DTH_PRAZO,  ")
                .append("    TIPO_ANDAMENTO.DES_TIPO_ANDAMENTO ") 
                .append("    FROM PROCESSO ")
                .append("    INNER JOIN ORGAO_JUDICIARIO OJ ")
                .append("       ON (PROCESSO.ORGAO_JUDICIARIO_CODIGO = OJ.ORGAO_JUDICIARIO_CODIGO) ")
                .append("    INNER JOIN JUIZO J ")
                .append("       ON (OJ.JUIZO_CODIGO = J.JUIZO_CODIGO) ")   
                .append("    INNER JOIN VW_ADV ")
                .append("       ON (VW_ADV.PROCESSO_CODIGO = PROCESSO.PROCESSO_CODIGO) ") 
                .append("    INNER JOIN ANDAMENTO ")
                .append("       ON (ANDAMENTO.PROCESSO_CODIGO = PROCESSO.PROCESSO_CODIGO) ")
                .append("    INNER JOIN TIPO_ANDAMENTO ")
                .append("       ON (TIPO_ANDAMENTO.TIPO_ANDAMENTO_CODIGO = ANDAMENTO.TIPO_ANDAMENTO_CODIGO) ")
                .append("    WHERE STS_PROCESSO = 1 ") 
                //.append("       AND ANDAMENTO.STS_INTERNO = 1 ")
                .append("       AND (? IS NULL OR VW_ADV.PESSOA_CODIGO = ?) ")    
                .append("       AND (? IS NULL OR TIPO_ANDAMENTO.TIPO_ANDAMENTO_CODIGO = ?) ") 
                .append("       AND (? IS NULL OR DTH_PRAZO >= ?) ")
                .append("       AND (? IS NULL OR DTH_PRAZO <= ?) ")      
                .append("    ORDER BY NOME_RESPOSNSAVEL,ANDAMENTO.DTH_PRAZO ");
                          
            stmt = conn.prepareStatement(sql.toString());
            
            setParameterValueStatementFilter(stmt, filtro, new String[]{"pessoa.codigo", 
                    "tipoAndamento.codigo", "prazoInicial", "prazoFinal"});
            
            rs = stmt.executeQuery();
            while(rs.next()){
                AgendaProcessosAssembler assembler = new AgendaProcessosAssembler();
                assembler.setCodigo(new Integer(rs.getInt("PROCESSO_CODIGO")));
                assembler.setNumero(rs.getString("STR_NUMERO_PROCESSO"));
                assembler.setOrgaoJudiciario(new OrgaoJudiciarioVO(rs.getString("DES_ORGAO_JUDICIARIO")));
                assembler.setAdvogadoResponsavel(rs.getString("NOME_RESPOSNSAVEL"));
                
                //AUTORES
                stmt = conn.prepareStatement(sqlAutor);
                stmt.setInt(1, rs.getInt("PROCESSO_CODIGO"));
                resPartes = stmt.executeQuery();
                StringBuffer partes = new StringBuffer();
                while(resPartes.next()){
                    if (partes.length() == 0 ){
                        partes.append(resPartes.getString("NOME"));
                    }else{
                        partes.append(", " + resPartes.getString("NOME"));
                    }
                }
                assembler.setAutores(partes.toString());
                
                resPartes.close();
                
                //R�US
                stmt = conn.prepareStatement(sqlReu);
                stmt.setInt(1, rs.getInt("PROCESSO_CODIGO"));
                resPartes = stmt.executeQuery();
                partes = new StringBuffer();
                while(resPartes.next()){
                    if (partes.length() == 0){
                        partes.append(resPartes.getString("NOME"));
                    }else{
                        partes.append(", " + resPartes.getString("NOME"));
                    }
                }
                assembler.setReus(partes.toString());
                
                resPartes.close();
                
                AndamentoVO andamento = new AndamentoVO();
                
                TipoAndamentoVO tipoAndamentoVO = new TipoAndamentoVO();
                tipoAndamentoVO.setDescricao(rs.getString("DES_TIPO_ANDAMENTO"));
                andamento.setTipoAndamento(tipoAndamentoVO);
                
                andamento.setObservacao(rs.getString("DES_ANDAMENTO"));
                andamento.setDataHoraPrazo(rs.getTimestamp("DTH_PRAZO"));
                
                assembler.setAndamento(andamento);
                
                agendamentos.add(assembler);
                
            }
            return agendamentos;
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            this.fechar(conn, stmt, rs);
        }
    }
}
