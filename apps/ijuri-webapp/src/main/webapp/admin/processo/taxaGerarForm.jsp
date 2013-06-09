<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
<head>
<title><fmt:message key="lb_titulo"/></title>
  <link href='<c:url value="/nucleo/style/juriweb_frame.css"/>' rel="stylesheet" type="text/css" />	
</head>
<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/js/string.js"/>">
</script>
<script>
	
	function gerarPeriodo(){
	
		msg = "";
		if (!validarMesAno(document.forms[0].periodoInicio)){
	       msg += "- O Período inicial não foi preenchido ou possui valor inválido. \n";
    	}       
	    if (!validarMesAno(document.forms[0].periodoFim)){
	       msg += "- O Período final não foi preenchido ou possui valor inválido.";
	    }
	    if (msg != ""){
	    	alert(msg);
	    	return false;
	    }
	    
	    dateIni = createDate("01/"+document.forms[0].periodoInicio.value);
	    dateFim = createDate("01/"+document.forms[0].periodoFim.value);
	    if (dateIni.getTime()>dateFim.getTime()){
   	    	alert("O Período Inicial deve ser menor ou igual ao Período Final.");
   	    	return false;	
   	    }	
	
		periodoIni = document.forms[0].periodoInicio.value;
		periodoFim = document.forms[0].periodoFim.value;		
		
		window.opener.gerarPeriodo(periodoIni,periodoFim);
		
		window.close();
	}
	
	function testarEnter(){
		if (event.keyCode == 13){
			gerarPeriodo();
		}
	}
	
</script>

	<html:form action="/admin/Processo/Taxa/List" method="post">
		<input type="hidden" name="acao" value='U'>
		<html:hidden property="codigo" />
		<html:hidden property="tipoCodigo" />
			 
		<div id="ConteudoSistema" >    
			<p><html:errors/></p>
			<fieldset>
				<legend><fmt:message key="lb_gerarTaxas" /></legend>
					
				<label><fmt:message key="lb_periodo"/>:</label>
				
				<html:text onkeypress="testarEnter()" maxlength="7" styleClass="sist_input" property="periodoInicio" style="width:90px" onfocus="limparLabel(this,'mm/aaaa')" onblur="setarLabel(this,'mm/aaaa');" onkeyup="maskFormatPeriodo(this)" value="mm/aaaa"/>
				<html:text onkeypress="testarEnter()" maxlength="7" styleClass="sist_input" property="periodoFim" style="width:90px" onfocus="limparLabel(this,'mm/aaaa')" onblur="setarLabel(this,'mm/aaaa');" onkeyup="maskFormatPeriodo(this)" value="mm/aaaa"/>
				
			</fieldset>
			
			<div class="space">
				<input name="button" type="button" class="login_botao_sist" value="Gerar" onclick="gerarPeriodo()" />
				<input name="Submit" type="button" class="login_botao_sist" value="Fechar" onclick="window.close()"/>
			</div>
		</div>
		<c:out escapeXml="false" value="${requestScope.script}" />
	</html:form>
</html:html>