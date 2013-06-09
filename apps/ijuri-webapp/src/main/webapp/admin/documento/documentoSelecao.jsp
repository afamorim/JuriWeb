<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/tree" prefix="tree" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teste</title>
<link rel="stylesheet" href='<c:url value="/css/estiloTree.css"/>' type="text/css"> 
<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>">
</script>
</head>
	<table width="98%"  border="0" cellspacing="2" cellpadding="2">
	  <tr>
	    <td width="80%" class="td01"><fmt:message key="lb_nome"/></td>
	    <td width="5%" align="center"  class="td01">--</td>
	  </tr>
	  <pg:pager url="${pageContext.request.requestURL}" 
	  			maxPageItems="10" 
	  			maxIndexPages="10"
	          	export="currentPageNumber=pageNumber"
	          	scope="request"
	          	>
	   <c:forEach items="${requestScope.children}" var="item">
		  <pg:item>
			  <tr class="td02" onMouseOver="this.style.backgroundColor='#eeeeee'" onMouseOut="this.style.backgroundColor='#FFE8CC'" onClick="javascript:selecionarDoc(${item.codigo},'${item.nome}');" style="cursor:hand">
			    <td>${item.nome}</td>
			    <td onclick="check=true;" align="center"><img src="<c:url value="/img/delete.gif"/>" onclick="excluirDoc('${item.codigo}')"></td>
			  </tr>
		  </pg:item>	
	</c:forEach>  
   	<tr>
	    <td colspan="6" class="td02" align="center"> 
	    	  <c:import url="/admin/navegador.jsp" />
	   </td>
    </tr>
    </pg:pager>	  
  </table>  
  <div id="rodapeDocumento" class="conteudo_div" style="position: absolute;top:328px;left:27px;width:302px;height:50px;border:1px solid #808080;border:none;">
  			 <table border="0" width="100%" style="vertical-align:bottom">
				  <tr valign="top" align="center">
				    <td height="36px">
				    	<c:out value="${msg}"/>
				    </td>
				  </tr>  
				  <tr valign="top" align="center">
				    <td>
					    <input type="button" value="Novo" onclick="novoDocumento()" class="login_botao_sist" title="Novo Documento" />
				    </td>
				  </tr>
			  </table>	
  </div>
</html>