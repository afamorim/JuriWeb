/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.core.processo.acordo.RegimeMultaVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.AcordoForm;

/**
 * @author Antonio Amadeu
 */
public class AcordoCarregarCadastroAC extends BaseAction{
	
	private AcordoDelegate acordoDelegate;
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        AcordoForm acordoForm = (AcordoForm)form;
        
        if (!isCampoVazio(acordoForm.getCodigo()))
        {
            AcordoVO acordoVO = new AcordoVO();
            acordoVO.setCodigo(Long.valueOf(acordoForm.getCodigo()));
            
            acordoVO = acordoDelegate.findByPrimaryKey(acordoVO);
            
            BeanUtils.copyProperties(acordoForm, acordoVO);
            
            acordoForm.setProcessoCodigo(acordoVO.getProcesso().getCodigo());
            acordoForm.setProcessoNumero(acordoVO.getProcesso().getNumero());
            
            if (acordoVO.getIndiceReajuste()!=null && acordoVO.getIndiceReajuste().getCodigo()!=null){
                acordoForm.setIndiceReajusteCodigo(acordoVO.getIndiceReajuste().getCodigo());
            }
            
            if (acordoVO.getValorIndiceMulta()!=null){
                acordoForm.setRegimeMultaCodigo(RegimeMultaVO.MULTA.toString());
            }else{
                acordoForm.setRegimeMultaCodigo(RegimeMultaVO.CLAUSULA_PENAL.toString());
            }
            
            DecimalFormat formatter = new DecimalFormat("#,##0.00",new DecimalFormatSymbols(getLocale()));
            acordoForm.setValor(formatter.format(acordoVO.getProcesso().getValorCausa().doubleValue()));
            if (!isCampoVazio(acordoVO.getProcesso().getHonorario()))
            		acordoForm.setHonorario(DecimalFormat.getPercentInstance(getLocale()).format(acordoVO.getProcesso().getHonorario().doubleValue()/100));
        }
        ServletContext servletContext = this.servlet.getServletContext();
    	WebApplicationContext webAppContext = 
    		WebApplicationContextUtils.getWebApplicationContext(servletContext);
        acordoForm.carregarComponentes(mapping, request,webAppContext);
        return mapping.getInputForward();
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}

	public CorrecaoMonetariaDelegate getCorrecaoMonetariaDelegate() {
		return correcaoMonetariaDelegate;
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}

	public AcordoDelegate getAcordoDelegate() {
		return acordoDelegate;
	}
		
}