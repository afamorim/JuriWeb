package br.com.vortice.ijuri.webjava.monetario.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseActionForm;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;

public class IndiceReajusteImportacaoForm extends BaseActionForm {
	
	private Integer indiceReajusteCodigo;
   
    private FormFile arquivo;
    
    public FormFile getArquivo() {
        return arquivo;
    }
    public void setArquivo(FormFile arquivo) {
        this.arquivo = arquivo;
    }
    public Integer getIndiceReajusteCodigo() {
        return indiceReajusteCodigo;
    }
    public void setIndiceReajusteCodigo(Integer indiceReajusteCodigo) {
        this.indiceReajusteCodigo = indiceReajusteCodigo;
    }
    
    public void carregarComponentes(ActionMapping mapping, HttpServletRequest request, CorrecaoMonetariaDelegate delegate) throws Exception {
        Collection collIndiceReajuste = delegate.findAllIndiceReajuste();
        request.setAttribute("indices", collIndiceReajuste);
    } 
	
}
