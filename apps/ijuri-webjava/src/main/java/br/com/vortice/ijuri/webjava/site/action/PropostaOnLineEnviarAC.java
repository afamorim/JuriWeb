package br.com.vortice.ijuri.webjava.site.action;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vortice.ijuri.core.abstracao.util.VelocityUtil;
import br.com.vortice.ijuri.core.abstracao.util.email.EmailUtil;
import br.com.vortice.ijuri.core.site.PropostaOnLineVO;
import br.com.vortice.ijuri.webjava.abstracao.view.BaseAction;
import br.com.vortice.ijuri.webjava.site.form.PropostaOnLineForm;

public class PropostaOnLineEnviarAC extends BaseAction {

	protected ActionForward executar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = getServlet().getServletContext().getRealPath("/");
		
		String htmlAgradecimento = path + "site/agradecimento.htm";
		
		Properties p = new Properties();
	    p.setProperty("file.resource.loader.path", path + "WEB-INF/templates/");
	    VelocityUtil velocity = new VelocityUtil(p);
	    
		String htmlTemplate = "propostaOnLineEnvio.jsp";
		
		PropostaOnLineForm propostaOnLineForm = (PropostaOnLineForm)form;
		PropostaOnLineVO vo = new PropostaOnLineVO();
		BeanUtils.copyProperties(vo, propostaOnLineForm);
		propostaOnLineForm.setNomeCondominio("");
		propostaOnLineForm.setNumeroEmpregados("");
		propostaOnLineForm.setNumeroUnidades("");
		propostaOnLineForm.setNomeSolicitante("");
		propostaOnLineForm.setCargo("");
		propostaOnLineForm.setCnpjCpf("");
		propostaOnLineForm.setEmail("");
		propostaOnLineForm.setObservacao("");
		propostaOnLineForm.setTelefone("");
		propostaOnLineForm.setValorTaxaCondominio("");
		
		velocity.setTemplate(htmlTemplate);
		velocity.setObjContext(vo, "proposta");
		String formTemp = "propostaOnLineTmp.jsp";
		StringWriter buffer = new StringWriter();
		
		velocity.mergeTemplate(buffer);
		//System.out.println(buffer);
		
		buffer.flush();
		formTemp = buffer.getBuffer().toString();
		
		buffer.close();
		File[] images = new File[4];
		images[0] = new File(path + "site/img/bg_home.jpg");
		images[1] = new File(path + "site/img/AcabAreasDeAtuacao.jpg");
		images[2] = new File(path + "site/img/bg_topo.jpg");
		images[3] = new File(path + "site/img/bg_geral.jpg");
		//Enviar Email de do Formulario
		
		EmailUtil.enviarEmailHTMLTextComAutenticacao(
				"smtp.muricysampaio.adv.br",  "juridico@muricysampaio.adv.br","juridico",
				//"smtp.gmail.com","afamorim","sa102101",
				   "Fomulario de Proposta On Line",
				   "juridico@muricysampaio.adv.br",
				   //"afamorim@gmail.com",
				   "Muricy & Sampaio Advogados e Consultores",
				   "juridico@muricysampaio.adv.br",
				   formTemp, images);
		
		//Enviar Email de Agradecimento
		EmailUtil.enviarEmailHTMLFileComAutenticacao(
				"smtp.muricysampaio.adv.br", "juridico@muricysampaio.adv.br", "juridico",
				//"smtp.gmail.com","afamorim","sa102101",
				   "Agradecimento Muricy & Sampaio Advogados e Consultores",
				   "juridico@muricysampaio.adv.br",
				   //"afamorim@gmail.com",
				   "Muricy & Sampaio Advogados e Consultores",
				   vo.getEmail(),
				   htmlAgradecimento,
				   images);
		request.setAttribute("msg", "Proposta Enviado com sucesso.");
		return new ActionForward(mapping.findForward("success"));
	}

}
