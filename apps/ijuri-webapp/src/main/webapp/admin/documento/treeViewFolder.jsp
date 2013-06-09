<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/tree" prefix="tree" %>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
		 <form name="treeForm" id="treeForm" method="post">
			<input type="hidden" name="tree" value="${requestScope.treeBase64}"/>    	
		 </form>
		<div>
			<c:out value="${msg}" escapeXml="false" />
		</div>
		
		<%-- Generating the Tree HTML --%>
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tree:tree tree="tree.model" node="tree.node" scope="request">
		    <tr><td
		    ><table cellspacing="0" cellpadding="0" border="0">
		    <tr><td><tree:nodeIndent    node="tree.node" indentationType="type"><tree:nodeIndentVerticalLine indentationType="type" ><img src="<c:url value="/img/tree/verticalLine.gif"/>"></tree:nodeIndentVerticalLine><tree:nodeIndentBlankSpace   indentationType="type" ><img src="<c:url value="/img/tree/blankSpace.gif"/>"></tree:nodeIndentBlankSpace></tree:nodeIndent></td>
		    <tree:nodeMatch    node="tree.node" expanded="false" hasChildren="true"  isLastChild="false"><td><a href="javascript:refresh('<c:url value="/admin/Documento/TreeFolder/List.do"/>'+'?expand=<tree:nodeId node="tree.node"/>','tree',document.treeForm.tree.value);"><img src="<c:url value="/img/tree/collapsedMidNode.gif"/>" border="0"></a><img src="<c:url value="/img/tree/closedFolder.gif"/>"></td></tree:nodeMatch>
		    <tree:nodeMatch    node="tree.node" expanded="true"  hasChildren="true"  isLastChild="false"><td><a href="javascript:refresh('<c:url value="/admin/Documento/TreeFolder/List.do"/>'+'?collapse=<tree:nodeId node="tree.node"/>','tree',document.treeForm.tree.value);"><img src="<c:url value="/img/tree/expandedMidNode.gif"/>"  border="0"></a><img src="<c:url value="/img/tree/openFolder.gif"/>"></td></tree:nodeMatch>
		    <tree:nodeMatch    node="tree.node" expanded="false" hasChildren="true"  isLastChild="true" ><td><a href="javascript:refresh('<c:url value="/admin/Documento/TreeFolder/List.do"/>'+'?expand=<tree:nodeId node="tree.node"/>','tree',document.treeForm.tree.value);"><img src="<c:url value="/img/tree/collapsedLastNode.gif"/>"  border="0"></a><img src="<c:url value="/img/tree/closedFolder.gif"/>"></td></tree:nodeMatch>
		    <tree:nodeMatch    node="tree.node" expanded="true"  hasChildren="true"  isLastChild="true" ><td><a href="javascript:refresh('<c:url value="/admin/Documento/TreeFolder/List.do"/>'+'?collapse=<tree:nodeId node="tree.node"/>','tree',document.treeForm.tree.value);"><img src="<c:url value="/img/tree/expandedLastNode.gif"/>" border="0"></a><img src="<c:url value="/img/tree/openFolder.gif"/>"></td></tree:nodeMatch>
		    <tree:nodeMatch    node="tree.node" expanded="false" hasChildren="false" isLastChild="false"><td><img src="<c:url value="/img/tree/noChildrenMidNode.gif"/>"><img src="<c:url value="/img/tree/closedFolder.gif"/>"></td></tree:nodeMatch>
		    <tree:nodeMatch    node="tree.node" expanded="false" hasChildren="false" isLastChild="true" ><td><img src="<c:url value="/img/tree/noChildrenLastNode.gif"/>"><img src="<c:url value="/img/tree/closedFolder.gif"/>"></td></tree:nodeMatch>
		
		    <td valign="middle">
		    <tree:nodeMatch node="tree.node" selected="true">
		   		<a onclick="select(this)" id="nodo<tree:nodeId node="tree.node"/>" class="node" title="<tree:nodeToolTip node="tree.node" />" href="javascript:exibirDetalhes('detail','<tree:nodeId node="tree.node"/>');">
							    		<span style="Font-Size: 10px;"><tree:nodeName node="tree.node"/></span>
	    		</a> 
		    </tree:nodeMatch>
		    <tree:nodeMatch node="tree.node" selected="false">
		    	<a onclick="select(this)" id="nodo<tree:nodeId node="tree.node"/>" class="node" title="<tree:nodeToolTip node="tree.node" />" href="javascript:exibirDetalhes('detail','<tree:nodeId node="tree.node"/>');">
							    		<span style="Font-Size: 10px;"><tree:nodeName node="tree.node"/></span>
	    		</a>
	   		</tree:nodeMatch>
		    </td>
		    </tr>
		    </table></td></tr>
		</tree:tree>
		</table>
