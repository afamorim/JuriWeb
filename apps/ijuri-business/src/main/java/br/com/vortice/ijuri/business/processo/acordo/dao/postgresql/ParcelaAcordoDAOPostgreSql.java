/*
 * Created on 03/04/2005
 */
package br.com.vortice.ijuri.business.processo.acordo.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import br.com.vortice.ijuri.business.pagamento.dao.postgresql.PagamentoDAOPostgreSql;
import br.com.vortice.ijuri.business.processo.acordo.dao.ParcelaAcordoDAOIf;
import br.com.vortice.ijuri.core.pagamento.PagamentoVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoFiltroReportAssembler;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoReportAssembler;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Antonio Amadeu
 */
public class ParcelaAcordoDAOPostgreSql extends PagamentoDAOPostgreSql implements ParcelaAcordoDAOIf {

	public PagamentoVO insert(PagamentoVO pagamentoVO) throws AmbienteException {
        ParcelaAcordoVO parcelaVO = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		try{
            super.insert(pagamentoVO);
            
            conn = getConexao();
			
            sql = new StringBuffer(" INSERT INTO PARCELA_ACORDO(parcela_acordo_codigo,acordo_codigo,pagamento_codigo, ")
                           .append("                           num_parcela,dt_repasse,vl_repasse, ")
                           .append("                           vl_honorario,vl_clausula_penal,str_observacao) ")
                           .append(" VALUES (?,?,?,?,?,?,?,?) ").toString();
            stmt = conn.prepareStatement(sql);
            
            parcelaVO = (ParcelaAcordoVO)pagamentoVO;
            parcelaVO.setCodigo(new Long(this.getSequence("SEQ_PAGAMENTO").intValue()));
            setParameterValueStatement(stmt, parcelaVO, 
                    new String[]{"codigo","acordo.codigo","codigo","numero",
                                 "dataRepasse","valorRepasse","valorHonorario",
                                 "valorClausulaPenal","observacao"});
			
            stmt.execute();
            
			return parcelaVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{		    fechar(conn,stmt,null);
        }
	}
    
    public void insert(Collection parcelas, AcordoVO acordoVO) throws AmbienteException {
        ParcelaAcordoVO parcelaVO = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
            //Insere pagamentos Gen�ricos
            super.insert(parcelas);
            
            if (parcelas!=null && !parcelas.isEmpty()){
                sql = new StringBuffer(" INSERT INTO PARCELA_ACORDO(parcela_acordo_codigo,acordo_codigo,pagamento_codigo, ")
                                .append("                           num_parcela,dt_repasse,vl_repasse, ")
                                .append("                           vl_honorario,str_observacao) ")
                                .append(" VALUES (?,?,?,?,?,?,?,?) ").toString();
                
                conn = getConexao();
                
                stmt = conn.prepareStatement(sql);
                
                Iterator it = parcelas.iterator();
                while(it.hasNext()){    
                    parcelaVO = (ParcelaAcordoVO)it.next();
                    setParameterValueStatement(stmt, parcelaVO, 
                            new String[]{"codigo","acordo.codigo","codigo","numero",
                                         "dataRepasse","valorRepasse","valorHonorario","observacao"});
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

	public void update(PagamentoVO pagamentoVO) throws AmbienteException {
        ParcelaAcordoVO parcelaVO = null;
        Connection conn = null;
		PreparedStatement stmt = null;
        String sql = null;
        try{
            super.update(pagamentoVO);
            
            sql = new StringBuffer(" UPDATE PARCELA_ACORDO SET ")
                        .append("                           dt_repasse = ?,vl_repasse = ?, ")
                        .append("                           vl_honorario = ?,str_observacao = ?, vl_clausula_penal = ? ")
                        .append("    WHERE parcela_acordo_codigo = ? ").toString();
            
            conn = getConexao();
            
            stmt = conn.prepareStatement(sql);
            
            parcelaVO = (ParcelaAcordoVO)pagamentoVO;
            
            setParameterValueStatement(stmt, parcelaVO, 
                 new String[]{"dataRepasse","valorRepasse","valorHonorario","observacao","valorClausulaPenal","codigo"});
            
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
		String sql = null;
        ParcelaAcordoVO parcelaVO = null;
		try{
            super.remove(pagamentoVO);
            
			conn = getConexao();
            
            sql = " DELETE FROM PARCELA_ACORDO WHERE PARCELA_ACORDO_CODIGO = ? ";
			stmt = conn.prepareStatement(sql);
            
            parcelaVO = (ParcelaAcordoVO)pagamentoVO;
			stmt.setLong(1, parcelaVO.getCodigo().longValue());
			
            stmt.execute();
            
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}
    
    /**
     * M�todo remove todas as parcelas de um determinado acordo e os registros de pagamento gen�rico pois 
     * a constraint � delete cascade.
     * @param acordoVO
     * @throws AmbienteException
     * @throws AplicacaoException
     */
    public void remove(AcordoVO acordoVO) throws AmbienteException,AplicacaoException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
        try{
                sql = " DELETE FROM PARCELA_ACORDO WHERE ACORDO_CODIGO = ? ";
                
                conn = getConexao();
                
                stmt = conn.prepareStatement(sql);
                
                stmt.setLong(1,acordoVO.getCodigo().longValue());
                
                stmt.execute();
            
        }catch(SQLException sqlEx){
             tratarExcessaoRemove(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
    }

	public ParcelaAcordoVO findByPrimaryKey(ParcelaAcordoVO parcelaVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("         parcela_acordo_codigo,acordo_codigo, ")
                      .append("         num_parcela,dt_repasse,vl_repasse, ")
                      .append("         vl_honorario,A.str_observacao, ")
                      .append("         forma_pagamento_codigo,vl_vencimento,vl_pago,dt_pagamento, ")
                      .append("         sts_pagamento,vl_multa,vl_juros,dt_vencimento, ")
                      .append("         C.cheque_codigo,C.num_cheque,C.num_agencia,")
                      .append("         B.num_banco,B.nom_banco,C.dt_recebimento,C.dt_compensacao, ") 
                      .append("         vl_correcao,vl_clausula_penal ")
                      .append("     FROM ")
                      .append("         PARCELA_ACORDO A ")
                      .append("         INNER JOIN PAGAMENTO P ON (P.pagamento_codigo = A.pagamento_codigo) ")
                      .append("         LEFT JOIN CHEQUE C ON (C.cheque_codigo = P.cheque_codigo) ")
                      .append("         LEFT JOIN BANCO B ON (B.num_banco = C.num_banco) ")
                      .append("     WHERE ")
                      .append("         parcela_acordo_codigo = ? ").toString();
                      
            
			stmt = conn.prepareStatement(sql);
			
            stmt.setLong(1, parcelaVO.getCodigo().longValue());
			
            rs = stmt.executeQuery();
            
            return (ParcelaAcordoVO)createVO(rs,ParcelaAcordoVO.class, 
                    new String[]{"codigo","acordo.codigo","numero","dataRepasse","valorRepasse",
                                 "valorHonorario","observacao","formaPagamento.codigo",
                                 "valorVencimento","valorPago","dataPagamento",
                                 "status.codigo","valorMulta","valorJuros","dataVencimento",
                                 "cheque.codigo","cheque.numero","cheque.numeroAgencia",
                                 "cheque.banco.numero","cheque.banco.nome",
                                 "cheque.dataRecebimento","cheque.dataCompensacao",
                                 "valorCorrecao","valorClausulaPenal"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

	public Collection findByAcordo(AcordoVO acordoVO) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("           parcela_acordo_codigo, parcela_acordo.num_parcela, ")
                      .append("           pagamento.vl_vencimento,pagamento.vl_pago, ")
                      .append("           pagamento.sts_pagamento,pagamento.dt_pagamento, ")
                      .append("           pagamento.dt_vencimento,pagamento.forma_pagamento_codigo, ")
                      .append("           num_parcela||'/'||acordo.qtd_parcelas AS num_parcela_fmt ")
                      .append("     FROM ")
                      .append("         parcela_acordo ")
                      .append("         INNER JOIN pagamento ")
                      .append("             ON (parcela_acordo.pagamento_codigo = pagamento.pagamento_codigo) ")
                      .append("         INNER JOIN acordo ")
                      .append("             ON (parcela_acordo.acordo_codigo = acordo.acordo_codigo) ")
                      .append("     WHERE parcela_acordo.acordo_codigo = ? ")
                      .append("     ORDER BY parcela_acordo.num_parcela ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, acordoVO.getCodigo().longValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,ParcelaAcordoVO.class, 
                    new String[]{"codigo","numero","valorVencimento",
                                 "valorPago","status.codigo",
                                 "dataPagamento","dataVencimento",
                                 "formaPagamento.codigo","numeroFormatado"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }   
        
    }
    
    public Collection findRelatorioClienteByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        ArrayList filtros = new ArrayList();
        
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("         P.STR_NUMERO_PROCESSO,P.PROCESSO_CODIGO,A.ACORDO_CODIGO, ")
                      .append("         PA.DT_REPASSE,PA.VL_REPASSE, ")
                      .append("         PAG.DT_PAGAMENTO,PAG.VL_PAGO,")
                      .append("         PAG.VL_VENCIMENTO,PAG.DT_VENCIMENTO,")
                      .append("         PA.VL_HONORARIO, PA.NUM_PARCELA||'/'||A.QTD_PARCELAS AS NUM_PARCELA_FMT, ")
                      .append("         VW_REU.NOME,P.BLOCO,P.APTO, C.DT_COMPENSACAO ")
                      .append("     FROM ACORDO A ")
                      .append("         INNER JOIN PARCELA_ACORDO PA") 
                      .append("             ON (A.ACORDO_CODIGO = PA.ACORDO_CODIGO)")    
                      .append("         INNER JOIN PAGAMENTO PAG")
                      .append("             ON (PAG.PAGAMENTO_CODIGO = PA.PARCELA_ACORDO_CODIGO) ")
                      .append("         LEFT JOIN CHEQUE C ON (C.cheque_codigo = PAG.cheque_codigo) ")
                      .append("         INNER JOIN PROCESSO P ") 
                      .append("             ON (A.PROCESSO_CODIGO = P.PROCESSO_CODIGO)")
                      .append("         INNER JOIN VW_REU ")
                      .append("             ON (P.PROCESSO_CODIGO = VW_REU.PROCESSO_CODIGO) ")    
                      .append("     WHERE  P.STS_PROCESSO = 1 ")
                      .append("            AND VW_REU.PESSOA_CODIGO IN (SELECT PESSOA_CODIGO FROM VW_REU ")
                      .append("                     WHERE VW_REU.PROCESSO_CODIGO =  A.PROCESSO_CODIGO ")
                      .append("                     LIMIT 1) ")      
                      .append("            AND (PA.DT_REPASSE > ? OR ? IS NULL) ")
                      .append("            AND (P.STR_NUMERO_PROCESSO = ? OR ? IS NULL) ");
            
            //seta os filtros do statement
            filtros.add("dataRepasseInicio");
            filtros.add("dataRepasseFinal");
            filtros.add("processo.numero");
            filtros.add("processo.numero");
           
            //Filtra o cliente (AUTOR)
            if (filtroAssembler.getCliente()!=null){
                sql.append(" AND  A.PROCESSO_CODIGO IN ")  
                .append(" (SELECT PROCESSO_CODIGO ")
                .append("  FROM   VW_AUTOR ") 
                .append("  WHERE  PESSOA_CODIGO = ?) ");
                
                filtros.add("cliente.codigo");
            }
            
           //Filtra o devedor (R�U)
           if (filtroAssembler.getDevedor()!=null){
               sql.append(" AND  A.PROCESSO_CODIGO IN ")  
               .append(" (SELECT PROCESSO_CODIGO ")
               .append("  FROM   VW_REU ") 
               .append("  WHERE  PESSOA_CODIGO = ?) ");
               
               filtros.add("devedor.codigo");
           }
           
           sql.append("     ORDER BY VW_REU.BLOCO,VW_REU.APTO,P.PROCESSO_CODIGO,A.ACORDO_CODIGO,PA.NUM_PARCELA ").toString();
            
           stmt = conn.prepareStatement(sql.toString());
        
           setParameterValueStatement(stmt, filtroAssembler,filtros);
           
           rs = stmt.executeQuery();
        
           return createCollection(rs,ParcelaAcordoReportAssembler.class, 
                new String[]{"acordo.processo.numero","acordo.processo.codigo",
                             "acordo.codigo","dataRepasse","valorRepasse",
                             "dataPagamento","valorPago","valorVencimento",
                             "dataVencimento","valorHonorario",
                             "numeroFormatado","devedor.nome",
                             "devedor.bloco","devedor.apto","cheque.dataCompensacao"});
           
           
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }   
        
    }
    
    public Collection findRelatorioByFilter(ParcelaAcordoFiltroReportAssembler filtroAssembler) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        ArrayList filtros = new ArrayList();
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                      .append("         P.STR_NUMERO_PROCESSO,P.PROCESSO_CODIGO,A.ACORDO_CODIGO, ")
                      .append("         PA.DT_REPASSE,PA.VL_REPASSE, ")
                      .append("         PAG.DT_PAGAMENTO,PAG.VL_PAGO,")
                      .append("         PAG.VL_VENCIMENTO,PAG.DT_VENCIMENTO,")
                      .append("         PA.VL_HONORARIO, PA.NUM_PARCELA||'/'||A.QTD_PARCELAS AS NUM_PARCELA_FMT, ")
                      .append("         VW_REU.NOME,P.BLOCO,P.APTO, ")
                      .append("         (SELECT VW_AUTOR.NOME ")
                      .append("          FROM VW_AUTOR ")
                      .append("              INNER JOIN PROCESSO  ")
                      .append("                     ON (PROCESSO.PROCESSO_CODIGO = VW_AUTOR.PROCESSO_CODIGO) ")
                      .append("          WHERE PROCESSO.PROCESSO_CODIGO = P.PROCESSO_CODIGO ")
                      .append("         ) AS NOME_AUTOR, C.DT_COMPENSACAO ")
                      .append("     FROM ACORDO A ")
                      .append("         INNER JOIN PARCELA_ACORDO PA") 
                      .append("             ON (A.ACORDO_CODIGO = PA.ACORDO_CODIGO)")    
                      .append("         INNER JOIN PAGAMENTO PAG")
                      .append("             ON (PAG.PAGAMENTO_CODIGO = PA.PARCELA_ACORDO_CODIGO) ")
                      .append("         LEFT JOIN CHEQUE C ON (C.cheque_codigo = PAG.cheque_codigo) ")
                      .append("         INNER JOIN PROCESSO P ") 
                      .append("             ON (A.PROCESSO_CODIGO = P.PROCESSO_CODIGO)")
                      .append("         INNER JOIN VW_REU ")
                      .append("             ON (P.PROCESSO_CODIGO = VW_REU.PROCESSO_CODIGO) ")    
                      .append("     WHERE  P.STS_PROCESSO = 1 ") 
                      .append("            AND VW_REU.PESSOA_CODIGO IN (SELECT PESSOA_CODIGO FROM VW_REU ")
                      .append("                     WHERE VW_REU.PROCESSO_CODIGO =  A.PROCESSO_CODIGO ")
                      .append("                     LIMIT 1) ")
                      .append("            AND (PA.DT_REPASSE >= ? OR ? IS NULL) ")
                      .append("            AND (PA.DT_REPASSE <= ? OR ? IS NULL) ")
                      .append("            AND (PAG.DT_PAGAMENTO >= ? OR ? IS NULL) ")                      
                      .append("            AND (PAG.DT_PAGAMENTO <= ? OR ? IS NULL) ")
                      .append("            AND (PAG.DT_VENCIMENTO >= ? OR ? IS NULL) ")                      
                      .append("            AND (PAG.DT_VENCIMENTO <= ? OR ? IS NULL) ")
                      .append("            AND (P.STR_NUMERO_PROCESSO = ? OR ? IS NULL) ")
                      .append("            AND (PAG.STS_PAGAMENTO = ? OR ? IS NULL) ");
            
            
            //seta os filtros do statement
            filtros.add("dataRepasseInicio");
            filtros.add("dataRepasseInicio");
            filtros.add("dataRepasseFinal");
            filtros.add("dataRepasseFinal");
            filtros.add("dataPagamentoInicio");
            filtros.add("dataPagamentoInicio");
            filtros.add("dataPagamentoFinal");
            filtros.add("dataPagamentoFinal");
            filtros.add("dataVencimentoInicio");
            filtros.add("dataVencimentoInicio");
            filtros.add("dataVencimentoFinal");
            filtros.add("dataVencimentoFinal");
            filtros.add("processo.numero");
            filtros.add("processo.numero");
            filtros.add("statusPagamento.codigo");
            filtros.add("statusPagamento.codigo");
            
            //Filtra o cliente (AUTOR)
            if (filtroAssembler.getCliente()!=null){
                sql.append(" AND  A.PROCESSO_CODIGO IN ")  
                .append(" (SELECT PROCESSO_CODIGO ")
                .append("  FROM   VW_AUTOR ") 
                .append("  WHERE  PESSOA_CODIGO = ?) ");
                
                filtros.add("cliente.codigo");
            }
            
           //Filtra o devedor (R�U)
           if (filtroAssembler.getDevedor()!=null){
               sql.append(" AND  A.PROCESSO_CODIGO IN ")  
               .append(" (SELECT PROCESSO_CODIGO ")
               .append("  FROM   VW_REU ") 
               .append("  WHERE  PESSOA_CODIGO = ?) ");
               
               filtros.add("devedor.codigo");
           }
           
           sql.append("     ORDER BY NOME_AUTOR,VW_REU.BLOCO,VW_REU.APTO,P.PROCESSO_CODIGO,A.ACORDO_CODIGO,PA.NUM_PARCELA ").toString();
            
           stmt = conn.prepareStatement(sql.toString());
        
           setParameterValueStatement(stmt, filtroAssembler,filtros);
           
           rs = stmt.executeQuery();
        
           return createCollection(rs,ParcelaAcordoReportAssembler.class, 
                new String[]{"acordo.processo.numero","acordo.processo.codigo",
                             "acordo.codigo","dataRepasse","valorRepasse",
                             "dataPagamento","valorPago","valorVencimento",
                             "dataVencimento","valorHonorario",
                             "numeroFormatado","devedor.nome",
                             "devedor.bloco","devedor.apto","cliente.nome","cheque.dataCompensacao"});
           
           
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }   
        
    }


}