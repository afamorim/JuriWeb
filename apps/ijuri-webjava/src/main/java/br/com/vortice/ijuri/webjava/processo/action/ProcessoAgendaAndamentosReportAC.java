/*
 * Created on 12/02/2005
 */
package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoAgendaVO;
import br.com.vortice.ijuri.core.processo.TipoAndamentoVO;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;


/**
 * @author Antonio Amadeu
 */
public class ProcessoAgendaAndamentosReportAC extends BaseReportAction{
    
	private ProcessoDelegate	processoDelegate;
	
    protected Object configurationReport(HttpServletRequest request, HttpServletResponse response, Map parametros) 
    	throws AmbienteException, AplicacaoException 
    {
        super.setParameterImagePath(parametros, "P_LOGO_PATH", "/img/logo.gif");   
        FiltroProcessoAgendaVO filtro = new FiltroProcessoAgendaVO();
        
        if (request.getParameter("tipoAndamentoCodigo").trim().length()>0)
            filtro.setTipoAndamento(new TipoAndamentoVO(Long.valueOf(request.getParameter("tipoAndamentoCodigo"))));
        
        DateUtil dateUtil = DateUtil.getInstance(getLocale());
        if (request.getParameter("prazoInicial").trim().length()>0)
            filtro.setPrazoInicial(dateUtil.convertStringToDate(request.getParameter("prazoInicial")));
        
        if (request.getParameter("prazoFinal").trim().length()>0)
            filtro.setPrazoFinal(dateUtil.convertStringToDate(request.getParameter("prazoFinal")));
        
        if (request.getParameter("pessoaCodigo").trim().length()>0)
            filtro.setPessoa(new PessoaVO(Integer.valueOf(request.getParameter("pessoaCodigo"))));
        return processoDelegate.findAgenda(filtro);
    }

    protected String getReport(HttpServletRequest pRequest) {
        return "/admin/processo/relatorio/AgendaAndamentosReport.jasper";
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