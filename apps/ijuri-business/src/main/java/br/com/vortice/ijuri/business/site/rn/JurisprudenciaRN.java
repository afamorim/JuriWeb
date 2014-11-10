package br.com.vortice.ijuri.business.site.rn;

import java.util.Collection;

import br.com.vortice.ijuri.business.abstracao.RegraNegocio;
import br.com.vortice.ijuri.business.site.dao.JurisprudenciaDAOIf;
import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.core.site.JurisprudenciaVO;

import com.vortice.core.exception.AmbienteException;

public class JurisprudenciaRN extends RegraNegocio {
    private JurisprudenciaDAOIf dao;
    
    public JurisprudenciaRN(){
    }
    
    public JurisprudenciaVO insert(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException{
        return dao.insert(jurisprudenciaVO);
    }

    public void remove(JurisprudenciaVO jurisprudencia) throws AmbienteException{
        dao.remove(jurisprudencia); 
    }
    
    public void update(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException{
        dao.update(jurisprudenciaVO);
    }
    
    public JurisprudenciaVO findByPrimaryKey(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException{
        return dao.findByPrimaryKey(jurisprudenciaVO);
    }
    
    public Collection findByFilter(JurisprudenciaVO jurisprudencia) throws AmbienteException{
        return dao.findByFilter(jurisprudencia);
    }
    
    public Collection findByAreaAtuacao(AreaAtuacaoVO area) throws AmbienteException {
        return dao.findByAreaAtuacao(area);
    }

	public void setDao(JurisprudenciaDAOIf dao) {
		this.dao = dao;
	}
    
}
