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
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script> 
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
			function voltar(){
				location.href = '<c:url value="/admin/Localidade/Municipio/CarregarPesquisa.do"/>';
			}
		</script>
	</head>
	<body>
		<html:form action="/admin/Localidade/Municipio/Salvar" method="post">
			<input type="hidden" name="acao" value='U' />
			<html:hidden property="codigo" />
			<div id="ConteudoSistema"> 
				<h1><span><fmt:message key="lb_tituloMunicipio" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloMunicipio" /></legend>
						<p><html:errors/></p>
						<label><fmt:message key="lb_estado"/>:</label>
						<html:select property="estadoCodigo" style="width:220px" styleClass="sist_input">
				    		<html:option value=""><fmt:message key="lb_comboCadastro"/></html:option>
				    		<html:options collection="collEstado" labelProperty="nome" property="codigo"/>
						</html:select><br/>
				
						<label><fmt:message key="lb_nome"/>:</label>
						<html:text property="nome" maxlength="50" style="width:220px" styleClass="sist_input"/>
				</fieldset>
				<div class="space">
					<input name="Submit" type="submit" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>"/>
					<input name="Submit" type="submit" class="login_botao_sist" value="Limpar" onclick="limpar()"/>
					<input name="Submit" type="button" class="login_botao_sist" value="Voltar" onclick="voltar()"/>
				</div>
			</div>
	  	</html:form>
  	</body>
</html:html>