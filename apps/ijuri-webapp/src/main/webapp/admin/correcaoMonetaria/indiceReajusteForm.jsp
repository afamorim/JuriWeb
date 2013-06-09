<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href="<c:url value="/nucleo/style/juriweb.css"/>" rel="stylesheet" type="text/css" />
		<script language="JavaScript" type="text/JavaScript">
			<c:if test="${not empty requestScope.msg}">
				alert("${requestScope.msg}");
				window.opener.atualizaIndiceOpener();
			</c:if>
			
			function consultar(){
				window.document.forms[0].action = "<c:url value="/admin/CorrecaoMonetario/IndiceReajuste/Consultar.do"/>";
				window.document.forms[0].submit();
			}
			
			var check = false;
			function detalhar(codigo){
				if (!check){
					window.document.forms[0].elements['codigo'].value = codigo;
					window.document.forms[0].action = "<c:url value="/admin/CorrecaoMonetaria/IndiceReajuste/Detalhar.do"/>?codigo=" + codigo;
					window.document.forms[0].submit();
				}
			}
			
			function remover(){
				window.document.forms[0].action = "<c:url value="/admin/CorrecaoMonetaria/IndiceReajuste/Remover.do"/>";
				window.document.forms[0].submit();
			}
			
			function validarForm(){
				if (document.forms[0].elements['nome'].value != ""){
						document.forms[0].submit();
				}else{
					alert("O campo nome do indice reajuste não pode ser vazio.");
					return false;
				}
			}
			
			function limpar(){
				document.forms[0].elements['nome'].value = "";
				document.forms[0].elements['codigo'].value = "";
				document.forms[0].elements['descricao'].value = "";
			}
		</script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
	</head>
	<body>
		<html:form action="/admin/CorrecaoMonetaria/IndiceReajuste/Salvar" method="post">
			<html:hidden property="codigo" />
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloIndiceReajuste" /></span></h1>
				<p><html:errors/></p>
				<fieldset>
					<legend><fmt:message key="lb_tituloIndiceReajuste" /></legend>
					<p><html:errors/></p>
					<label><fmt:message key="lb_nome"/>:</label>
					<html:text property="nome" maxlength="50" styleClass="sist_input" style="width:300px"/><br/>
					
					<label><fmt:message key="lb_descricao"/>:</label>
					<html:text property="descricao" maxlength="50" style="width:300px" styleClass="sist_input"/><br/>
					
					<input name="Submit" type="button" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" 
						onClick="validarForm();"/>
					<input name="Submit" class="login_botao_sist" value="Consultar" type="button" onclick="consultar()" />
					<input name="button" class="login_botao_sist" value="Limpar" type="button" onclick="limpar()" />
				</fieldset>
				
				<c:if test="${not empty requestScope.collIndice}">
					<table  border="0" cellpadding="2" cellspacing="1">
						<tr class="td02">
							<td width="85%" class="td01"><fmt:message key="lb_nome"/>:</td>
							<td width="5%" class="td01">Excluir</td>
						</tr>
						<pg:pager url="${pageContext.request.requestURL}" maxPageItems="10" maxIndexPages="10"
						export="currentPageNumber=pageNumber" scope="request" >
							<c:forEach items="${requestScope.collIndice}" var="collIndice">
								<pg:item>
									<tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" 
										onMouseOut="this.style.backgroundColor='#eeeeee'" onClick="javascript:detalhar(${collIndice.codigo});"
										style="cursor:hand">
										<td class="tdLeft">${collIndice.nome}</td>
										<td align="center" onclick="check=true;">
											<input type="checkbox" name="codigo" value="${collIndice.codigo}"/>
										</td>
									</tr>
								</pg:item>
							</c:forEach>
							<tr>
								<td colspan="6" class="td02" align="center"> 
									<%@ include file='../navegador.jsp' %>
								</td>
						     </tr>  
						</pg:pager>
					</table>
					<div class="space"><input type="button" name="removerAC" value="Remover" onClick="remover();"/></div>
				</c:if>
			</div>
	  	</html:form>
  	</body>
</html:html>