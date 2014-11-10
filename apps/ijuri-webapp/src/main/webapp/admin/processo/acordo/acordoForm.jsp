<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<html:html>
	<head>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="-1">
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/string.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/js/abaAcordo.js"/>"></script>
		<script>
			var imgAjax = '<c:url value="/img/ajax/spinner.gif"/>';
			
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
			
			function atualizarProcesso(codigo,numero,valorCausa,honorario){
				document.forms[0].processoCodigo.value = codigo;
				document.forms[0].valor.value = valorCausa;
				
				reloadTaxasComuns();
				setTimeout('reloadTaxasExtras();',2000);
			}
			
			function desabilitarCampos(){
				codigoAcordo = '${requestScope.AcordoForm.codigo}'; 
				if (codigoAcordo.trim() != ""){ 
					document.forms[0].quantidadeParcelas.readOnly = true;
					document.forms[0].dataVencimento.readOnly = true;
					//representa o hidden
					document.forms[0].regimeMultaCodigo[0].disabled = false;
					//representa o combo
					document.forms[0].regimeMultaCodigo[1].disabled = true;
					document.forms[0].valorIndiceMulta.readOnly = true;
					document.forms[0].valorClausulaPenalMes.readOnly = true;
					document.forms[0].valorJurosMes.readOnly = true;
				}else{
					//representa o hidden
					document.forms[0].regimeMultaCodigo[0].disabled = true;
				}	
			}
			
			function focalizaCampoNaoEditavel(nomeCampo){
				codigoAcordo = '${requestScope.AcordoForm.codigo}'; 
				if (codigoAcordo.trim() != ""){
					alert("O campo '"+nomeCampo+"' não pode ser alterado, pois o Acordo Judicial já foi cadastrado."); 
				} 
			}
			
			function editarParcelas(){
				if (document.forms[0].codigo.value.trim() != ""){
					url	= '<c:url value="/admin/Processo/Acordo/Parcela/CarregarCadastro.do"/>';
					url += '?acordoCodigo='+document.forms[0].codigo.value;
					url += '&urlVoltar=<c:url value="/admin/Processo/Acordo/CarregarCadastro.do?codigo="/>';
					url += document.forms[0].codigo.value;
					location.href = url;
				}else{
					alert('Para editar parcelas, é necessário primeiro cadastrar um Acordo Judicial.');
				}
			}
			
			function editarProcesso(){
				if (document.forms[0].codigo.value.trim() != ""){
					url	= '<c:url value="/admin/Processo/CarregarCadastro.do"/>';
					url += '?acao=find&codigo='+document.forms[0].processoCodigo.value;
					url += '&urlVoltar=<c:url value="/admin/Processo/Acordo/CarregarCadastro.do?codigo="/>';
					url += document.forms[0].codigo.value;
					location.href = url;
				}else{
					alert('Para editar o processo, é necessário primeiro cadastrar um Acordo Judicial.');
				}
			}
			
			function habilitarRegimeMulta(){
				if (document.forms[0].regimeMultaCodigo[1].value == "1"){
				
					document.getElementById("tdMulta").style.display = 'block';
					document.getElementById("tdClausulaPenal").style.display = 'none';		
				
				}else if (document.forms[0].regimeMultaCodigo[1].value == "2"){
				
					document.getElementById("tdMulta").style.display = 'none';
					document.getElementById("tdClausulaPenal").style.display = 'block';
				
				}else{
		
					document.getElementById("tdMulta").style.display = 'none';
					document.getElementById("tdClausulaPenal").style.display = 'none';
				}
			}
			
			//Seta as variáveis de imagens da aba, usadas no abaParcelaAcordo.js 
			var urlImageAbaInativa = "<c:url value="/img/aba/aba-right-inativa.gif"/>";
			var urlImageAbaAtiva = "<c:url value="/img/aba/aba-right-ativa.gif"/>";
			
			function reloadTaxasExtras(){
				url = '<c:url value="/admin/Processo/Acordo/Taxa/List.do?tipoCodigo=2&processoCodigo="/>'+document.forms[0].processoCodigo.value;
				
				ajaxReqTxExtra = new AjaxRequest(url,"txExtraFrame",imgAjax);
				ajaxReqTxExtra.atualizarDivViaGet();
			}
			
			function reloadTaxasComuns(){
				url = '<c:url value="/admin/Processo/Acordo/Taxa/List.do?tipoCodigo=1&processoCodigo="/>'+document.forms[0].processoCodigo.value;
				
				ajaxReqTxComun = new AjaxRequest(url,"txComunFrame",imgAjax);
				ajaxReqTxComun.atualizarDivViaGet();
			}
		</script> 
	</head>
	
	<body>
		<html:form action="/admin/Processo/Acordo/Cadastro" method="post">
			<input type="hidden" name="acao" value='U'>
			<html:hidden property="codigo" />
			<html:hidden property="regimeMultaCodigo"/>
			<html:hidden property="processoCodigo" />
			<html:hidden property="valor" />
			<div id="ConteudoSistema">
				<h1><span><fmt:message key="lb_tituloAcordo" /></span></h1>
				<fieldset>
					<legend><fmt:message key="lb_tituloAcordo" /></legend>
					<p><html:errors/></p>
				  
					<label><fmt:message key="lb_numParcelas"/>:</label>
					<input type="text" class="sist_input" onfocus="focalizaCampoNaoEditavel('<fmt:message key="lb_numParcelas"/>')" 
						name="quantidadeParcelas" value="${requestScope.AcordoForm.quantidadeParcelas}"  style="width:190px"/><br/>
					
					<label><fmt:message key="lb_primDtVencimento"/>:</label>
					<input type="text" class="sist_input" onkeypress="return formataData(this,event)" 
						onfocus="focalizaCampoNaoEditavel('<fmt:message key="lb_primDtVencimento"/>')" 
						name="dataVencimento" value="${requestScope.AcordoForm.dataVencimento}"  style="width:190px" /><br/>
					
					<span onclick="focalizaCampoNaoEditavel('<fmt:message key="lb_jurosMes"/>')">
					<label><fmt:message key="lb_jurosMes"/>:</label>
					<html:text styleClass="sist_input" onkeydown='formataValor(this, 5)' property="valorJurosMes" style="width:190px" />
					</span>
					
					<label><fmt:message key="lb_indiceReajuste"/>:</label>
					<html:select property="indiceReajusteCodigo" styleClass="sist_input" style="width:190px" onchange="habilitarRegimeMulta()">
						<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
						<html:options 	collection="indices" labelProperty="nome"property="codigo"/>		
					</html:select><br/>
					
					<span onclick="focalizaCampoNaoEditavel('<fmt:message key="lb_regimeMulta"/>')">	    
						<label><fmt:message key="lb_regimeMulta"/>:</label>
						<html:select property="regimeMultaCodigo" styleClass="sist_input" style="width:190px" onchange="habilitarRegimeMulta()">
							<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
							<html:options collection="regimes" labelProperty="descricao" property="codigo"/>		
						</html:select>
						<span id="tdMulta" style="display:none">
							<html:text styleClass="sist_input" property="valorIndiceMulta" onkeydown='formataValor(this, 7)'/>
						</span>   
						<span id="tdClausulaPenal" style="display:none">
							<html:text styleClass="sist_input" property="valorClausulaPenalMes" onkeydown='formataValor(this, 7)' />
						</span> 									
					</span><br/>		
					
					<label><fmt:message key="lb_observacao"/>:</label>
					<html:textarea rows="5" styleClass="sist_input" property="observacao"  style="width:330px" /><br/>
				</fieldset><br/>
				
				<div class="space">
					<input name="Submit" class="login_botao_sist" value="<fmt:message key="lb_btEnviar"/>" type="submit">
					<input name="botao" class="login_botao_sist" value="Editar Parcelas" type="button" onclick="editarParcelas()">
					<input name="botao" class="login_botao_sist" value="Editar Processo" type="button" onclick="editarProcesso()">
					<input name="Submit" class="login_botao_sist" value="Limpar" type="submit" onclick="limpar()">
					<input name="Submit" class="login_botao_sist" value="Voltar" type="submit" onclick="voltar()">
				</div> <br/>
				
				<fieldset>
					<legend>Processos</legend>
        			<br/>
			        <IFRAME style="width:96%;height=120px"
			        	src="<c:url value="/admin/Processo/Popup/CarregarFrame.do?codigo="/>${requestScope.AcordoForm.processoCodigo}" 
			        	frameborder="0" marginheight="0" marginwidth="0" scrolling="no">
			        </IFRAME><br/>
					
					<table align="center" border="0" cellspacing="1" cellpadding="2" style="width: 100%; !important">
						<tr>
							<td >
								<div id="txComunFrame" style="width:150px;height:100px">
								</div>
							</td>
							<td>
								<div id="txExtraFrame" style="width:150px;height:100px">
						  		</div>
							</td>
						</tr>
					</table>
				</fieldset>
				
			</div>
		</html:form>
	</body> 
	<c:out escapeXml="false" value="${msg}" />
	<!-- SCRIPT DE INCIALIZAÇÃO DE COMPONENTES DA TELA -->
	<script>
		//openAba('acordoMenu','acordoImg','divAcordo');	
		desabilitarCampos();
		habilitarRegimeMulta();
	</script>	
</html:html>