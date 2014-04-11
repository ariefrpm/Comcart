<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="top">
<h1><bean:message key="text.searching"/></h1>
</div>
<div class="middle">
<h3><bean:message key="text.productsmeetingthesearchcrtria"/></h3>
<logic:empty name="searchProducts">
<div class="buttons">
	<table style="font-size:12px">
		<tr>
		<td align="left">
			<bean:message key="text.thereisnoproduct"/>
		</td>
		</tr>
	</table>
</div>
</logic:empty>
<logic:notEmpty name="searchProducts">	
	<table class="list">
	<tr>
	<logic:iterate id="product" name="searchProducts" indexId="id">		
		<% if(id%4==0){ %>
     	</tr><tr>
      	<% } %>
			<td width="25%">
				<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
				${product.productId}" style="text-decoration:none;">
				<img src="<html:rewrite page='/images/thumb${product.image}'/>" border="0">
				</a>					
				<br>
				<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
				${product.productId}}">
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