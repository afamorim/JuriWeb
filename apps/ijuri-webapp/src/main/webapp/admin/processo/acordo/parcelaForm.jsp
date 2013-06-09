<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page import="br.com.vortice.ijuri.pagamento.StatusPagamentoVO"%>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:html>

<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>">
	</script>
	<LINK href='<c:url value="/css/painelStyle.css"/>' type="text/css" rel="stylesheet"> 
	<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
	</script>
	<script type="text/javascript" src="<c:url value="/js/string.js"/>">
	</script>
	<script>
		function selecionarPagamentoCheque(){
			document.getElementById("divPagamentoCheque").style.display = "block";
		}
		
		function selecionarPagamentoDinheiro(){
			document.getElementById("divPagamentoCheque").style.display = "none";
		}
		
		function quitarParcela(){	
			if (document.forms[0].statusPagamentoCodigo.value == <%=StatusPagamentoVO.EM_ABERTO%>){
				document.getElementById("divPagamento").style.display = "none";
			}else{
				document.getElementById("divPagamento").style.display = "block";
				
				if (document.forms[0].statusPagamentoCodigo.value == <%=StatusPagamentoVO.PAGO%>){
					if (!document.forms[0].formaPagamentoCodigo[1].checked)
						document.forms[0].formaPagamentoCodigo[0].checked = true;	
				}else if (document.forms[0].statusPagamentoCodigo.value == <%=StatusPagamentoVO.AGUARDANDO_COMPENSACAO_CHEQUE%>){
					document.forms[0].formaPagamentoCodigo[1].checked = true;	
					selecionarPagamentoCheque();
				}
			}	
		}
		
		function selecionaNomeBanco() {
		   var numeroDeBancos = document.forms[0].bancoNumero.options.length;
		   
		   if (document.forms[0].txtBancoNumero.value.length > 2) {
		      var np = false;
		      for (ib = 1; ib < numeroDeBancos; ib++) {
		         if (document.forms[0].txtBancoNumero.value == document.forms[0].bancoNumero.options[ib].value) {
		            document.forms[0].bancoNumero.options[ib].selected = true;
		            ib = numeroDeBancos;
		            np = true;
		         }
		      }
		      if (np == false) {
		        document.forms[0].bancoNumero.options[ib-1].selected = true;
		      } 
		   } else {  
		       document.forms[0].bancoNumero.options[0].selected = true;
		   }
		}
		
		function escreveCodigoBanco() {
		   var bancoSelecionado = document.forms[0].bancoNumero.options.selectedIndex;
		   document.forms[0].txtBancoNumero.value = document.forms[0].bancoNumero.options[bancoSelecionado].value;
		   document.forms[0].numeroAgencia.focus(); 
		}	
		
		function voltarTela(){
			parent.location.href = document.forms[0].urlVoltar.value;
		}
		
		function emitirRecibo(){
			parent.emitirRecibo(document.forms[0].formaPagamentoCodigo[1].checked);
			
		}
		
		var imgAjax = '<c:url value="/img/ajax/spinner.gif"/>';
		function corrigirValores(){
			if (document.forms[0].statusPagamentoCodigo.value != <%=StatusPagamentoVO.EM_ABERTO%>){
				document.forms[0].acao.value = 'CALCULAR';
				url = '<c:url value="/admin/Processo/Acordo/Parcela/CorrigirValores.do"/>';
				url += '?dataPagamento='+document.forms[0].dataPagamento.value;
	
				ajaxReq = new AjaxRequest(url,"divValoresPagamento",imgAjax,document.forms[0]);
			    ajaxReq.atualizarDivViaPost();

			}else{
				alert("Para corrigir valores, é necessário alterar o 'Status' do pagamento da parcela.");
			}	
		}
	</script>
