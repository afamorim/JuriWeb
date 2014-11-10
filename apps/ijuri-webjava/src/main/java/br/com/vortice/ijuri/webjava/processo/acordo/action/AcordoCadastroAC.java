/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.acordo.AcordoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;
import br.com.vortice.ijuri.webjava.processo.acordo.form.AcordoForm;

/**
 * @author Antonio Amadeu
 */
public class AcordoCadastroAC extends BaseAction{
    
	private AcordoDelegate acordoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{    
        AcordoForm acordoForm = (AcordoForm)form;
        AcordoVO acordoVO = new AcordoVO();
        BeanUtils.copyProperties(acordoVO, acordoForm);
        
        ProcessoVO processo = new ProcessoVO(acordoForm.getProcessoCodigo());
        acordoVO.setProcesso(processo);
        
        IndiceReajusteVO indiceReajuste = new IndiceReajusteVO();
        indiceReajuste.setCodigo(acordoForm.getIndiceReajusteCodigo());
        acordoVO.setIndiceReajuste(indiceReajuste);
        
        if (isCampoVazio(acordoVO.getCodigo())){
            acordoVO = acordoDelegate.insert(acordoVO);
            acordoForm.setCodigo(acordoVO.getCodigo().toString());
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }else{
        	acordoDelegate.update(acordoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }
        acordoForm.carregarComponentes(mapping, request,getWebApplicationContext());

        return mapping.getInputForward();
	}

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}
	
}