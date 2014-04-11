<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<form action="<html:rewrite action='/actions/admin/rekeningForm'/>" id="rekeningForm">
<logic:notEmpty name="success">
	<div class="success">Success: You have modified Bank Rekening!</div>
</logic:notEmpty>
<div class="heading">
  <h1>Rekening</h1>
  <div class="buttons">
  <a href="<html:rewrite action='/actions/admin/rekeningForm'/>?id=insert" class="button" style="text-decoration:none">
  	<span class="button_left button_insert"></span>
  	<span class="button_middle">Insert</span>
  	<span class="button_right"></span>
  </a>
  <a href="#" onclick="rekeningForm.submit()" class="button" style="text-decoration:none;">
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
        <td class="left">Bank</td>
        <td class="left">Rekening</td>
        <td class="left">Account</td>
        <td class="right">Action</td>
      </tr>
    </thead>
    <tbody>
    	<input type="hidden" name="id" value="delete">
    	<% String cla="odd"; %>
    	<logic:iterate id="rekening" name="rekenings">
    	<% cla=(cla.equals("even")?"odd":"even"); %>
    	<tr class="<%= cla %>">
    		<td>
    		<input type="checkbox" name="delete" 
    		value="<bean:write name='rekening' property='rekeningId'/>"/>
    		</td>
    		<td class="left"><bean:write name='rekening' property='bank'/></td>
    		<td class="left"><bean:write name='rekening' property='rekening'/></td>
    		<td class="left"><bean:write name='rekening' property='account'/></td>
    		<td class="right">[
    		<a href="<html:rewrite action='/actions/admin/rekeningForm'/>?id=edit&rekeningId=<bean:write name='rekening' property='rekeningId'/>">
    		Edit</a>&nbsp;]
    		</td>
    	</tr>
    	</logic:iterate>    	
    </tbody>
</table>
</form>