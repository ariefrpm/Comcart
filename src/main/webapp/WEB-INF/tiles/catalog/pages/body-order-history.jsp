<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top"><h1><bean:message key="text.orderhistory"/></h1></div>

<div class="middle">
<logic:iterate id="order" name="orders">

<div style="margin-bottom: 3px;float:left;width=49%;">
<b><bean:message key="text.orderid"/>:</b>&nbsp;#<bean:write name="order" property="orderIdCode"/>
</div>

<div style="margin-bottom: 3px;float:right;width=49%;">
<b><bean:message key="text.status"/>:</b>&nbsp;<bean:write name="order" property="status"/>
</div>

<div class="buttons">
<table style="font-size: 12px;">
<tr>
<td>Added&nbsp;Date:&nbsp;<bean:write name="order" property="orderDate"/></td>
<td><bean:message key="text.customer"/>:&nbsp;<bean:write name="customer" property="firstName"/>&nbsp;
<bean:write name="customer" property="lastName"/></td>
<td rowspan="2"><a href="<html:rewrite action='/actions/catalog/orderHistory'/>?id=
<bean:write name='order' property='orderId'/>" class="button">
<span><bean:message key="text.view"/></span></a></td>
</tr>
<tr>
<td><bean:message key="text.product"/>:&nbsp;<bean:write name="order" property="numProducts"/></td>
<td>Total:&nbsp;<bean:write name="order" property="total"/></td>
</tr>
</table>
</div>

</logic:iterate>

</div>

<div class="bottom">&nbsp;</div>