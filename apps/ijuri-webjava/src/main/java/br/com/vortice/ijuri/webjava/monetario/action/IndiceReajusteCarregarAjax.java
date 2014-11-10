package br.com.vortice.ijuri.webjava.monetario.action;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.com.vortice.ijuri.core.monetario.IndiceReajusteVO;
import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;

public class IndiceReajusteCarregarAjax extends FrameworkServlet {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
		try{
			Collection collIndiceReajuste = correcaoMonetariaDelegate.findAllIndiceReajuste();
			String xml = "";
			if (collIndiceReajuste != null && collIndiceReajuste.size() > 0){
				xml = "<componentes>";
				Iterator iterator = collIndiceReajuste.iterator();
				while(iterator.hasNext()){
					IndiceReajusteVO vo = (IndiceReajusteVO)iterator.next();
					xml += "<componente><codigo>" + vo.getCodigo() + "</codigo><nome>" + vo.getNome() + "</nome></componente>";
				}
				xml += "</componentes>";
			}
			response.getWriter().write(xml);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		correcaoMonetariaDelegate = (CorrecaoMonetariaDelegate)context.getBean("correcaoMonetariaDelegate");
	}

    protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}
}
