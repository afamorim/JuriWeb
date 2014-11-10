package br.com.vortice.ijuri.webjava.processo.action;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;

import br.com.vortice.ijuri.webjava.abstracao.view.BaseComboAjaxAction;
import br.com.vortice.ijuri.webjava.processo.ProcessoDelegate;

import com.vortice.core.exception.AmbienteException;
import com.vortice.core.exception.AplicacaoException;

public class TipoAndamentoCarregarComboAjaxAC extends BaseComboAjaxAction {
	
	private ProcessoDelegate processoDelegate;
	
    protected Collection getCollection(HttpServletRequest request, HttpServletResponse response) 
      throws AmbienteException,AplicacaoException {
        
        return processoDelegate.findAllTipoAndamento();
    }

    protected String getTextField() {
        return "descricao";
    }

    protected String getValueField() {
        return "codigo";
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
