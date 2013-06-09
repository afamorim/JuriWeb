<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/tree" prefix="tree" %>
<%@ page language="java" contentType="text/html;" pageEncoding="ISO-8859-1"%>
<html>
<title><fmt:message key="lb_titulo"/></title>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta http-equiv="Cache-Control" content="no-store,no-cache, must-revalidate" />
	<meta http-equiv="Cache-Control" content="pre-check=0, post-check=0, max-age=0" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="${now}" />
	
	<link rel="stylesheet" href='<c:url value="/css/estiloTree.css"/>' type="text/css"> 
	<link href='<c:url value="/css/painelStyle.css"/>' type="text/css" rel="stylesheet"> 
	<link href='<c:url value="/css/default.css"/>' rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="<c:url value="/js/ajaxUtil.js"/>">
	</script>
	<script type="text/javascript" src="<c:url value="/js/formUtil.js"/>">
	</script>
	<script>
		var imgAjax = '<c:url value="/img/ajax/spinner.gif"/>';
		
		function refresh(url,id,text){
			body = "tree="+convertBase64ToReqAjax(text);
			body += "&idNodeSelected="+document.getElementById("idNodeSelected").value;
		 	ajaxReq = new AjaxRequest(url,id);
			ajaxReq.atualizarDivViaPost(body,true);
		}
		
		function exibirDetalhes(id,idNodo){
			url = '<c:url value="/admin/Documento/SelecaoList.do"/>?idNodeSelected='+idNodo;
			document.getElementById("idNodeSelected").value = idNodo;
			ajaxReq = new AjaxRequest(url,id,imgAjax);
			ajaxReq.atualizarDivViaGet();
		}
		
		function atualizarInicial(){
			urlIni = '<c:url value="/admin/Documento/TreeFolder/List.do"/>';
			ajaxReqIni = new AjaxRequest(urlIni,'tree',imgAjax);
			ajaxReqIni.atualizarDivViaGet();
		}
		
		function atualizarInicialFrame(){
			urlIni = '<c:url value="/admin/Documento/TreeFolder/List.do"/>';
			document.getElementById("treeFrame").src = urlIni;
		}
		
		function novoDocumento(){
			url = '<c:url value="/admin/Documento/Carregar.do"/>?diretorioCodigo='+document.getElementById("idNodeSelected").value
			ajaxReq = new AjaxRequest(url,'detail',imgAjax);
			ajaxReq.atualizarDivViaGet();
		}
		
		function excluirDoc(id){
			if (confirm("Deseja excluir o documento? ")){
				url = '<c:url value="/admin/Documento/Remover.do"/>?codigo='+id;
				url += '&diretorioCodigo='+document.getElementById("idNodeSelected").value;
				ajaxReq = new AjaxRequest(url,'detail',imgAjax);
				ajaxReq.atualizarDivViaGet();
			}
		}
		
		function paginar(pagerOffset){
			url = '<c:url value="/admin/Documento/List.do"/>?idNodeSelected='+document.getElementById("idNodeSelected").value;
			url += '&pagerOffset='+pagerOffset;
			ajaxReq = new AjaxRequest(url,'detail',imgAjax);
			ajaxReq.atualizarDivViaGet();
		}
		
		var check = false;
		function selecionarDoc(id,nome){
			if (!check){
				window.opener.atualizarDocumento(id,nome);
				window.close();
			}
			check = false;
		}
		
		
		function novaPasta(){
			url = '<c:url value="/admin/Documento/Diretorio/Carregar.do"/>?diretorioPaiCodigo='+document.getElementById("idNodeSelected").value
			ajaxReq = new AjaxRequest(url,'detail',imgAjax);
			ajaxReq.atualizarDivViaGet();
		}
		
		function editarPasta(){
			url = '<c:url value="/admin/Documento/Diretorio/Carregar.do"/>?codigo='+document.getElementById("idNodeSelected").value
			ajaxReq = new AjaxRequest(url,'detail',imgAjax);
			ajaxReq.atualizarDivViaGet();
		}
		
		function excluirPasta(){
			if (confirm("Deseja excluir a pasta? ")){
				url = '<c:url value="/admin/Documento/Diretorio/RemoverSelecao.do"/>?codigo='+document.getElementById("idNodeSelected").value
				location.href = url;
			}
			
		}
		
		function cadastrarPasta(){
			document.forms['DiretorioForm'].action = '<c:url value="/admin/Documento/Diretorio/CadastroSelecao.do"/>';
			document.forms['DiretorioForm'].submit();
		}
		
		function cadastrarDoc(){
			document.forms['DocumentoForm'].action = '<c:url value="/admin/Documento/CadastroSelecao.do"/>';
			document.forms['DocumentoForm'].submit();
		}
		
		function confirmar(url,id,form){
			ajaxReq = new AjaxRequest(url+buildQueryString(form),id,imgAjax);
			ajaxReq.atualizarDivViaGet();
			
			setTimeout("location.reload();",3000);
		}
		
		function select(obj){
			var elements = document.getElementsByTagName('a');
			for (i=0;i<elements.length;i++){
				if (elements[i].id.indexOf('nodo')>=0) 
					elements[i].className = 'node';	
			}

			obj.className='nodeSelected';
		}
		
		setTimeout("atualizarInicial()",400);
	
	</script>
</head>
<body>
	<div id="conteudo_sist" style="width:600px">    
		<h1><fmt:message key="lb_tituloDirDoc" /></h1> 
		<div align="center">
			<p><html:errors /></p>
			<c:out value="${msg}" escapeXml="false" />
		</div>
		<input type="hidden" name="idNodeSelected" id="idNodeSelected" />
		<div id="tree" class="conteudo_div" style="left: 20px; width: 233px; position: absolute; top: 69px; height: 367px; border:1px solid #808080; overflow: auto; border-bottom:none;overflow:"></div>
		<div id="detail" class="conteudo_div" style="left: 253px; width: 367px; position: absolute; top: 69px; height: 401px;border:1px solid #808080;">		</div>
	    <div id="rodapeTree" class="conteudo_div" style="position: absolute;top:436px;left:20px;width:233px;height:34px;border:1px solid #808080;border-top:none;">
	    	<table width="100%">
				  <tr align="center">
				    <td>
					    <input type="button" value="Novo" onclick="novaPasta()" class="login_botao_sist" title="Nova Pasta" />
					    <input type="button" value="Editar" onclick="editarPasta()" class="login_botao_sist" title="Editar nome da Pasta" />
					    <input type="button" value="Excluir" onclick="excluirPasta()" class="login_botao_sist" title="Excluir Pasta"/>
				    </td>
				  </tr>
			  </table>		
	    </div>
  </div> 
  <iframe id="frmDoc" marginheight="0" marginwidth="0" width="0" height="0"></iframe>
</body>
</html>