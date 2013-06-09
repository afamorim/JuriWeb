<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/c" 				prefix="c" %>
<%@ taglib uri="/tags/fmt" 				prefix="fmt" %>
<%@ taglib uri="sitemesh-decorator" 	prefix="decorator" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Sistema JuriWeb :: <decorator:title /></title>
		<link href="<c:url value="/nucleo/style/juriweb.css"/>" rel="stylesheet" type="text/css" />
		<script language="JavaScript">
		<!--
		function MM_findObj(n, d) { //v4.0
		  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
		    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		  if(!x && document.getElementById) x=document.getElementById(n); return x;
		}
		function MM_dmenu(objname) {
		  var obj = MM_findObj(objname);
		  if(obj && obj.style) obj.style.display = (obj.style.display=="none") ? "" : "none";
		}
		function MM_dmenuh() {
		  var a=MM_dmenuh.arguments;
		  for (i=0; i<a.length; i++) {var obj = MM_findObj(a[i]); if(obj && obj.style) obj.style.display = "none"; }
		}
		//-->
		</script>
		<decorator:head />
	</head>
	
	<body>
		<div id="Geral">
		<div class="log">Bem vindo, <strong>${sessionScope.usuario.login}</strong></div>
		<div id="MenuSistema">
		  <ul>
			<li><a href="javascript://" class="link01" onclick= "MM_dmenuh('pessoa','processo','acordo','correcao','documento');MM_dmenu('localidade');">Localidade</a></li>
		     <li id="localidade" style="display:none;" >
		         <ul>
		            <li><a href="<c:url value="/admin/Localidade/Municipio/CarregarPesquisa.do"/>" 
		            	class="link02">Município</a></li>
		         </ul>
		     </li>
		     <li><a href="javascript://" class="link01" onclick= "MM_dmenuh('processo','localidade','acordo','correcao','documento');MM_dmenu('pessoa');">Pessoa</a></li>
		     <li id="pessoa" style="display:none;">
		        <ul>
		          <li><a href="<c:url value="/admin/Pessoa/Fisica/CarregarPesquisa.do"/>" 
		          	class="link02">Pessoa Física</a></li>
		          <li><a href="<c:url value="/admin/Pessoa/Juridica/CarregarPesquisa.do"/>" 
		          	class="link02">Pessoa Jurídica</a></li>
		        </ul>
		     </li>
		     <li><a href="javascript://" class="link01" onclick= "MM_dmenuh('pessoa','localidade','acordo','correcao','documento');MM_dmenu('processo');">Processo</a></li>
			<li id="processo" style="display:none;" >
				<ul>
		            <li><a href="<c:url value="/admin/Processo/CarregarPesquisa.do"/>" class="link02">Processo</a></li>
		            <li><a href="<c:url value="/admin/Processo/Orgao/CarregarPesquisa.do"/>" class="link02">Orgão Judiciário</a></li>
		            <li><a href="<c:url value="/admin/Processo/TipoAndamento/CarregarPesquisa.do"/>" class="link02">Tipo de Andamento</a></li>
		            <li><a href="<c:url value="/admin/Processo/ClasseProcesso/CarregarPesquisa.do"/>" class="link02">Classe Processo</a></li>
		            <li><a href="<c:url value="/admin/Processo/Juizo/CarregarPesquisa.do"/>" class="link02">Juízo</a></li>
		            <li><a href="<c:url value="/admin/Processo/Comarca/CarregarPesquisa.do"/>" class="link02">Comarca</a></li>
		            <li><a href="<c:url value="/admin/Processo/CarregarClienteListReport.do"/>" class="link02">Relatório (Externo)</a></li>
		            <li><a href="<c:url value="/admin/Processo/CarregarListReport.do"/>" class="link02">Relatório (Interno)</a></li>
		            <li><a href="<c:url value="/admin/processo/agendaAndamentosReport.jsp"/>" class="link02">Agenda de Andamentos</a></li>		
				</ul>
		    </li>
		      <li><a href="javascript://" class="link01" onclick= "MM_dmenuh('pessoa','localidade','processo','correcao','documento');MM_dmenu('acordo');">Acordo</a></li>
		      <li id="acordo" style="display:none;">
		         <ul>
		           <li><a href="<c:url value="/admin/Processo/Acordo/List.do"/>" class="link02">Acordo</a></li>
		           <li><a href="<c:url value="/admin/processo/acordo/parcelaAcordoClienteListReport.jsp"/>" class="link02">Relatório (Externo)</a></li>
		           <li><a href="<c:url value="/admin/processo/acordo/parcelaAcordoListReport.jsp"/>" class="link02">Relatório (Interno)</a></li>
		         </ul>
		      </li>
		      <li><a href="javascript://" class="link01" onclick= "MM_dmenuh('pessoa','localidade','acordo','processo','documento');MM_dmenu('correcao');">Cálculo</a></li>
		      <li id="correcao" style="display:none;">
		        <ul>
		          <li><a href="<c:url value="/admin/correcaoMonetaria/correcaoMonetariaForm.jsp"/>" class="link02">Correção Monetária</a></li>
		          <li><a href="<c:url value="/admin/correcaoMonetaria/periodoIndicePesquisa.jsp"/>" class="link02">Índice de Reajuste</a></li>
		        </ul>
		    </li>
		  </ul>
		</div>
			<!-- CONTEUDO -->
			<decorator:body />
			<!-- CONTEUDO -->
		</div>
	</body>
</html>