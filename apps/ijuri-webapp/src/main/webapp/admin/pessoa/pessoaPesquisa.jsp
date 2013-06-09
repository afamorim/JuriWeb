<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		  <link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />	
		<script language="JavaScript" type="text/JavaScript">
			<!--
			
			function MM_preloadImages() { //v3.0
			  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			}
			//-->
		</script>
		<script language="JavaScript" type="text/JavaScript">
			<!--
			function MM_reloadPage(init) {  //reloads the window if Nav4 resized
			  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
			    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
			  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
			}
			MM_reloadPage(true);
			//-->
			function novo(){
				location.href = '<c:url value="/admin/pessoa/pessoaForm.jsp"/>';
			}
			function detalhar(codigo){
				url = '<c:url value="/admin/Pessoa/Fisica/CarregarCadastro.do"/>';
				location.href =url+'?codigo='+codigo;
			}
		</script>
	</head>
	<body>
		<html:form action="/admin/Pessoa/Fisica/List" method="post">
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_pesquisaPessoaFisica"/></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_pesquisaPessoaFisica" /></legend>
					<p><html:errors/></p>
					<label><fmt:message key="lb_nome"/>:</label>
				    <html:text property="descricao" maxlength="50" size="60" styleClass="sist_input"/><br />
		
					<input name="Submit" type="submit" class="login_botao_sist" value="Pesquisar"/>
			  	</fieldset>
			  	
			  	<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
				<table  border="0" cellpadding="2" cellspacing="1">
					<tr class="td02">
						<td width="10%" class="td01"><fmt:message key="lb_codigo"/>:</td>
						<td width="90%" class="td01"><fmt:message key="lb_nome"/>:</td>
						<td class="td01">Excluir:</td>
					</tr>
					<c:forEach items="${requestScope.pessoas}" var="pessoas">
						<tr class="td02">
							<td class="tdLeft">
								<a class="link01" href="javascript:detalhar(${pessoas.codigo})">
									${pessoas.codigo}
								</a>
							</td>
							<td class="tdLeft">${pessoas.nome}</td>
							<td onclick="check=true;">
								<input type="checkbox" name="checkbox" value="${pessoas.codigo}"/>
							</td>
						</tr>
					</c:forEach>  
			  </table>
			  	<div class="space">
			  		<input onclick="novo()" type="button" class="login_botao_sist" value="Novo" />
					<input name="Submit" type="submit" class="login_botao_sist" value="Excluir" />
				</div> 
			 </div>
		</html:form>
	</body>
</html:html>