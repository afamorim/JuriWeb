<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href="<c:url value="/nucleo/style/juriweb_frame.css"/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script>
				var check=false;
				function editar(codigo){
				      if (!check){
				          url = '<c:url value="/admin/Processo/Andamento/CarregarCadastro.do"/>';
				          url += '?codigo='+codigo;
				          url += '&juizoCodigo='+parent.juizoCodigo; 
						  openPopUp(url, 'andamento',640,290);
					  }
					  check=false;
				}	
				function excluir(codigo){
					if (confirm("Deseja excluir definitivamente o(s) registro(s)?")){
						url = '<c:url value="/admin/Processo/Andamento/Remover.do"/>';
						url += '?codigo='+codigo;
						document.forms[0].action  =url;
						document.forms[0].submit();
					}	
				}
		</script>
	</head>
	<body>
		<form name='andamentoForm' method='post' action="<c:url value="/admin/Processo/Andamento/List.do"/>" />
			<input type="hidden" name="processoCodigo" value="${param.processoCodigo}" />
			<div id="ConteudoSistema" >
				<display:table name="andamentos" id="item" cellpadding="2" cellspacing="1" style="width:100%; " >
					<display:column title="Lançamento:" headerClass="th01" class="td01" style="width:10%" 
						scope="onMouseOut='this.style.backgroundColor=#eeeeee;'">
						<fmt:formatDate value="${item.dataLancamento}"/>
					</display:column>
					<display:column title="Tipo:" headerClass="th01" class="td01" style="width:50%" property="tipoAndamento.descricao"/>
					<display:column title="Prazo:" headerClass="th01" class="td01" style="width:18%" >
						<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${item.dataHoraPrazo}"/>
					</display:column>
					<display:column title="Interno:" headerClass="th01" class="td01" style="width:5%" >
						<c:choose>
							<c:when test="${item.interno}"><img src='<c:url value="/img/check.gif"/>'></c:when>
							<c:otherwise><img src='<c:url value="/img/uncheck.gif"/>'></c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="Ações" headerClass="th01" class="td01" style="width:5%" >
						<img src="<c:url value="/img/delete.gif"/>" onclick="excluir('${item.codigo}')">
					</display:column>
				</display:table>
			</div> <!-- onMouseOut="this.style.backgroundColor='#eeeeee';" onClick="javascript:editar(${item.codigo});"
									style="cursor:hand" -->
		</form>
	</body>
</html:html>