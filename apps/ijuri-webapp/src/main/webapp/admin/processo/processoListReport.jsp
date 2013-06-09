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
				openPopUp(url,'pessoaList',640,600);
			}
			
			function atualizarPessoa(codigo,nome){
				document.forms[0].elements['pessoaCodigo'].value = codigo;
				document.forms[0].elements['pessoaNome'].value = nome;		
			}
			
			function excluir(){
				return excluirRegistros('codigos','<c:url value="/admin/Processo/Remover.do"/>');
			}
			
			function exportar(){
				pessoaCodigo = window.document.forms[0].pessoaCodigo.value;
				juizoCodigo = window.document.forms[0].juizoCodigo.value;
				orgaoJudiciarioCodigo = window.document.forms[0].orgaoJudiciarioCodigo.value;
				classeProcessoCodigo = window.document.forms[0].classeProcessoCodigo.value;
				numero = window.document.forms[0].numero.value;
				//interno = (window.document.forms[0].interno.checked) ? window.document.forms[0].interno.value : 0;
				interno = 1;
				statusCodigo = document.forms[0].statusCodigo.value;
				url = '<c:url value="/admin/Processo/ProcessoListReport?reportType=1"/>&pessoaCodigo=' + pessoaCodigo + '&juizoCodigo=' + juizoCodigo + '&orgaoJudiciarioCodigo=' + orgaoJudiciarioCodigo + '&classeProcessoCodigo=' + classeProcessoCodigo + '&numero=' + numero + "&interno=" + interno + "&statusCodigo=" + statusCodigo;
				openPopUpMaximizado(url,'ProcessoListReport');
			
			}
			
		</script>
	</head>
	<body>
		<html:form action="/admin/Processo/List" method="post">
		<html:hidden property="pessoaCodigo" />
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_pesquisaProcesso"/></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_pesquisaProcesso" /></legend>
					<p><html:errors/></p>
					
					<label><fmt:message key="lb_nomeParte"/>:</label>
					<html:text readonly="true" property="pessoaNome" size="40" styleClass="sist_input"/>
					<span style="valign:bottom">
						<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa()" />
						<img src="<c:url value="/img/borracha.gif"/>" onclick="limpar('pessoaNome','pessoaCodigo')" />	    	
					</span><br/>
					
					<label><fmt:message key="lb_juizo"/>: </label>
					<html:select property="juizoCodigo" styleClass="sist_input" style="width:262px" onchange="refresh()">
						<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
						<html:options collection="juizos" labelProperty="descricao" property="codigo"/>		
					</html:select><br/>
					
					<label><fmt:message key="lb_orgao"/>:</label>
					<html:select property="orgaoJudiciarioCodigo" styleClass="sist_input" style="width:262px">
						<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
						<html:options collection="orgaos" labelProperty="descricao" property="codigo"/>		
					</html:select><br/>
					
					<label><fmt:message key="lb_numeroProcesso"/>:</label>
					<html:text property="numero" maxlength="30" style="width:262px" styleClass="sist_input"/><br/>
	
					<label><fmt:message key="lb_classeProcesso"/>:</label>
					<html:select property="classeProcessoCodigo" styleClass="sist_input" style="width:262px">
						<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
						<html:options collection="classesProcesso" labelProperty="descricao" property="codigo"/>		
					</html:select><br/>
	
					<label><fmt:message key="lb_situacao"/>:</label>
					<html:select property="statusCodigo" styleClass="sist_input" style="width:262px">
						<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
						<html:options collection="situacoes" labelProperty="descricao" property="codigo"/>		
					</html:select><br/>
				</fieldset>
				<div class="space"><input name="botao" class="login_botao_sist" value="Exportar" type="button" onclick="exportar()"></div>
			</div>
		</html:form>
	</body>
</html:html>