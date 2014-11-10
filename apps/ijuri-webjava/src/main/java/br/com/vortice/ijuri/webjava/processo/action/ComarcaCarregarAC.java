package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.localidade.EstadoVO;
import br.com.vortice.ijuri.core.processo.ComarcaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.localidade.LocalidadeDelegate;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ComarcaForm;

public class ComarcaCarregarAC extends BaseAction {
	
	private ProcessoDelegate processoDelegate;
	private LocalidadeDelegate localidadeDelegate;
	private static final Logger LOG = Logger.getLogger(ComarcaCarregarAC.class);
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		ComarcaForm comarcaForm = (ComarcaForm)form;		
		Collection collEstado = localidadeDelegate.findAllEstado();
		Collection collMunicipio = new ArrayList();
		
		if (comarcaForm.getCodigo()!=null && comarcaForm.getCodigo().intValue()>0)
        {
        	LOG.debug("veio editar");
            ComarcaVO comarcaVO = new ComarcaVO();
            comarcaVO.setCodigo(new Long(comarcaForm.getCodigo().longValue()));
            comarcaVO = processoDelegate.findByPrimaryKey(comarcaVO);
            
            comarcaForm.setEstadoCodigo(comarcaVO.getMunicipio().getEstadoVO().getCodigo());
            
            LOG.debug("comarcaVO.getCodigo() " + comarcaVO.getCodigo());
            comarcaForm.setCodigo(new Integer(comarcaVO.getCodigo().intValue()));
            LOG.debug("comarcaVO.getMunicipio().getCodigo() " + comarcaVO.getMunicipio().getCodigo());
            comarcaForm.setMunicipioCodigo(comarcaVO.getMunicipio().getCodigo());
        }
		
        if (comarcaForm.getEstadoCodigo() != null && comarcaForm.getEstadoCodigo().intValue()>0 )
        {
        	LOG.debug("VAI CARREGAR O MUNICIPIO");
        	LOG.debug("localidadeDelegate.findMunicipioByUF(new EstadoVO(comarcaForm.getEstadoCodigo())) " + localidadeDelegate.findMunicipioByUF(new EstadoVO(comarcaForm.getEstadoCodigo())));
			collMunicipio = localidadeDelegate.findMunicipioByUF(new EstadoVO(comarcaForm.getEstadoCodigo()));
		}
		request.setAttribute("collEstado", collEstado);
		request.setAttribute("collMunicipio", collMunicipio);
		request.setAttribute("ComarcaForm", comarcaForm);
		LOG.debug("collMunicipio " + collMunicipio);
		if (collMunicipio != null) LOG.debug("collMunicipio.size " + collMunicipio.size());
		LOG.debug("comarcaForm.getCodigo() " + comarcaForm.getCodigo());
		LOG.debug("comarcaForm.getMunicipioCodigo() " + comarcaForm.getMunicipioCodigo());
		return mapping.findForward("success");
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
	public void setLocalidadeDelegate(LocalidadeDelegate localidadeDelegate) {
		this.localidadeDelegate = localidadeDelegate;
	}


}