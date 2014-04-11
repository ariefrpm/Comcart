<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>

<div class="top">
<h1>${title}</h1>
</div>
<div class="middle">
<logic:notEmpty name="categoryBodies">  
	<table class="list">
	<tr>
	<logic:iterate id="category" name="categoryBodies" indexId="id">
		<% if(id%4==0){ %>
      	</tr><tr>
      	<% } %>      	
		<td width="25%">									
			<a href="<html:rewrite action='/actions/catalog/catalog'/>?catId=
			${category.parentId}_${category.categoryId}" style="text-decoration:none;">
						
			<img src="<html:rewrite page='/images/thumb${category.image}'/>" border="0"/>
						
			</a>
			<br>
			<a href="<html:rewrite action='/actions/catalog/catalog'/>?catId=
			${category.parentId}_${category.categoryId}">
			${category.name}</a>
		</td>
	</logic:iterate>
	</tr>
	</table>
	<div class="sort"></div>
</logic:notEmpty>

  
<logic:notEmpty name="products">
	<pg:pager url="catalog.do" maxIndexPages="25" maxPageItems="12" export="currentPageNumber=pageNumber">
  	<pg:param name="pg"/>
  	<pg:param name="q"/>
	<table class="list">
	<tr>
	<logic:iterate id="product" name="products" indexId="id">
		<% if(id%4==0){ %>
      	</tr><tr>
      	<% } %>	
      	<pg:item>	
		<td width="25%">
			<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
			${product.productId}&catId=${product.catId}" style="text-decoration:none;">
						
			<img src="<html:rewrite page='/images/thumb${product.image}'/>" border="0"/>
						
			</a>
			<br>
			
			<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
			${product.productId}&catId=${product.catId}">
			${product.name}
			</a>					
			<div class="spacer"></div>
			<c:if test="${product.special==0}">
				<span style="color: #900; font-weight: bold;">
					$${product.price}
				</span>
			</c:if>
			<c:if test="${product.special!=0}">
				<strike>$${product.special}</strike>
				<span style="color: #900; font-weight: bold;">
				$${product.price}
				</span>
			</c:if>
		</td>
		</pg:item>
	</logic:iterate>
	</tr>	
	</table>
	<br />
<div class="buttons">
<table style="font-size:12px;">
<tr><td align="right">
  <pg:index>
    <font face=Helvetica size=-1 color=ActiveCaption>Result Pages:
    <pg:prev>&nbsp;<a href="<%= pageUrl %>&catId=<%=request.getParameter("catId")%>" style="text-decoration:none">[&lt;&lt; Prev]</a></pg:prev>
    <pg:pages><%
      if (pageNumber.intValue() < 10) {
        %>&nbsp;<%
      }
      if (pageNumber == currentPageNumber) {
        %><b><%= pageNumber %></b><%
      } else {
        %><a href="<%= pageUrl %>&catId=<%=request.getParameter("catId")%>" style="text-decoration:none"><%= pageNumber %></a><%
      }
    %>
    </pg:pages>
    <pg:next>&nbsp;<a href="<%= pageUrl %>&catId=<%=request.getParameter("catId")%>" style="text-decoration:none">[Next &gt;&gt;]</a></pg:next>
    <br></font>
  </pg:index>
</pg:pager>
</td></tr>
</table>
</div>

</logic:notEmpty>



</div>
<div class="bottom">&nbsp;</div>