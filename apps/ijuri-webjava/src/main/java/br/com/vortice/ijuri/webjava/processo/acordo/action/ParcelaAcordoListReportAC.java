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
import br.com.vortice.ijuri.core.pagamento.StatusPagamentoVO;
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
public class ParcelaAcordoListReportAC extends BaseReportAction {
	
	private AcordoDelegate acordoDelegate;
	
    protected String getReport(HttpServletRequest pRequest) {
        return "/admin/processo/acordo/relatorio/ParcelaAcordoListReport.jasper";
    }

    protected Object configurationReport(HttpServletRequest request,
                                         HttpServletResponse response, 
                                         Map parametros) throws AmbienteException,AplicacaoException {
        
        //Filtro
        ParcelaAcordoFiltroReportAssembler filtro = new ParcelaAcordoFiltroReportAssembler();
        
        String nomeCliente = request.getParameter("clienteNome");
        if (nomeCliente!=null && nomeCliente.trim().length()>0){
            PessoaVO cliente = new PessoaVO();
            cliente.setNome(request.getParameter("clienteNome"));
            cliente.setCodigo(Integer.valueOf(request.getParameter("clienteCodigo")));
            filtro.setCliente(cliente);
        }
        
        String nomeDevedor = request.getParameter("devedorNome");
        if (nomeDevedor!=null && nomeDevedor.trim().length()>0){
            PessoaVO devedor = new PessoaVO();
            devedor.setNome(nomeDevedor);
            devedor.setCodigo(Integer.valueOf(request.getParameter("devedorCodigo")));
            filtro.setDevedor(devedor);
        }
        
        DateUtil dateFormat = DateUtil.getInstance();
        String dataRepasseInicio = request.getParameter("dataRepasseInicio");
        if (dataRepasseInicio!=null && dataRepasseInicio.trim().length()>0){
            filtro.setDataRepasseInicio(dateFormat.convertStringToDate(dataRepasseInicio));
        }
        
        String dataRepasseFinal = request.getParameter("dataRepasseFinal");
        if (dataRepasseFinal!=null && dataRepasseFinal.trim().length()>0){
            filtro.setDataRepasseFinal(dateFormat.convertStringToDate(dataRepasseFinal));
        }
        
        String dataPagamentoInicio = request.getParameter("dataPagamentoInicio");
        if (dataPagamentoInicio!=null && dataPagamentoInicio.trim().length()>0){
            filtro.setDataPagamentoInicio(dateFormat.convertStringToDate(dataPagamentoInicio));
        }
        
        String dataPagamentoFinal = request.getParameter("dataPagamentoFinal");
        if (dataPagamentoFinal!=null && dataPagamentoFinal.trim().length()>0){
            filtro.setDataPagamentoFinal(dateFormat.convertStringToDate(dataPagamentoFinal));
        }
        
        String dataVencimentoInicio = request.getParameter("dataVencimentoInicio");
        if (dataVencimentoInicio!=null && dataVencimentoInicio.trim().length()>0){
            filtro.setDataVencimentoInicio(dateFormat.convertStringToDate(dataVencimentoInicio));
        }
        
        String dataVencimentoFinal = request.getParameter("dataVencimentoFinal");
        if (dataVencimentoFinal!=null && dataVencimentoFinal.trim().length()>0){
            filtro.setDataVencimentoFinal(dateFormat.convertStringToDate(dataVencimentoFinal));
        }
        
        String numeroProcesso = request.getParameter("processoNumero");
        if (numeroProcesso!=null && numeroProcesso.trim().length()>0){
            ProcessoVO processo = new ProcessoVO();
            processo.setNumero(numeroProcesso);
            filtro.setProcesso(processo);
        }
        
        String statusPagamentoCodigo = request.getParameter("statusPagamentoCodigo");
        if (statusPagamentoCodigo!=null && statusPagamentoCodigo.trim().length()>0){
            filtro.setStatusPagamento(StatusPagamentoVO.findByCodigo(Integer.valueOf(statusPagamentoCodigo)));
        }
        //Parametros
        super.setParameterImagePath(parametros, "P_LOGO_PATH", "/img/logo.gif");       
        //DataSource
        return acordoDelegate.findRelatorioByFilter(filtro);
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