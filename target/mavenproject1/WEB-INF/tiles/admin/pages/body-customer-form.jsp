<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<div class="heading">
  <h1>Customers</h1>
  <div class="buttons">
  <a href="<html:rewrite action='/actions/admin/customerForm'/>?id=cancel" class="button" style="text-decoration:none;">
  	<span class="button_left button_back"></span>
  	<span class="button_middle">Back</span>
  	<span class="button_right"></span>
  </a>
  </div>
</div>
<div class="tabs2"></div>
<div class="page">
<table class="form">	
	<tr>
		<td>First Name</td>
		<td>${customer.firstName}</td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td>${customer.lastName}</td>
	</tr>
	<tr>
		<td>Email</td>
		<td>${customer.email}</td>
	</tr>
	<tr>
		<td>Telephone</td>
		<td>${customer.phone}</td>
	</tr>
	<tr>
		<td>Password</td>
		<td>${customer.password}</td>
	</tr>
	<tr>
		<td>Date Added</td>
		<td>${customer.dateCreated}</td>
	</tr>
	<tr>
		<td>Num Logins</td>
		<td>${customer.numLogins}</td>
	</tr>
	<tr>
		<td>Last Login</td>
		<td>${customer.lastLogin}</td>
	</tr>	
</table>
</div>