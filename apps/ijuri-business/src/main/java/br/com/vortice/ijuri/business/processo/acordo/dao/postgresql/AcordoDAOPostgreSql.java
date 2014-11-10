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

import br.com.vortice.ijuri.business.processo.acordo.dao.AcordoDAOIf;
import br.com.vortice.ijuri.core.processo.TipoParteVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoFiltroAssembler;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

/**
 * @author Antonio Amadeu
 */
public class AcordoDAOPostgreSql extends PostGreSqlDAO implements AcordoDAOIf {

	public AcordoVO insert(AcordoVO acordoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
	
        try{
            conn = getConexao();
			
            sql = new StringBuffer("INSERT INTO ACORDO( acordo_codigo,processo_codigo,val_acordo,")
                   .append("                            vl_indice_multa, vl_juros_mes, ")
                   .append("                            indice_reajuste_codigo, vl_indice_clasula_penal_mes,")
                   .append("                            qtd_parcelas,dt_vencimento, str_observacao ) ")
                   .append(" VALUES (?,?,?,?,?,?,?,?,?,?) ").toString();
            stmt = conn.prepareStatement(sql);
            
            acordoVO.setCodigo(new Long(this.getSequence("SEQ_ACORDO").intValue()));
            setParameterValueStatement(stmt, acordoVO, 
                    new String[]{"codigo","processo.codigo","valor",
                                 "valorIndiceMulta","valorJurosMes",
                                 "indiceReajuste.codigo", "valorClausulaPenalMes",
                                 "quantidadeParcelas","dataVencimento","observacao"});
			
            stmt.execute();
            
			return acordoVO;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
		    fechar(conn,stmt,null);
        }
	}

	public void update(AcordoVO acordoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("UPDATE ACORDO SET processo_codigo = ?,val_acordo = ?,")
                   .append("                          qtd_parcelas = ? , dt_vencimento = ?, str_observacao = ?, ")
                   .append("                          vl_indice_multa = ?, vl_juros_mes = ?, ")
                   .append("                          indice_reajuste_codigo = ?, vl_indice_clasula_penal_mes = ? ")
                   .append("        WHERE acordo_codigo = ? ").toString();
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, acordoVO, 
                    new String[]{"processo.codigo","valor",
                                 "quantidadeParcelas","dataVencimento","observacao",
                                 "valorIndiceMulta","valorJurosMes",
                                 "indiceReajuste.codigo", "valorClausulaPenalMes",                     
                                 "codigo"});
            
