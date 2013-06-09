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
		
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script> 
		<script type="text/javascript" src="<c:url value="/js/ajaxComboUtil.js"/>"></script>
		  
		<script language="JavaScript" type="text/JavaScript">
			function novo(){
				location.href = '<c:url value="/admin/Processo/CarregarCadastro.do"/>'+'?acao=limparSessao';
			}
		
			
			function refresh(){
				var url = '<c:url value="/admin/Processo/Orgao/CarregarCombo"/>';
				url += '?juizoCodigo='+document.forms[0].elements['juizoCodigo'].value;
				
				var combo = document.forms[0].elements['orgaoJudiciarioCodigo'];
				req = new AjaxRequestCombo(combo,url,'<fmt:message key="lb_comboFiltro" />');
			 	req.atualizar();
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
			
			function exportar(){
				pessoaCodigo = window.document.forms[0].elements['pessoa.codigo'].value;
				juizoCodigo = window.document.forms[0].juizoCodigo.value;
				orgaoJudiciarioCodigo = window.document.forms[0].orgaoJudiciarioCodigo.value;
				classeProcessoCodigo = window.document.forms[0].classeProcessoCodigo.value;
				numero = window.document.forms[0].numero.value;
				pessoaNome = window.document.forms[0].elements['pessoa.nome'].value;
				url = '<c:url value="/adminCliente/Processo/ProcessoClienteListReport?reportType=1"/>&pessoaCodigo=' + pessoaCodigo + '&juizoCodigo=' + juizoCodigo + '&orgaoJudiciarioCodigo=' + orgaoJudiciarioCodigo + '&classeProcessoCodigo=' + classeProcessoCodigo + '&numero=' + numero + "&pessoaNome=" + pessoaNome;
				if (pessoaCodigo != ""){
					openPopUpMaximizado(url,'ProcessoListReport');
				}else{
					alert("Selecione o cliente para gerar o relatorio.");
				}
			
			}
			
		</script>
	</head>
	<%@ include file="../mainTop.jsp" %>
	<html:form action="/adminCliente/Processo/List" method="post">
		<input type="hidden" name="usuario.codigo" value="${sessionScope.usuario.codigo}"/>
		<input type="hidden" name="pessoa.codigo" value="${sessionScope.pessoa.codigo}"/>
		<input type="hidden" name="pessoa.nome" value="${sessionScope.pessoa.nome}"/>
		<div id="conteudo_sist">    
			<h1><fmt:message key="lb_pesquisaProcessoCliente"/></h1>
			<p><html:errors/></p>
			<table border="0" cellspacing="1" cellpadding="2">
				<tr>
					<td><label><fmt:message key="lb_juizo"/>: </label></td>
					<td>
						<html:select property="juizoCodigo" styleClass="sist_input" style="width:262px" onchange="refresh()">
							<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
							<html:options collection="juizos" labelProperty="descricao" property="codigo"/>		
						</html:select>
				    </td>
				 </tr>	    
				 <tr>
					<td><label><fmt:message key="lb_orgao"/>:</label></td>
					<td>
						<html:select property="orgaoJudiciarioCodigo" styleClass="sist_input" style="width:262px">
							<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
							<html:options collection="orgaos" labelProperty="descricao" property="codigo"/>		
						</html:select>
					</td>
				</tr>
				<tr>
					<td><label><fmt:message key="lb_numeroProcesso"/>:</label></td>
					<td><html:text property="numero" maxlength="30" style="width:262px" styleClass="sist_input"/></td>
				</tr>
				<tr>
					<td><label><fmt:message key="lb_classeProcesso"/>:</label></td>
					<td>
						<html:select property="classeProcessoCodigo" styleClass="sist_input" style="width:262px">
							<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
							<html:options collection="classesProcesso" labelProperty="descricao" property="codigo"/>		
						</html:select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">
						<input name="botao" class="login_botao_sist" value="Exportar" type="button" onclick="exportar()">
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<%@ include file="../mainBottom.jsp" %>
</html:html>