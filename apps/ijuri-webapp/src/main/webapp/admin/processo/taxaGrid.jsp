<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>

<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<link href="<c:url value="/nucleo/style/juriweb.css"/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
		</script>
		<script>
				var check=false;
				var tipo;
				function editar(pos,mes,ano){
				      if (!check){
				          url = '<c:url value="/admin/Processo/Taxa/CarregarCadastro.do"/>';
				          url += '?pos='+pos+'&mes='+mes+'&ano='+ano+'&tipoCodigo='+tipo;
						  openPopUp(url, 'taxaForm',445,190);
					  }
					  check=false;
				}	
				
				function novo(){
		            url = '<c:url value="/admin/Processo/Taxa/CarregarCadastro.do"/>';
		            url += '?tipoCodigo='+tipo;
		            openPopUp(url, 'taxaForm',445,190);
				}
				
				function excluir(){
					if (confirm("Deseja excluir as taxas selecionadas definitivamente?")){
						url = '<c:url value="/admin/Processo/Taxa/Remover.do"/>';
						document.forms[0].action  =url;
						document.forms[0].submit();
					}	
				}
				
				function gerarPeriodo(periodoIni,periodoFim){
						url = '<c:url value="/admin/Processo/Taxa/Gerar.do"/>';
						
						document.forms[0].periodoInicio.value = periodoIni;
						document.forms[0].periodoFim.value = periodoFim;				
						
						document.forms[0].action = url;
						document.forms[0].submit();
				}
				
				function cadastrar(mes,ano){
					url = '<c:url value="/admin/Processo/Taxa/Cadastro.do"/>';
						
					document.forms[0].mes.value = mes;
					document.forms[0].ano.value = ano;				
						
					document.forms[0].action  =url;
					document.forms[0].submit();
				}
				
				function atualizar(pos,mes,ano){
					url = '<c:url value="/admin/Processo/Taxa/Cadastro.do"/>';
					
					document.forms[0].pos.value = pos;	
					document.forms[0].mes.value = mes;
					document.forms[0].ano.value = ano;				
						
					document.forms[0].action  =url;
					document.forms[0].submit();
				}	
				
				codigo = '<c:out value="${param.processoCodigo}"/>';
				
				function reloadTaxas(){
				  	url = '<c:url value="/admin/Processo/Taxa/List.do?processoCodigo="/>'+codigo;
		        	document.getElementById("taxaFrame").src= url;
			    }
					  
			    function novasTaxas(){
				     url = '<c:url value="/admin/processo/taxaGerarForm.jsp"/>';
				     openPopUp(url, 'taxa',435,160);
			    }
			    
			    function selecionarCheckTodos(chkTodos,chkName){
			    	if (chkTodos.checked){
			    		marcarTodos(chkName);
			    	}else{
			    		desmarcarTodos(chkName);
			    	}
			    }
			    
			    function marcarTodos(chkName){
			    	checks = window.document.forms[0].elements[chkName];
			    	//se existe algum checkbox
					if (typeof checks != "undefined" ){
						//se existe um único checkbox
						if (typeof checks.length == "undefined" ){
							checks.checked = true;
						}else{
					    	for(i=0;i<checks.length;i++){
					    		checks[i].checked = true;
					    	}
					    }	
			    	}
			    }	
			    
			    function desmarcarTodos(chkName){
			    	checks = window.document.forms[0].elements[chkName];
			    	//se existe algum checkbox
					if (typeof checks != "undefined" ){
						//se existe um único checkbox
						if (typeof checks.length == "undefined" ){
							checks.checked = true;
						}else{
					    	for(i=0;i<checks.length;i++){
					    		checks[i].checked = false;
					    	}
					    }	
			    	}
			    }
				
		</script>	
	</head>

  <html:form method='post' action="/admin/Processo/Taxa/List.do">
  	  <html:hidden property="taxas"/>	
  	  <html:hidden property="periodoInicio"/>	
  	  <html:hidden property="periodoFim"/>	
  	  <html:hidden property="mes"/>	
  	  <html:hidden property="ano"/>	
  	  <html:hidden property="tipoCodigo"/>	
  	  <input type="hidden" name="pos"> 
  	  <div style="background-color:#E5E5E5;heigth:100%" >
  	  <table border="0" height="100%">
          <tr>
			<td colspan="4" valign="top">
			<fieldset style="width:230px;height:100%">
			  	<legend>
					<table>
						 <tr>
							 <td><LABEL>
							 	  <c:choose>	
							 	  	<c:when test='${requestScope.TaxaForm.tipoCodigo eq "1"}'>
							 	  		Taxas Comuns
							 	  		  <script>
							 	  		    tipo = 1;
										  	parent.document.forms[0].taxasComunsCode.value = document.forms[0].taxas.value;
										  </script>
							 	  	</c:when>
							 	  	<c:otherwise>
							 	  		Taxas Extras
							 	  		  <script>
										  	tipo = 2;
										  	parent.document.forms[0].taxasExtrasCode.value = document.forms[0].taxas.value;
										  </script>
							 	  	</c:otherwise>
							 	  </c:choose>
							 	  </LABEL></td>
					         <td>
								<c:choose>
									<c:when test="${!empty requestScope.taxas}">
						         		<img style="cursor:hand" src="<c:url value="/img/novo.gif"/>" onclick="novo()" alt="Adicionar um Período da Taxa">
						         		<img style="cursor:hand" src="<c:url value="/img/delete.gif"/>" onclick="excluir()">
						         	</c:when>
						         	<c:otherwise>
						         		<img style="cursor:hand" src="<c:url value="/img/novo.gif"/>" onclick="novasTaxas()" alt="Gerar Período das Taxas">
						         	</c:otherwise>
					         	</c:choose>					         
					        </td>
				         </tr>
					</table>
				</legend>
			    <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
				          <tr>
					 		  <td class="td01" width="80%">Mês/Ano:</td>
			 		   		  <td class="td01" width="13%"><input type="checkbox" name="checkTodos" onclick="selecionarCheckTodos(this,'checks')"/></td>
			 		   		  <td height="7px" width="7%"></td>
					 	  </tr> 
					 	  <pg:pager url="${pageContext.request.requestURL}" 
				  			maxPageItems="10" 
				  			maxIndexPages="10"
				          	export="currentPageNumber=pageNumber"
				          	scope="request"
				          	>
				          <tr>
							<td colspan="3">
								<div id="grid" style="background-color:#E5E5E5;height:160px">
				      	  	  	   <table width="99%" border="0" align="center" cellpadding="2" cellspacing="1">
					          		<c:forEach items="${requestScope.taxas}" var="item" varStatus="status">
						                <tr class="td02" onMouseOver="this.style.backgroundColor='#CCCCCC';" onMouseOut="this.style.backgroundColor='#eeeeee';" onClick="javascript:editar(${status.count-1},'${item.mes}','${item.ano}');" style="cursor:hand">
							                  <td width="95%"><fmt:formatDate value="${item.mesAno}" pattern="MMMM/yyyy" /></td>
				        			          <td width="5%" onclick="check=true;"><input type="checkbox" name="checks" value="${status.count-1}"></td>
				                		</tr>
							  		</c:forEach> 
							  		</table>
          					  </div>
				          	</td>	
				          </tr>	
											          
						<tr>
						    <td colspan="6" class="td02" align="center"> 
						    	<c:import url="/admin/navegador.jsp" />
						   </td>
					    </tr>
				    </pg:pager>	
			      </table>
			  </fieldset>
			 </td>
		</tr>
	  </table>
	  </div>
  </html:form>
</html:html>
