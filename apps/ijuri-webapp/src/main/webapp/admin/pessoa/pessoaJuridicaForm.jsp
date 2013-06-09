<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script language="JavaScript" type="text/JavaScript">
		<!--
		
		function MM_preloadImages() { //v3.0
		  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		}
		//-->
		</script>
		<script language="JavaScript" type="text/JavaScript">
		<!--
		function MM_reloadPage(init) {  //reloads the window if Nav4 resized
		  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
		    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
		  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
		}
		MM_reloadPage(true);
		//-->
		function voltar(){
			location.href = '<c:url value="/admin/Pessoa/Juridica/List.do"/>';
		}
		</script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
	</head>
	
	<body>
		<html:form action="/admin/Pessoa/Juridica/Cadastro" method="post">
			<input type="hidden" name="acao" value='U' />
			<html:hidden property="codigo" />
			<html:hidden property="usuarioCodigo"/>
			
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloPessoaJuridica" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloPessoaJuridica" /></legend>
						<p><html:errors/></p>
						<div class="coluna01">
							<label><fmt:message key="lb_nome"/>:</label>
						    <html:text property="nome" maxlength="50" style="width:170px" styleClass="sist_input"/><br />
						
							<label><fmt:message key="lb_email"/>:</label>
						    <html:text property="email" maxlength="100" style="width:170px" styleClass="sist_input"/><br/>
						
							<label><fmt:message key="lb_cnpj"/>:</label>
						    <html:text onkeypress="formataCgc(event)" property="cnpj" maxlength="50" style="width:170px" 
						    	styleClass="sist_input"/><br />
						
							<label><fmt:message key="lb_endereco"/>:</label>
						    <html:textarea property="endereco" rows="4" style="width:170px" styleClass="sist_input"/><br />
						
							<label><fmt:message key="lb_cep"/>:</label>
						    <html:text property="cep" maxlength="50" style="width:170px" styleClass="sist_input"/><br />
						</div>
						
						<div class="coluna02">
							<label><fmt:message key="lb_contato"/>:</label>
						    <html:text property="contato" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
						
							<label><fmt:message key="lb_telefone"/>:</label>
						    <html:textarea style="width:170px" property="telefone" rows="4" styleClass="sist_input"/><br />
						
							<label><fmt:message key="lb_senha"/>:</label>
						    <html:password property="senha" maxlength="50" style="width:170px" styleClass="sist_input"/><br />
						
							<label><fmt:message key="lb_rsenha"/>:</label>
						    <html:password property="rsenha" maxlength="50" style="width:170px" styleClass="sist_input"/><br />
						
							<label><fmt:message key="lb_perfil"/>:</label>
							<html:select property="perfilCodigo" style="width:170px">
					 			<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
								<html:options collection="perfis" labelProperty="nome" property="codigo"/>
							</html:select><br />
						</div>
				</fieldset>
				
				<div class="space">
					<input name="Submit" type="submit" class="botao" value="<fmt:message key="lb_btEnviar"/>"/>
					<input name="button" type="submit" class="botao" value="Limpar" onclick="limpar()"/>
					<input name="button2" type="button" class="botao" value="Voltar" onclick="voltar()"/>
				</div>
			</div>
	  	</html:form>
		<c:if test="${empty PessoaJuridicaForm.usuarioCodigo}">
			<script>
				document.forms[0].perfilCodigo.disabled = true;
				document.forms[0].senha.disabled = true;
				document.forms[0].rsenha.disabled = true;
			</script>
		</c:if>
	</body>
</html:html>
