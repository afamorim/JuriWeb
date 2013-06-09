<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>

<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<title><fmt:message key="lb_titulo"/></title>
	<link href='<c:url value="/css/estiloAba.css"/>' rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="<c:url value="/js/abaProcesso.js"/>" ></script>
	<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>" ></script>
	<script type="text/javascript" src="<c:url value="/js/ajaxComboUtil.js"/>"></script>
	<script type="text/javascript">
		function refresh(){
			var url = '<c:url value="/admin/Processo/Orgao/CarregarCombo"/>';
			url += '?juizoCodigo='+document.forms[0].elements['juizoCodigo'].value;
			
			var combo = document.forms[0].elements['orgaoJudiciarioCodigo'];
			req = new AjaxRequestCombo(combo,url);
		 	req.atualizar();
			
		}
		
		function voltar(){
			document.forms[0].acao.value = '';
			document.forms[0].action = '<c:url value="/admin/Processo/Voltar.do"/>';
		}
		
		function exportar(){
			codigoProcesso = parseInt(document.forms[0].codigo.value);
			if (codigoProcesso>0){
				url = '<c:url value="/admin/Processo/DetalhamentoReport?reportType=1&codigo="/>'+codigoProcesso;
				openPopUpMaximizado(url,'ProcessoReport'+codigoProcesso);
			}else{
				alert("Para exportar um processo, é necessário cadastrá-lo antes.");
			}
		}
		
		function pesquisarAcordos(){
			codigoProcesso = parseInt(document.forms[0].codigo.value);
			if (codigoProcesso>0){
				url = '<c:url value="/admin/Processo/Acordo/List.do?processoNumero="/>';
				url += document.forms[0].numero.value;
				url += '&acao=F';
				location.href = url;
			}else{
				alert("Para ver acordos, é necessário cadastrar um processo.");
			}
		}
		
		var juizoCodigo = '<c:out value="${requestScope.ProcessoForm.juizoCodigo}"/>';
		function novoAndamento(){
			if (parseInt(codigo)>0){
				url = '<c:url value="/admin/Processo/Andamento/CarregarCadastro.do?processoCodigo="/>'+codigo;
				url += '&juizoCodigo='+juizoCodigo;
				openPopUp(url, 'andamento',640,320);
			}else{
				alert('Para cadastrar um novo andamento é necessário cadastrar primeiro um processo.');
			}
		}
		
		function reloadAndamentos(){
			url = '<c:url value="/admin/Processo/Andamento/List.do?processoCodigo="/>'+codigo;
		   	document.getElementById("andamentoFrame").src= url;
		}
		
		var codigo = '<c:out value="${requestScope.ProcessoForm.codigo}"/>';			  
		function novaParte(){
			url = '<c:url value="/admin/Processo/ParteProcesso/CarregarCadastro.do"/>';
			url += '?idSessaoParte='+document.forms[0].elements['idSessaoParte'].value
			openPopUpDefault(url, 'parteProcesso');
		}
	</script>
