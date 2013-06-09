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
		<title><fmt:message key="lb_titulo"/></title>
		<link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
		<script>
			function selecionarDocumento(){
				url = '<c:url value="/admin/documento/treeViewSelecao.jsp"/>';
				openPopUp(url,'treeViewSel',660,500);
			}
			
			function atualizarDocumento(codigo,nome){
				document.forms[0].documentoCodigo.value = codigo;
				document.forms[0].documentoNome.value = nome;
			}
	
			function novo(){
				window.location.href = "<c:url value="/admin/Site/Jurisprudencia/CarregarCadastro.do"/>";
			}
			
			check = false;
			function detalhar(codigo){
				if (!check) 
					location.href = "<c:url value="/admin/Site/Jurisprudencia/CarregarCadastro.do"/>?codigo=" + codigo;
				check = false;
			}
			
			function excluir(){
				return excluirRegistros('codigosExclusao','<c:url value="/admin/Site/Jurisprudencia/Remover.do"/>');
			}
			
		</script>
	</head>
	<body>
		<%@ include file="../mainTop.jsp" %>
		<html:form action="/admin/Site/Jurisprudencia/Pesquisar" method="post">
			<div id="conteudo_sist">    
				<h1><fmt:message key="lb_pesquisaJurisprudencia" /></h1>
				<p><html:errors/></p>
				<table border="0" cellspacing="1" cellpadding="2">
					<tr>
					    <td align="right"><label><fmt:message key="lb_nome"/>: </label></td>
					    <td><html:text property="documentoNome" maxlength="50" style="width:270px" styleClass="sist_input" />
					    	<html:hidden property="documentoCodigo" />
					    	<span style="valign:bottom">
						    	<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarDocumento()" />
						    	<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('documentoNome','documentoCodigo')" />	    	
					    	</span>
					    </td>
					  </tr>
					  <tr>
					  	<td align="right"><label><fmt:message key="lb_titulo_juris"/>: </label></td>
					    <td><html:text property="titulo" maxlength="50" style="width:270px" styleClass="sist_input" /> </td>
					  </tr>
					  	  <tr>
						  	<td align="right"><label><fmt:message key="lb_areaAtuacao"/>:</label></td>
						  	<td><html:select property="areaAtuacaoCodigo" styleClass="sist_input" style="width:270px">
								    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
											<html:options 	collection="areas" 
															labelProperty="descricao" 
															property="codigo"/>		
									    	</html:select>
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
						<td class="td01"><fmt:message key="lb_titulo_juris"/></td>
						<td class="td01"><fmt:message key="lb_arquivo"/></td>
						<td class="td01">--</td>
					</tr>
					 <pg:pager url="${pageContext.request.requestURL}" 
			  			maxPageItems="10" 
			  			maxIndexPages="10"
			          	export="currentPageNumber=pageNumber"
			          	scope="request">
	    			  <c:forEach items="${requestScope.jurisprudencias}" var="item">
						<pg:item>
							<tr class="td02" onMouseOver="this.style.backgroundColor='#eeeeee'" onMouseOut="this.style.backgroundColor='#FFE8CC'" onClick="javascript:detalhar(${item.codigo});" style="cursor:hand">
								<td>${item.titulo}</td>
								<td>${item.documento.nome}</td>
								<td onclick="check=true;" align="center"><input type="checkbox" name="codigosExclusao" value="${item.codigo}"/></td>
							</tr>
						</pg:item>
					</c:forEach>
					<tr>
					    <td colspan="6" class="td02" align="center"> 
					    	<c:import url="/admin/navegador.jsp" />
					   </td>
				    </tr>
				    </pg:pager>	  
					<tr>
						<td align="center" colspan="3">
							<input onclick="novo();" type="button" class="login_botao_sist" value="Novo" />&nbsp;
							<input name="button" type="submit" class="login_botao_sist" value="Excluir" onClick="excluir();"/>
						</td>
					</tr>
				</table>  
			</div>
			<%@ include file="../mainBottom.jsp" %>
		</html:form>
	</body>
	<c:out escapeXml="false" value="${msg}" />
</html>