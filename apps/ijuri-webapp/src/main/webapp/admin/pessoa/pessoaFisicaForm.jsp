<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script language="JavaScript" type="text/JavaScript">
			function voltar(){
				location.href = '<c:url value="/admin/Pessoa/Fisica/List.do"/>';
			}
		</script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
	</head>
	<body>
		<html:form action="/admin/Pessoa/Fisica/Cadastro" method="post">
			<input type="hidden" name="acao" value='U'>
			<html:hidden property="codigo" />
			<html:hidden property="usuarioCodigo"/>
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloPessoaFisica" /></span></h1>
				
				<fieldset>
					<legend><fmt:message key="lb_tituloPessoaFisica" /></legend>
					<p><html:errors/></p>
					<div class="coluna01">
						<label><fmt:message key="lb_nome"/>:</label>
						<html:text property="nome" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_email"/>:</label>
						<html:text property="email" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_cpf"/>:</label>
						<html:text onkeypress="formataCpf(event);" property="cpf" maxlength="25" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_cep"/>:</label>
						<html:text property="cep" maxlength="12" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_endereco"/>:</label>
						<html:textarea property="endereco" rows="4" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_apto"/>:</label>
						<html:text property="apto" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
					</div>
					
					<div class="coluna02">
						<label><fmt:message key="lb_bloco"/>:</label>
						<html:text property="bloco" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_telefone"/>:</label>
						<html:textarea style="width:170px" property="telefone" rows="4" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_senha"/>:</label>
						<html:password property="senha" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_rsenha"/>:</label>
						<html:password property="rsenha" maxlength="50" style="width:170px" styleClass="sist_input"/><br/>
						
						<label><fmt:message key="lb_perfil"/>:</label>
						<html:select property="perfilCodigo" style="width:170px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
				    		<html:options collection="perfis" labelProperty="nome" property="codigo"/>
						</html:select>
					</div>
				</fieldset>
				
				<div class="space">
					<input name="Submit" type="submit" class="botao" value="<fmt:message key="lb_btEnviar"/>"/>
					<input name="button" class="botao" value="Limpar" type="submit" onclick="limpar()">
			    	<input name="Submit" class="botao" value="Voltar" type="button" onclick="voltar()">
				</div>
			</div>
	  	</html:form>
	  	<c:if test="${empty PessoaFisicaForm.usuarioCodigo}">
			<script>
				document.forms[0].perfilCodigo.disabled = true;
				document.forms[0].senha.disabled = true;
				document.forms[0].rsenha.disabled = true;
			</script>
		</c:if>
  	</body>
</html:html>