<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script> 
		<script type="text/javascript" src="<c:url value="/js/string.js"/>"></script> 
		<script language="JavaScript" type="text/JavaScript">
			function novo(){
				location.href = '<c:url value="/admin/Processo/Acordo/CarregarCadastro.do"/>';
			}
		
			var check = false;
			function detalhar(codigo){
				if (!check){
					url = '<c:url value="/admin/Processo/Acordo/CarregarCadastro.do"/>';
					location.href = url+'?codigo='+codigo;
				}
				check = false;
			}
			var tipoParte = '';
			function selecionarPessoa(aTipoParte){
				tipoParte = aTipoParte;
				url = '<c:url value="/admin/Pessoa/List.do"/>';
				openPopUp(url,'pessoaList',640,600);
			}
			
			function atualizarPessoa(codigo,nome){
				if (tipoParte == '1'){
					document.forms[0].elements['clienteCodigo'].value = codigo;
					document.forms[0].elements['clienteNome'].value = nome;		
				}else{
					document.forms[0].elements['devedorCodigo'].value = codigo;
					document.forms[0].elements['devedorNome'].value = nome;		
				}
			}
			
			function excluir(){
				return excluirRegistros('codigos','<c:url value="/admin/Processo/Acordo/Remover.do"/>');
			}
			
			function pesquisar(){
				msg = "";
		
				if (document.forms[0].periodoTaxaComumInicio.value=="mm/aaaa"){
					document.forms[0].periodoTaxaComumInicio.value = '';	
				}else if (!validarMesAnoNaoRequerido(document.forms[0].periodoTaxaComumInicio)){
			       msg += "- O Período inicial que referencia as Taxas Comuns, não foi preenchido ou possui valor inválido. \n";
		    	}     
		    	  
			    if (document.forms[0].periodoTaxaComumFim.value=="mm/aaaa"){
					document.forms[0].periodoTaxaComumFim.value = '';	
				}else if (!validarMesAnoNaoRequerido(document.forms[0].periodoTaxaComumFim)){
			       msg += "- O Período Final que referencia as Taxas Comuns, não foi preenchido ou possui valor inválido. \n";
			    }
			    
			    if (document.forms[0].periodoTaxaExtraInicio.value=="mm/aaaa"){
					document.forms[0].periodoTaxaExtraInicio.value = '';	
				}else if (!validarMesAnoNaoRequerido(document.forms[0].periodoTaxaExtraInicio)){
			       msg += "- O Período inicial que referencia as Taxas Extras, não foi preenchido ou possui valor inválido. \n";
		    	}     
		    	  
			    if (document.forms[0].periodoTaxaExtraFim.value=="mm/aaaa"){
					document.forms[0].periodoTaxaExtraFim.value = '';	
				}else if (!validarMesAnoNaoRequerido(document.forms[0].periodoTaxaExtraFim)){
			       msg += "- O Período Final que referencia as Taxas Extras, não foi preenchido ou possui valor inválido. \n";
			    }
			    
			    if (msg != ""){
			    	alert(msg);
			    	return false;
			    }
			    
			}
			
			function limpar(textBox,hidden){
				document.forms[0].elements[textBox].value = '';
				document.forms[0].elements[hidden].value = '';	
			}
			
			function editarParcelas(){
				checkBoxs = document.forms[0].elements['codigos'];
				codigoAcordo = '';
				//se existe algum checkbox
				if (typeof checkBoxs != "undefined" ){
					//se existe um único checkbox na tela
					if (typeof checkBoxs.length == "undefined" ){
						if (checkBoxs.checked){
								codigoAcordo = checkBoxs.value;
						}else{
							alert("Para editar Parcelas é necessário selecionar um único Acordo Judicial."); 
							return false;
						}
					}else{
						//se existe muitos checkbox
						count = 0;
						for(i=0;i<checkBoxs.length;i++){
							if (checkBoxs[i].checked){
								count++;
								if (count == 1){
									codigoAcordo = checkBoxs[i].value;
								}
							}	
						}
						if (count != 1){
							alert("Para editar Parcelas é necessário selecionar um único Acordo Judicial."); 
							return false;
						}
					}
				}else{
					alert("Para editar Parcelas é necessário selecionar um único Acordo Judicial."); 
					return false;
				}
				
				url	= '<c:url value="/admin/Processo/Acordo/Parcela/CarregarCadastro.do"/>';
				location.href = url+'?acordoCodigo='+codigoAcordo+'&urlVoltar=<c:url value="/admin/Processo/Acordo/List.do"/>';
			}
			
		</script>
	
	</head>
	<body>
		<html:form action="/admin/Processo/Acordo/List" method="post" onsubmit="pesquisar()">
		<html:hidden property="acao" value="F" />
		<html:hidden property="clienteCodigo" />
		<html:hidden property="devedorCodigo" />
			<div id="ConteudoSistema">    
			<h1><span><fmt:message key="lb_pesquisaAcordo"/></span></h1>
			<fieldset>
				<legend><fmt:message key="lb_pesquisaAcordo" /></legend>
				<p><html:errors/></p>
			  
				<label><fmt:message key="lb_cliente"/>:</label>
			    <html:text readonly="true" property="clienteNome" size="40" styleClass="sist_input"/>
			    <span style="valign:bottom;cursor:hand">
				   	<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa(1)" />
				   	<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('clienteNome','clienteCodigo');return false;" />	    	
			    </span><br/>
				
				<label><fmt:message key="lb_devedor"/>:</label>
				<html:text readonly="true" property="devedorNome" size="40" styleClass="sist_input"/>
			    <span style="valign:bottom;cursor:hand">
				   	<img src="<c:url value="/img/lupa.gif"/>" onclick="selecionarPessoa(2)" />
				   	<img src="<c:url value="/img/borracha.gif"/>" onclick="limparCampos('devedorNome','devedorCodigo');return false;" />	    	
			    </span><br/>
			    
			    <label><fmt:message key="lb_numeroProcesso"/>:</label>
			    <html:text property="processoNumero" maxlength="35" size="40" styleClass="sist_input"/><br/>
				
				<label><fmt:message key="lb_taxasComuns"/>:</label>
				<html:text maxlength="7" styleClass="sist_input" property="periodoTaxaComumInicio" style="width:95px"
					onfocus="limparLabel(this,'mm/aaaa')" onblur="setarLabel(this,'mm/aaaa');" onkeyup="maskFormatPeriodo(this)" 
					value="mm/aaaa"/>
				<html:text maxlength="7" styleClass="sist_input" property="periodoTaxaComumFim" style="width:95px" 
					onfocus="limparLabel(this,'mm/aaaa')" onblur="setarLabel(this,'mm/aaaa');" onkeyup="maskFormatPeriodo(this)" 
					value="mm/aaaa"/><br/>
				
				<label><fmt:message key="lb_taxasExtras"/>:</label>
				<html:text maxlength="7" styleClass="sist_input" property="periodoTaxaExtraInicio" style="width:95px" 
					onfocus="limparLabel(this,'mm/aaaa')" onblur="setarLabel(this,'mm/aaaa');" onkeyup="maskFormatPeriodo(this)" 
					value="mm/aaaa"/>
				<html:text maxlength="7" styleClass="sist_input" property="periodoTaxaExtraFim" style="width:95px" 
					onfocus="limparLabel(this,'mm/aaaa')" onblur="setarLabel(this,'mm/aaaa');" onkeyup="maskFormatPeriodo(this)" 
					value="mm/aaaa"/><br />
				
				<input name="button" type="submit" class="login_botao_sist" value="Pesquisar"/>
			</fieldset>
			
			<h1 class="titulo"><fmt:message key="lb_resultado"/></h1>
			
			<table  border="0" cellpadding="2" cellspacing="1">
			<tr class="td02">
			    <td width="45%" class="td01"><fmt:message key="lb_numeroProcesso"/></td>
			    <td width="20%" class="td01"><fmt:message key="lb_valor"/></td>
		 	    <td width="20%" class="td01"><fmt:message key="lb_numParcelas"/></td>
			    <td width="5%" align="center"  class="td01">Excluir</td>
			  </tr>
			  <pg:pager url="${pageContext.request.requestURL}" 
			  			maxPageItems="10" 
			  			maxIndexPages="10"
			          	export="currentPageNumber=pageNumber"
			          	scope="request"
			          	>
			   <c:forEach items="${requestScope.acordos}" var="acordo">
				 <pg:item>
				  <tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC'" onMouseOut="this.style.backgroundColor='#eeeeee'" 
				  	onClick="javascript:detalhar(${acordo.codigo});" style="cursor:hand">
				    <td class="tdLeft">${acordo.processo.numero}</td>
				    <td class="tdLeft"><fmt:formatNumber value="${acordo.valor}" pattern="#,##0.00"/></td>
				    <td class="tdLeft">${acordo.quantidadeParcelas}</td>
			   	    <td align="center"><input onclick="check=true;" type="checkbox" name="codigos" value="${acordo.codigo}"/></td>
				  </tr>
				  </pg:item>	
			</c:forEach>  
		   	<tr>
			    <td colspan="6" class="td02" align="center"> 
			    	  <c:import url="/admin/navegador.jsp" />
			   </td>
		    </tr>
		    </pg:pager>	  
			</table>
			<div class="space">	
				<input onclick="novo()" type="button" class="login_botao_sist" value="Novo" />
				<input name="Submit" onclick="return excluir()" type="submit" class="login_botao_sist" value="Excluir" />
				<input name="botao" class="login_botao_sist" value="Editar Parcelas" type="button" onclick="editarParcelas()">
			</div>
			</div>
		</html:form>
	</body>
</html:html>
