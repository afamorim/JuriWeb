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
		Este sistema pode ser adquirido através do pagamento de uma taxa mínima de implantação de uma versão sob medida para você. Sendo assim, formará uma parceria conosco, pagando um valor mensal
		predefinido sobre cada processo cadastrado. Esta parceria possibilitará que pequenos escritórios ou até grandes associações de advogados, consigam
		controlar os processos e acordos judiciais e, também, divulgar informações aos clientes de maneira mais fácil e eficaz.
		
	</div>
</html:html>
