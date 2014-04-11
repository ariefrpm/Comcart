<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="box">
	<div class="top">
	<img src="<html:rewrite page='/view/catalog/images/icon_basket.png'/>" alt="" /><bean:message key="text.shopcart"/>
	</div>
	<div class="middle">
	<logic:empty name="shoppingCart">
	<bean:message key="text.noitem"/>
	</logic:empty>

	<logic:notEmpty name="shoppingCart">
	
	<table cellpadding="2" cellspacing="0" style="width: 100%;font-size:12px;">
		<logic:iterate id="cartItem" name="shoppingCart" property="shoppingCartItems">
		<tr>
		<td valign="top" align="right" width="1">
		<bean:write name="cartItem" property="numItems"/>&nbsp;x&nbsp;</td>
		<td align="left" valign="top">
		<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=
		<bean:write name='cartItem' property='itemId'/>">
		<bean:write name="cartItem" property="itemName"/>
		</a>
		</td>
		</tr>
		</logic:iterate>
	</table>
	<div class="spacer"></div>
	<div style="text-align: right;">Sub Total&nbsp;:&nbsp;$${shoppingCart.grandTotal}</div>
	<div class="spacer"></div>
	<a href="<html:rewrite page='/view/catalog/pages/cart.jsp'/>" class="button"><span><bean:message key="text.carts"/></span></a>

	</logic:notEmpty>
	</div>
	<div class="bottom">&nbsp;</div>
</div>