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
  				
  				clienteCodigo = document.forms[0].clienteCodigo.value;
				if (clienteCodigo.trim() == ""){
					msg = "- O cliente deve ser informado.\n";
  				}
  				
  				dataRepIni = document.forms[0].dataRepasseInicio.value;
  				if (dataRepIni.trim() != "" && !validarData(dataRepIni)){
  					msg += "- A data de Repasse inicial est� inv�lida.\n";
  				}
  				
  				dataRepFim = document.forms[0].dataRepasseFinal.value;
  				if (dataRepFim.trim() != "" && !validarData(dataRepFim)){
  					msg += "- A data de Repasse final est� inv�lida.\n";
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
					url = '<c:url value="/admin/Processo/Acordo/Parcela/ClienteListReport"/>'+queryString+'&reportType=1';
					openPopUpMaximizado(url,'ParcelaListClienteReport');
				}
			}
			
		</script>
	</head>
	<body>
		<form method="post">
			<input type="hidden" name="clienteCodigo" />
			<input type="hidden" name="devedorCodigo" />
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_pesquisaAcordoRelatorioCliente"/></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloMunicipio" /></legend>
					<p><html:errors/></p>
					
					<label><fmt:message key="lb_cliente"/>:</label>
			    	<input type="text" readonly name="clienteNome" style="width:210px" class="sist_input"/>
			    	<span style="valign:bottom;cursor:hand" >
						<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa(1)" />
						<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('clienteNome','clienteCodigo');return false;" />	    	
					</span><br/>
					
					<label><fmt:message key="lb_devedor"/>:</label>
			 		<input type="text" readonly name="devedorNome" style="width:210px" class="sist_input"/>
					<span style="valign:bottom;cursor:hand" >
						<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa(2)" />
						<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('devedorNome','devedorCodigo');return false;" />	    	
					</span><br/>
					
					<label><fmt:message key="lb_numeroProcesso"/>:</label>
					<input type="text" name="processoNumero" maxlength="30" style="width:210px" class="sist_input"/><br/>
					
					<label><fmt:message key="lb_dtRepasse"/>:</label>
					<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataRepasseInicio" 
						style="width:90px"/><label>�</label>
					<input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataRepasseFinal" 
						style="width:90px"/><br/>
				</fieldset>
				<div class="space"><input name="botao" class="login_botao_sist" value="Exibir Relat�rio" type="button" onclick="exportar()"></div>
			</div>
		</form>
	</body>
</html:html>