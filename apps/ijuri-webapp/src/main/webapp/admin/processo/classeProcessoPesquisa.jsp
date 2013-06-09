<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script> 
		<script language="JavaScript" type="text/JavaScript">
				function novo(){
					location.href = '<c:url value="/admin/processo/classeProcessoForm.jsp"/>';
				}
				check=false;
				function detalhar(codigo){
					if (!check){
					    url = '<c:url value="/admin/Processo/ClasseProcesso/CarregarCadastro.do"/>';
						location.href =url+'?codigo='+codigo;
					}
					check=false;	
				}
				function excluir(){
					return excluirRegistros('codigosExclusao','<c:url value="/admin/Processo/ClasseProcesso/Remover.do"/>');
				}
		</script>
	</head>
	<body>
		<html:form action="/admin/Processo/ClasseProcesso/List" method="post">
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_pesquisaClasseProcesso" /></span></h1>
   				<fieldset>
					<legend><fmt:message key="lb_pesquisaClasseProcesso" /></legend>
						<p><html:errors/></p>
						
						<label><fmt:message key="lb_nome"/>:</label>
						<html:text property="descricao" maxlength="50" size="60" styleClass="sist_input"/><br/>
						
						<input name="Submit" type="submit" class="botao" value="Pesquisar"/>
		  		</fieldset>
		  	
  				<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
				<table  border="0" cellpadding="2" cellspacing="1" width="715px">
					<tr class="td02">
						<td width="93%" class="th01">Nome:</td>
						<td width="5%" class="th01">Excluir</td>
					</tr>
					<pg:pager url="${pageContext.request.requestURL}" maxPageItems="10" maxIndexPages="10" export="currentPageNumber=pageNumber" 
	  					scope="request">
						<c:forEach items="${requestScope.classeProcessos}" var="item">
							<pg:item>
								<tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" 
									onMouseOut="this.style.backgroundColor='#eeeeee'" onClick="javascript:detalhar(${item.codigo});" 
									style="cursor:hand">
									<td class="tdLeft">${item.descricao}</td>
									<td align="center" onclick="check=true;">
										<input type="checkbox" name="codigosExclusao" value="${item.codigo}"/>
									</td>
								</tr>
							</pg:item>
						</c:forEach>  
						<tr><td colspan="6" class="td02" align="center"><%@ include file='../navegador.jsp' %></td></tr>
 					</pg:pager>	 	
				</table>
				<div class="space">
					<input onclick="novo()" type="button" class="botao" value="Novo" />
				    <input name="Submit" onclick="return excluir()" type="submit" class="botao" value="Excluir" />
				</div>
 			</div>
		</html:form>
		<c:out escapeXml="false" value="${msg}" />
	</body>
</html:html>
