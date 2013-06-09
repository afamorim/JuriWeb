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
		<LINK href='<c:url value="/css/painelStyle.css"/>' type="text/css" rel="stylesheet"> 
		<script type="text/javascript" src="<c:url value="/js/abaParcelaAcordo.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/string.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>"></script>
		<script>
			function refresh(){
				document.forms[0].action = '<c:url value="/admin/Processo/CarregarComboOrgaoCadastro.do"/>';
				document.forms[0].acao.value = '';
				document.forms[0].submit();
			}
			
			function voltar(){
				document.forms[0].acao.value = '';
				document.forms[0].action = '<c:url value="/admin/Processo/Acordo/List.do"/>';
			}
			
			function selecionarProcesso(){
				codigoAcordo = '${requestScope.AcordoForm.codigo}'; 
				if (codigoAcordo.trim() == ""){
					return true;
				}else{
					alert("O Processo não pode ser alterado, pois o Acordo Judicial já foi cadastrado.");
					return false;
				}
			}
			
			
			function desabilitarCampos(){
				codigoAcordo = '${requestScope.AcordoForm.codigo}'; 
				if (codigoAcordo.trim() != ""){ 
					document.forms[0].quantidadeParcelas.readOnly = true;
					document.forms[0].dataVencimento.readOnly = true;
					
				}	
			}
			
			function focalizaCampoNaoEditavel(nomeCampo){
				codigoAcordo = '${requestScope.AcordoForm.codigo}'; 
				if (codigoAcordo.trim() != ""){
					alert("O campo '"+nomeCampo+"' não pode ser alterado, pois o Acordo Judicial já foi cadastrado."); 
				} 
			}
			
			function desmarcarTodos(){
				numParcelas = parseInt(document.forms[0].qtdParcelas.value); 
				for(i=0;i<numParcelas;i++){
					nomeLinha = "trParcela"+i;
					document.getElementById(nomeLinha).style.backgroundColor='#EEEEEE';
					nomeCheck = "chkParcela"+i;
					document.getElementById(nomeCheck).checked = false;
				}
			}
			
			var check = false;
			function editarParcela(codigo,pos){
				if (!check){
					desmarcarTodos();
					document.getElementById("trParcela"+pos).style.backgroundColor='#CCCCCC';
					document.getElementById("chkParcela"+pos).checked = true;
					
					document.forms[0].codigo.value = codigo;
					document.forms[0].submit(); 
				}
				check = false;
			}
			
			function selecionarCheckParcela(checkBox,pos){
				nomeLinha = "trParcela"+pos;
				if (checkBox.checked){
					document.getElementById(nomeLinha).style.backgroundColor='#CCCCCC';
				}else{
					document.getElementById(nomeLinha).style.backgroundColor='#eeeeee';
				}
			}
			
			function obterFormaPagamentoPrimParcelaMarcada(){
				numParcelas = parseInt(document.forms[0].qtdParcelas.value); 
				for(i=0;i<numParcelas;i++){
					nomeCheck = "chkParcela"+i;
					if (document.getElementById(nomeCheck).checked){
						return document.getElementById("formaPagamento"+i).value;
					}
						
				}
				
				return 0;
			}
			
			function emitirRecibo(){
				document.forms[0].action = '<c:url value="/admin/Processo/Acordo/EmissaoRecibo"/>?reportType=1&formaPagamentoCodigo=1';
				document.forms[0].target = '_blank';
				
				document.forms[0].submit();
				
				//retorna o estado anterior
				document.forms[0].target = 'parcelaDetalheFrame';
				document.forms[0].action = '<c:url value="/admin/Processo/Acordo/Parcela/CarregarFrame.do"/>';
				
			}
			
			var imgAjax = '<c:url value="/img/ajax/spinner.gif"/>';
			function atualizarGridParcelas(codigoParcelaSelecionada,numero){
				var url = '<c:url value="/admin/Processo/Acordo/Parcela/RefreshGrid.do"/>';
				url += '?acordoCodigo=${requestScope.ParcelaAcordoForm.acordoCodigo}';
				url += '&codigo='+codigoParcelaSelecionada;
				url += '&numero='+numero;
					
				ajaxReq = new AjaxRequest(url,"divGridParcelas",imgAjax);
				ajaxReq.atualizarDivViaGet();
			}
			
			//Seta as variáveis de imagens da aba, usadas no abaParcelaAcordo.js 
			var urlImageAbaInativa = "<c:url value="/img/aba/aba-right-inativa.gif"/>";
			var urlImageAbaAtiva = "<c:url value="/img/aba/aba-right-ativa.gif"/>";
			
		</script>
	</head>

	<body>
		<html:form action="/admin/Processo/Acordo/Parcela/CarregarFrame" method="post">
			<html:hidden property="codigo" />
			<html:hidden property="quantidadeParcelas" />
			<html:hidden property="honorario" />
			<html:hidden property="urlVoltar" />
			<div id="conteudo_sist">
	  			<h1><span><fmt:message key="lb_tituloParcelaAcordo" /></span></h1>
	  			
	  			<fieldset>
	  				<legend><label>Acordo Judicial</label></legend>
					
					<label><fmt:message key="lb_valorTotal"/>:</label>
					<input name="txtValorTotal" style="width:80px" readonly="readonly" type="text" 
						value="${requestScope.ParcelaAcordoForm.valorTotal}" class="sist_input">
					<label><fmt:message key="lb_numParcelas"/>:</label>
					<input name="qtdParcelas" style="width:80px" readonly="readonly" type="text" 
						value="${requestScope.ParcelaAcordoForm.quantidadeParcelas}" class="sist_input">
					
					<label><fmt:message key="lb_valorTotalPago"/>:</label>
					<input style="width:80px" readonly="readonly" type="text" value="${requestScope.ParcelaAcordoForm.valorTotalPago}" 
						class="sist_input">
					<label><fmt:message key="lb_valorTotalEmAberto"/>:</label>
					<input style="width:80px" readonly="readonly" type="text" value="${requestScope.ParcelaAcordoForm.valorTotalEmAberto}" 
					class="sist_input" ><br/>
					
					<html:hidden property="processoCodigo" />
					<IFRAME style="width:96%;height=120px"  
						src="<c:url value="/admin/Processo/Popup/CarregarFrame.do?codigo="/>${requestScope.ParcelaAcordoForm.processoCodigo}" 
						frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></IFRAME>
				 </fieldset>	  
				
				<fieldset style="width:100%;">
					<legend>
						<label>Parcelas</label>
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
														onClick="javascript:editarParcela('${parcela.codigo}','${status.count-1}');" 
															style="cursor:hand;">
														<td width="6%">${parcela.numeroFormatado}</td>
														<td width="26%">
															<fmt:formatDate value="${parcela.dataVencimento}" pattern="dd/MM/yyyy"/>
														</td>
														<td width="25%">
															<fmt:formatNumber value="${parcela.valorVencimento}" pattern="#,##0.00"/>
														</td>
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
														<td width="26%">
															<fmt:formatDate value="${parcela.dataVencimento}" pattern="dd/MM/yyyy"/>
														</td>
														<td width="25%">
															<fmt:formatNumber value="${parcela.valorVencimento}" pattern="#,##0.00"/>
														</td>
														<td width="20%">${parcela.status.descricao}</td>
														<td width="4%" align="center">
															<input name="chkParcela" id="chkParcela${status.count-1}" type="checkbox" 
																checked="checked" value="${parcela.codigo}" 
																onclick="check=true;selecionarCheckParcela(this,'${status.count-1}')">
															<input type="hidden" id="formaPagamento${status.count-1}" 
																value="${parcela.formaPagamento.codigo}"/>
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
			
				<table>
					<tr>
						<td width="580px" height="380px">
							<IFRAME height="100%" width="100%" id="parcelaDetalheFrame" name="parcelaDetalheFrame" frameborder="0" 
								scrolling="no"></IFRAME> 
							<script>
								document.forms[0].target = 'parcelaDetalheFrame';
								document.forms[0].submit();
							</script>
							<html:hidden property="acordoCodigo" />   			
						</td>
					</tr>
				</table>
				<!-- SELECIONA A PARCELA DO GRID -->
			    <script>
			    	location.href = '#linhaSelecionada';
			    	document.forms[0].txtValorTotal.focus();
				</script>
			</div>  
 		</html:form>
		<!-- INICIA TELA NA ABA PARCELAS -->
		<c:out escapeXml="false" value="${msg}" />
	</body>
</html:html>