package br.com.vortice.ijuri.webjava.site.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.site.JurisprudenciaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.JurisprudenciaForm;

public class JurisprudenciaCarregarAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
		{
        JurisprudenciaForm jurisForm = (JurisprudenciaForm)form;
        if (!isCampoVazio(jurisForm.getCodigo())){
            JurisprudenciaVO jurisprudenciaVO = new JurisprudenciaVO();
            jurisprudenciaVO.setCodigo(jurisForm.getCodigo());
            
            jurisprudenciaVO = siteDelegate.findJurisprudenciaByPrimaryKey(jurisprudenciaVO);
            
            jurisForm.setCodigo(jurisprudenciaVO.getCodigo());
            jurisForm.setTitulo(jurisprudenciaVO.getTitulo());
            jurisForm.setAreaAtuacaoCodigo(jurisprudenciaVO.getAreaAtuacao().getCodigo());
            jurisForm.setDocumentoCodigo(jurisprudenciaVO.getDocumento().getCodigo());
            jurisForm.setDocumentoNome(jurisprudenciaVO.getDocumento().getNome());
        }
        jurisForm.carregarComponentes(mapping, request);
        return mapping.getInputForward();
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}
