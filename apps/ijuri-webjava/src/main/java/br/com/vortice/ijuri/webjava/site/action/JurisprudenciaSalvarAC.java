package br.com.vortice.ijuri.webjava.site.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.documento.DocumentoVO;
import br.com.vortice.ijuri.core.site.AreaAtuacaoVO;
import br.com.vortice.ijuri.core.site.JurisprudenciaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.site.SiteDelegate;
import br.com.vortice.ijuri.webjava.site.form.JurisprudenciaForm;

public class JurisprudenciaSalvarAC extends BaseAction {
	
	private SiteDelegate siteDelegate;
	
	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception 
	{
		
        JurisprudenciaForm jurisForm = (JurisprudenciaForm)form;
        
        JurisprudenciaVO jurisprudenciaVO = new JurisprudenciaVO();
        
        jurisprudenciaVO.setTitulo(jurisForm.getTitulo());
        jurisprudenciaVO.setAreaAtuacao(AreaAtuacaoVO.findByCodigo(jurisForm.getAreaAtuacaoCodigo()));
        
        DocumentoVO documentoVO = new DocumentoVO();
        documentoVO.setCodigo(jurisForm.getDocumentoCodigo());
        jurisprudenciaVO.setDocumento(documentoVO);

        if (!isCampoVazio(jurisForm.getCodigo())){
            jurisprudenciaVO.setCodigo(jurisForm.getCodigo());
            siteDelegate.update(jurisprudenciaVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }else{
            siteDelegate.insert(jurisprudenciaVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }
        
        jurisForm.limpar();
        
        jurisForm.carregarComponentes(mapping, request);
        
        return mapping.findForward("success");
	}

	public void setSiteDelegate(SiteDelegate siteDelegate) {
		this.siteDelegate = siteDelegate;
	}

}