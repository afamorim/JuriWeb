<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="lb_tituloPerioIndiceTitulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
		<script type="text/javascript" src="<c:url value="/js/indiceReajuste.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/periodoIndice.js"/>"></script>
		<script>
			function validaForm(){
				if (window.document.forms[0].indiceReajusteCodigo.value != ""){
					mes = window.document.forms[0].mes.value;
					if (mes != "" && (mes > 0 && mes < 13)){
						if (window.document.forms[0].ano.value != ""){
							if (window.document.forms[0].valor.value != ""){
								window.document.forms[0].submit();
							}else{
								alert("O Campo valor não pode ser vazio.");
								window.document.forms[0].valor.focus();
								return false;
							}
						}else{
							alert("O Campo ano não pode ser vazio.");
							window.document.forms[0].ano.focus();
							return false;
						}
					}else{
						alert("O Campo mes não pode ser vazio ou é invalido.");
						window.document.forms[0].mes.focus();
						return false;
					}
				}else{
					alert("O Campo Indice Reajuste não pode ser vazio.");
					window.document.forms[0].indiceReajusteCodigo.focus();
					return false;
				}
			}
			
			function limpar(){
				document.forms[0].elements['indiceReajusteCodigo'].value = "";
				document.forms[0].elements['mes'].value = "";
				document.forms[0].elements['ano'].value = "";
				document.forms[0].elements['valor'].value = "";
				document.forms[0].elements['codigo'].value = "";
			}
			
			function atualizaIndiceOpener(){
				atualizaIndice(window.document.forms[0].indiceReajusteCodigo, '<c:url value="/admin/CorrecaoMonetaria/CarregarIndice.ajax"/>');
			}
			
			function voltar(){
				window.location.href = "<c:url value="/admin/correcaoMonetaria/periodoIndicePesquisa.jsp"/>";
			}
		</script>
	</head>
	<body>
		<html:form action="/admin/Monetario/PeriodoIndiceReajuste/Salvar" method="post">
			<html:hidden property="codigo"/>
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloPerioIndiceTitulo" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloPerioIndiceTitulo" /></legend>
					
					<label><fmt:message key="lb_indiceReajuste"/>:</label>
					<select name="indiceReajusteCodigo" style="width:90px" 
						onChange="atualizaPeriodo('<c:url value="/admin/CorrecaoMonetaria/PeriodoIndice/CarregaUltimoMes.ajax"/>', this.value);" 
						class="sist_input">
					</select>
					<a href="javaScript:openIndiceReajuste('<c:url value="/admin/CorrecaoMonetaria/IndiceReajuste/CarregarCadastro.do"/>');">
						<img src="<c:url value="/img/novo.gif"/>" border="0" />
					</a><br/>
					
					<label><fmt:message key="lb_mes"/></label>
					<html:text property="mes" maxlength="2" style="width:90px" styleClass="sist_input" onkeypress="soNumero(this, event);"/><br/>
					
					<label><fmt:message key="lb_ano"/></label>
					<html:text property="ano" maxlength="4" style="width:90px" styleClass="sist_input" onkeypress="soNumero(this, event);"/><br/>
					
					<label><fmt:message key="lb_valor"/>:</label>
			    	<html:text property="valor" maxlength="50" style="width:90px" styleClass="sist_input" onkeydown='formataValor(this, 5)' /><br/>
				</fieldset>
				
				<div class="space">	
					<input name="Submit" type="button" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" onClick="validaForm();"/>
					<input name="Submit" type="button" class="login_botao_sist" value="Limpar" onclick="limpar()"/>
					<input name="Submit" class="login_botao_sist" value="Voltar" type="button" onclick="voltar();">
				</div>
			</div>
		</html:form>
	</body>
	<script>
		atualizaIndice(window.document.forms[0].indiceReajusteCodigo, '<c:url value="/admin/CorrecaoMonetaria/CarregarIndice.ajax"/>', '${requestScope.PeriodoIndiceReajusteForm.indiceReajusteCodigo}');
	</script>
</html>