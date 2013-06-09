<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<div id="topo">
	<!-- div id="logo"></div-->
	<div id="login_logado"><img src="<c:url value="/img/logo.gif"/>"/> Seja bem vindo a intranet, <span class="link03"><c:out value="${sessionScope.usuario.login}"/></span>.&nbsp;&nbsp;&nbsp;<a style="cursor:hand" class="link01" alt="Sai do sistema e volta para tela inicial do site." onclick="javascript:location.href='<c:url value="/Usuario/Logout.do"/>'"> [Sair]</a></div>
	
</div>
<div id="principal">
<%@ include file="menu.jsp" %>
