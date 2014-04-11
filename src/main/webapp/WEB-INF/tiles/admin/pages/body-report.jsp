<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<pg:pager url="report.do" maxIndexPages="25" maxPageItems="12" export="currentPageNumber=pageNumber">
<pg:param name="pg"/>
<pg:param name="q"/>
  		
<logic:notEmpty name="viewedProducts">
<div class="heading">
  <h1>Products Viewed Report</h1> 
</div>

<table class="list">
    <thead>
      <tr> 
        <td>Product Name</td>
        <td class="left">Price</td>
        <td class="right">Viewed</td>
      </tr>
    </thead>
    <tbody>   	
  		
    	<input type="hidden" name="id" value="delete">
    	<% String cla="odd"; %>
    	<logic:iterate id="view" name="viewedProducts">
    	<% cla=(cla.equals("even")?"odd":"even"); %>
    	<tr class="<%= cla %>">
    		<pg:item>
    		<td><bean:write name='view' property='name'/></td>
    		<td class="left">$<bean:write name='view' property='price'/></td>
    		<td class="right"><bean:write name='view' property='viewed'/></td>
    		</pg:item>
    	</tr>
    	</logic:iterate>
    </tbody>
</table>
</form>
</logic:notEmpty>

<logic:notEmpty name="orderProducts">
<div class="heading">
  <h1>Products Purchased Report</h1>
</div>

<table class="list">
    <thead>
      <tr> 
        <td>Product Name</td>
        <td class="left">Price</td>
        <td class="right">Num Ordered</td>
      </tr>
    </thead>
    <tbody>  		
    	<input type="hidden" name="id" value="delete">
    	<% String cla="odd"; %>
    	<logic:iterate id="order" name="orderProducts">
    	<% cla=(cla.equals("even")?"odd":"even"); %>
    	<tr class="<%= cla %>">
    		<pg:item>
    		<td><bean:write name='order' property='name'/></td>
    		<td class="left"><bean:write name='order' property='price'/></td>
    		<td class="right"><bean:write name='order' property='quantity'/></td>
    		</pg:item>
    	</tr>
    	</logic:iterate>    	
    </tbody>
</table>
</form>
</logic:notEmpty>


<div style="background: #F8F8F8; margin-bottom: 10px; clear: both; padding: 5px;">
<table style="font-size:12px;" width="100%">
<tr><td align="right">
  <pg:index>
    <font face=Helvetica size=-1 color=ActiveCaption>Result Pages:
    <pg:prev>&nbsp;<a href="<%= pageUrl %>&id=<%=request.getParameter("id")%>" style="text-decoration:none">[&lt;&lt; Prev]</a></pg:prev>
    <pg:pages><%
      if (pageNumber.intValue() < 10) {
        %>&nbsp;<%
      }
      if (pageNumber == currentPageNumber) {
        %><b><%= pageNumber %></b><%
      } else {
        %><a href="<%= pageUrl %>&id=<%=request.getParameter("id")%>" style="text-decoration:none"><%= pageNumber %></a><%
      }
    %>
    </pg:pages>
    <pg:next>&nbsp;<a href="<%= pageUrl %>&id=<%=request.getParameter("id")%>" style="text-decoration:none">[Next &gt;&gt;]</a></pg:next>
    <br></font>
  </pg:index>
</pg:pager>
</td></tr>
</table>
</div>
