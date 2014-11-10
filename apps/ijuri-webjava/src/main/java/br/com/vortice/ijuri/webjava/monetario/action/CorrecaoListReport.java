package br.com.vortice.ijuri.webjava.monetario.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class CorrecaoListReport extends BaseReportAction {

	protected String getReport(HttpServletRequest pRequest) {
		return "/admin/correcaoMonetaria/relatorio/CorrecaoMonetariaReport.jasper";
	}

	protected Object configurationReport(HttpServletRequest request, HttpServletResponse response, Map map) throws AmbienteException, AplicacaoException {
		super.setParameterImagePath(map, "P_LOGO_PATH", "/img/logo.gif");
		map.put("CLIENTE", request.getParameter("cliente"));
		//System.out.println("request.getParameter(cliente): " + request.getParameter("cliente"));
		ArrayList collCorrecao = null;
	
		collCorrecao = (ArrayList)Base64Decoder.decodeToObject(request.getParameter("arrayCorrecao"));
		
		return collCorrecao;
	}
	
	@Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
	}

    protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}

}
