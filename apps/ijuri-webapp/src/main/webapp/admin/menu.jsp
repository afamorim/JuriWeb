<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
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

<div id="menu_sist">
	<div id="menu_sist_tit"><a href="javascript://" class="link01" onclick="MM_dmenuh('pessoa'); MM_dmenuh('processo','acordo');MM_dmenuh('correcao');MM_dmenuh('documento'); MM_dmenu('localidade');MM_dmenuh('site');">Localidade</a></div>
	<div id="localidade" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Localidade/Municipio/CarregarPesquisa.do" />" class="link03">Município</a></li>
	  </ul>
	</div>
	<div id="menu_sist_tit"><a href="javascript://" class="link01" onclick="MM_dmenuh('localidade');MM_dmenuh('processo','acordo');MM_dmenuh('correcao');MM_dmenuh('documento');MM_dmenuh('site'); MM_dmenu('pessoa');">Pessoa</a></div>
	<div id="pessoa" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Pessoa/Fisica/CarregarPesquisa.do" />" class="link03">Pessoa Física</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Pessoa/Juridica/CarregarPesquisa.do" />" class="link03">Pessoa Jurídica</a></li>
	  </ul>
	</div>
	<div id="menu_sist_tit">
	<a href="javascript://" class="link01" onclick="MM_dmenuh('pessoa'); MM_dmenuh('localidade','acordo');MM_dmenuh('correcao');MM_dmenuh('documento');MM_dmenuh('site'); MM_dmenu('processo');">Processo</a></div>
	<div id="processo" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/CarregarPesquisa.do" />" class="link03">Processo</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/Orgao/CarregarPesquisa.do" />" class="link03">Orgão Judiciário</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/TipoAndamento/CarregarPesquisa.do" />" class="link03">Tipo de Andamento</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/ClasseProcesso/CarregarPesquisa.do" />" class="link03">Classe Processo</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/Juizo/CarregarPesquisa.do" />" class="link03">Juízo</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/Comarca/CarregarPesquisa.do" />" class="link03">Comarca</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/CarregarClienteListReport.do" />" class="link03">Relatório <br> &nbsp;&nbsp;(Externo)</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/CarregarListReport.do" />" class="link03">Relatório <br> &nbsp;&nbsp;(Interno)</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/processo/agendaAndamentosReport.jsp" />" class="link03">Agenda de <br> &nbsp;&nbsp;Andamentos</a></li>		
	  </ul>
	</div>
	<div id="menu_sist_tit"><a href="javascript://" class="link01" onclick="MM_dmenuh('localidade');MM_dmenuh('processo');MM_dmenuh('correcao'); MM_dmenuh('pessoa');MM_dmenuh('documento');MM_dmenuh('site');MM_dmenu('acordo');">Acordo</a></div>
	<div id="acordo" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Processo/Acordo/List.do" />" class="link03">Acordo</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/processo/acordo/parcelaAcordoClienteListReport.jsp" />" class="link03">Relatório <br> &nbsp;&nbsp;(Externo)</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/processo/acordo/parcelaAcordoListReport.jsp" />" class="link03">Relatório <br> &nbsp;&nbsp;(Interno)</a></li>
	  </ul>
	  
	</div>
	<div id="menu_sist_tit">
	<a href="javascript://" class="link01" onclick="MM_dmenuh('pessoa'); MM_dmenuh('localidade'); MM_dmenuh('processo','acordo');MM_dmenuh('documento');MM_dmenuh('site');MM_dmenu('correcao');">Cálculo</a></div>
	<div id="correcao" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/correcaoMonetaria/correcaoMonetariaForm.jsp" />" class="link03">Correção <br> &nbsp;&nbsp;Monetária</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/correcaoMonetaria/periodoIndicePesquisa.jsp" />" class="link03">Índice <br> &nbsp;&nbsp;de Reajuste</a></li>
	  </ul>
	</div>
	<!--
	div id="menu_sist_tit">
	<a href="javascript://" class="link01" onclick="MM_dmenuh('pessoa'); MM_dmenuh('localidade'); MM_dmenuh('processo','acordo');MM_dmenuh('documento');MM_dmenuh('correcao');MM_dmenu('site');">Site</a></div>
	<div id="site" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Site/Link/CarregarPesquisa.do" />" class="link03">Link</a></li>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/Site/Jurisprudencia/CarregarPesquisa.do" />" class="link03">Jurisprudência</a></li>
	  </ul>
	</div>
	<div id="menu_sist_tit">
	<a href="javascript://" class="link01" onclick="MM_dmenuh('pessoa'); MM_dmenuh('localidade'); MM_dmenuh('processo','acordo','correcao');MM_dmenuh('site');MM_dmenu('documento');">Documento</a></div>
	<div id="documento" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/admin/documento/treeView.jsp" />" class="link03">Documento</a></li>
	  </ul>
	</div
	-->
</div>