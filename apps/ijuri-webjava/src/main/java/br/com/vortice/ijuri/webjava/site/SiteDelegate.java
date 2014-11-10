package br.com.vortice.ijuri.webjava.site;

import java.util.Collection;

import br.com.vortice.ijuri.business.site.rn.JurisprudenciaRN;
import br.com.vortice.ijuri.business.site.rn.LinkRN;
import br.com.vortice.ijuri.business.site.rn.ParceiroRN;
import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.core.site.JurisprudenciaVO;
import br.com.vortice.ijuri.core.site.LinkVO;
import br.com.vortice.ijuri.core.site.ParceiroVO;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class SiteDelegate {
	
	private LinkRN 				linkRN;
	private JurisprudenciaRN	jurisprudenciaRN;
    private ParceiroRN 			parceiroRN;
	
	public SiteDelegate() throws AmbienteException{
	}
	
	public LinkVO insert(LinkVO vo) throws AmbienteException, AplicacaoException{
		return linkRN.insert(vo);
	}
	
	public void update(LinkVO vo) throws AmbienteException, AplicacaoException{
		linkRN.update(vo);
	}
	
	public void remove(LinkVO vo) throws AmbienteException, AplicacaoException{
		linkRN.remove(vo);
	}
	
	public LinkVO findByPrimaryKey(LinkVO vo) throws AmbienteException, AplicacaoException{
		return linkRN.findByPrimaryKey(vo);
	}
	
	public Collection findSiteByFilter(LinkVO vo) throws AmbienteException, AplicacaoException{
		return linkRN.findByFilter(vo);
	}
	
	public JurisprudenciaVO insert(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException,AplicacaoException{
		return jurisprudenciaRN.insert(jurisprudenciaVO);
    }

    public void remove(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException,AplicacaoException{
    	jurisprudenciaRN.remove(jurisprudenciaVO);
    }

    public void update(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException,AplicacaoException{
    	jurisprudenciaRN.update(jurisprudenciaVO);
    }
    
    public JurisprudenciaVO findJurisprudenciaByPrimaryKey(JurisprudenciaVO jurisprudenciaVO)
        throws AmbienteException{
    	return jurisprudenciaRN.findByPrimaryKey(jurisprudenciaVO);
    }
    
    public Collection findJurisprudenciaByFilter(JurisprudenciaVO jurisprudenciaVO) throws AmbienteException{        
    	return jurisprudenciaRN.findByFilter(jurisprudenciaVO);
    }
    
    public Collection findJurisprudenciaByAreaAtuacao(AreaAtuacaoVO area) throws AmbienteException{
    	return jurisprudenciaRN.findByAreaAtuacao(area);
    }
    
    public ParceiroVO insert(ParceiroVO parceiroVO) throws AmbienteException,AplicacaoException{
    	return parceiroRN.insert(parceiroVO);
    }

    public void remove(ParceiroVO parceiro) throws AmbienteException,AplicacaoException{
    	parceiroRN.remove(parceiro); 
    }
    
    public void update(ParceiroVO parceiroVO) throws AmbienteException,AplicacaoException{
    	parceiroRN.update(parceiroVO);
    }
    
    public ParceiroVO findParceiroByPrimaryKey(ParceiroVO parceiroVO) throws AmbienteException{
    	return parceiroRN.findByPrimaryKey(parceiroVO);
    }
    
    public Collection findParceiroByFilter(ParceiroVO parceiro) throws AmbienteException{
    	return parceiroRN.findByFilter(parceiro);
    }
    
    public Collection findAll() throws AmbienteException {
    	return parceiroRN.findAll();
    }

	public void setJurisprudenciaRN(JurisprudenciaRN jurisprudenciaRN) {
		this.jurisprudenciaRN = jurisprudenciaRN;
	}

	public void setLinkRN(LinkRN linkRN) {
		this.linkRN = linkRN;
	}

	public void setParceiroRN(ParceiroRN parceiroRN) {
		this.parceiroRN = parceiroRN;
	}
    
}