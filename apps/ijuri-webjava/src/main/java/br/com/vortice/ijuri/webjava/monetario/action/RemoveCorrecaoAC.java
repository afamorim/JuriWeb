package br.com.vortice.ijuri.webjava.monetario.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Decoder;
import br.com.vortice.ijuri.core.abstracao.util.base64.Base64Encoder;

public class RemoveCorrecaoAC extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		ArrayList collCorrecao = null;
		if (request.getParameter("arrayCorrecao") != null)
			collCorrecao = (ArrayList)Base64Decoder.decodeToObject(request.getParameter("arrayCorrecao"));
		
		if (collCorrecao != null)
			collCorrecao.remove((new Integer(codigo)).intValue());

		request.setAttribute("arrayCorrecao", Base64Encoder.encode(collCorrecao));
		request.setAttribute("collCorrecao", collCorrecao);
		
		request.getRequestDispatcher("/admin/correcaoMonetaria/tabelaCorrecaoMonetaria.jsp").forward(request, response);
	}
}
