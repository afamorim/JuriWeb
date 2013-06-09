<%@page isELIgnored="false" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<c:set var="totalCalculo" value="0"/>
<fieldset style="width:690px">
	<legend><fmt:message key="lb_resultado"/></legend>
		<div id="grid" style="background-color:transparent;height:100%" >
		<table width="680px" border="0" cellpadding="1" cellspacing="1">
			<tr width="100%">
				<td class="th01"><fmt:message key="lb_devedor"/></td>
				<td class="th01"><fmt:message key="lb_valorCorrigido"/></td>
				<td class="th01"><fmt:message key="lb_multaValor"/></td>
				<td class="th01"><fmt:message key="lb_jurosMesValor"/></td>
				<td class="th01"><fmt:message key="lb_honorariosValor"/></td>
				<td class="th01"><fmt:message key="lb_total"/></td>
				<td class="th01">&nbsp;</td>
			</tr>
			<c:if test="${not empty requestScope.collCorrecao}">
				<c:forEach items="${requestScope.collCorrecao}" var="correcao" varStatus="status">
					<c:set value="${correcao.valorHistorico + correcao.multa + correcao.juros + correcao.honorarios}" var="totalLinha"/>
					<tr width="100%" class="td02" >
						<td>${correcao.devedor}</td>
						<td align="right"><fmt:formatNumber value="${correcao.valorHistorico}" maxFractionDigits="2"/></td>
						<td align="right"><fmt:formatNumber value="${correcao.multa}" maxFractionDigits="2"/></td>
						<td align="right"><fmt:formatNumber value="${correcao.juros}" maxFractionDigits="2"/></td>
						<td align="right"><fmt:formatNumber value="${correcao.honorarios}" maxFractionDigits="2"/></td>
						<td align="right"><fmt:formatNumber value="${totalLinha}" maxFractionDigits="2"/></td>
						<td><a href="javaScript:excluirCorrecao('<c:url value="/admin/CorrecaoMonetaria/RemoveCorrecao.ajax"/>', '${status.count-1}')"><img src="<c:url value="/img/delete.gif"/>" border="0"/></a></td>
					</tr>
					<c:set var="totalCalculo" value="${totalLinha + totalCalculo}"/>
				</c:forEach>
				<tr width="100%" class="td02">
					<td colspan="6" align="right"><fmt:formatNumber value="${totalCalculo}" maxFractionDigits="2"/></td>
					<td>&nbsp;</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="6" align="center">
					<input type="button" value="Exportar" onClick="exportar();" class="botao" />
				</td>
			</tr>
		</table>
	</div>
</fieldset>
<input type="hidden" name="arrayCorrecao" value="${requestScope.arrayCorrecao}"/>
<script>
	alert(document.forms[0].elements['arrayCorrecao'].value);
</script>