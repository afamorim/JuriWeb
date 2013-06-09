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
	
	<div id="menu_sist_tit">
	<a href="javascript://" class="link01" onclick="MM_dmenuh('pessoa'); MM_dmenuh('localidade','acordo');MM_dmenuh('correcao');MM_dmenuh('documento');MM_dmenuh('site'); MM_dmenu('processo');">Processo</a></div>
	<div id="processo" style="display:none">
	  <ul>
		<li><strong>&raquo;</strong><a href="<c:url value="/adminCliente/Processo/CarregarClienteListReport.do" />" class="link03">Relatório <br> &nbsp;&nbsp;(Externo)</a></li>
	  </ul>
	</div>
</div>