<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>				
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href="<c:url value="/nucleo/style/juriweb_frame.css"/>" rel="stylesheet" type="text/css" />
		<script language="JavaScript" type="text/JavaScript">
			function MM_preloadImages() { //v3.0
			  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			}
			
			function MM_reloadPage(init) {  //reloads the window if Nav4 resized
			  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
			    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
			  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
			}
			MM_reloadPage(true);
			
			function novo(){
				location.href = '<c:url value="/admin/Pessoa/Fisica/CarregarCadastro.do"/>';
			}
			
			function selecionar(codigo,nome){
				window.opener.atualizarPessoa(codigo,nome);
				window.close();
			}
			
		</script>
	</head>
	<body>
	<html:form action="/admin/Pessoa/List" method="get">
		<input type="hidden" name="acao" value="P"/>
		<div id="ConteudoSistema">    
			<h1><span><fmt:message key="lb_selecaoPessoa"/></span></h1>
			<fieldset>
				<legend><fmt:message key="lb_selecaoPessoa" /></legend>
				<p><html:errors/></p>
				<label><fmt:message key="lb_nome"/>:</label>
			    <html:text property="nome" maxlength="50" size="60" styleClass="sist_input"/><br/>
			  
				<label><fmt:message key="lb_tipo"/>:</label>
			    <html:radio property="tipo" value="1"/>Pessoa Física &nbsp;<html:radio property="tipo" value="2"/>Pessoa Jurídica<br/>
			  
				<input name="Submit" type="submit" class="login_botao_sist" value="Pesquisar"/>
			</fieldset>
			<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
			
			<table  border="0" cellpadding="2" cellspacing="1" width="715px">
				<tr class="td02">
					<td width="100%" class="td01"><fmt:message key="lb_nome"/>:</td>
				</tr>
				<pg:pager url="${pageContext.request.queryString}" maxPageItems="20" maxIndexPages="10" 
					export="offset,currentPageNumber=pageNumber" scope="request">
					<c:forEach items="${requestScope.pessoas}" var="pessoa">
						<pg:item>
							<tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" 
								onMouseOut="this.style.backgroundColor='#eeeeee'" 
								onClick="javascript:selecionar(${pessoa.codigo},'${pessoa.nome}')" style="cursor:hand">
								<td width="100%">${pessoa.nome}</td>
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
		</div>
	</html:form>
	</body>
</html:html>