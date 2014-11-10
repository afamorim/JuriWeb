package br.com.vortice.ijuri.webjava.monetario.action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vortice.ijuri.webjava.monetario.CorrecaoMonetariaDelegate;

public class CorrecaoMonetariaCarregarAjax extends HttpServlet {
	
	private CorrecaoMonetariaDelegate correcaoMonetariaDelegate;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Collection collIndiceReajuste = correcaoMonetariaDelegate.findAllIndiceReajuste();
			request.setAttribute("collIndiceReajuste", collIndiceReajuste);
			request.getRequestDispatcher("/admin/correcaoMonetaria/correcaoMonetariaForm.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
