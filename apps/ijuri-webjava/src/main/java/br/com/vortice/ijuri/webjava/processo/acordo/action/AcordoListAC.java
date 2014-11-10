/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoFiltroAssembler;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.AcordoForm;


/**
 * @author Antonio Amadeu
 */
public class AcordoListAC extends BaseAction{
    
	private AcordoDelegate acordoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception
	{
        AcordoForm acordoForm = (AcordoForm)form;
        Collection acordos  = new ArrayList();
        if (acordoForm.getAcao()!=null){
            AcordoFiltroAssembler acordoFiltro = new AcordoFiltroAssembler();
           
            if (!isCampoVazio(acordoForm.getDevedorCodigo()))
                acordoFiltro.setDevedor(new PessoaVO(acordoForm.getDevedorCodigo()));
            
            if (!isCampoVazio(acordoForm.getClienteCodigo()))
                acordoFiltro.setCliente(new PessoaVO(acordoForm.getClienteCodigo()));
            
            if (!isCampoVazio(acordoForm.getPeriodoTaxaComumInicio()) && 
                    acordoForm.getPeriodoTaxaComumInicio().indexOf("mm/aaaa")<0)
                acordoFiltro.setPeriodoTaxaComumInicio(DateUtil.getInstance().convertStringToDate("01/"+acordoForm.getPeriodoTaxaComumInicio()));
            
            if (!isCampoVazio(acordoForm.getPeriodoTaxaComumFim()) &&
                    acordoForm.getPeriodoTaxaComumFim().indexOf("mm/aaaa")<0)
                acordoFiltro.setPeriodoTaxaComumFim(DateUtil.getInstance().convertStringToDate("01/"+acordoForm.getPeriodoTaxaComumFim()));
            
            if (!isCampoVazio(acordoForm.getPeriodoTaxaExtraInicio()) &&
                    acordoForm.getPeriodoTaxaExtraInicio().indexOf("mm/aaaa")<0)
                acordoFiltro.setPeriodoTaxaExtraInicio(DateUtil.getInstance().convertStringToDate("01/"+acordoForm.getPeriodoTaxaExtraInicio()));
            
            if (!isCampoVazio(acordoForm.getPeriodoTaxaExtraFim()) &&
                    acordoForm.getPeriodoTaxaExtraFim().indexOf("mm/aaaa")<0)
                acordoFiltro.setPeriodoTaxaExtraFim(DateUtil.getInstance().convertStringToDate("01/"+acordoForm.getPeriodoTaxaExtraFim()));
            
            if (!isCampoVazio(acordoForm.getProcessoNumero())){
                ProcessoVO processo = new ProcessoVO();
                processo.setNumero(acordoForm.getProcessoNumero());
                acordoFiltro.setProcesso(processo);
            }    
            acordos  = acordoDelegate.findByFilter(acordoFiltro);
        }
        request.setAttribute("acordos", acordos);
        return mapping.getInputForward();		
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}
	
}