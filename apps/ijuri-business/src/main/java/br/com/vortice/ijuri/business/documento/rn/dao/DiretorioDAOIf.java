package br.com.vortice.ijuri.business.documento.rn.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.documento.DiretorioVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public interface DiretorioDAOIf {

    public abstract DiretorioVO insert(DiretorioVO diretorioVO)
            throws AmbienteException;

    public abstract void update(DiretorioVO diretorioVO)
            throws AmbienteException;

    public abstract DiretorioVO findByPrimaryKey(DiretorioVO diretorioVO)
            throws AmbienteException;

    public abstract Collection findByLevel(Integer level)
            throws AmbienteException;
    
    public abstract Collection findBySubDiretorio(DiretorioVO diretorioVO)
            throws AmbienteException;
    
    public abstract Collection findAll() 
            throws AmbienteException;
    
    public abstract void remove(DiretorioVO diretorioVO) 
        throws AmbienteException,AplicacaoException;
    
    public abstract Collection findFilhosBydiretorio(DiretorioVO diretorioVO) 
        throws AmbienteException;
    
}