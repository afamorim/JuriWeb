<%@ page language="java" %>
<%@page contentType="text/html"%>
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
  <link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />	

<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
</script> 
  
<script language="JavaScript" type="text/JavaScript">
	function novo(){
		location.href = '<c:url value="/admin/Processo/CarregarCadastro.do"/>'+'?acao=limparSessao';
	}

	var check = false;
	function detalhar(codigo){
		if (!check){
			url = '<c:url value="/admin/Processo/CarregarCadastro.do"/>';
			location.href =url+'?codigo='+codigo+'&acao=find';	
		}
		check = false;
	}
	function refresh(){
		document.forms[0].action = '<c:url value="/admin/Processo/CarregarComboOrgaoSelecao.do"/>';
		document.forms[0].submit();
	}
	function selecionarPessoa(){
		url = '<c:url value="/admin/Pessoa/List.do"/>';
		openPopUp(url,'pessoaList',640,620);
	}
	
	function atualizarPessoa(codigo,nome){
		document.forms[0].elements['pessoaCodigo'].value = codigo;
		document.forms[0].elements['pessoaNome'].value = nome;		
	}
	
	function excluir(){
		return excluirRegistros('codigos','<c:url value="/admin/Processo/Remover.do"/>');
	}
	
	function selecionar(codigo,numero,valorCausa,honorario){
		window.opener.atualizarProcesso(codigo,numero,valorCausa,honorario);
		window.close();
	}
	
	function limpar(textBox,hidden){
		document.forms[0].elements[textBox].value = '';
		document.forms[0].elements[hidden].value = '';	
	}
	
</script>

</head>
<html:form action="/admin/Processo/SelecaoList" method="post">
<div id="conteudo_popup">    
   <h1><fmt:message key="lb_pesquisaProcesso"/></h1>
   <p><html:errors/></p>
   <table border="0" cellspacing="1" cellpadding="2">
	  <tr>
	    <td><label><fmt:message key="lb_nomeParte"/>:</label></td>
	    <td>
	    	<html:hidden property="pessoaCodigo" />
	    	<html:text readonly="true" property="pessoaNome" size="40" styleClass="sist_input"/>
	    	<span style="valign:bottom;cursor:hand">
		    	<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa()" />
		    	<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('pessoaNome','pessoaCodigo')" />	    	
	    	</span>
	    </td>
	  </tr>	    
	  <tr>
	    <td><label><fmt:message key="lb_juizo"/>: </label></td>
		    <td><html:select property="juizoCodigo" styleClass="sist_input" style="width:262px" onchange="refresh()">
		     	<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
				<html:options 	collection="juizos" 
								labelProperty="descricao" 
								property="codigo"/>		
		    	</html:select>
		    </td>
	  </tr>	    
	  <tr>
	     <td><label><fmt:message key="lb_orgao"/>:</label></td>
		 <td><html:select property="orgaoJudiciarioCodigo" styleClass="sist_input" style="width:262px">
	     	<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
				<html:options 	collection="orgaos" 
								labelProperty="descricao" 
								property="codigo"/>		
		    	</html:select>
		    </td>
	  </tr>
	  <tr>
	    <td><label><fmt:message key="lb_numeroProcesso"/>:</label></td>
	    <td><html:text property="numero" maxlength="30" style="width:262px" styleClass="sist_input"/></td>
	  </tr>
	  <tr>
		    <td><label><fmt:message key="lb_classeProcesso"/>:</label></td>
		    <td><html:select property="classeProcessoCodigo" styleClass="sist_input" style="width:262px">
		     	<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
				<html:options 	collection="classesProcesso" 
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
	    <td width="40%" class="td01"><fmt:message key="lb_numeroProcesso"/></td>
	    <td width="37%" class="td01"><fmt:message key="lb_orgao"/></td>
 	    <td width="10%" class="td01"><fmt:message key="lb_situacao"/></td>
	    <td width="13%" class="td01"><fmt:message key="lb_dtDistribuicao"/></td>
	  </tr>
	  <pg:pager url="${pageContext.request.requestURL}" 
	  			maxPageItems="10" 
	  			maxIndexPages="10"
	          	export="currentPageNumber=pageNumber"
	          	scope="request"
	          	>
	   <c:forEach items="${requestScope.processos}" var="processo">
		 <pg:item>
		  <tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" onMouseOut="this.style.backgroundColor='#eeeeee'" onClick="javascript:selecionar(${processo.codigo},'${processo.numero}','<fmt:formatNumber pattern="#,##0.00" value="${processo.valorCausa}" maxFractionDigits="2"/>','<fmt:formatNumber value="${processo.honorario/100}" pattern="##0.##%" maxFractionDigits="2"/>');" style="cursor:hand">
		    <td>${processo.numero}</td>
		    <td>${processo.orgaoJudiciario.descricao}</td>
		    <td>${processo.status.descricaoList}</td>
	   	    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${processo.dataDistribuicao}"/></td>
		  </tr>
		  </pg:item>	
	</c:forEach>  
   	<tr>
	    <td colspan="6" class="td02" align="center"> 
	    	<c:import url="/admin/navegador.jsp"/>
	   </td>
    </tr>
    </pg:pager>	  
  </table>  
 </div>
  </html:form>
</html:html>