            stmt.execute();
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }
	}

	public void remove(AcordoVO acordoVO) throws AmbienteException,AplicacaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = new StringBuffer("DELETE FROM ACORDO WHERE ACORDO_CODIGO=?").toString();
		try{
			conn = getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, acordoVO.getCodigo().longValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
		}finally{
            fechar(conn,stmt,null);
        }
	}

	public AcordoVO findByPrimaryKey(AcordoVO acordoVO) throws AmbienteException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("       acordo_codigo, proc.processo_codigo, val_acordo, ")
                        .append("       qtd_parcelas, dt_vencimento,  acordo.str_observacao,")
                        .append("       proc.str_numero_processo, ")
                        .append("       proc.vl_causa, proc.percentagem_honorario, ")
                        .append("       vl_indice_multa, vl_juros_mes, ")
                        .append("       indice_reajuste_codigo, vl_indice_clasula_penal_mes ")
                        .append("   FROM ")
                        .append("       acordo ")
                        .append("       INNER JOIN processo proc ON (acordo.processo_codigo=proc.processo_codigo) ")
                        .append("   WHERE ")
                        .append("       (acordo_codigo = ?) ").toString();
            
			stmt = conn.prepareStatement(sql);
			
            stmt.setLong(1, acordoVO.getCodigo().longValue());
			
            rs = stmt.executeQuery();
            
            return (AcordoVO)createVO(rs,AcordoVO.class, 
                    new String[]{"codigo","processo.codigo","valor",
                                 "quantidadeParcelas","dataVencimento","observacao",
                                 "processo.numero","processo.valorCausa","processo.honorario",
                                 "valorIndiceMulta","valorJurosMes",
                                 "indiceReajuste.codigo", "valorClausulaPenalMes"});
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
            fechar(conn,stmt,rs);
        }
	}

	public Collection findByFilter(AcordoFiltroAssembler acordoFiltro) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        Collection collAcordo = new ArrayList();
        try{
            conn = getConexao();
            
            sql = new StringBuffer();
            sql.append( " SELECT DISTINCT ") 
                .append("   acordo.acordo_codigo, acordo.processo_codigo, ")
                .append("   acordo.val_acordo, acordo.qtd_parcelas, ")
                .append("   acordo.dt_vencimento, acordo.str_observacao, ")
                .append("   proc.str_numero_processo,proc.vl_causa, ")
                .append("   proc.percentagem_honorario ")
                .append(" FROM ")
                .append("   acordo ")
                .append("   INNER JOIN public.processo proc ON (acordo.processo_codigo=proc.processo_codigo) ")
                .append("   INNER JOIN public.parte parte_devedor ON (proc.processo_codigo=parte_devedor.processo_codigo) ") 
                .append("   INNER JOIN public.pessoa devedor ON (parte_devedor.pessoa_codigo=devedor.pessoa_codigo) ")
                .append("   INNER JOIN public.parte parte_cliente ON (proc.processo_codigo=parte_cliente.processo_codigo) ") 
                .append("   INNER JOIN public.pessoa cliente ON (parte_cliente.pessoa_codigo=cliente.pessoa_codigo)  ")
                .append("WHERE ")
                .append("   (proc.str_numero_processo = ? OR ? IS NULL) AND ")
                .append("   ((devedor.pessoa_codigo = ? and parte_devedor.tipo_parte = "+TipoParteVO.REU+") OR  ? IS NULL) AND ")
                .append("   ((cliente.pessoa_codigo = ? and parte_cliente.tipo_parte = "+TipoParteVO.AUTOR+") OR  ? IS NULL) ");
            
//          Taxa Comun
            boolean pesquisaTaxaComun = false;
            if (acordoFiltro.getPeriodoTaxaComumInicio()!=null || acordoFiltro.getPeriodoTaxaComumFim()!=null){
                sql.append(" AND acordo.processo_codigo IN ")
                .append("       (SELECT taxa.processo_codigo ")
                .append("       FROM processo ")
                .append("            INNER JOIN taxa ON(processo.processo_codigo = taxa.processo_codigo) ")
                .append("       WHERE ")
                .append("             tipo_taxa = "+TipoTaxaVO.COMUN)
                .append("             AND (? IS NULL OR (taxa.num_mes + (taxa.num_ano * 100) >= (? + (? * 100))) ) ")
                .append("             AND (? IS NULL OR (taxa.num_mes + (taxa.num_ano * 100) <= (? + (? * 100))) ) ")
                .append(" ) ");
                
                pesquisaTaxaComun = true;
            }    
        
//          Taxa Extra 
            boolean pesquisaTaxaExtra = false;
            if (acordoFiltro.getPeriodoTaxaExtraInicio()!=null || acordoFiltro.getPeriodoTaxaExtraFim()!=null){
                sql.append(" AND acordo.processo_codigo IN ")
                .append("       (SELECT taxa.processo_codigo ")
                .append("       FROM processo ")
                .append("            INNER JOIN taxa ON(processo.processo_codigo = taxa.processo_codigo) ")
                .append("       WHERE ")
                .append("             tipo_taxa = "+TipoTaxaVO.EXTRA)
                .append("             AND (? IS NULL OR (taxa.num_mes + (taxa.num_ano * 100) >= (? + (? * 100))) ) ")
                .append("             AND (? IS NULL OR (taxa.num_mes + (taxa.num_ano * 100) <= (? + (? * 100))) ) ")
                .append(" ) ");
                
                pesquisaTaxaExtra = true;
            }
        
            stmt = conn.prepareStatement(sql.toString());
            
            
            ArrayList filtros = new ArrayList(); 
            
            int i = -1;
            filtros.add(++i,"processo.numero");
            filtros.add(++i,"processo.numero");
            filtros.add(++i,"devedor.codigo");
            filtros.add(++i,"devedor.codigo");
            filtros.add(++i,"cliente.codigo");
            filtros.add(++i,"cliente.codigo");
            
            if (pesquisaTaxaComun){
                filtros.add(++i,"periodoTaxaComumInicio");
                filtros.add(++i,"mesTaxaComumInicio");
                filtros.add(++i,"anoTaxaComumInicio");
                filtros.add(++i,"periodoTaxaComumFim");
                filtros.add(++i,"mesTaxaComumFim");
                filtros.add(++i,"anoTaxaComumFim");
            }
            
            if (pesquisaTaxaExtra){
                filtros.add(++i,"periodoTaxaExtraInicio");
                filtros.add(++i,"mesTaxaExtraInicio");
                filtros.add(++i,"anoTaxaExtraInicio");
                filtros.add(++i,"periodoTaxaExtraFim");
                filtros.add(++i,"mesTaxaExtraFim");
                filtros.add(++i,"anoTaxaExtraFim");
            }
            
            setParameterValueStatement(stmt, acordoFiltro, filtros);
            
            rs = stmt.executeQuery();
            
            return createCollection(rs, AcordoVO.class, 
                    new String[]{"codigo","processo.codigo","valor",
                                 "quantidadeParcelas","dataVencimento","observacao",
                                 "processo.numero","processo.valorCausa","processo.honorario"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
    }
    
    public Collection findRelatorioExternoByFilter(AcordoFiltroAssembler acordoFiltro) throws AmbienteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        Collection collAcordo = new ArrayList();
        try{
            conn = getConexao();
            
            sql = new StringBuffer();
            sql.append( " SELECT DISTINCT ") 
                .append("   acordo.acordo_codigo, acordo.processo_codigo, ")
                .append("   acordo.val_acordo, acordo.qtd_parcelas, ")
                .append("   acordo.dt_vencimento, acordo.str_observacao, ")
                .append("   proc.str_numero_processo,proc.vl_causa, ")
                .append("   proc.percentagem_honorario ")
                .append(" FROM ")
                .append("   acordo ")
                .append("   INNER JOIN public.processo proc ON (acordo.processo_codigo=proc.processo_codigo) ")
                .append("   INNER JOIN public.parte parte_devedor ON (proc.processo_codigo=parte_devedor.processo_codigo) ") 
                .append("   INNER JOIN public.pessoa devedor ON (parte_devedor.pessoa_codigo=devedor.pessoa_codigo) ")
                .append("   INNER JOIN public.parte parte_cliente ON (proc.processo_codigo=parte_cliente.processo_codigo) ") 
                .append("   INNER JOIN public.pessoa cliente ON (parte_cliente.pessoa_codigo=cliente.pessoa_codigo)  ")
                .append("WHERE ")
                .append("   (proc.str_numero_processo = ? OR ? IS NULL) AND ")
                .append("   ((devedor.pessoa_codigo = ? and parte_devedor.tipo_parte = "+TipoParteVO.REU+") OR  ? IS NULL) AND ")
                .append("   ((cliente.pessoa_codigo = ? and parte_cliente.tipo_parte = "+TipoParteVO.AUTOR+") OR  ? IS NULL) ");
            
        
            stmt = conn.prepareStatement(sql.toString());
            
            ArrayList filtros = new ArrayList(); 
            filtros.add("processo.numero");
            filtros.add("processo.numero");
            filtros.add("devedor.codigo");
            filtros.add("devedor.codigo");
            filtros.add("cliente.codigo");
            filtros.add("cliente.codigo");
            
            
            setParameterValueStatement(stmt, acordoFiltro, filtros);
            
            rs = stmt.executeQuery();
            
            return createCollection(rs, AcordoVO.class, 
                    new String[]{"codigo","processo.codigo","valor",
                                 "quantidadeParcelas","dataVencimento","observacao",
                                 "processo.numero","processo.valorCausa","processo.honorario"});
            
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }
    }


}