<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/c" 				prefix="c" %>
<%@ taglib uri="/tags/fmt" 				prefix="fmt" %>
<%@ taglib uri="sitemesh-decorator" 	prefix="decorator" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>JuriWeb - Contato</title>
		<link href="<c:url value="/nucleo/style/juriweb-site.css"/>" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div id="Topo">
			<h1><a href="<c:url value="/site/index.jsf"/>"><span class="alt">JuriWeb - Sistema de Controle de Processos e Acordos Judiciais</span></a></h1>
		</div>
		<div id="Menu">
			<a href="<c:url value="/site/sobreosistema.jsf"/>">Sobre o Sistema</a> | <a href="<c:url value="/site/comoadiquirir.jsf"/>">Como adquirir</a> | <a href="<c:url value="/site/demo.jsf"/>">Demo</a> | <a href="<c:url value="/site/contato.jsf"/>">Contato</a>
		</div><br />
			
		<!-- CONTEUDO -->
		<decorator:body />
		<!-- CONTEUDO -->
			
	</body>
</html>
