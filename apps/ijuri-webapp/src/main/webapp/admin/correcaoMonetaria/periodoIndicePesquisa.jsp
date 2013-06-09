<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="lb_tituloPerioIndiceTitulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/indiceReajuste.js"/>"></script>
		<script>
			function novo(){
				window.location.href = "<c:url value="/admin/Monetario/PeriodoIndiceReajuste/CarregarCadastro.do"/>";
			}
			
			check = false;
			function detalhar(codigo){
				if (!check) window.location.href = "<c:url value="/admin/Monetario/PeriodoIndiceReajuste/CarregarCadastro.do"/>?codigo=" + codigo;
				check = false;
			}
			
			function excluir(){
				window.document.forms[0].action = "<c:url value="/admin/Monetario/PeriodoIndiceReajuste/Remover.do"/>";
				window.document.forms[0].submit();
			}
			
			function atualizaIndiceOpener(){
				atualizaIndice(window.document.forms[0].indiceReajusteCodigo, '<c:url value="/admin/CorrecaoMonetaria/CarregarIndice.ajax"/>');
			}
		</script>
	</head>
	<body>
		<html:form action="/admin/Monetario/PeriodoIndiceReajuste/Consultar" method="post">
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_pesquisaIndiceReajuste" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_pesquisaIndiceReajuste" /></legend>
					<p><html:errors/></p>
					
					<label><fmt:message key="lb_indiceReajuste"/>:</label>
					<select name="indiceReajusteCodigo" style="width:90px" class="sist_input"></select>
					<a href="javaScript:openIndiceReajuste('<c:url value="/admin/CorrecaoMonetaria/IndiceReajuste/CarregarCadastro.do"/>');">
						<img src="<c:url value="/img/novo.gif"/>" border="0" />
					</a><br/>
					
					<label><fmt:message key="lb_mes"/>:</label>
					<html:text property="mes" maxlength="2" style="width:90px" styleClass="sist_input" onkeypress="soNumero(this, event);"/><br/>
					
					<label><fmt:message key="lb_ano"/>:</label>
					<html:text property="ano" maxlength="4" style="width:90px" styleClass="sist_input" onkeypress="soNumero(this, event);"/><br/>
					
					<input name="Submit" type="submit" class="login_botao_sist" value="Pesquisar"/>
				</fieldset>
				
				<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
				<table  border="0" cellpadding="2" cellspacing="1">
					<tr class="td02">
						<td class="td01"><fmt:message key="lb_indiceReajuste"/></td>
						<td class="td01"><fmt:message key="lb_mes"/></td>
						<td class="td01"><fmt:message key="lb_ano"/></td>
						<td class="td01"><fmt:message key="lb_valor"/></td>
						<td class="td01">Excluir</td>
					</tr>
					<pg:pager url="${pageContext.request.requestURL}" maxPageItems="10" maxIndexPages="10"
						export="currentPageNumber=pageNumber" scope="request" >
						<c:forEach items="${requestScope.collPeriodoIndice}" var="item">
							<pg:item>
								<tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" 
									onMouseOut="this.style.backgroundColor='#eeeeee'"
									onClick="javascript:detalhar(${item.codigo});" style="cursor:hand">
									<td class="tdLeft">${item.indiceReajuste.nome}</td>
									<td class="tdLeft">${item.mes}</td>
									<td class="tdLeft">${item.ano}</td>
									<td class="tdLeft"><fmt:formatNumber value="${item.valor}" pattern="#,##0.00"/></td>
									<td onclick="check=true;" align="center"><input type="checkbox" name="codigo" value="${item.codigo}"/></td>
								</tr>
							</pg:item>
						</c:forEach> 
						<tr>
							<td colspan="5" class="td02" align="center"> 
								<%@ include file='../navegador.jsp' %>
							</td>
					    </tr>  
					</pg:pager> 
				</table>
				
				<div class="space">
					<input onclick="novo();" type="button" class="login_botao_sist" value="Novo" />
					<input name="button" type="button" class="login_botao_sist" value="Excluir" onClick="excluir();"/>
				</div>
			</div>
		</html:form>
	</body>
	<script>
		atualizaIndice(window.document.forms[0].indiceReajusteCodigo, '<c:url value="/admin/CorrecaoMonetaria/CarregarIndice.ajax"/>', '${requestScope.PeriodoIndiceReajusteForm.indiceReajusteCodigo}');
		<c:if test="${not empty requestScope.msg}">
			alert("${requestScope.msg}");
		</c:if>
	</script>
</html>