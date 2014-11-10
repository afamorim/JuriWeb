package br.com.vortice.ijuri.business.site.dao;

import java.util.Collection;

import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.core.site.JurisprudenciaVO;

import com.vortice.core.exception.AmbienteException;

public interface JurisprudenciaDAOIf {

    public JurisprudenciaVO insert(JurisprudenciaVO jurisprudenciaVO)
            throws AmbienteException;

    public void remove(JurisprudenciaVO jurisprudencia)
            throws AmbienteException;

    public void update(JurisprudenciaVO jurisprudenciaVO)
            throws AmbienteException;

    public JurisprudenciaVO findByPrimaryKey(JurisprudenciaVO jurisprudenciaVO)
            throws AmbienteException;

    public Collection findByFilter(JurisprudenciaVO jurisprudencia)
            throws AmbienteException;
    
    public Collection findByAreaAtuacao(AreaAtuacaoVO area) 
            throws AmbienteException;

}