/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;


/**
 * @author Antonio Amadeu
 */
public class ProcessoDetalhamentoReportAC extends BaseReportAction{
    
	private ProcessoDelegate processoDelegate;
	private static final Logger LOG = Logger.getLogger(ProcessoDetalhamentoReportAC.class);
	
    protected Object configurationReport(HttpServletRequest pRequest, 
                                         HttpServletResponse pResponse, 
                                         Map parametros) throws AmbienteException, AplicacaoException {
        
        ProcessoVO processoVO = new ProcessoVO(Long.valueOf(pRequest.getParameter("codigo")));
        processoVO = processoDelegate.findByPrimaryKey(processoVO);

        super.setParameterImagePath(parametros, "P_LOGO_PATH", "/img/logo.gif");
        
        Collection andamentos = processoDelegate.findAndamentosByProcesso(processoVO);
        super.addSubReport("AndamentosProcesso", 
                "/admin/processo/relatorio/AndamentosProcessoSubReport.jasper", 
                andamentos, 
                parametros);
        
        Collection partes = processoDelegate.findPartesByProcesso(processoVO);
        LOG.debug("partes " + partes);
        LOG.debug("parametros " + parametros);
        super.addSubReport("PartesProcesso", "/admin/processo/relatorio/PartesProcessoSubReport.jasper", partes, parametros);
        
        return processoVO;
    }

    protected String getReport(HttpServletRequest pRequest) {
        return "/admin/processo/relatorio/ProcessoDetalhamentoReport.jasper";
    }
    
    @Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		processoDelegate = (ProcessoDelegate)context.getBean("processoDelegate");
	}

    protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}

    
}
