<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="lb_linkTitulo"/></title>
		<link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
		<script>
			function novo(){
				window.location.href = "<c:url value="/admin/Site/Link/CarregarCadastro.do"/>";
			}
			
			check = false;
			function detalhar(codigo){
				if (!check) window.location.href = "<c:url value="/admin/Site/Link/CarregarCadastro.do"/>?codigo=" + codigo;
				check = false;
			}
			
			function excluir(){
				window.document.forms[0].action = "<c:url value="/admin/Site/Link/Remover.do"/>";
				window.document.forms[0].submit();
			}
		</script>
	</head>
	<body>
		<%@ include file="../mainTop.jsp" %>
		<html:form action="/admin/Site/Link/Consultar" method="post">
			<div id="conteudo_sist">    
				<h1><fmt:message key="lb_pesquisaLink" /></h1>
				<p><html:errors/></p>
				<table border="0" cellspacing="1" cellpadding="2">
					<tr>
						<td align="right"><label><fmt:message key="lb_nome"/>:</label></td>
						<td>
							<html:text property="nome" maxlength="50" style="width:300px" styleClass="sist_input"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="3">
							<input name="Submit" type="submit" class="login_botao_sist" value="Pesquisar"/>
						</tr>
				</table>
				<h2>Resultado</h2>
				<table width="100%"  border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td class="td01"><fmt:message key="lb_nome"/></td>
						<td class="td01"><fmt:message key="lb_endereco"/></td>
						<td class="td01">--</td>
					</tr>
					<c:forEach items="${requestScope.collLink}" var="item">
						<tr class="td02" onMouseOver="this.style.backgroundColor='#eeeeee'" onMouseOut="this.style.backgroundColor='#FFE8CC'" onClick="javascript:detalhar(${item.codigo});" style="cursor:hand">
							<td>${item.nome}</td>
							<td>${item.endereco}</td>
							<td onclick="check=true;" align="center"><input type="checkbox" name="codigo" value="${item.codigo}"/></td>
						</tr>
					</c:forEach>
					<tr>
						<td align="center" colspan="3">
							<input onclick="novo();" type="button" class="login_botao_sist" value="Novo" />&nbsp;
							<input name="button" type="button" class="login_botao_sist" value="Excluir" onClick="excluir();"/>
						</td>
					</tr>
				</table>  
			</div>
			<%@ include file="../mainBottom.jsp" %>
		</html:form>
	</body>
	<script>
		<c:if test="${not empty requestScope.msg}">
			alert("${requestScope.msg}");
		</c:if>
	</script>
</html>