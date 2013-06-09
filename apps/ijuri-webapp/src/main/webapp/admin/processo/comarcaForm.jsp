<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
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
		
		function atualizaEstado(codigo){
			window.location.href = "<c:url value="/admin/Processo/Comarca/CarregarCadastro.do" />?estadoCodigo=" + codigo;
		}
		function voltar(){
			location.href = '<c:url value="/admin/Processo/Comarca/CarregarPesquisa.do"/>';
		}
		</script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
	</head>
	<body>
		<html:form action="/admin/Processo/Comarca/Salvar" method="post">
			<input type="hidden" name="acao" value='U' />
			<html:hidden property="codigo" />
			<div id=ConteudoSistema>    
			  <h1><span><fmt:message key="lb_tituloComarca" /></span></h1>
			  <fieldset>
					<legend><fmt:message key="lb_tituloComarca" /></legend>
					<p><html:errors/></p>
				  
					<label><fmt:message key="lb_estado" /></label>
				    <html:select property="estadoCodigo" onchange="atualizaEstado(this.value);" style="width:220px" styleClass="sist_input">
				    	<html:option value=""><fmt:message key="lb_comboCadastro"/></html:option>
				    	<html:options collection="collEstado" labelProperty="nome" property="codigo"/>
				    </html:select><br/>
				    
				    <label><fmt:message key="lb_municipio" /></label>
			    	<html:select property="municipioCodigo" style="width:220px" styleClass="sist_input">
				    	<html:option value=""><fmt:message key="lb_comboCadastro"/></html:option>
				    	<html:options collection="collMunicipio" labelProperty="nome" property="codigo"/>
				    </html:select>
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