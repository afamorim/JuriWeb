<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<link href="<c:url value="/nucleo/style/juriweb_frame.css"/>" rel="stylesheet" type="text/css" />
		<script>
			function reloadAndamentos(acao){
				if (acao=='U')
					window.opener.document.andamentoForm.submit(); 
				else{
					window.opener.reloadAndamentos();
				}	
				window.close();
			}
			
			function selTipoAndamento(codigo){
				//length = window.document.forms[0].tipoAndamentoCodigo.length;
				if (codigo != ""){
					for (i = 0; i < window.document.forms[0].tipoAndamentoCodigo.length; i++){
						//alert("Veio");
						//alert(selTipoAndamento.options[i].value);
						if (window.document.forms[0].tipoAndamentoCodigo.options[i].value == codigo){
							//alert("achou");
							window.document.forms[0].tipoAndamentoCodigo.selectedIndex = i;
						}
					}
				}
			}
			
			function setTextTA(codigo){
				window.document.forms[0].textTA.value = codigo;;
			}
		</script>
	</head>
	<body>
		<html:form action="/admin/Processo/Andamento/Salvar" method="post">
			<input type="hidden" name="acao" value='U' />
			<html:hidden property="codigo" />
			<html:hidden property="processoCodigo" />
			<html:hidden property="juizoCodigo" />
			<div id="ConteudoSistema">
				<fieldset>
					<legend><fmt:message key="lb_tituloAndamento" /></legend>
					<p><html:errors/></p>
					
					<label><fmt:message key="lb_tipoAndamento"/>:</label>
					<input type="text" name="textTA" size="3" class="sist_input" value="${requestScope.AndamentoForm.tipoAndamentoCodigo}" 
						onKeyUp="selTipoAndamento(this.value);">
					<html:select property="tipoAndamentoCodigo" style="width:250px" onchange="setTextTA(this.value);">
						<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
						<html:options collection="collTipoAndamento" labelProperty="descricao" property="codigo"/>
					</html:select><br/>
					
					<label><fmt:message key="lb_dtPrazo" />:</label>
					<html:text onkeypress="formataDataHora(event)" property="dataHoraPrazo" maxlength="20" style="width:250px" 
						styleClass="sist_input"/><br/>
					
					<label><fmt:message key="lb_dtLancamento" />:</label>
					<html:text property="dataLancamento" maxlength="10" onkeypress="return formataData(this,event)" 
						style="width:250px" styleClass="sist_input"/><br/>
					
					<label><fmt:message key="lb_observacao" />:</label>
					<html:textarea rows="5" property="observacao" style="width:250px"  styleClass="sist_input"/>
					
					<label><fmt:message key="lb_interno" />:</label>
					<html:checkbox property="interno" value="1"/>
				</fieldset>
				<div class="space">
					<input name="Submit" type="submit" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" />
					<input name="Submit" type="reset" class="login_botao_sist" value="Limpar"/>
					<input name="Submit" type="button" class="login_botao_sist" value="Fechar" onclick="window.close()"/>
				</div>
		  </div>
		  <c:out escapeXml="false" value="${requestScope.script}" />
		</html:form>
	</body>
</html:html>