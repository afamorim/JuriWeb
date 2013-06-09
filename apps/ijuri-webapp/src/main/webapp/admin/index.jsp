<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="iso-8859-1"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<c:if test="${usuario.perfil.codigo == 2}">
	<script>window.location.href='<c:url value="/adminCliente/index.jsp"/>';</script>
</c:if>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />	
		
	</head>
	
	<div id="ConteudoSistema"> 
	<br>
		Este sistema pode ser adquirido atrav�s do pagamento de uma taxa m�nima de implanta��o de uma vers�o sob medida para voc�. Sendo assim, formar� uma parceria conosco, pagando um valor mensal
		predefinido sobre cada processo cadastrado. Esta parceria possibilitar� que pequenos escrit�rios ou at� grandes associa��es de advogados, consigam
		controlar os processos e acordos judiciais e, tamb�m, divulgar informa��es aos clientes de maneira mais f�cil e eficaz.
		
	</div>
</html:html>
