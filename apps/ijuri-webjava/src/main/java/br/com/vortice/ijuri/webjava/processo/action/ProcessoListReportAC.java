package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.processo.ClasseProcessoVO;
import br.com.vortice.ijuri.core.processo.FiltroProcessoVO;
import br.com.vortice.ijuri.core.processo.OrgaoJudiciarioVO;
import br.com.vortice.ijuri.core.processo.StatusProcessoVO;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class ProcessoListReportAC extends BaseReportAction {
	
	private ProcessoDelegate processoDelegate;
	
	protected String getReport(HttpServletRequest pRequest) {
		return "/admin/processo/relatorio/ProcessoReport.jasper";
	}

	protected Object configurationReport(HttpServletRequest request, HttpServletResponse response, Map map) throws AmbienteException, AplicacaoException {
		
		 super.setParameterImagePath(map, "P_LOGO_PATH", "/img/logo.gif");
		
        FiltroProcessoVO filtroProcesso = new FiltroProcessoVO();
        filtroProcesso  = new FiltroProcessoVO();
        if (request.getParameter("numero") != null && !request.getParameter("numero").equals(""))
            filtroProcesso.setNumero(request.getParameter("numero"));
        if (request.getParameter("orgaoJudiciarioCodigo") != null && !request.getParameter("orgaoJudiciarioCodigo").equals(""))
            filtroProcesso.setOrgaoJudiciario(new OrgaoJudiciarioVO(new Long(request.getParameter("orgaoJudiciarioCodigo"))));
        if (request.getParameter("classeProcessoCodigo") != null && !request.getParameter("classeProcessoCodigo").equals(""))
            filtroProcesso.setClasseProcesso(new ClasseProcessoVO(new Long(request.getParameter("classeProcessoCodigo"))));
        if (request.getParameter("pessoaCodigo")!= null && !request.getParameter("pessoaCodigo").equals("") && !request.getParameter("pessoaCodigo").equals("0"))
            filtroProcesso.setPessoa(new PessoaVO(new Integer(request.getParameter("pessoaCodigo"))));
        if (request.getParameter("interno") != null && request.getParameter("interno").equals("1")){
        	filtroProcesso.setInterno(new Boolean(true));
        }else{
        	filtroProcesso.setInterno(new Boolean(false));
        }
        if (request.getParameter("statusCodigo") != null && !request.getParameter("statusCodigo").equals("")){
        	filtroProcesso.setStatus(StatusProcessoVO.findByCodigo(new Integer(request.getParameter("statusCodigo"))));
        }
        Collection collProcesso = processoDelegate.findByFilterReport(filtroProcesso);
		return collProcesso;
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
