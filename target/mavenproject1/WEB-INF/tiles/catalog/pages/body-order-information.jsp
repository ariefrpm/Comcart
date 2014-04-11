<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="top"><h1><bean:message key="text.orderinformation"/></h1></div>

<div class="middle">
	<logic:notEmpty name="failed">
		<div class="warning">${failed}</div>
	</logic:notEmpty>
    <div class="buttons">
	<table width="100%" style="font-size : 12px;">
		<tr><td><h3><bean:message key="text.deliveryaddress"/></h3></td><td><h3><bean:message key="text.prefferedbank"/></h3></td></tr>
		<tr>
		<td><bean:write name="deliveryAddress" property="firstName"/>
		&nbsp;<bean:write name="deliveryAddress" property="lastName"/></td>
		<td><bean:write name="rekening" property="bank"/></td>
		</tr>
		<tr>
		<td><bean:write name="deliveryAddress" property="address"/></td>
		<td>Rek.&nbsp;<bean:write name="rekening" property="rekening"/></td>
		</tr>
		<tr>
		<td><bean:write name="deliveryAddress" property="city"/></td>
		<td><bean:message key="text.account"/>&nbsp;<bean:write name="rekening" property="account"/></td>
		</tr>
		<logic:notEmpty name="deliveryAddress" property="region">
			<tr><td><bean:write name="deliveryAddress" property="region"/></td></tr>
		</logic:notEmpty>
		<logic:notEmpty name="deliveryAddress" property="country">
			<tr><td><bean:write name="deliveryAddress" property="country"/></td></tr>
		</logic:notEmpty>
		<td></td>
		<td align="right"><a href="<html:rewrite page='/view/catalog/pages/delivery-information.jsp'/>" class="button">
		<span><bean:message key="text.change"/></span></a></td>		
		</tr>
	</table>	
	</div>
	
	<div class="buttons">
	<table width="100%" style="font-size : 12px;">
		<tr>		
		<td align="left"><h3><bean:message key="form.productName"/></h3></td>
		<td align="right"><h3><bean:message key="form.price"/></h3></td>
		<td align="right"><h3><bean:message key="form.qty"/></h3></td>
		<td align="right"><h3><bean:message key="form.totalPrice"/></h3></td>
		</tr>
		<logic:iterate id="cartItem" name="shoppingCart" property="shoppingCartItems">
		<tr>		
			<td>
			<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
			<bean:write name='cartItem' property='itemId'/>">
			<bean:write name="cartItem" property="itemName"/>
			</a>
			</td>
			<td align="right"><bean:write name="cartItem" property="price"/></td>
			<td align="right"><bean:write name='cartItem' property='numItems'/></td>
			<td align="right"><bean:write name="cartItem" property="totalPrice"/></td>
		</tr>
		</logic:iterate>		
	</table>
	<br />
    <div style="width: 100%; display: inline-block;">
      <table style="float: right; display: inline-block; font-size:12px;">
        <tr>			
			<td align="right"><b>Grand Total&nbsp;:</b></td>
			<td align="right">${shoppingCart.grandTotal}</td>
		</tr>
      </table>
      <br />
    </div>
	</div>
	
	<logic:notEmpty name="comment">
	
    <div class="buttons">
    <table width="100%" style="font-size : 12px;">
    <tr><td><h3><bean:message key="text.comment"/></h3></td></tr>
    <tr><td>${comment}</td></tr>
    </table>      
    </div>
    </logic:notEmpty>
	
	<div class="buttons">
	<table style="font-size:12px;">
		<tr>		
		<td align="right">
		<form action="<html:rewrite action='/actions/catalog/checkOut'/>" id="checkout">
		<input type="checkbox" name="agree" value="yes">		
		<bean:message key="text.iaggreewith"/> <a href="<html:rewrite page='/view/catalog/pages/terms.jsp'/>" target="_blank"><bean:message key="text.terms"/>
		<input type="hidden" name="id" value="order">
		<a href="#" onclick="checkout.submit()" class="button">
		<span><bean:message key="text.order"/></span></a></td>
		</form>
		</tr>
	</table>
	</div>
	
</div>

<div class="bottom">&nbsp;</div>