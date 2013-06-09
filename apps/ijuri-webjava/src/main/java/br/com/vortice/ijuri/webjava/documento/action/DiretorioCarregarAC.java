package br.com.vortice.ijuri.documento.cliente.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.abstracao.view.BaseAction;
import br.com.vortice.ijuri.documento.DiretorioVO;
import br.com.vortice.ijuri.documento.cliente.web.DocumentoDelegate;
import br.com.vortice.ijuri.documento.cliente.web.form.DiretorioForm;

/**
 * @author Amadeu
 *
 */
public class DiretorioCarregarAC extends BaseAction {

	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DiretorioForm diretorioForm = (DiretorioForm)form;
        
        if (!isCampoVazio(diretorioForm.getCodigo())){
            //Carregar Edição
            DiretorioVO diretorio = new DiretorioVO();
            diretorio.setCodigo(diretorioForm.getCodigo());
            
            diretorio = documentoDelegate.findDiretorioByPrimaryKey(diretorio);
            
            diretorioForm.setDiretorioPaiCodigo(diretorio.getParent().getCodigo());
            diretorioForm.setNome(diretorio.getNome());
        }
        
        return mapping.getInputForward();
    }

	public void setDocumentoDelegate(DocumentoDelegate documentoDelegate) {
		this.documentoDelegate = documentoDelegate;
	}
}