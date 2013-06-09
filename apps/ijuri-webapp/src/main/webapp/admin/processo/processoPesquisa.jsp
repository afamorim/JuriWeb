<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>

		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>" ></script>
		<script type="text/javascript" src="<c:url value="/js/ajaxComboUtil.js"/>" ></script>
  
		<script language="JavaScript" type="text/JavaScript">
			function novoProcesso(){
				window.location.href = '<c:url value="/admin/Processo/CarregarCadastro.do"/>'+'?acao=limparSessao';
			}
		
			var check = false;
			function detalhar(codigo){
				if (!check){
					url = '<c:url value="/admin/Processo/CarregarCadastro.do"/>';
					location.href =url+'?codigo='+codigo+'&acao=find';	
				}
				check = false;
			}
			
			function refresh(){
				var url = '<c:url value="/admin/Processo/Orgao/CarregarCombo"/>';
				url += '?juizoCodigo='+document.forms[0].elements['juizoCodigo'].value;
				
				var combo = document.forms[0].elements['orgaoJudiciarioCodigo'];
				req = new AjaxRequestCombo(combo,url,'<fmt:message key="lb_comboFiltro" />');
			 	req.atualizar();
			}
			
			function selecionarPessoa(){
				url = '<c:url value="/admin/Pessoa/List.do"/>';
				openPopUp(url,'pessoaList',640,620);
			}
			
			function atualizarPessoa(codigo,nome){
				document.forms[0].elements['pessoaCodigo'].value = codigo;
				document.forms[0].elements['pessoaNome'].value = nome;		
			}
			
			function excluir(){
				return excluirRegistros('codigos','<c:url value="/admin/Processo/Remover.do"/>');
			}
		</script>

	</head>
	<body>
		<html:form action="/admin/Processo/List" method="post">
			<html:hidden property="pessoaCodigo" />
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_pesquisaProcesso"/></span></h1>
				<fieldset>
		   			<legend><fmt:message key="lb_pesquisaProcesso"/></legend>
					<p><html:errors/></p>
		   			
		   			<label><fmt:message key="lb_nomeParte"/>:</label>
			    	<html:text readonly="true" property="pessoaNome" size="40" styleClass="sist_input"/>
			    	<span style="valign:bottom;cursor:hand">
				    	<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa()" />
				    	<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('pessoaNome','pessoaCodigo')" />	    	
			    	</span><br />
			    	
			    	<label><fmt:message key="lb_juizo"/>: </label>
				    <html:select property="juizoCodigo" styleClass="sist_input" style="width:215px" onchange="refresh()">
				     	<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
						<html:options 	collection="juizos" labelProperty="descricao" property="codigo"/>
					</html:select><br />
				    
			 		<label><fmt:message key="lb_orgao"/>:</label>
					<html:select property="orgaoJudiciarioCodigo" styleClass="sist_input" style="width:215px">
			     		<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
						<html:options 	collection="orgaos" labelProperty="descricao" property="codigo"/>		
				    </html:select><br />
				    
					<label><fmt:message key="lb_numeroProcesso"/>:</label>
					<html:text property="numero" maxlength="40" size="40" styleClass="sist_input"/><br />
		
					<label><fmt:message key="lb_classeProcesso"/>:</label>
					<html:select property="classeProcessoCodigo" styleClass="sist_input" style="width:215px">
						<html:option value=""><fmt:message key="lb_comboFiltro" /></html:option>
						<html:options collection="classesProcesso" labelProperty="descricao" property="codigo"/>		
					</html:select><br />
				    
					<input name="Submit" type="submit" class="botao" value="Pesquisar"/>
				</fieldset>
		
				<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
				<table border="0" cellpadding="2" cellspacing="1" width="715px">
					<tr class="td02">
						<th width="40%" class="th01"><fmt:message key="lb_numeroProcesso"/></th>
						<th width="37%" class="th01"><fmt:message key="lb_orgao"/></th>
						<th width="10%" class="th01"><fmt:message key="lb_situacao"/></th>
						<th width="10%" class="th01"><fmt:message key="lb_dtDistribuicao"/></th>
						<th width="3%" align="center" class="th01">Excluir:</th>
					</tr>
					
					<pg:pager url="${pageContext.request.requestURL}" maxPageItems="10" maxIndexPages="10" export="currentPageNumber=pageNumber"
						scope="request">
						<input type="hidden" name="pager.offset"  value="<c:out value="${requestScope.paramOffset}"/>" />
						<c:forEach items="${requestScope.processos}" var="processo">
							<pg:item>
								<tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" onMouseOut="this.style.backgroundColor='#eeeeee'" 
									onClick="javascript:detalhar(${processo.codigo});" style="cursor:hand">
									<td class="tdLeft">${processo.numero}</td>
									<td class="tdLeft">${processo.orgaoJudiciario.descricao}</td>
									<td class="tdLeft">${processo.status.descricaoList}</td>
									<td class="tdLeft"><fmt:formatDate pattern="dd/MM/yyyy" value="${processo.dataDistribuicao}"/></td>
									<td><input onclick="check=true;" type="checkbox" name="codigos" value="${processo.codigo}"/></td>
								</tr>
							</pg:item>	
						</c:forEach>  
						<tr>
							<td colspan="6" class="td02" align="center"> 
								<%@ include file='../navegador.jsp' %>
							</td>
						</tr>
					</pg:pager>	  
				</table>
				
				<div class="space">
					<input onclick="novoProcesso();" type="button" class="botao" value="Novo" />
					<input name="Submit" onclick="return excluir()" type="submit" class="botao" value="Excluir" />
				</div>  
			</div>
		</html:form>
	</body>
</html:html>