/**
 * 
 */
package br.com.vortice.ijuri.webjava.processo.acordo.action;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.abstracao.util.DateUtil;
import br.com.vortice.ijuri.core.pessoa.PessoaVO;
import br.com.vortice.ijuri.core.processo.ProcessoVO;
import br.com.vortice.ijuri.core.processo.acordo.ParcelaAcordoFiltroReportAssembler;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;
import br.com.vortice.ijuri.webjava.processo.acordo.AcordoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

/**
 * @author Amadeu
 *
 */
public class ParcelaAcordoClienteListReportAC extends BaseReportAction {
	
	private AcordoDelegate acordoDelegate;
	
    protected String getReport(HttpServletRequest pRequest) {
        return "/admin/processo/acordo/relatorio/ParcelaAcordoListClienteReport.jasper";
    }

    protected Object configurationReport(HttpServletRequest request, HttpServletResponse response, Map parametros) 
    	throws AmbienteException,AplicacaoException 
    {
        //Filtro
        ParcelaAcordoFiltroReportAssembler filtro = new ParcelaAcordoFiltroReportAssembler();
        
        PessoaVO cliente = new PessoaVO();
        cliente.setNome(request.getParameter("clienteNome"));
        cliente.setCodigo(Integer.valueOf(request.getParameter("clienteCodigo")));
        filtro.setCliente(cliente);
        
        String nomeDevedor = request.getParameter("devedorNome");
        if (nomeDevedor!=null && nomeDevedor.trim().length()>0){
            PessoaVO devedor = new PessoaVO();
            devedor.setNome(nomeDevedor);
            devedor.setCodigo(Integer.valueOf(request.getParameter("devedorCodigo")));
            filtro.setDevedor(devedor);
        }
        
        DateUtil dateFormat = DateUtil.getInstance(getLocale());
        String dataRepasseInicio = request.getParameter("dataRepasseInicio");
        if (dataRepasseInicio!=null && dataRepasseInicio.trim().length()>0){
            filtro.setDataRepasseInicio(dateFormat.convertStringToDate(dataRepasseInicio));
        }
        
        String dataRepasseFinal = request.getParameter("dataRepasseFinal");
        if (dataRepasseFinal!=null && dataRepasseFinal.trim().length()>0){
            filtro.setDataRepasseFinal(dateFormat.convertStringToDate(dataRepasseFinal));
        }
        
        String numeroProcesso = request.getParameter("processoNumero");
        if (numeroProcesso!=null && numeroProcesso.trim().length()>0){
            ProcessoVO processo = new ProcessoVO();
            processo.setNumero(numeroProcesso);
            filtro.setProcesso(processo);
        }
        //Parametros
        parametros.put("P_NOME_CLIENTE", cliente.getNome());
        super.setParameterImagePath(parametros, "P_LOGO_PATH", "/img/logo.gif");
        
        //DataSource
        return acordoDelegate.findRelatorioClienteByFilter(filtro);
    }

	public void setAcordoDelegate(AcordoDelegate acordoDelegate) {
		this.acordoDelegate = acordoDelegate;
	}
	
	@Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		acordoDelegate = (AcordoDelegate)context.getBean("acordoDelegate");
	}

    protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}

}