</head>
<div id="conteudo_frame" style="overflow-y:auto;">
	<html:form action="/admin/Processo/Acordo/Parcela/Cadastro" method="post">		
			<html:hidden property="codigo" />
			<html:hidden property="honorario" />
			<html:hidden property="urlVoltar" />
			<html:hidden property="numero" />
			<html:hidden property="acao"/>
			<html:hidden property="processoCodigo" />
			<html:errors />
			<fieldset style="width:100%">
			  	<legend>
					<table>
						 <td><LABEL>Parcela n.º ${requestScope.ParcelaAcordoForm.numero}</LABEL></td>
					</table>
				</legend>
				<table width="98%" align="center" border="0" cellspacing="1" cellpadding="2">
					<tr>
					 	<td><label><fmt:message key="lb_observacao"/>:</label></td>
   						<td colspan="5"><html:textarea rows="2" styleClass="sist_input" property="observacao" style="width:400px"/></td>
					</tr>
					<tr>
						<td><label><fmt:message key="lb_valorParcela"/>:</label></td>
		   				<td><html:text onkeydown='formataValor(this, 10)' styleClass="sist_input" property="valorVencimento" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_dtVencimento"/>:</label></td>
					    <td><html:text maxlength="10" onkeypress="return formataData(this,event)" styleClass="sist_input" property="dataVencimento" style="width:80px"/></td>
					    <td><label><fmt:message key="lb_statusPagamento"/>:</label></td>
					    <td>
					    	<html:select property="statusPagamentoCodigo" style="width:130px" styleClass="sist_input" onchange="quitarParcela();">
								<html:options collection="tiposPagamento" labelProperty="descricao" property="codigo" />	
					    	</html:select>
						</td>
					 </tr>
					 <tr>
					 	<td colspan="6" id="divPagamento">
					 		<DIV id="divValoresPagamento" style="width:100%;height:130px">
						 		<FIELDSET>
							 		<legend><label>Pagamento</label></legend>
							 		<table width="99%" align="center" border="0" cellspacing="1" cellpadding="2">
							 			<tr>
										 	<td height="5px"></td>
										</tr>	
							 			<tr>
							 				<td><label><fmt:message key="lb_valorPago"/>:</label></td>
				   							<td><html:text styleClass="sist_input" onkeydown='formataValor(this, 10)' property="valorPago" style="width:80px"/></td>
										    <td><label><fmt:message key="lb_dtPagamento"/>:</label></td>
										    <td><html:text maxlength="10" onkeypress="return formataData(this,event)" styleClass="sist_input" property="dataPagamento" style="width:80px"/></td>
										    <td><label><fmt:message key="lb_valorHonorario"/>:</label></td>
										    <td><html:text styleClass="sist_input" onkeydown='formataValor(this, 10)' property="valorHonorario" style="width:80px"/></td>
							    		</tr>
							    		<tr>
							 				<td><label><fmt:message key="lb_valorRepasse"/>:</label></td>
				   							<td><html:text styleClass="sist_input" onkeydown='formataValor(this, 10)' property="valorRepasse" style="width:80px"/></td>
										    <td><label><fmt:message key="lb_dtRepasse"/>:</label></td>
										    <td><html:text maxlength="10" onkeypress="return formataData(this,event)" styleClass="sist_input" property="dataRepasse" style="width:80px"/></td>
										    <td><label><fmt:message key="lb_valorCorrecao"/>:</label></td>
				   							<td><html:text styleClass="sist_input" onkeydown='formataValor(this, 10)' property="valorCorrecao" style="width:80px"/></td>
							    		</tr>
							    		<tr>
							 				<td><label><fmt:message key="lb_valorJuros"/>:</label></td>
				   							<td><html:text styleClass="sist_input" onkeydown='formataValor(this, 10)' property="valorJuros" style="width:80px"/></td>
										    <td><label><fmt:message key="lb_valorMulta"/>:</label></td>
										    <td><html:text maxlength="10" onkeypress="return formataData(this,event)" styleClass="sist_input" property="valorMulta" style="width:80px"/></td>
										    <td><label><fmt:message key="lb_valorClausulaPenal"/>:</label></td>
										    <td><html:text maxlength="10" onkeypress="return formataData(this,event)" styleClass="sist_input" property="valorClausulaPenal" style="width:80px"/></td>
							    		</tr>
							    		<tr>
							 				<td colspan="6">
							 					<html:checkbox property="gerarAndamento"> <label><fmt:message key="lb_gerarAndamento"/></label></html:checkbox>	
							 				</td>
				   						</tr>
							    		<tr>
										 	<td height="5px"></td>
										</tr>
							    	</table>
						 		</FIELDSET>
					 		</div>
					 		<TABLE width="100%">
					 		<tr>
				 				<td><label><fmt:message key="lb_formaPagamento"/>:</label>      
		 							       &nbsp;<html:radio onclick="selecionarPagamentoDinheiro()" property="formaPagamentoCodigo" value="1"><span class="radio">Dinheiro</span></html:radio>
				 					       &nbsp;<html:radio onclick="selecionarPagamentoCheque()" property="formaPagamentoCodigo" value="2"><span class="radio">Cheque</span></html:radio>
				 					 
				 				</td>
					 		</tr>	
					 		<tr id="divPagamentoCheque">
					 			<TD colspan="4">
					 				<FIELDSET>
							 		   <legend><label>Pagamento em Cheque</label></legend>
							 		   <TABLE width="95%" align="center">
								 		   	<tr>
								 				<td><label><fmt:message key="lb_banco"/>:</label></td>
					   							<td colspan="3">
					   								<input type="text" name="txtBancoNumero" class="sist_input" value="${requestScope.ParcelaAcordoForm.bancoNumero}" title="Informe o número do banco destinatário ou selecione-o ao lado." alt="Informe o número do banco destinatário ou selecione-o ao lado." size="4" maxlength="3" onKeyup="selecionaNomeBanco()" /> 
					   								&nbsp;&nbsp;
					   								<html:select styleClass="sist_input" property="bancoNumero" style="width:312px" onchange="escreveCodigoBanco();">
					   									<html:option value=""><fmt:message key="lb_comboCadastro" /></html:option>
					   									<html:options 	collection="bancos" 
																		labelProperty="descricao" 
																		property="numeroString"/>	
														<html:option value="999" styleClass="textoMsgErro"> >>> &nbsp;&nbsp;&nbsp; BANCO INEXISTENTE. &nbsp;&nbsp;&nbsp;<<< </html:option>					
					   								</html:select>
					   							</td>
								    		</tr>
								    		<tr>
								 				<td><label><fmt:message key="lb_agencia"/>:</label></td>
					   							<td><html:text styleClass="sist_input" property="numeroAgencia" style="width:100px"/></td>
											    <td><label><fmt:message key="lb_numeroCheque"/>:</label></td>
											    <td><html:text styleClass="sist_input" property="numeroCheque" style="width:100px"/></td>
								    		</tr>
								    		<tr>
								 				<td><label><fmt:message key="lb_dtRecebto"/>:</label></td>
					   							<td><html:text styleClass="sist_input" onkeypress="return formataData(this,event)" property="dataRecebimentoCheque" style="width:100px"/></td>
											    <td><label><fmt:message key="lb_dtCompensacao"/>:</label></td>
											    <td><html:text styleClass="sist_input" onkeypress="return formataData(this,event)" property="dataCompensacaoCheque" style="width:100px"/></td>
								    		</tr>
							 		   </TABLE>	
							 		</FIELDSET> 
					 			</TD>
					 		</tr>
					 		</TABLE>
					 	</td>
					 </tr>
					 <tr>
					 	<td height="5px"></td>
					 </tr>	
				</table>
			</fieldset>
			<table border="0" align="center" width="80%" height="30px">
				<tr>  
				    <td colspan="4" align="center">
					      <input name="Submit" class="login_botao_sist" value="Corrigir Valores" type="button" onclick="corrigirValores()" />
					      <input name="Submit" class="login_botao_sist" value="Confimar" type="submit" onclick="document.forms[0].acao.value='I';" >
					      <input name="Submit" class="login_botao_sist" value="Emitir Recibo" type="button" onclick="emitirRecibo()" >
					      <input name="Submit" class="login_botao_sist" value="Voltar" type="button" onclick="voltarTela()">
					      
				    </td>
				</tr>
			</table>
			<html:hidden property="acordoCodigo" />   
			<!-- SCRIPT INCIAL DA TELA -->
			<script type="text/javascript">
				document.forms[0].honorario.value = parent.document.forms[0].honorario.value;
				document.forms[0].urlVoltar.value = parent.document.forms[0].urlVoltar.value;
				document.forms[0].acordoCodigo.value = parent.document.forms[0].acordoCodigo.value;
				document.forms[0].processoCodigo.value = parent.document.forms[0].processoCodigo.value;
				
				if (document.forms[0].statusPagamentoCodigo.value != <%=StatusPagamentoVO.EM_ABERTO%>){
					document.getElementById("divPagamento").style.display = "block";
					
					//Cheque
					<c:choose>
						<c:when test="${requestScope.ParcelaAcordoForm.formaPagamentoCodigo == 2}">
							document.getElementById("divPagamentoCheque").style.display = "block";
						</c:when>
						<c:otherwise>	
							document.getElementById("divPagamentoCheque").style.display = "none";
						</c:otherwise>		
					</c:choose>
				}else{
						document.getElementById("divPagamento").style.display = "none";
						document.getElementById("divPagamentoCheque").style.display = "none";
				}
				
			</script>
			<!-- FIM DO SCRIPT INCIAL DA TELA -->

			<c:out escapeXml="false" value="${msg}" />
			<c:out escapeXml="false" value="${script}" />
		</html:form>
</div>
</html:html>		

