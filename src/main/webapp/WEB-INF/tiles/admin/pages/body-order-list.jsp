<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<form action="<html:rewrite action='/actions/admin/orderForm'/>" id="orderForm">
<logic:notEmpty name="success">
	<div class="success">Success: You have modified orders!</div>
</logic:notEmpty>
<logic:notEmpty name="failed">
	<div class="success">Failed!</div>
</logic:notEmpty>
<div class="heading">
  <h1>Orders</h1>
  <div class="buttons">
  <a href="#" onclick="orderForm.submit()" class="button" style="text-decoration:none;">
  	<span class="button_left button_delete"></span>
  	<span class="button_middle">Delete</span>
  	<span class="button_right"></span>
  </a>
  </div>
</div>

<table class="list">
    <thead>
      <tr> 
        <td>Delete</td>
        <td class="left">Order Id</td>
        <td class="left">Customer Name</td>
        <td class="left">Status</td>
        <td class="left">Date Added</td>
        <td class="left">Total</td>
        <td class="right">Action</td>
      </tr>
    </thead>
    <tbody>
    	<pg:pager maxIndexPages="25" maxPageItems="12" export="currentPageNumber=pageNumber">
  		<pg:param name="pg"/>
  		<pg:param name="q"/>
    	<input type="hidden" name="id" value="delete">
    	<% String cla="odd"; %>
    	<logic:iterate id="order" name="orders">
    	<% cla=(cla.equals("even")?"odd":"even"); %>
    	<tr class="<%= cla %>">
    		<pg:item>
    		<td>
    		<input type="checkbox" name="delete" 
    		value="<bean:write name='order' property='orderId'/>"/>
    		</td>
    		<td class="left"><bean:write name='order' property='orderId'/></td>
    		<td class="left"><bean:write name='order' property='customerName'/></td>
    		<td class="left"><bean:write name='order' property='status'/></td>
    		<td class="left"><bean:write name='order' property='orderDate'/></td>
    		<td class="left"><bean:write name='order' property='total'/></td>
    		<td class="right">[
    		<a href="<html:rewrite action='/actions/admin/orderForm'/>?id=edit&orderId=<bean:write name='order' property='orderId'/>">
    		Edit</a>&nbsp;]
    		</td>
    		</pg:item>
    	</tr>
    	</logic:iterate>    	
    </tbody>
</table>
</form>

<div style="background: #F8F8F8; margin-bottom: 10px; clear: both; padding: 5px;">
<table style="font-size:12px;" width="100%">
<tr><td align="right">
  <pg:index>
    <font face=Helvetica size=-1 color=ActiveCaption>Result Pages:
    <pg:prev>&nbsp;<a href="<%= pageUrl %>" style="text-decoration:none">[&lt;&lt; Prev]</a></pg:prev>
    <pg:pages><%
      if (pageNumber.intValue() < 10) {
        %>&nbsp;<%
      }
      if (pageNumber == currentPageNumber) {
        %><b><%= pageNumber %></b><%
      } else {
        %><a href="<%= pageUrl %>" style="text-decoration:none"><%= pageNumber %></a><%
      }
    %>
    </pg:pages>
    <pg:next>&nbsp;<a href="<%= pageUrl %>" style="text-decoration:none">[Next &gt;&gt;]</a></pg:next>
    <br></font>
  </pg:index>
</pg:pager>
</td></tr>
</table>
</div>