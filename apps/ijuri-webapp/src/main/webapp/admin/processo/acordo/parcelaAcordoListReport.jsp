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
		<script type="text/javascript" src="<c:url value="/js/string.js"/>"></script> 
		<script language="JavaScript" type="text/JavaScript">
			var tipoParte = '';
			function selecionarPessoa(aTipoParte){
				tipoParte = aTipoParte;
				url = '<c:url value="/admin/Pessoa/List.do"/>';
				openPopUp(url,'pessoaList',640,600);
			}
			
			function atualizarPessoa(codigo,nome){
				if (tipoParte == '1'){
					document.forms[0].elements['clienteCodigo'].value = codigo;
					document.forms[0].elements['clienteNome'].value = nome;		
				}else{
					document.forms[0].elements['devedorCodigo'].value = codigo;
					document.forms[0].elements['devedorNome'].value = nome;		
				}
			}
			
			function validarForm(){
  				msg = "";
  				
  				dataRepIni = document.forms[0].dataRepasseInicio.value;
  				if (dataRepIni.trim() != "" && !validarData(dataRepIni)){
  					msg += "- A data de Repasse inicial está inválida.\n";
  				}
  				
  				dataRepFim = document.forms[0].dataRepasseFinal.value;
  				if (dataRepFim.trim() != "" && !validarData(dataRepFim)){
  					msg += "- A data de Repasse final está inválida.\n";
  				}
  				
  				dataPagamentoIni = document.forms[0].dataPagamentoInicio.value;
  				if (dataPagamentoIni.trim() != "" && !validarData(dataPagamentoIni)){
  					msg += "- A data de Pagamento inicial está inválida.\n";
  				}
  				
  				dataPagamentoFim = document.forms[0].dataPagamentoFinal.value;
  				if (dataPagamentoFim.trim() != "" && !validarData(dataPagamentoFim)){
  					msg += "- A data de Pagamento final está inválida.\n";
  				}
  				
  				dataVencimentoIni = document.forms[0].dataVencimentoInicio.value;
  				if (dataVencimentoIni.trim() != "" && !validarData(dataVencimentoIni)){
  					msg += "- A data de Vencimento inicial está inválida.\n";
  				}
  				
  				dataVencimentoFim = document.forms[0].dataVencimentoFinal.value;
  				if (dataVencimentoFim.trim() != "" && !validarData(dataVencimentoFim)){
  					msg += "- A data de Vencimento final está inválida.\n";
  				}
  				
  				if (msg != ""){
  					alert("Alguns erros foram encontrados:\n"+msg);
  					return false;
  				}
  				
  				return true;
  					
			}
					
			function exportar(){
				if (validarForm()){
					queryString = buildQueryString(document.forms[0]);
					url = '<c:url value="/admin/Processo/Acordo/Parcela/ListReport"/>'+queryString+'&reportType=1';
					openPopUpMaximizado(url,'ParcelaListReport');
				}
			}
			
		</script>
	</head>
	<body>
	<form method="post">
		<input type="hidden" name="clienteCodigo" />
		<input type="hidden" name="devedorCodigo" />
		<div id="ConteudoSistema">    
			<h1><span><fmt:message key="lb_pesquisaAcordoRelatorio"/></span></h1>
			<fieldset>
				<legend><fmt:message key="lb_pesquisaAcordoRelatorio" /></legend>
				<p><html:errors/></p>
				
				<label><fmt:message key="lb_cliente"/>:</label>
				<input type="text" readonly name="clienteNome" style="width:260px" class="sist_input"/>
				<span style="valign:bottom;cursor:hand" >
					<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa(1)" />
					<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('clienteNome','clienteCodigo');" />	    	
				</span><br/>

				<label><fmt:message key="lb_devedor"/>:</label>
				<input type="text" readonly name="devedorNome" style="width:260px" class="sist_input"/>
				<span style="valign:bottom;cursor:hand" >
					<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa(2)" />
					<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('devedorNome','devedorCodigo');return false;" />	    	
				</span><br/>
				
				<label><fmt:message key="lb_numeroProcesso"/>:</label>
				<input type="text" name="processoNumero" maxlength="30" style="width:260px" class="sist_input"/>
				
				<label><fmt:message key="lb_dtRepasse"/>:</label>
				<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataRepasseInicio" 
					style="width:115px"/><label>à</label>
				<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataRepasseFinal" 
					style="width:115px"/><br/>
				
				<label><fmt:message key="lb_dtPagamento"/>:</label>
				<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataPagamentoInicio" 
					style="width:115px"/><label>à</label>
				<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataPagamentoFinal" 
					style="width:115px"/><br/>
				
				<label><fmt:message key="lb_dtVencimento"/>:</label>
				<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataVencimentoInicio" 
					style="width:115px"/><label>à</label>
				<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataVencimentoFinal" 
					style="width:115px"/><br/>
				
				<label><fmt:message key="lb_statusPagamento"/>:</label>
		    	<SELECT name="statusPagamentoCodigo" style="width:260px" class="sist_input">
					<option value=""><fmt:message key="lb_comboFiltro"/></option>
					<option value="1">Pago</option>
					<option value="2">Em Aberto</option>
				</SELECT><br/>
			</fieldset>
			
			<div class="space"><input name="botao" class="login_botao_sist" value="Exibir Relatório" type="button" onclick="exportar()"></div>
		</div>
		</form>
	</body>
</html:html>