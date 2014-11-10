package br.com.vortice.ijuri.webjava.site.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.documento.DocumentoVO;
import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.core.site.JurisprudenciaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.JurisprudenciaForm;

public class JurisprudenciaPesquisarAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
        JurisprudenciaForm jurisForm = (JurisprudenciaForm)form;
        
        JurisprudenciaVO jurisprudenciaVO = new JurisprudenciaVO();
        
        if (!isCampoVazio(jurisForm.getTitulo())){
            jurisprudenciaVO.setTitulo(jurisForm.getTitulo());
        }
        if (!isCampoVazio(jurisForm.getDocumentoCodigo())){
            DocumentoVO documentoVO = new DocumentoVO();
            documentoVO.setCodigo(jurisForm.getDocumentoCodigo());
            jurisprudenciaVO.setDocumento(documentoVO);
        }
        if (!isCampoVazio(jurisForm.getAreaAtuacaoCodigo())){
            jurisprudenciaVO.setAreaAtuacao(AreaAtuacaoVO.findByCodigo(jurisForm.getAreaAtuacaoCodigo()));
            //Caso seja demonstrado no Site
            request.setAttribute("areaAtuacao", jurisprudenciaVO.getAreaAtuacao().getDescricao());
        }
        Collection jurisprudencias = siteDelegate.findJurisprudenciaByFilter(jurisprudenciaVO);
        request.setAttribute("jurisprudencias", jurisprudencias);
        
         jurisForm.carregarComponentes(mapping, request);    
        
        return mapping.getInputForward();
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}