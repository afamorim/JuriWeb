<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
		<head>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="-1">
		<title><fmt:message key="lb_titulo"/></title>
		<link href="<c:url value="/nucleo/style/juriweb_frame.css"/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript">
			var check=false;
			function editar(pos){
				if (!check){
			      url = '<c:url value="/admin/Processo/ParteProcesso/CarregarCadastro.do"/>';
			  	  url += '?idSessaoParte='+document.tipoParteForm.elements['idSessaoParte'].value+'&position='+pos;
				  openPopUpDefault(url, 'parteProcesso');
				} 
				check=false;
			}	
			
			function novo(){
	            url = '<c:url value="/admin/Processo/ParteProcesso/CarregarCadastro.do"/>';
	            url += '?idSessaoParte='+document.tipoParteForm.elements['idSessaoParte'].value
				openPopUpDefault(url, 'parteProcesso');
			}
			
			function excluir(position){
				if (confirm("Deseja excluir definitivamente o(s) registro(s)?")){
					url = '<c:url value="/admin/Processo/ParteProcesso/Remover.do"/>';
					url += '?positions='+position;
					document.forms[0].action  =url;
					document.forms[0].submit();
				}
			}
		</script>	
	</head>
	<body>
		<form name='tipoParteForm' method='post' >
  			<input type="hidden" name="idSessaoParte" value='<c:out value="${requestScope.idSessaoParte}"/>' />
			<div id="ConteudoSistema" style="width: 100%">
				<display:table name="partes" id="item" cellpadding="2" cellspacing="1" style="width:100%; " >
					<c:choose>
						<c:when test="${item.situacaoCliente == 1}">
							<display:column property="nomeParte" title="Nome" headerClass="th01" style="width:60%; " class="tdLeft"/>
						</c:when>
						<c:otherwise>
							<display:column property="nomeParte" title="Nome" headerClass="th01" class="tdLeft" style="font-family: Arial; font-size: 11px;"/>
						</c:otherwise>
					</c:choose>
					<display:column property="tipoParte.descricao" title="Tipo" headerClass="th01" class="tdLeft" style="font-family: Arial; font-size: 11px;"/>
					<display:column title="--" headerClass="th01" class="tdLeft" style="font-family: Arial; font-size: 11px;">
						<img src="<c:url value="/img/delete.gif"/>" onclick="excluir('${status.count-1}')">
					</display:column>
				</display:table>
			</div>
 		</form>
	</body>
</html:html>