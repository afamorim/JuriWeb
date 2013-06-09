<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@page pageEncoding="ISO-8859-1"%>

<!-- PARTE DO GRID DE PARCELAS A SER USADA VIA XML HTTP REQUEST -->			
<fieldset style="width:100%;">
  	<legend>
		<LABEL>Parcelas</LABEL>
	</legend>
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td class="td01" width="8%"><fmt:message key="lb_num"/></td>
			<td class="td01" width="30%"><fmt:message key="lb_dtVencimento"/></td>
			<td class="td01" width="25"><fmt:message key="lb_valor"/> </td>
			<td class="td01" width="24%"><fmt:message key="lb_situacao"/></td>
			<td class="td01" width="5%" align="center">--</td>
			<td height="7px" width="3%"></td>
		</tr>
		<tr>
			<td colspan="6">
				<div id="grid" style="background-color:#E5E5E5;height:90px">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						<c:forEach items="${requestScope.parcelas}" var="parcela" varStatus="status">
							<c:choose>
								<c:when test="${requestScope.ParcelaAcordoForm.codigo != parcela.codigo}">
									<tr id="trParcela${status.count-1}" class="td02"
										onClick="javascript:editarParcela('${parcela.codigo}','${status.count-1}');" style="cursor:hand;">
										<td width="6%">${parcela.numeroFormatado}</td>
										<td width="26%"><fmt:formatDate value="${parcela.dataVencimento}" pattern="dd/MM/yyyy"/></td>
										<td width="25%"><fmt:formatNumber value="${parcela.valorVencimento}" pattern="#,##0.00"/></td>
										<td width="20%">${parcela.status.descricao}</td>
										<td width="4%" align="center">
											<input name="chkParcela" id="chkParcela${status.count-1}" type="checkbox"
												value="${parcela.codigo}"
												onclick="check=true;selecionarCheckParcela(this,'${status.count-1}')">
											<input type="hidden" id="formaPagamento${status.count-1}" 
												value="${parcela.formaPagamento.codigo}"/>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr id="trParcela${status.count-1}" class="td02" 
										onClick="javascript:editarParcela('${parcela.codigo}','${status.count-1}');" 
										style="cursor:hand;background-color='#eeeeee'">
									<td width="6%">${parcela.numeroFormatado} <a name="linhaSelecionada"></a></td>
									<td width="26%"><fmt:formatDate value="${parcela.dataVencimento}" pattern="dd/MM/yyyy"/></td>
									<td width="25%"><fmt:formatNumber value="${parcela.valorVencimento}" pattern="#,##0.00"/></td>
									<td width="20%">${parcela.status.descricao}</td>
									<td width="4%" align="center">
										<input name="chkParcela" id="chkParcela${status.count-1}" type="checkbox" checked="checked" 
											value="${parcela.codigo}" onclick="check=true;selecionarCheckParcela(this,'${status.count-1}')">
										<input type="hidden" id="formaPagamento${status.count-1}" value="${parcela.formaPagamento.codigo}"/>
									</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					</table>
       			</div>
			</td>	
		</tr>
	</table>
</fieldset>
<script>
	setTimeout("location.href = '#linhaSelecionada';",300);
</script>