<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value="/nucleo/style/juriweb_frame.css"/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/string.js"/>"></script>
		<script>
			function atualizarProcesso(codigo,numero,valorCausa,honorario){
				document.forms[0].action = '<c:url value="/admin/Processo/Popup/CarregarFrame.do"/>';
				document.forms[0].codigo.value = codigo;
				document.forms[0].submit();
			}
			
			function selecionarProcesso(){
				url = '<c:url value="/admin/Processo/Popup/CarregarSelecao.do"/>';
				openPopUp(url,'procFrameList',640,600);
			}	
		
		</script>
	</head>
	<body>
		<div id="ConteudoSistema">
			<html:form action="/admin/Processo/Popup/CarregarFrame" method="post">		
				<html:hidden property="codigo" />
				<html:hidden property="clienteCodigo" />
				<html:hidden property="devedorCodigo" />
				
				<label>Processo</label>
				<img src="<c:url value="/img/lupa.gif"/>" onclick="parent.selecionarProcesso() && selecionarProcesso()"><br/>
				
				<label><fmt:message key="lb_numero"/>:</label>
		   		<html:text readonly="true" styleClass="sist_input" property="numero" style="width:100px"/>
				<label><fmt:message key="lb_valor"/>:</label>
				<html:text readonly="true" styleClass="sist_input" property="valorCausa"  style="width:70px"/>
				<label><fmt:message key="lb_honorario"/>:</label>
				<html:text readonly="true" styleClass="sist_input" property="honorario"  style="width:70px"/><br/>
				
				<label><fmt:message key="lb_cliente"/>:</label>
				<html:text readonly="true" styleClass="sist_input" property="clienteNome" style="width:270px"/><br/>
				<label><fmt:message key="lb_devedor"/>:</label>
				<html:text readonly="true" styleClass="sist_input" property="devedorNome" style="width:270px"/><br/>
				
			</html:form>		
		</div>
	</body>
	<script>
		try{
			parent.atualizarProcesso(document.forms[0].codigo.value,document.forms[0].numero.value,document.forms[0].valorCausa.value,document.forms[0].honorario.value);
		}catch(Exception){
		}
	</script>
</html>