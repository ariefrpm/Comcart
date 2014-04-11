<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="top">
<h1><bean:write name="productDetail" property="name"/></h1>
</div>

<div class="middle">

<table width="100%" style="font-size:12px;">
	<tr>
	<td width="50%">
		<img src="<html:rewrite page='/images/med${productDetail.image}'/>" border="0">
	</td>
	<td width="50%">
		<table style="font-size:12px;">
		<tr>
			<td width="50%"><b><bean:message key="form.price"/>:<b></td>
			<td width="50%">
			<c:if test="${productDetail.special==0}">
				<span style="color: #900; font-weight: bold;">
					$${productDetail.price}
				</span>
			</c:if>
			<c:if test="${productDetail.special!=0}">
				<strike>$${productDetail.special}</strike>
				<span style="color: #900; font-weight: bold;">
				$${productDetail.price}
				</span>
			</c:if>			
			</td>
		</tr>
		<tr>
		<td colspan="2">
		<form action="<html:rewrite action='/actions/catalog/addToCart'/>" id="add">
		<div class="buttons">
			Qty<input type="text" name="quantity" size="3" value="1" />
			<input type="hidden" name="id" value="<bean:write name="productDetail" property="productId"/>" />
        	<a href="#" onclick="add.submit();" class="button">
        		<span><bean:message key="button.add"/></span>
        	</a>
        </div>        
        </form>        
		</td>			
		</tr>
		</table>
	</td>
	</tr>
</table>
<h3><bean:message key="form.description"/>:</h3>
<div class="spacer"></div>
${productDetail.description}
</div>
<div class="bottom">&nbsp;</div>
