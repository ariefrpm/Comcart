<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top"><h1><bean:message key="text.shopcart"/></h1></div>

<div class="middle">

	<logic:empty name="shoppingCart">

		<bean:message key="text.noitem"/>

	</logic:empty>
	
	<logic:notEmpty name="shoppingCart">
	<form action="<html:rewrite action='/actions/catalog/addToCart'/>" id="cart">
		<table class="cart">
			<tr>
				<th><bean:message key="text.remove"/></th>
				<th><bean:message key="form.image"/></th>
				<th align="left"><bean:message key="form.productName"/></th>
				<th align="right"><bean:message key="form.price"/></th>
				<th align="right"><bean:message key="form.qty"/></th>
				<th align="right"><bean:message key="form.totalPrice"/></th>
			</tr>
				<logic:iterate id="cartItem" name="shoppingCart" property="shoppingCartItems">
					<tr>
						<td align="center">
							<input type="checkbox" name="<bean:write name='cartItem' property='itemId'/>">
						</td>
						
						<td><img src="<html:rewrite page='/images/thumb${cartItem.image}'/>" border="0"></td>
						
						<td>
							<bean:write name="cartItem" property="itemName"/>
						</td>
						<td align="right">
							<bean:write name="cartItem" property="price"/>
						</td>
						<td align="right">
							<input type="text" name="<bean:write name='cartItem' property='itemId'/>"size="3" value="<bean:write name='cartItem' property='numItems'/>">
						</td>
						<td align="right">
							<bean:write name="cartItem" property="totalPrice"/>
						</td>
					</tr>
				</logic:iterate>
				<tr>
					<td></td><td></td><td></td><td></td>
					<td align="right"><b>Sub Total&nbsp;:</b></td>
					<td align="right">${shoppingCart.grandTotal}</td>
				</tr>
		</table>

	<div class="buttons">
	<table>
		<tr>
		<td align="left"><a href="#" onclick="cart.submit();" class="button"><span><bean:message key="text.update"/></span></a></td>
		<td align="center"><a href="<html:rewrite action='/home'/>" class="button"><span><bean:message key="text.continueshop"/></span></a></td>
		<td align="right"><a href="<html:rewrite action='/actions/catalog/checkOut'/>?id=checkout" class="button"><span><bean:message key="text.checkout"/></span></a></td>
		</tr>
	</table>
	</div>
	</form>
	</logic:notEmpty>
</div>
<div class="bottom">&nbsp;</div>