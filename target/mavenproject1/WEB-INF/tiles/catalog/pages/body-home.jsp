<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="top"><h1><bean:message key="text.welcome"/></h1></div>

<div class="middle">
<div class="heading"><bean:message key="text.latestproduct"/></div>
<logic:notEmpty name="products">	
	<table class="list">
	<tr>
	<logic:iterate id="product" name="products" indexId="id" length="12">		
		<% if(id%4==0){ %>
     	</tr><tr>
      	<% } %> 
			<td width="25%">			
				<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
				${product.productId}&catId=${product.catId}" style="text-decoration:none;">
				<img src="<html:rewrite page='/images/thumb${product.image}'/>" border="0" class="resize">
				</a>					
				<br>
				<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
				${product.productId}&catId=${product.catId}">
				${product.name}
				</a>					
				<br>					
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
	</logic:iterate>		
	</tr>	
	</table>
</logic:notEmpty>
</div>

<div class="bottom">&nbsp;</div>