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
		location.href = '<c:url value="/admin/Site/Jurisprudencia/CarregarPesquisa.do" />';
	}
	
	function selecionarDocumento(){
		url = '<c:url value="/admin/documento/treeViewSelecao.jsp"/>';
		openPopUp(url,'treeViewSel',660,500);
	}
	
	function atualizarDocumento(codigo,nome){
		document.forms[0].documentoCodigo.value = codigo;
		document.forms[0].documentoNome.value = nome;
	}
	
</script>
</head>
<%@ include file="../mainTop.jsp" %>
<html:form action="/admin/Site/Jurisprudencia/Salvar" method="post">
<div id="conteudo_sist">    
  <h1><fmt:message key="lb_tituloJurisprudencia" /></h1>
  <p><html:errors/></p>
  <table border="0" cellspacing="1" cellpadding="2">
	  <input type="hidden" name="acao" value='U'>
	  <html:hidden property="codigo" />
	  <tr>
	    <td align="right"><label><fmt:message key="lb_nome"/>:</label></td>
	    <td><html:text readonly="true" property="documentoNome" maxlength="50" style="width:270px" styleClass="sist_input" />
	    	<html:hidden property="documentoCodigo" />
	    	<span style="valign:bottom">
		    	<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarDocumento()" />
		    	<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('documentoNome','documentoCodigo')" />	    	
	    	</span>
	    </td>
	  </tr>
	  <tr>
	  	<td align="right"><label><fmt:message key="lb_titulo_juris"/>:</label></td>
	    <td><html:text property="titulo" maxlength="50" style="width:270px" styleClass="sist_input" /> </td>
	  </tr>
	  <tr>
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
	      <input name="Submit" type="submit" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>"/>
	      <input name="Submit" type="submit" class="login_botao_sist" value="Limpar" onclick="limpar()"/>
   	      <input name="Submit" type="button" class="login_botao_sist" value="Voltar" onclick="voltar()"/></td>
	  </tr>
  </table>
  </div>
  <%@ include file="../mainBottom.jsp" %>
  </html:form>
</html:html>
