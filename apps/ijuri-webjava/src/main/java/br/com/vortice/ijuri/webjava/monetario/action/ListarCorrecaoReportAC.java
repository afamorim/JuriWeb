package br.com.vortice.ijuri.webjava.monetario.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.webjava.abstracao.relatorio.BaseReportAction;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class ListarCorrecaoReportAC extends BaseReportAction {

	protected String getReport(HttpServletRequest pRequest) {
		return "/admin/correcaoMonetaria/relatorio/CorrecaoReport.jasper";
	}

	protected Object configurationReport(HttpServletRequest request, HttpServletResponse response, Map pMap) throws AmbienteException, AplicacaoException {
		super.setParameterImagePath(pMap, "P_LOGO_PATH", "/img/logo.gif");
		
		ArrayList collCorrecao = null;
		
		collCorrecao = (ArrayList)Base64Decoder.decodeToObject(request.getParameter("arrayCorrecao"));
		
		return collCorrecao;
	}

}
