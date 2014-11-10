/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.core.processo.ComarcaVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.StatusProcessoVO;
import br.com.vortice.ijuri.core.processo.TurnoVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagemSucessoIf;
import br.com.vortice.ijuri.webjava.abstracao.view.MensagensErroIf;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;
import br.com.vortice.ijuri.webjava.processo.form.ProcessoForm;

/**
 * @author Antonio Amadeu
 */
public class ProcessoCadastroAC extends BaseAction{
	
	private ProcessoDelegate processoDelegate;
	
	public ActionForward executar(ActionMapping mapping, ActionForm form,  HttpServletRequest request, HttpServletResponse response) throws Exception 
	{    
        
		ProcessoVO processoVO = new ProcessoVO();
        ProcessoForm processoForm = (ProcessoForm)form;
        
        Collection partes = (Collection)request.getSession().getAttribute(request.getParameter("idSessaoParte"));
        if (partes==null || partes.isEmpty()){
            return exibirMensagem(request, MensagensErroIf.VAZIO, new String[]{"Pelo menos uma parte"}, mapping, processoForm);
        }
        processoVO.setPartes(partes);
        
        
        
        BeanUtils.copyProperties(processoVO, processoForm);
        processoVO.setClasseProcesso(new ClasseProcessoVO(processoForm.getClasseProcessoCodigo()));
        processoVO.setOrgaoJudiciario(new OrgaoJudiciarioVO(processoForm.getOrgaoJudiciarioCodigo()));
        processoVO.setComarca(new ComarcaVO(processoForm.getComarcaCodigo()));
        processoVO.setStatus(StatusProcessoVO.findByCodigo(processoForm.getStatusCodigo()));
        processoVO.setTurno(TurnoVO.findByCodigo(processoForm.getTurnoCodigo()));
        
        //seta dados do apto
        if (!isCampoVazio(processoForm.getApto()))
        	processoVO.setApto(Integer.valueOf(processoForm.getApto()));
        if (!isCampoVazio(processoForm.getBloco()))
        	processoVO.setBloco(processoForm.getBloco());
        
        //seta as taxas comuns
        ArrayList taxas = (ArrayList)Base64Decoder.decodeToObject(processoForm.getTaxasComunsCode());
        processoVO.setTaxasComuns(taxas); 
        
        //seta as taxas Extras
        taxas = (ArrayList)Base64Decoder.decodeToObject(processoForm.getTaxasExtrasCode());
        processoVO.setTaxasExtras(taxas);
		
        
        if (processoForm.getCodigo()!=null && processoForm.getCodigo().longValue()>0){
        	processoDelegate.update(processoVO);
            registrarMensagemSucesso(request, MensagemSucessoIf.UPDATE);
        }else{    
        	
            processoVO = processoDelegate.insert(processoVO);
            processoForm.setCodigo(processoVO.getCodigo());
            registrarMensagemSucesso(request, MensagemSucessoIf.INSERT);
        }
        
        processoForm.carregarComponentes(mapping, request, getWebApplicationContext());
        
        /*//Mantem nome de Sess�o das partes
        String nomeSessao = request.getParameter(ParteProcessoListAC.NOME_ATRIBUTO_SESSAO);
        request.setAttribute(ParteProcessoListAC.NOME_ATRIBUTO_SESSAO, nomeSessao);
        */ 
        return new ActionForward(mapping.findForward("success").getPath()+"?codigo="+processoVO.getCodigo());
	}

	public void setProcessoDelegate(ProcessoDelegate processoDelegate) {
		this.processoDelegate = processoDelegate;
	}
	
}