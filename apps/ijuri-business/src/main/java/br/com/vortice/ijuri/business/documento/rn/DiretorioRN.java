package br.com.vortice.ijuri.business.documento.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.documento.rn.dao.DiretorioDAOIf;
import br.com.vortice.ijuri.business.documento.rn.dao.DocumentoDAOIf;
import br.com.vortice.ijuri.core.documento.DiretorioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class DiretorioRN extends RegraNegocio {
    private DiretorioDAOIf diretorioDAO;
    private DocumentoDAOIf documentoDAO; 
    
    public DiretorioRN(){
    }
    
    public DiretorioVO insert(DiretorioVO diretorioVO) throws AmbienteException,AplicacaoException{
        DiretorioVO diretorioPai = diretorioDAO.findByPrimaryKey(diretorioVO.getParent());
        
        int nivel = diretorioPai.getLevel().intValue();
        String nivelStr = null;
        if (nivel>=10)
            nivelStr = String.valueOf(nivel);
        else
            nivelStr = "0"+nivel;
        
        String sortkey = diretorioPai.getSortKey().concat("/"+nivelStr);
        diretorioVO.setSortKey(sortkey);
        
        return diretorioDAO.insert(diretorioVO);
    }

    public void update(DiretorioVO diretorioVO) throws AmbienteException{
        diretorioDAO.update(diretorioVO);
    }

    public DiretorioVO findByPrimaryKey(DiretorioVO diretorioVO) throws AmbienteException{
        return diretorioDAO.findByPrimaryKey(diretorioVO);
    }

    public Collection findByLevel(Integer level) throws AmbienteException{
        return diretorioDAO.findByLevel(level);
    }
    
    public Collection findBySubDiretorio(DiretorioVO diretorioVO) throws AmbienteException{
        return diretorioDAO.findBySubDiretorio(diretorioVO);
    }
    
    public Collection findAll() throws AmbienteException{
        return diretorioDAO.findAll();
    }
    
    public void remove(DiretorioVO diretorioVO) throws AmbienteException,AplicacaoException{
        Collection filhos = diretorioDAO.findFilhosBydiretorio(diretorioVO); 
        if (filhos.size() == 0){
             Collection documentos = documentoDAO.findByDiretorio(diretorioVO);
             if (documentos.size() == 0){
                 diretorioDAO.remove(diretorioVO);
             }else{
                 throw new AplicacaoException("O diretório não pode ser excluído, pois este possui documentos.");
             }
             
        }else{
            throw new AplicacaoException("O diretório não pode ser excluído, pois este possui outros diretórios.");
        }
    }

	public void setDiretorioDAO(DiretorioDAOIf diretorioDAO) {
		this.diretorioDAO = diretorioDAO;
	}

	public void setDocumentoDAO(DocumentoDAOIf documentoDAO) {
		this.documentoDAO = documentoDAO;
	}
    
}