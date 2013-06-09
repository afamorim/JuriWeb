<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title><fmt:message key="lb_titulo"/></title>
		<link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
		<script>
		
			function voltar(){
				location.href = '<c:url value="/admin/Site/Link/CarregarPesquisa.do" />';
			}
		
			function validaForm(){
				nome = document.forms[0].elements['nome'].value;
				if (nome != ""){
					if (document.forms[0].elements['endereco'].value != ""){
						document.forms[0].submit();
					}else{
						alert("O endereço do link não pode ser vazio.");
						return false;
					}
				}else{
					alert("O nome do link não pode ser vazio.");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<%@ include file="../mainTop.jsp" %>
		<html:form action="/admin/Site/Link/Salvar" method="post">
			<div id="conteudo_sist">
				<h1><fmt:message key="lb_linkTitulo" /></h1>
				<table border="0" cellspacing="1" cellpadding="2">
					<tr>
						<td><label><fmt:message key="lb_nome"/></label></td>
						<td><html:text property="nome" maxlength="100" style="width:300px" styleClass="sist_input"/></td>
					</tr>
					<tr>
						<td><label><fmt:message key="lb_endereco"/></label></td>
						<td><html:text property="endereco" maxlength="200" style="width:300px" styleClass="sist_input"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					    <td colspan="3">
					      <input name="Submit" type="button" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" onClick="validaForm();"/>
					      <input name="Submit" type="button" class="login_botao_sist" value="Limpar" onclick="limpar()"/>
					      <input name="Submit" class="login_botao_sist" value="Voltar" type="button" onclick="voltar()">
					    </td>
					</tr>
				</table>
			</div>
			<html:hidden property="codigo"/>
		</html:form>
		<%@ include file="../mainBottom.jsp" %>
	</body>
</html>