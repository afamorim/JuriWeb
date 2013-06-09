<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
<head>
<title><fmt:message key="lb_titulo"/></title>
  <link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />	
  <script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
</script>  
<script language="JavaScript" type="text/JavaScript">
	function voltar(){
		//location.href = '<c:url value="/admin/Processo/Orgao/CarregarPesquisa.do" />';
	}
</script>
</head>

<%@ include file="../mainTop.jsp" %>
<html:form action="/admin/CorrecaoMonetaria/IndiceReajuste/Importacao" method="post" enctype="multipart/form-data" >
<div id="conteudo_sist">    
  <h1>Importação de valores de índice de reajuste</h1>
  <p><html:errors/></p>
  <table border="0" cellspacing="1" cellpadding="2">
	  <input type="hidden" name="acao" value='U'>
	  <tr>
				 	<td><label><fmt:message key="lb_indiceReajuste"/>:</label></td>
					<td>
						<html:select property="indiceReajusteCodigo" styleClass="sist_input" style="width:250px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options 	collection="indices" 
											labelProperty="nome" 
											property="codigo"/>		
					    </html:select>
				    </td>
	  </tr>
	  <tr>
	    <td align="right"><label>Arquivo Texto:</label></td>
	    <td><html:file property="arquivo" style="width:250px" styleClass="sist_input"/></td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td colspan="3">
	      <input name="Submit" type="submit" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>"/>
	      <input name="Submit" type="submit" class="login_botao_sist" value="Limpar" onclick="limpar()"/>
   	      <input name="mit" type="button" class="login_botao_sist" value="Voltar" onclick="voltar()"/>
	    </td>
	  </tr>
  </table>
  </div>
  <c:out escapeXml="false" value="${msg}"/>
  <%@ include file="../mainBottom.jsp" %>
  </html:form>
</html:html>
