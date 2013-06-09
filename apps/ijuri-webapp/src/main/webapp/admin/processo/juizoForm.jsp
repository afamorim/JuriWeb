<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
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
		//-->
		
		function voltar(){
			location.href = '<c:url value="/admin/Processo/Juizo/CarregarPesquisa.do" />';
		}
		</script>
	</head>
	<body>
		<html:form action="/admin/Processo/Juizo/Cadastro" method="post">
			<input type="hidden" name="acao" value='U' />
			<html:hidden property="codigo" />
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_tituloJuizo"/></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloAcordo" /></legend>
					<p><html:errors/></p>
					  
					<label><fmt:message key="lb_nome" /></label>
					<html:text property="descricao" maxlength="50" size="60" styleClass="sist_input"/>
				</fieldset>
				<div class="space">
					<input name="Submit" type="submit" class="botao" value="<fmt:message key="lb_btEnviar"/>"/>
					<input name="Submit" type="submit" class="botao" value="Limpar" onclick="limpar()"/>
					<input name="Submit" type="button" class="botao" value="Voltar" onclick="voltar()"/>
				</div>
			</div>
		</html:form>
	</body>
</html:html>