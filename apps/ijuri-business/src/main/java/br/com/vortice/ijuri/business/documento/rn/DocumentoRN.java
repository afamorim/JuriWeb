package br.com.vortice.ijuri.business.documento.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.documento.rn.dao.DocumentoDAOIf;
import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.core.documento.DocumentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class DocumentoRN extends RegraNegocio {
	
    private DocumentoDAOIf documentoDAO;
    
    public DocumentoRN(){
    }
    
    public DocumentoVO insert(DocumentoVO documentoVO) throws AmbienteException,AplicacaoException{
        return documentoDAO.insert(documentoVO);
    }
    
    public void remove(DocumentoVO documentoVO) throws AmbienteException,AplicacaoException{
        documentoDAO.remove(documentoVO);
    }

    public DocumentoVO findByPrimaryKey(DocumentoVO documentoVO) throws AmbienteException{
        return documentoDAO.findByPrimaryKey(documentoVO);
    }

    public Collection findByDiretorio(DiretorioVO diretorioVO) throws AmbienteException{
        return documentoDAO.findByDiretorio(diretorioVO);
    }

	public void setDocumentoDAO(DocumentoDAOIf documentoDAO) {
		this.documentoDAO = documentoDAO;
	}
    
}