<%@ page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page contentType="text/html; charset=ISO-8859-1"  %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<fieldset style="width:150px;height:100%; !important">
	<legend>
	
		<c:choose>
			<c:when test='${requestScope.TaxaForm.tipoCodigo eq "1"}'>
				Taxas Comuns<script>tipo = 1;</script>
			</c:when>
			<c:otherwise>Taxas Extras<script>tipo = 2;</script></c:otherwise>
		</c:choose>
		
	</legend>
	<label>Mês/Ano:</label><br/>
	<div id="grid" style="background-color:#CCCCCC; ">
		<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1">
			<c:forEach items="${requestScope.taxas}" var="item" varStatus="status">
				<tr class="td02">
					<td width="98%"><fmt:formatDate value="${item.mesAno}" pattern="MMMM/yyyy" /></td>
				</tr>
			</c:forEach> 
		</table>
	</div>
	
</fieldset>