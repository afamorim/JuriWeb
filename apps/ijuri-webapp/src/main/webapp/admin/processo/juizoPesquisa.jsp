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
		<script language="JavaScript" type="text/JavaScript">
		<!--
		function MM_preloadImages() { //v3.0
		  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		}
		//-->
		</script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script> 
		<script language="JavaScript" type="text/JavaScript">
			function novo(){
				location.href = '<c:url value="/admin/processo/juizoForm.jsp"/>';
			}
			check=false;
			function detalhar(codigo){
				if (!check){
					url = '<c:url value="/admin/Processo/Juizo/CarregarCadastro.do"/>';
					location.href =url+'?codigo='+codigo;
				}
				check=false;		
			}
			function excluir(){
				return excluirRegistros('codigosExclusao','<c:url value="/admin/Processo/Juizo/Remover.do"/>');
			}
		</script>
	
	</head>
	<body>
		<html:form action="/admin/Processo/Juizo/List" method="post">
			<div id="ConteudoSistema">    
			   <h1><span>Pesquisar Juizo</span></h1>
			   <fieldset>
					<legend><fmt:message key="lb_tituloAcordo" /></legend>
					<p><html:errors/></p>
					
					<label>Nome:</label>
				    <html:text property="descricao" maxlength="50" size="60" styleClass="sist_input"/><br/>
				    
					<input name="Submit" type="submit" class="botao" value="Pesquisar"/>
				</fieldset>
				
				<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
				
				<table  border="0" cellpadding="2" cellspacing="1" width="715px">
					<tr class="td02">
					    <td width="93%" class="th01">Nome:</td>
					    <td width="5%" class="th01">Excluir</td>
					</tr>
					<pg:pager url="${pageContext.request.requestURL}" 
					  			maxPageItems="10" 
					  			maxIndexPages="10"
					          	export="currentPageNumber=pageNumber"
					          	scope="request">  
						<c:forEach items="${requestScope.juizos}" var="item">
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
						<tr>
						    <td colspan="6" class="td02" align="center"> 
						    	<%@ include file='../navegador.jsp' %>
						   </td>
					    </tr>
				    </pg:pager>	 
				</table>  
			</div>
		</html:form>
		<c:out escapeXml="false" value="${msg}" />
	</body>
</html:html>