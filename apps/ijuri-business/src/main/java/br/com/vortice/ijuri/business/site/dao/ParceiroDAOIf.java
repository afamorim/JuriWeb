package br.com.vortice.ijuri.business.site.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.site.ParceiroVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public interface ParceiroDAOIf {

    public ParceiroVO insert(ParceiroVO parceiroVO)
            throws AmbienteException,AplicacaoException;

    public void remove(ParceiroVO parceiro)
            throws AmbienteException,AplicacaoException;

    public void update(ParceiroVO parceiroVO)
            throws AmbienteException,AplicacaoException;

    public ParceiroVO findByPrimaryKey(ParceiroVO parceiroVO)
            throws AmbienteException;

    public Collection findByFilter(ParceiroVO parceiro)
            throws AmbienteException;
    
    public Collection findAll()
        throws AmbienteException;
    
    
}