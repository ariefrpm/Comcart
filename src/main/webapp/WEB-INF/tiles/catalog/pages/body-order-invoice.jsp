<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top"><h1><bean:message key="text.orderinvoice"/></h1></div>

<div class="middle">

<div class="buttons">
<table style="font-size: 12px;">
<tr>
	<td style="vertical-align: top;"><h3><bean:message key="text.orderid"/></h3></td>
	<td style="vertical-align: top;"><h3><bean:message key="form.email"/></h3></td>
	<td style="vertical-align: top;"><h3><bean:message key="form.phone"/></h3></td>
	<td style="vertical-align: top;"><h3><bean:message key="text.deliveryaddress"/></h3></td>
</tr>
<tr>
	<td style="vertical-align: top;">#<bean:write name="order" property="orderIdCode"/></td>
	
	<td style="vertical-align: top;"><bean:write name="customer" property="email"/></td>
	
	<td style="vertical-align: top;"><bean:write name="customer" property="phone"/></td>

	<td style="vertical-align: top;">	
	<bean:write name="order" property="deliveryFirstName"/>&nbsp;<bean:write name="order" property="deliveryLastName"/><br/>
	<bean:write name="order" property="deliveryAddress"/><br/>
	<bean:write name="order" property="deliveryCity"/>
	<c:if test="${order.deliveryZip>0}" >
		&nbsp;<bean:write name="order" property="deliveryZip"/>
	</c:if>
	<br/>

	<logic:notEmpty name="order" property="deliveryRegion">
		<bean:write name="order" property="deliveryRegion"/>
	</logic:notEmpty>
	<br/>

	<logic:notEmpty name="order" property="deliveryCountry">
		<bean:write name="order" property="deliveryCountry"/>
	</logic:notEmpty>
	<br/>
	</td>
	
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
		<logic:iterate id="product" name="orderProducts">
		<tr>		
			<td>
			<a href="<html:rewrite action='/actions/view'/>?id=
			<bean:write name='product' property='productId'/>">
			<bean:write name="product" property="name"/>
			</a>
			</td>
			<td align="right"><bean:write name="product" property="price"/></td>
			<td align="right"><bean:write name='product' property='quantity'/></td>
			<td align="right"><bean:write name="product" property="totalPrice"/></td>
		</tr>
		</logic:iterate>		
	</table>
	<br />
    <div style="width: 100%; display: inline-block;">
      <table style="float: right; display: inline-block; font-size:12px;">
        <tr>			
			<td align="right"><b>Grand Total&nbsp;:</b></td>
			<td align="right">${order.total}</td>
		</tr>
      </table>
      <br />
    </div>
</div>

<logic:notEmpty name="order" property="comment">
<div class="buttons">
	<table width="100%" style="font-size : 12px;">
		<tr>		
		<td><h3><bean:message key="text.comment"/></h3></td>
		</tr>		
		<tr>
		<td><bean:write name="order" property="comment"/></td>
		</tr>		
	</table>    
</div>
</logic:notEmpty>

<b style="margin-bottom: 3px; display: block;"><bean:message key="text.orderhistory"/></b>
<div class="buttons">
	<table width="100%" style="font-size : 12px;">
		<tr>		
		<td><h3><bean:message key="text.addeddate"/></h3></td>
		<td><h3><bean:message key="text.status"/></h3></td>
		<td><h3><bean:message key="text.comment"/></h3></td>		
		</tr>
		<logic:iterate id="history" name="orderHistory">
		<tr>
		<td><bean:write name="history" property="dateAdded"/></td>		
		<td><bean:write name="history" property="status"/></td>
		<td><bean:write name="history" property="comment"/></td>
		</tr>
		</logic:iterate>
	</table>    
</div>

<div class="buttons">
	<table>
		<tr>		
		<td align="right"><a href="<html:rewrite action='/actions/catalog/orderHistory'/>" class="button"><span><bean:message key="text.back"/></span></a></td>
		</tr>
	</table>    
</div>

</div>

<div class="bottom">&nbsp;</div>