<%@page language="java" %>
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
		<script>
			var DHTML_SUITE_THEME_FOLDER = '<c:url value="/nucleo/themes/"/>';
			var DHTML_SUITE_THEME = 'zune';
			var IMG_USER_ATIVADO = "<c:url value="/img/user_ativado.gif"/>";
			var IMG_USER_DESATIVADO = "<c:url value="/img/user_desativado.gif"/>";
		</script>
		<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/dwr/util.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/dwr/interface/PessoaRN.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/dwr/interface/PerfilRN.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/dhtmlSuite-common.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/dhtmlSuite-dynamicContent.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/dhtmlSuite-windowWidget.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/dhtmlSuite-dragDropSimple.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/dhtmlSuite-resize.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/pessoa.js"/>" />
		
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
				location.href = '<c:url value="/admin/Pessoa/Juridica/CarregarCadastro.do"/>';
			}
			
			var check = false;
			function detalhar(codigo){
				if (!check){
					url = '<c:url value="/admin/Pessoa/Juridica/CarregarCadastro.do"/>';
					location.href =url+'?codigo='+codigo;
				}
				check=false;	
			}
			
			function excluir(){
				return excluirRegistros('codigo','<c:url value="/admin/Pessoa/Juridica/Remover.do" />');
			}
		</script>
	</head>
	
	<body>
	<html:form action="/admin/Pessoa/Juridica/List" method="post">
		<input type="hidden" name="perfilCodigo" value=""/>
		<input type="hidden" name="pessoaCodigo" value=""/>
		<div id="ConteudoSistema">    
			<h1><span><fmt:message key="lb_pesquisaPessoaJuridica"/></span></h1>
			<fieldset>
				<p><html:errors/></p>
				<legend><fmt:message key="lb_pesquisaPessoaJuridica" /></legend>
				
				<label><fmt:message key="lb_nome"/>:</label>
				<html:text property="nome" maxlength="50" size="60" styleClass="sist_input"/><br/>
						
				<input name="Submit" type="submit" class="botao" value="Pesquisar"/>
			</fieldset>
			
			<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
			<table  border="0" cellpadding="2" cellspacing="1" width="715px">
				<tr class="td02">
					<th width="40%" class="th01"><fmt:message key="lb_nome"/>:</th>
					<th width="36%" class="th01"><fmt:message key="lb_email"/>:</th>
					<th width="7%" class="th01" nowrap="nowrap"><fmt:message key="lb_usuario"/>:</th>
					<th class="th01" nowrap="nowrap" width="7%">Excluir</th>
				</tr>
				<pg:pager url="${pageContext.request.requestURL}" maxPageItems="10" maxIndexPages="10" export="currentPageNumber=pageNumber" 
					scope="request">
					<c:forEach items="${requestScope.collPessoa	}" var="pessoas">
						<pg:item>
							<tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" onMouseOut="this.style.backgroundColor='#eeeeee'" 
								 style="cursor:hand">
								<td class="tdLeft" onClick="javascript:detalhar(${pessoas.codigo});">${pessoas.nome}</td>
								<td class="tdLeft" onClick="javascript:detalhar(${pessoas.codigo});">${pessoas.email}</td>
								<td align="center">
									<div id="acaoUsuario">
									<c:choose>
										<c:when test="${empty pessoas.usuario}">
											<a href="#" onclick="createWindowPerfil(${pessoas.codigo});" title="<fmt:message key="lb_criarUsuario"/>">
												<img src="<c:url value="/img/criar_usuario.gif"/>" border="0" alt="<fmt:message key="lb_criarUsuario"/>"/>
											</a>
										</c:when>
										<c:when test="${pessoas.usuario.ativo}">
											<a href="#" onclick="desativarUsuario(${pessoas.codigo});" title="<fmt:message key="lb_desativarUsuario"/>">
												<img src="<c:url value="/img/user_ativado.gif"/>" border="0" id="imgUsuario_${pessoas.codigo}"
													title="<fmt:message key="lb_desativarUsuario"/>"/>
											</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="ativarUsuario(${pessoas.codigo});" title="<fmt:message key="lb_ativarUsuario"/>">
												<img src="<c:url value="/img/user_desativado.gif"/>" border="0" title="<fmt:message key="lb_ativarUsuario"/>"/>
											</a>
										</c:otherwise>
									</c:choose>
									</div>
								</td>
								<td onclick="check=true;" align="center"><input type="checkbox" name="codigo" value="${pessoas.codigo}"/></td>
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
			<div class="space">
				<input onclick="novo()" type="button" class="botao" value="Novo" />
				<input name="Submit" type="submit" class="botao" value="Excluir" onClick="return excluir();" />
			</div>
		</div>
	</html:form>
	<c:out escapeXml="false" value="${msg}" />
	</body>
</html:html>
