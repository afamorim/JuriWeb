<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
<head>
<title><fmt:message key="lb_titulo"/></title>
  <link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />	
</head>
<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/js/string.js"/>">
</script>
<script>
	function reloadTaxas(){
		
		window.opener.document.forms[0].taxas.value = document.forms[0].taxas.value;
		window.opener.document.forms[0].submit(); 

		window.close();
	}
	
	function confirmar(){
		if (!validarAno(document.forms[0].ano)){
			alert("- O ano não foi preenchido ou possui valor inválido.");
			return false;
		}
		
		document.forms[0].taxas.value = window.opener.document.forms[0].taxas.value;

		url = '<c:url value="/admin/Processo/Taxa/Cadastro.do"/>';
		document.forms[0].action  = url;
		
		document.forms[0].submit();
	}
	
	function testarEnter(){
		if (event.keyCode == 13){
			confirmar();
		}
	}
	
</script>

<html:form action="/admin/Processo/Taxa/List" method="post">
	<html:hidden property="taxas"/>
	<html:hidden property="pos"/>
	<html:hidden property="tipoCodigo" />
	<div id="conteudo_popup" style="width:380px">    
	<h1><fmt:message key="lb_tituloTaxaAcordo" /></h1>
	<p><html:errors/></p>
	<table border="0" cellspacing="1" cellpadding="2">
		<tr>
			<td><label><fmt:message key="lb_mes"/>:</label>
			</td>
			<td> 
              <select name="mes" class="sist_input" style="width:160px">
                  <c:forEach varStatus="status" begin="0" end="11" items="${requestScope.meses}" var="item" >
                    <c:choose>
                        <c:when test="${requestScope.TaxaForm.mes == status.count}">
                                <option selected value="<c:out value="${status.count}"/>"><c:out value="${item}"/></option>
                        </c:when>
                        <c:otherwise>
                                <option value="<c:out value="${status.count}"/>"><c:out value="${item}"/></option>
                        </c:otherwise>
                    </c:choose>
                  </c:forEach>	
			  </select>
			</td>
		</tr>
		<tr>	
			<td><label><fmt:message key="lb_ano"/>:</label></td>
			<td> 
				<html:text onkeypress="testarEnter()" maxlength="4" styleClass="sist_input" onfocus="limparLabel(this,'aaaa')" onblur="setarLabel(this,'aaaa');" property="ano" style="width:160px"/>
				<SCRIPT>
					if (document.forms[0].ano.value.trim()=="")
						document.forms[0].ano.value = 'aaaa';
				</SCRIPT>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input name="button" type="button" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" onclick="confirmar()" />
				<input name="Submit" type="reset" class="login_botao_sist" value="Limpar"/>
				<input name="Submit" type="button" class="login_botao_sist" value="Fechar" onclick="window.close()"/>
			</td>
	  	</tr>
	</table>
  </div>
  <input type="hidden" name="acao" value='U'>
  <html:hidden property="codigo" />
  
  <c:out escapeXml="false" value="${requestScope.script}" />
 
  <c:out escapeXml="false" value="${requestScope.msg}" />
  
 </html:form>
</html:html>
