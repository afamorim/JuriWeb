<%@ page language="java" %>
<%@page contentType="text/html"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/navigator" prefix="pg" %>
<%@page pageEncoding="ISO-8859-1"%>

<pg:index>
	<script type="text/javascript">
	function paginar(pagerOffset){
		document.forms[0].pagerOffset.value = pagerOffset;
		document.forms[0].submit();
	}
	</script>
	<input type="hidden" name="pagerOffset">  
	<pg:first export="firstPageUrl=pageUrl">
	     <a class="link01" href="${firstPageUrl}">&lt;&lt;</a>  
    </pg:first>
    <pg:prev export="prevPageUrl=pageUrl">
	      &nbsp;<a class="link01" href="${prevPageUrl}">&lt;</a> 
    </pg:prev>
    <pg:pages>
   		<c:if test="${pageNumber < 10}">
	       &nbsp;
	    </c:if>
	    <c:choose>
	    	<c:when test="${pageNumber eq currentPageNumber}">
	    		<label style="color:#990000"><c:out value="${pageNumber}"/></label>
	    	</c:when>
	    	<c:otherwise>
 	    		<a class="link01" href="<c:out value="${pageUrl}"/>"><c:out value="${pageNumber}"/></a> 
	    	</c:otherwise>
	    </c:choose>
	</pg:pages>
	<pg:next export="nextPageUrl=pageUrl">
		&nbsp;<a class="link01" href="${nextPageUrl}">&gt;</a>
	</pg:next>	
	<pg:last export="lastPageUrl=pageUrl">
		&nbsp;<a class="link01" href="${lastPageUrl}">&gt;&gt;</a>
	</pg:last>	
</pg:index>