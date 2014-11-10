package br.com.vortice.ijuri.webjava.monetario.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vortice.ijuri.core.monetario.PeriodoIndiceVO;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;

public class PeriodoIndiceFindLastAjax extends HttpServlet {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    String indiceCodigo = request.getParameter("indiceCodigo");
	    //System.out.println("indiceCodigo " + indiceCodigo);
	    try{
			String xml = "";
			if (indiceCodigo != null){
				PeriodoIndiceVO vo = correcaoMonetariaDelegate.findLasPeriodo(new PeriodoIndiceVO(new Integer(indiceCodigo)));
				if (vo != null){
					xml += "<periodo>";
						xml += "<mes>" + vo.getMes() + "</mes>";
						xml += "<ano>" + vo.getAno() + "</ano>";
					xml += "</periodo>";
				}
			}
			//System.out.println("XML: " + xml);
			response.getWriter().write(xml);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}

	public void setCorrecaoMonetariaDelegate(
			CorrecaoMonetariaDelegate correcaoMonetariaDelegate) {
		this.correcaoMonetariaDelegate = correcaoMonetariaDelegate;
	}
	
}
