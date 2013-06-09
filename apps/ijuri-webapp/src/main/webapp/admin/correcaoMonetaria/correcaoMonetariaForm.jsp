<%@page isELIgnored="false"%>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/indiceReajuste.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/correcaoMonetaria.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>"></script>
		<script>
		function validarForm(){
			if (document.forms[0].elements['arrayCorrecao'] != undefined){
				if (document.forms[0].elements['arrayCorrecao'].value != ""){
					return true;
				}else{
					alert("Monte as correções.");
					return false;
				}
			}else{
				alert("Monte as correções.");
				return false;
			}
			return false;
		}
		
		function atualizaIndiceOpener(){
			atualizaIndice(window.document.forms[0].indiceReajusteCodigo, '<c:url value="/admin/CorrecaoMonetaria/CarregarIndice.ajax"/>');
		}

		function exportar(){
			if (validarForm()){
				//document.forms[0].elements['arrayCorrecao'].value = convertBase64ToReqAjax(document.forms[0].elements['arrayCorrecao'].value);
				document.forms[0].target = "_blank";
				document.forms[0].action = '<c:url value="/admin/CorrecaoMontearia/ListReport"/>?reportType=1';
				//openPopUpMaximizado(url,'CorrecaoListReport');
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body>
		<form method="post">
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloCorrecaoMonetaria" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloCorrecaoMonetaria" /></legend>
						
					<label><fmt:message key="lb_cliente"/>:</label>
					<input type="text" name="cliente" class="sist_input" style="width:400px"/><br/>
					
					<label><fmt:message key="lb_devedor"/>:</label>
					<input type="text" name="devedor" class="sist_input" style="width:400px"/><br/>
					
					<label><fmt:message key="lb_valorHistorico"/>:</label>
					<input type="text" name="valorHistorico" onkeydown='formataValor(this, 13);' class="sist_input" maxlength="13"/>
					<label><fmt:message key="lb_indiceReajuste"/>:</label>
					<select name="indiceReajusteCodigo" class="sist_input" style="width:120px">					    		
					</select>
					<a href="javaScript:openIndiceReajuste('<c:url value="/admin/CorrecaoMonetaria/IndiceReajuste/CarregarCadastro.do"/>');">
						<img src="<c:url value="/img/novo.gif"/>" border="0" />
					</a><br/>
					
					<label><fmt:message key="lb_dataInicial"/>:</label>
					<input type="text" name="dataInicial" onkeypress="return formataData(this,event);" maxlength="10" class="sist_input"/>
					<label><fmt:message key="lb_dataFinal"/>:</label>
					<input type="text" name="dataFinal" onkeypress="return formataData(this,event);" maxlength="10" 
						value="<fmt:formatDate value="${now}"/>" class="sist_input"/><br/>
					
					<label><fmt:message key="lb_multa"/>:</label>
					<input type="text" name="multa" onkeydown='formataValor(this, 6);' class="sist_input"/>
					<label><fmt:message key="lb_juros"/>:</label>
					<input type="text" name="juros" onkeydown='formataValor(this, 6);' class="sist_input"/><br/>
					
					<label><fmt:message key="lb_honorarios"/>:</label>
					<input type="text" name="honorarios" onkeydown='formataValor(this, 6);' value="20" class="sist_input"/> <br/>
				</fieldset>
				
				<div class="space">
					<input name="Submit" type="button" class="botao" value="Calcular" 
						onClick="calculaCorrecao('<c:url value="/admin/CorrecaoMonetaria/CalcularCorrecao.ajax"/>');"/>
					<input name="Submit" type="button" class="botao" value="Limpar" onclick="limpaCorrecao()"/>
				</div><br/>
				
				<table border="0" cellpadding="2" cellspacing="1" width="700px">
					<tr class="td02">
						<td class="td01" width="100%">
							<div id="listaCorrecao" style="width=100%;"></div>
						</td>
					</tr>
				</table>
				<script>
					atualizaIndice(window.document.forms[0].indiceReajusteCodigo, '<c:url value="/admin/CorrecaoMonetaria/CarregarIndice.ajax"/>', 1);
				</script>
			</div>
		</form>
	</body>
</html>