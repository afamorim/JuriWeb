<%@page isELIgnored="false" %>
<%@ page language="java" contentType="text/html;"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/tree" prefix="tree" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/css/estiloTree.css"/>' type="text/css"> 
<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>">
</script>
</head>
<body>
   <html:form action="/admin/Documento/Cadastro" method="post" enctype="multipart/form-data">
	<h1><fmt:message key="lb_tituloDocumento" /></h1>
	<p><html:errors/></p>
	<table border="0" cellspacing="1" cellpadding="2">
		<input type="hidden" name="acao" value='U'>
		<html:hidden property="codigo" />
		<html:hidden property="diretorioCodigo" />
		<tr>
			<td align="right"><label><fmt:message key="lb_arquivo"/>:</label></td>
		    <td><html:file property="arquivo" maxlength="40" size="30" styleClass="sist_input"/></td>
		</tr>
		<tr>
			<td height="6px"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				 <input name="Submit" type="button" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" onclick="cadastrarDoc()"/>
			</td>
		</tr>	
 	 </table> 
   </html:form>
</body>
</html>