</head>

	<body>
		<html:form action="/admin/Processo/Cadastro" method="post">
			<html:hidden property="urlVoltar"/>
			<input type="hidden" name="acao" value='U'>
			<html:hidden property="codigo" />
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloProcesso" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloProcesso" /></legend>
					<p><html:errors/></p>
					<div class="coluna01">
						<label><fmt:message key="lb_numeroProcesso"/>:</label>
						<html:text styleClass="sist_inpu" property="numero"  style="width:160px"/><br />
						
						<label><fmt:message key="lb_situacao"/>:</label>
						<html:select property="statusCodigo" styleClass="sist_input" style="width:160px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="situacoes" labelProperty="descricao" property="codigo"/>		
						</html:select> <br/>
						
						<label><fmt:message key="lb_dtDistribuicao"/>:</label>
						<html:text onkeypress="return formataData(this,event)" styleClass="sist_input" property="dataDistribuicao"  style="width:160px" />
						
						<label><fmt:message key="lb_classeProcesso"/>:</label>
					    <html:select property="classeProcessoCodigo" styleClass="sist_input" style="width:160px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="classesProcesso" labelProperty="descricao" property="codigo"/>		
					    </html:select><br/>
					    
						<label><fmt:message key="lb_valorCausa"/>:</label>
						<html:text maxlength="13" onkeydown='formataValor(this, 13)' styleClass="sist_input" property="valorCausa" style="width:160px"/><br/>
					    
						<label><fmt:message key="lb_turno"/>:</label>
						<html:select property="turnoCodigo" styleClass="sist_input" style="width:160px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="turnos" labelProperty="descricao" property="codigo"/>		
					    </html:select><br/>
				    </div>
				    
					<div class="coluna02">
						<label><fmt:message key="lb_juizo"/>: </label>
					    <html:select property="juizoCodigo" styleClass="sist_input" style="width:160px" onchange="refresh()">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="juizos" labelProperty="descricao" property="codigo"/>		
					    </html:select> <br/>
					    
					     <label><fmt:message key="lb_orgao"/>:</label>
					    <html:select property="orgaoJudiciarioCodigo" styleClass="sist_input" style="width:160px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="orgaos" labelProperty="descricao" property="codigo"/>		
					    </html:select><br/>
					    
						<label><fmt:message key="lb_dtAutuacao"/>:</label>
						<html:text onkeypress="return formataData(this,event)" styleClass="sist_input" property="dataAutuacao"  style="width:160px" /><br />
						
						<label><fmt:message key="lb_comarca"/>:</label>
					    <html:select property="comarcaCodigo" styleClass="sist_input" style="width:160px">
				    		<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="comarcas" labelProperty="municipio.nome" property="codigo"/>		
					    </html:select><br/>
					    
					    <label><fmt:message key="lb_honorario"/>:</label>
						<html:text maxlength="5" onkeydown='formataValor(this, 5)' styleClass="sist_input" property="honorario" style="width:160px"/> <br/>
					</div>
				  
				  	<label><fmt:message key="lb_observacao"/>:</label>
				    <html:textarea property="observacao" rows="4" style="width:440px" />
				</fieldset><br/><br/>
				
				<table border="2" cellpadding="0" cellspacing="0" width="715px" style="background: transparent;">
					<tr> 
						<td align="left">
							<table width="10" border="0" cellspacing="2" cellpadding="0" height="19px" style="background: transparent;">
								<tr>
								<!-- ABA PARTES -->
								    <td id="partesMenu" class="corpoAbaAtiva" 
								    	onclick="openAba('partesMenu','partesImg','divPartes')" height="19px">&nbsp;&nbsp;Partes&nbsp;&nbsp;</td>
								<!-- ABA ANDAMENTO -->
								    <td id="andamentoMenu" class="corpoAbaInativa"  
								    	onclick="openAba('andamentoMenu','andamentoImg','divAndamento')" height="19px">&nbsp;&nbsp;Andamento&nbsp;&nbsp;</td>
								<!-- ABA TAXAS -->
								    <td id="taxasMenu" class="corpoAbaInativa" 
								    	onclick="openAba('taxasMenu','taxasImg','divTaxas')">&nbsp;Período&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr> 
						<td width="98%" align="left">
							<div id="divPartes">
								<p><html:errors/></p>
								<fieldset style="width: 600px">
								 	<legend style="vertical-align: middle;">
										Partes
										<img src="<c:url value="/img/novo.gif"/>" onclick="novaParte()">
									</legend>
									<input type="hidden" name="idSessaoParte" value="${requestScope.idSessaoParte}" />
									<table border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: transparent;">
										<tbody>
											<tr height="125px">
												<td colspan="4" align="center">
									    			<c:choose>
														<c:when test="${!(requestScope.ProcessoForm.acao eq 'U')}">
															<c:choose>
																<c:when test="${requestScope.ProcessoForm.codigo > 0}">
																	<IFRAME height="300px" width="90%" vspace="0" scrolling="no" 
																	name="partesFrame" frameborder="0" marginwidth="0" marginheight="0" 
																	id="partesFrame"
																	src='<c:url value="/admin/Processo/ParteProcesso/List.do?acao=find&processoCodigo="/>${requestScope.ProcessoForm.codigo}&idSessaoParte=${requestScope.idSessaoParte}'>
																	</IFRAME>
																</c:when>
																<c:otherwise>
																 	<IFRAME height="300px" width="90%" vspace="0" scrolling="no" 
																	name="partesFrame" frameborder="0" marginwidth="0" 
																	marginheight="0" id="partesFrame"
																	src='<c:url value="/admin/Processo/ParteProcesso/List.do?acao=clear&processoCodigo="/>${requestScope.ProcessoForm.codigo}&idSessaoParte=${requestScope.idSessaoParte}'>
																	</IFRAME>
																</c:otherwise>
															</c:choose>						    	  	
														</c:when>
														<c:otherwise>
															<IFRAME height="300px" width="90%" vspace="0" scrolling="no" 
															name="partesFrame" frameborder="0" marginwidth="0" 
															marginheight="0" id="partesFrame"
															src='<c:url value="/admin/Processo/ParteProcesso/List.do?acao=refresh&processoCodigo="/>${requestScope.ProcessoForm.codigo}&idSessaoParte=${requestScope.idSessaoParte}'>
															</IFRAME>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</tbody>
									</table>
								</fieldset>
							</div>
							<div id="divAndamento" style="DISPLAY:none">
								<p><html:errors/></p>
								<fieldset style="width: 600px">
								 	<legend>
										Andamentos
										<img src="<c:url value="/img/novo.gif"/>" onclick="novoAndamento()">
									</legend>
									<table border="0" cellpadding="2" cellspacing="2" width="100%" style="background-color: transparent;">
										<tbody>
									    	<tr height="300px">
												<td colspan="4" align="center">
									    			<IFRAME height="300px" width="100%" vspace="0" scrolling="no" name="andamentoFrame" id="andamentoFrame" 
									    				frameborder="0" marginwidth="0" marginheight="0">
									    			</IFRAME>
									 	    		<script>
														reloadAndamentos();
													</script>
									    		</td>
											</tr>
										</tbody>
									</table>
								</fieldset>
							</div>
							<div id="divTaxas" style="DISPLAY:none">
								<p><html:errors/></p>
								
								<table border="0" height="100%" style="background-color: transparent;">
									<tr>
										<td>	
											<!-- TAXAS COMUNS -->
											<html:hidden property="taxasComunsCode" />
											<table border="0" height="100%" style="background-color: transparent;">
											    <tr>
													<td width="250px" height="230px">
														<IFRAME style="width:100%;height:100%" vspace="0" scrolling="no" id="txComunFrame"
															name="txComunFrame" frameborder="0" marginwidth="0" marginheight="0">
														</IFRAME>
														<script>
					        								codigo = '<c:out value="${requestScope.ProcessoForm.codigo}"/>';
															function reloadTaxasComuns(){
																hasErrors = <c:out value="${!empty requestScope['org.apache.struts.action.ERROR'] }"/>;
																if (hasErrors){
																	url = '<c:url value="/admin/Processo/Taxa/List.do?tipoCodigo=1&acao=getSessao"/>';
																}else{
																	url = '<c:url value="/admin/Processo/Taxa/List.do?tipoCodigo=1&processoCodigo="/>'+codigo;
																}
																iFrameTxComun = document.getElementById("txComunFrame");
															  	iFrameTxComun.src= url;
															}
															reloadTaxasComuns();
														</script>
													</td>
												</tr>
											</table>	  
										</td>
										<td>
											<!-- TAXAS EXTRAS -->
											<html:hidden property="taxasExtrasCode" />
											<table border="0" height="100%" style="background-color: transparent;">
												<tr>
													<td width="250px" height="230px">
														<IFRAME style="width:100%;height:100%" vspace="0" scrolling="no" id="txExtraFrame" 
															name="txExtraFrame" frameborder="0" marginwidth="0" marginheight="0">
														</IFRAME>
														<script>
															codigo = '<c:out value="${requestScope.ProcessoForm.codigo}"/>';
															
															function reloadTaxasExtras(){
																hasErrors = <c:out value="${!empty requestScope['org.apache.struts.action.ERROR'] }"/>;
																if (hasErrors){
																	url = '<c:url value="/admin/Processo/Taxa/List.do?tipoCodigo=2&acao=getSessao"/>';
																}else{
																	url = '<c:url value="/admin/Processo/Taxa/List.do?tipoCodigo=2&processoCodigo="/>'+codigo;
																}
															
																iFrameTx = document.getElementById("txExtraFrame");
																iFrameTx.src= url;
															}
															reloadTaxasExtras();
														</script>
													</td>
												</tr>
											</table>	  
										</td>
									</tr>
								</table>
											
							</div>
						</td>
	 				</tr>
				</table>
				
				<div class="space">	
					<input name="Submit" class="botao" value="<fmt:message key="lb_btEnviar"/>" type="submit" />
					<input name="botao" class="botao" value="Exportar" type="button" onclick="exportar()" />
					<input name="botao" class="botao" value="Ver Acordos" type="button" onclick="pesquisarAcordos()" />
					<input name="Submit" class="botao" value="Limpar" type="submit" onclick="limpar()" />
					<input name="Submit" value="Voltar" type="submit" onclick="voltar()" class="botao" />
				</div><br/>
			</div>
	 	</html:form>
 	</body>
	<!-- INICIA TELA NA ABA PROCESSO -->
	<script>openAba('processoMenu','processoImg','divProcesso');</script>
	<c:out escapeXml="false" value="${msg}" />
</html:html>