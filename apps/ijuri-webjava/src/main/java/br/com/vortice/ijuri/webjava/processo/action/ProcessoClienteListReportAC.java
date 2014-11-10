package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;
import java.util.Iterator;
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
import br.com.vortice.ijuri.core.processo.ProcessoAssembler;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.TipoTaxaVO;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class ProcessoClienteListReportAC extends BaseReportAction {

	protected String getReport(HttpServletRequest pRequest) {
		return "/admin/processo/relatorio/ClienteProcessoReport.jasper";
	}
	
	private ProcessoDelegate processoDelegate;
	
	protected Object configurationReport(HttpServletRequest request, HttpServletResponse response, Map map) 
		throws AmbienteException, AplicacaoException 
	{
		FiltroProcessoVO filtroProcesso = new FiltroProcessoVO();	
		super.setParameterImagePath(map, "P_LOGO_PATH", "/img/logo.gif");
		map.put("NOME_PARTE", request.getParameter("pessoaNome"));
		
        if (request.getParameter("numero") != null && !request.getParameter("numero").equals(""))
            filtroProcesso.setNumero(request.getParameter("numero"));
        if (request.getParameter("orgaoJudiciarioCodigo") != null && !request.getParameter("orgaoJudiciarioCodigo").equals(""))
            filtroProcesso.setOrgaoJudiciario(new OrgaoJudiciarioVO(new Long(request.getParameter("orgaoJudiciarioCodigo"))));
        if (request.getParameter("classeProcessoCodigo") != null && !request.getParameter("classeProcessoCodigo").equals(""))
            filtroProcesso.setClasseProcesso(new ClasseProcessoVO(new Long(request.getParameter("classeProcessoCodigo"))));
        if (request.getParameter("pessoaCodigo")!= null && !request.getParameter("pessoaCodigo").equals("") && !request.getParameter("pessoaCodigo").equals("0")){
            filtroProcesso.setPessoa(new PessoaVO(new Integer(request.getParameter("pessoaCodigo"))));
            filtroProcesso.getPessoa().setNome(request.getParameter("pessoaNome"));
        }
        Collection collProcesso = processoDelegate.findByClienteFilterReport(filtroProcesso);
        if (collProcesso != null)
        {
        	Iterator iterator = collProcesso.iterator();
	        while(iterator.hasNext()){
	        	ProcessoAssembler processo = (ProcessoAssembler)iterator.next();    	
	        	Collection taxasComuns = processoDelegate.findTaxasByProcesso(new ProcessoVO(
	        			new Long(processo.getCodigo().longValue())), TipoTaxaVO.findByCodigo(TipoTaxaVO.COMUN)
	        	);
		        processo.setTaxasComuns(taxasComuns);
		       
		        Collection taxasExtras = processoDelegate.findTaxasByProcesso(new ProcessoVO(new Long(processo.getCodigo().longValue())), 
		        			TipoTaxaVO.findByCodigo(TipoTaxaVO.EXTRA)
		        );
		       
		        if (taxasExtras.size() > 0){
		            processo.setTaxasExtras(taxasExtras);
		        }
		        
	        }
		}
        
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