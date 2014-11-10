package br.com.vortice.ijuri.business.documento.rn.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.core.documento.DocumentoVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public interface DocumentoDAOIf {


    public abstract DocumentoVO insert(DocumentoVO documentoVO)
            throws AmbienteException,AplicacaoException;

    public abstract DocumentoVO findByPrimaryKey(DocumentoVO documentoVO)
            throws AmbienteException;

    public abstract Collection findByDiretorio(DiretorioVO diretorioVO)
            throws AmbienteException;
    
    public abstract void remove(DocumentoVO documentoVO) 
            throws AmbienteException,AplicacaoException;
}