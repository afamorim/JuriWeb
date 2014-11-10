package br.com.vortice.ijuri.webjava.documento.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.documento.DiretorioVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.documento.DocumentoDelegate;
import br.com.vortice.ijuri.webjava.documento.form.DiretorioForm;

/**
 * @author Amadeu
 *
 */
public class DiretorioCadastroAC extends BaseAction {

	private DocumentoDelegate documentoDelegate;
	
    protected ActionForward executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        DiretorioForm diretorioForm = (DiretorioForm)form;
        
        DiretorioVO diretorio = new DiretorioVO();
        diretorio.setCodigo(diretorioForm.getCodigo());
        
        DiretorioVO parent = new DiretorioVO();
        parent.setCodigo(diretorioForm.getDiretorioPaiCodigo());
        diretorio.setParent(parent);
        
        diretorio.setNome(diretorioForm.getNome());
        
        if (isCampoVazio(diretorioForm.getCodigo())){
            documentoDelegate.insert(diretorio);
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }else{
            documentoDelegate.update(diretorio);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }
        
        return mapping.getInputForward();
    }
    
}
