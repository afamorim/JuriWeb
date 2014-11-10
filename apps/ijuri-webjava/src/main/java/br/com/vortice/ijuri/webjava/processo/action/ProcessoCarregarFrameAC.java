/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.processo.ParteVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoParteVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ProcessoForm;


/**
 * @author Antonio Amadeu
 */
public class ProcessoCarregarFrameAC extends BaseAction{
    
	private ProcessoDelegate	processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ProcessoForm processoForm = (ProcessoForm)form;
        if (!isCampoVazio(processoForm.getCodigo())){
			ProcessoVO processoVO = new ProcessoVO();
			processoVO.setCodigo(processoForm.getCodigo());
			processoVO = processoDelegate.findByPrimaryKey(processoVO);
			
			BeanUtils.copyProperties(processoForm, processoVO);
			
			Collection partes = processoDelegate.findPartesByProcesso(processoVO);
			for (Iterator iter = partes.iterator(); iter.hasNext();) {
			    ParteVO parte = (ParteVO) iter.next();
			    if (parte.isCliente()){
			        processoForm.setClienteCodigo(parte.getCodigo());
			        processoForm.setClienteNome(parte.getNome());
			    }else if (parte.getTipoParte().getCodigo().intValue() == TipoParteVO.REU){
			        processoForm.setDevedorCodigo(parte.getCodigo());
			        processoForm.setDevedorNome(parte.getNome());
			    }
			}
        }
        return mapping.getInputForward();
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}
