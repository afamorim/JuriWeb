<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@page pageEncoding="ISO-8859-1"%>
<html:html>
	<head>
		<title><fmt:message key="lb_titulo"/></title>
		<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>"></script>    
		<script language="JavaScript" type="text/JavaScript">
		function transporteItem(formulario,sentido){
			if (sentido == 1){
				var lista1 = formulario.elements['juizoCodigosEsquerda']; // permissoes
				var lista2 = formulario.elements['juizoCodigosDireita']; // permiset
			}else if (sentido == 2){
				var lista1 = formulario.elements['juizoCodigosDireita'];
				var lista2 = formulario.elements['juizoCodigosEsquerda'];
			}
			
			for(i = 0;i < lista1.length;){
				if ( lista1.options[i].selected ){
					var item = new Option(lista1.options[i].text,lista1.options[i].value, false, false);
					lista2.options[lista2.length] = item;
					lista1.options[i] = null;
					if ( i > 0 ) i--;
				}else{
					i++;
				}
			}
		}
		
		function selecionarCategorias(f){
			var lista = f.elements['juizoCodigosDireita'];
			var cont;
			cont=0;
			for(i =0;i < lista.length;i++){
				if (lista.options[i].value != 0){
					lista.options[i].selected = true;
					cont++;
				}
			}
			if (cont == 0){
				alert("Não selecionou nenhuma Juizo!");
				return false;
			}
			else{
				return true;
			}
		}
		
		function voltar(){
			location.href = '<c:url value="/admin/Processo/TipoAndamento/CarregarPesquisa.do" />';
		}
		//-->
		</script>
	</head>
	<body>
		<html:form action="/admin/Processo/TipoAndamento/Salvar" method="post">
			<input type="hidden" name="acao" value='U'>
		  	<html:hidden property="codigo" />
			<div id="ConteudoSistema">    
				<h1><span><fmt:message key="lb_tituloTipoAndamento" /></span></h1>
				
				<fieldset>
					<legend><fmt:message key="lb_tituloTipoAndamento" /></legend>
					<p><html:errors/></p>
					
					<label><fmt:message key="lb_descricao" />:</label>
					<html:text property="descricao" maxlength="50" size="80" styleClass="sist_input"/><br/>
				
					<table border="0" cellspacing="1" cellpadding="2" >
						<tr>
							<td colspan="2"><label><fmt:message key="lb_juizosNS" />:</label></td>
							<td><label><fmt:message key="lb_juizosS" />:</label></td>
						</tr>
						<tr>
							<td>
								<html:select property="juizoCodigosEsquerda" size="5" multiple="true" style="width:225px">
									<html:options collection="collNJuizo" labelProperty="descricao" property="codigo" />
								</html:select>
							</td>
							<td class="articlecontent">
								<input name="adicionar" type="button" class="botao" value="&gt;&gt;" onclick="transporteItem(window.document.forms['0'], 1)"><br><br>
								<input name="remover" type="button" class="botao" value="&lt;&lt;" onclick="transporteItem(window.document.forms['0'], 2);">
							</td>
							<td>
								<html:select  property="juizoCodigosDireita" size="5" multiple="true" style="width:235px">
									<html:options collection="collJuizo" labelProperty="descricao" property="codigo" />
								</html:select>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="space">
					<input name="Submit" type="submit" class="botao" value="<fmt:message key="lb_btEnviar"/>" 
						onclick="selecionarCategorias(this.form)"/>
					<input name="Submit" type="submit" class="botao" value="Limpar" onclick="limpar()"/>
					<input name="mit" type="button" class="botao" value="Voltar" onclick="voltar()"/>
				</div>
			</div>
		</html:form>
	</body>
</html:html>
