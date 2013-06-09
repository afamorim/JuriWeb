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
		<script type="text/javascript" src="<c:url value="/js/string.js"/>"></script> 
		  
		<script language="JavaScript" type="text/JavaScript">
			function novo(){
				location.href = '<c:url value="/admin/Processo/CarregarCadastro.do"/>'+'?acao=limparSessao';
			}
		
			function validarForm(){ 
				msg = "";
  				
  				prazoIni = document.forms[0].prazoInicial.value;
  				if (prazoIni.trim() != "" && !validarData(prazoIni)){
  					msg += "- O prazo Inicial está inválido.\n";
  				}
  				
  				prazoFim = document.forms[0].prazoFinal.value;
  				if (prazoFim.trim() != "" && !validarData(prazoFim)){
  					msg += "- O prazo Final está inválido.\n";
  				}
  				
  				if (msg != ""){
  					alert("Alguns erros foram encontrados:\n"+msg);
  					return false;
  				}
  				
  				return true;
  			}
  			
  			function carregarTipoAndamentoCombo(){
  				var url = '<c:url value="/admin/Processo/TipoAndamento/CarregarCombo"/>';
  				var combo = document.forms[0].tipoAndamentoCodigo;
  				var req = new AjaxRequestCombo(combo,url,'<fmt:message key="lb_comboFiltro" />');
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
			
			
			function exportar(){
				if (validarForm() == true){
					url = '<c:url value="/admin/Processo/AgendaAndamentosReport"/>'+buildQueryString(document.forms[0]);
					openPopUpMaximizado(url,'AgendaAndamentosReport');
				}
			}
			setTimeout("carregarTipoAndamentoCombo();",400);
		</script>
	</head>
	<body>
		<form method="post">
			<input type="hidden" name="reportType" value="1" /> 
			<input type="hidden" name="pessoaCodigo" />
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_tituloAgendaAndamentos"/></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloAgendaAndamentos" /></legend>
					<p><html:errors/></p>
					
					<label><fmt:message key="lb_nomeAdv"/>:</label>
					<input type="text" readonly name="pessoaNome" style="width:320px" class="sist_input"/>
					<span style="valign:bottom;cursor:hand">
						<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa()" />
						<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('pessoaNome','pessoaCodigo')" />	    	
					</span><br/>
					   
					<label><fmt:message key="lb_dtPrazo"/>:</label>
					<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="prazoInicial" 
						style="width:145px"/>
					<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="prazoFinal" 
						style="width:145px"/><br/>
					
					<label><fmt:message key="lb_tipoAndamento"/>:</label>
					<select name="tipoAndamentoCodigo" style="width:320px"></select>
				</fieldset>
				<div class="space">
					<input name="botao" class="login_botao_sist" value="Exibir Relatório" type="button" onclick="exportar()">
				</div>
			</div>
		</form>
	</body>
</html:html>