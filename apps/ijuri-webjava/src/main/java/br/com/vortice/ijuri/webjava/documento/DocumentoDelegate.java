package br.com.vortice.ijuri.webjava.documento;

import java.util.Collection;

import br.com.vortice.ijuri.business.documento.rn.DiretorioRN;
import br.com.vortice.ijuri.business.documento.rn.DocumentoRN;
import br.com.vortice.ijuri.core.abstracao.BusinessDelegateAB;
import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.core.documento.DocumentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class DocumentoDelegate extends BusinessDelegateAB {
	
	private DocumentoRN documentoRN;
	private DiretorioRN	diretorioRN;
	
	public DocumentoDelegate() throws AmbienteException{
		//bean = (Documento)getEJBSessionRemote("Documento");
	}
	
	public DocumentoVO insert(DocumentoVO documentoVO) throws AmbienteException, AplicacaoException {
		return documentoRN.insert(documentoVO);
	}
    
    public void remove(DocumentoVO documentoVO) throws AmbienteException,AplicacaoException{
        documentoRN.remove(documentoVO);
    }
    
    public DocumentoVO findByPrimaryKey(DocumentoVO documentoVO) throws AmbienteException, AplicacaoException {
    	return documentoRN.findByPrimaryKey(documentoVO);
    }
    
    public Collection findByDiretorio(DiretorioVO diretorioVO) throws AmbienteException{
    	return documentoRN.findByDiretorio(diretorioVO);
    }
    
    public DiretorioVO insert(DiretorioVO diretorioVO) throws AmbienteException,AplicacaoException{
    	return diretorioRN.insert(diretorioVO);
    }

    public void update(DiretorioVO diretorioVO) throws AmbienteException{
    	diretorioRN.update(diretorioVO);
    }

    public DiretorioVO findDiretorioByPrimaryKey(DiretorioVO diretorioVO) throws AmbienteException{
    	return diretorioRN.findByPrimaryKey(diretorioVO);
    }
    
    public Collection findArvoreDiretorioByLevel(Integer level) throws AmbienteException{
    	return diretorioRN.findByLevel(level);
    }
    
    public Collection findArvoreDiretorioBySubDiretorio(DiretorioVO diretorioVO) throws AmbienteException{
    	return diretorioRN.findBySubDiretorio(diretorioVO);
    }
    
    public Collection findAllArvoreDiretorio() throws AmbienteException{
    	return diretorioRN.findAll();
    }
    
    public void remove(DiretorioVO diretorioVO) throws AmbienteException,AplicacaoException{
    	diretorioRN.remove(diretorioVO); 
    }

	public void setDocumentoRN(DocumentoRN documentoRN) {
		this.documentoRN = documentoRN;
	}

	public void setDiretorioRN(DiretorioRN diretorioRN) {
		this.diretorioRN = diretorioRN;
	}
	
}