<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<div class="heading">
  <h1>Confirmed Ordre</h1>
</div>

<table class="list">
    <thead>
      <tr> 
        <td class="left">Order Id</td>
        <td class="left">Transfer Date</td>
        <td class="right">Price</td>
      </tr>
    </thead>
    <tbody>
    	<% String cla="odd"; %>
    	<logic:iterate id="confirmOrder" name="confirmOrders">
    	<% cla=(cla.equals("even")?"odd":"even"); %>
    	<tr class="<%= cla %>">
    		<td class="left">${confirmOrder.orderId}</td>
    		<td class="left">${confirmOrder.transferDate}</td>
    		<td class="right">${confirmOrder.price}</td>
    	</tr>
    	</logic:iterate>   	
    </tbody>
</table>
</form>