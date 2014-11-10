package br.com.vortice.ijuri.business.documento.rn.dao.postgresql;

import java.util.Collection;

import br.com.vortice.ijuri.business.documento.rn.dao.DiretorioDAOIf;
import br.com.vortice.ijuri.core.documento.DiretorioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;
import com.vortice.core.persistencia.PostGreSqlDAO;

public class DiretorioDAOPostgreSql extends PostGreSqlDAO implements DiretorioDAOIf {
    
    public DiretorioVO insert(DiretorioVO diretorioVO) throws AmbienteException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
    
        try{
            conn = getConexao();
            
            sql = new StringBuffer("INSERT INTO diretorio(")
                    .append("           diretorio_codigo, nom_diretorio, diretorio_pai_codigo, str_sort_key) ") 
                    .append("       VALUES(?, ?, ?, ?) ").toString();
                   
            stmt = conn.prepareStatement(sql);
            
            diretorioVO.setCodigo(new Long(this.getSequence("SEQ_DIRETORIO").intValue()));
            setParameterValueStatement(stmt, diretorioVO, 
                    new String[]{"codigo","nome","parent.codigo","sortKey"});
            
            stmt.execute();
            
            return diretorioVO;
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }*/
    	return null;
    }

    public void update(DiretorioVO diretorioVO) throws AmbienteException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
    
        try{
            conn = getConexao();
            
            sql = new StringBuffer("UPDATE diretorio SET ")
                    .append("            nom_diretorio = ? ") 
                    .append("       where diretorio_codigo = ? ").toString();
                   
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, diretorioVO, 
                    new String[]{"nome","codigo"});
            
            stmt.execute();

        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }*/
    }
    
    public void remove(DiretorioVO diretorioVO) throws AmbienteException,AplicacaoException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;
    
        try{
            conn = getConexao();
            
            sql = new StringBuffer("DELETE FROM diretorio ")
                    .append("       WHERE diretorio_codigo = ? ").toString();
                   
            stmt = conn.prepareStatement(sql);
            
            setParameterValueStatement(stmt, diretorioVO, 
                    new String[]{"codigo"});
            
            stmt.execute();

        }catch(SQLException sqlEx){
            tratarExcessaoRemove(sqlEx);
        }finally{
            fechar(conn,stmt,null);
        }*/
    }

    public DiretorioVO findByPrimaryKey(DiretorioVO diretorioVO) throws AmbienteException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("     diretorio_codigo, nom_diretorio, ") 
                        .append("     diretorio_pai_codigo, str_sort_key,level ") 
                        .append("   FROM VW_DIRETORIO  ") 
                        .append("   WHERE diretorio_codigo = ? ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, diretorioVO.getCodigo().longValue());
            
            rs = stmt.executeQuery();
            
            return (DiretorioVO)createVO(rs,DiretorioVO.class, 
                    new String[]{"codigo","nome","parent.codigo",
                                 "sortKey", "level"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }*/
    	return null;
    }
    
    public Collection findFilhosBydiretorio(DiretorioVO diretorioVO) throws AmbienteException{
        /*Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("     diretorio_codigo, nom_diretorio, ") 
                        .append("     diretorio_pai_codigo, str_sort_key,level ") 
                        .append("   FROM VW_DIRETORIO  ") 
                        .append("   WHERE diretorio_pai_codigo = ? ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, diretorioVO.getCodigo().longValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,DiretorioVO.class, 
                    new String[]{"codigo","nome","parent.codigo",
                                 "sortKey", "level"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }*/
    	return null;
        
    }

    public Collection findByLevel(Integer level) throws AmbienteException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("     diretorio_codigo, nom_diretorio, ") 
                        .append("     diretorio_pai_codigo, str_sort_key,level ") 
                        .append("   FROM VW_DIRETORIO  ")
                        .append("   WHERE level <= ? ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, level.intValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,DiretorioVO.class, 
                    new String[]{"codigo","nome","parent.codigo",
                                 "sortKey", "level"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }*/
    	return null;
    }

    public Collection findAll() throws AmbienteException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("     diretorio_codigo, nom_diretorio, ") 
                        .append("     diretorio_pai_codigo, str_sort_key,level ") 
                        .append("   FROM VW_DIRETORIO  ").toString();
                        
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,DiretorioVO.class, 
                    new String[]{"codigo","nome","parent.codigo",
                                 "sortKey", "level"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }*/
    	return null;
    }

    public Collection findBySubDiretorio(DiretorioVO diretorioVO) throws AmbienteException {
        /*Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try{
            conn = getConexao();
            
            sql = new StringBuffer("SELECT ") 
                        .append("       diretorio_codigo, nom_diretorio, ") 
                        .append("       diretorio_pai_codigo, str_sort_key,level ") 
                        .append("   FROM VW_DIRETORIO  ")
                        .append("   WHERE str_sort_key = (SELECT str_sort_key || '%' ") 
                        .append("                         FROM vw_diretorio ") 
                        .append("                         WHERE diretorio_codigo = ? AND level <= ?) ").toString();
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, diretorioVO.getCodigo().longValue());
            stmt.setInt(2, diretorioVO.getLevel().intValue());
            
            rs = stmt.executeQuery();
            
            return createCollection(rs,DiretorioVO.class, 
                    new String[]{"codigo","nome","parent.codigo",
                                 "sortKey", "level"});
        }catch(SQLException sqlEx){
            throw new AmbienteException(sqlEx);
        }finally{
            fechar(conn,stmt,rs);
        }*/
    	return null;
    }
}