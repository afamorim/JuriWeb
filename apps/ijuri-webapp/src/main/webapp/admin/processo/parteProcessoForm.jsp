<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href="<c:url value="/nucleo/style/juriweb_frame.css"/>" rel="stylesheet" type="text/css" />
		  
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
		
		<script language="JavaScript" type="text/JavaScript">
			function selecionarPessoa(){
				url = '<c:url value="/admin/Pessoa/List.do"/>';
				openPopUp(url,'pessoaList',640,620);
			}
			
			function atualizarPessoa(codigo,nome){
				document.forms[0].elements['pessoaCodigo'].value = codigo;
				document.forms[0].elements['nome'].value = nome;		
			}
			
			function atualizarPartes(acao){
				  url = '<c:url value="/admin/Processo/ParteProcesso/List.do"/>?';
			      url += 'idSessaoParte='+document.forms[0].elements['idSessaoParte'].value
				  if (acao == 'U'){
		    		window.opener.location.href = url;
			      }else{	
			    	iFrame = window.opener.document.getElementById("partesFrame");
					iFrame.src = url;
				  }	
				  window.close();
				}
			function submeter(){
				form = document.forms[0];
				if (form.elements['pessoaCodigo'].value == ""){
					alert("Selecione uma pessoa válida para fazer parte do processo.");
					return false;
				}
				if (form.elements['tipoParteCodigo'].value == ""){
					alert("Selecione qual o tipo de parte esta pessoa será no processo.");
					return false;
				}
				
				if (document.forms[0].position.value!="")
					document.forms[0].action = '<c:url value="/admin/Processo/ParteProcesso/Alteracao.do"/>';
				return true;
			}
			
			//Esconde o check de cliente quando o tipo 'advogado' é escolhido. 
			function selecionarTipo(){
				if (document.forms[0].tipoParteCodigo.value == '3'){
					document.forms[0].elements['cliente'].checked = false;
					document.getElementById("divCliente").style.display = 'none';
				}else{
					document.getElementById("divCliente").style.display = 'block';
				}
			}
			
			setTimeout("selecionarTipo();",500);
		</script>
	</head>
	<body>
		<html:form action="/admin/Processo/ParteProcesso/Cadastro" method="post">
		<input type="hidden" name="acao" value='U' />
		<input type="hidden" name="idSessaoParte" value="${param.idSessaoParte}"/>
		<html:hidden property="position" />
		<html:hidden property="pessoaCodigo" />

	<div id="ConteudoSistema" >    
  		<h1><fmt:message key="lb_tituloParte" /></h1>
  		<p><html:errors/></p>
		<fieldset>
			<legend><fmt:message key="lb_tituloParte" /></legend>
		<label><fmt:message key="lb_nomeParte"/>:</label>
	  	<html:text readonly="true" property="nome" maxlength="50" size="60" styleClass="sist_input"/>
	    <img style="cursor:hand" src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa()" />
	    <img style="cursor:hand" src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('nome','pessoaCodigo')" /><br/>
	    
	    <label><fmt:message key="lb_tipo"/>:</label>
	    <html:select property="tipoParteCodigo" styleClass="sist_input" onchange="selecionarTipo(this)">
	    	<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
			<html:options collection="tipos" labelProperty="descricao" property="codigo"/>		
		</html:select><br/>
	  
		<div align="right" id="divCliente">
		<html:checkbox property="cliente" value="1"/><label><fmt:message key="lb_cliente"/></label><br/>
		</div>
		</fieldset>
		<div class="space">
			<input name="Submit" type="submit" class="login_botao_sist" onclick="return submeter();" value="<fmt:message key="lb_btEnviar"/>"/>
			<input name="Submit" type="reset" class="login_botao_sist" value="Limpar"/>
			<input name="Submit" type="button" class="login_botao_sist" value="Fechar" onclick="window.close()"/>
		</div>
  </div>
  </html:form>
  <c:out value="${requestScope.script}" escapeXml="false"/>
	</body>
</html:html>