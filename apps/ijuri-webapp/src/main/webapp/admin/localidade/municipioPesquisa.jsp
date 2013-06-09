<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href='<c:url value="/nucleo/style/juriweb.css"/>' rel="stylesheet" type="text/css" />	
		<script language="JavaScript" type="text/JavaScript">
		<!--
		
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
			location.href = '<c:url value="/admin/Localidade/Municipio/CarregarCadastro.do"/>';
		}
		check=false;
		function detalhar(codigo){
			if (!check){
				url = '<c:url value="/admin/Localidade/Municipio/CarregarCadastro.do"/>';
				location.href =url+'?codigo='+codigo;
			}
			check=false;
		}
		
		function excluir(){
			return excluirRegistros('codigo','<c:url value="/admin/Localidade/Municipio/Remover.do" />');
		}
		//-->
		</script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script> 
	
	</head>
	<body>
	<html:form action="/admin/Localidade/Municipio/List" method="post">
	<div id="ConteudoSistema">
		<h1><span><fmt:message key="lb_pesquisaMunicipio"/></span></h1>
		<p><html:errors/></p>
		<fieldset>
			<legend><fmt:message key="lb_pesquisaMunicipio" /></legend>
			<label><fmt:message key="lb_estado"/>:</label>
			<html:select property="estadoCodigo" style="width:220px" styleClass="sist_input">
				<html:option value=""><fmt:message key="lb_comboFiltro"/></html:option>
				<html:options collection="collEstado" labelProperty="nome" property="codigo"/>
			</html:select><br />
			
			<label><fmt:message key="lb_nome"/>:</label>
			<html:text property="nome" maxlength="50" style="width:220px" styleClass="sist_input"/><br />
			
			<input name="Submit" type="submit" class="botao" value="Pesquisar"/>
		</fieldset>
	  
		<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
		<table  border="0" cellpadding="2" cellspacing="1" width=715px">
			<tr class="td02">
				<th width="10%" class="th01"><fmt:message key="lb_codigo"/>:</th>
				<th width="80%" class="th01"><fmt:message key="lb_nome"/>:</th>
				<th width="10%" class="th01">Excluir:</th>
			</tr>
		
			<c:forEach items="${requestScope.collMunicipio}" var="municipios">
				<tr class="td02" onClick="javascript:detalhar(${municipios.codigo});" >
					<td  class="tdLeft">${municipios.codigo}</td>
					<td  class="tdLeft">${municipios.nome}</td>
					<td onclick="check=true;"><input type="checkbox" name="codigo" value="${municipios.codigo}"/></td>
				</tr>
			</c:forEach>  
		</table>
		
		<div class="space">
		  	<input onclick="novo()" type="button" class="botao" value="Novo" />
			<input name="button" type="submit" class="botao" value="Excluir" onClick="return excluir();"/>
		</div>  
	 </div>
	 </html:form>
	</body>
</html:html>