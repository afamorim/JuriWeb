<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@page pageEncoding="ISO-8859-1"%>

<!-- PARTE DE PAGAMENTO DA PARCELA A SER USADA VIA XML HTTP REQUEST -->			
		 		<FIELDSET>
			 		<legend><label >Pagamento</label></legend>
			 		<table width="99%" align="center" border="0" cellspacing="1" cellpadding="2">
			 			<tr>
						 	<td height="5px"></td>
						</tr>	
			 			<tr>
			 				<td><label><fmt:message key="lb_valorPago"/>:</label></td>
   							<td><input type="text" class="sist_input" onkeydown='formataValor(this, 10)' name="valorPago" value="${requestScope.ParcelaAcordoForm.valorPago}" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_dtPagamento"/>:</label></td>
					    <td><input type="text" class="sist_input" onkeypress="return formataData(this,event);" name="dataPagamento" value="${requestScope.ParcelaAcordoForm.dataPagamento}" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_valorHonorario"/>:</label></td>
					    <td><input type="text" class="sist_input" onkeydown='formataValor(this, 10)' name="valorHonorario" value="${requestScope.ParcelaAcordoForm.valorHonorario}" style="width:80px"/></td>
			    		</tr>
			    		<tr>
			 				<td><label><fmt:message key="lb_valorRepasse"/>:</label></td>
   							<td><input type="text" class="sist_input" onkeydown='formataValor(this, 10)' name="valorRepasse" value="${requestScope.ParcelaAcordoForm.valorRepasse}" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_dtRepasse"/>:</label></td>
					    <td><input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="dataRepasse" value="${requestScope.ParcelaAcordoForm.dataRepasse}" style="width:80px"/></td>
						    <td><label><fmt:message key="lb_valorCorrecao"/>:</label></td>
   							<td><input type="text" class="sist_input" onkeydown='formataValor(this, 10)' name="valorCorrecao" value="${requestScope.ParcelaAcordoForm.valorCorrecao}" style="width:80px"/></td>
			    		</tr>
			    		<tr>
			 				<td><label><fmt:message key="lb_valorJuros"/>:</label></td>
   							<td><input type="text" class="sist_input" onkeydown='formataValor(this, 10)' name="valorJuros" value="${requestScope.ParcelaAcordoForm.valorJuros}" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_valorMulta"/>:</label></td>
					    <td><input type="text" maxlength="10" onkeypress="return formataData(this,event)" name="valorMulta" class="sist_input" value="${requestScope.ParcelaAcordoForm.valorMulta}" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_valorClausulaPenal"/>:</label></td>
					    <td><input type="text" maxlength="10" onkeypress="return formataData(this,event)" class="sist_input" name="valorClausulaPenal" value="${requestScope.ParcelaAcordoForm.valorClausulaPenal}" style="width:80px"/></td>
		    		</tr>
		    		<tr>
					 	<td height="5px"></td>
					</tr>
		    	</table>
	 		</FIELDSET>
					 		