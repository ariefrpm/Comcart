<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<form action="<html:rewrite action='/actions/admin/mail'/>" id="mail" method="post">
<logic:notEmpty name="success">
		<div class="success">Success: your message has sent!</div>
</logic:notEmpty>
<div class="heading">	
  <h1>Mail</h1>
  <div class="buttons">
  <a href="#" onclick="mail.submit()" class="button" style="text-decoration:none">
  	<span class="button_left button_send"></span>
  	<span class="button_middle">Send</span>
  	<span class="button_right"></span>
  </a>
  <a href="<html:rewrite action='/actions/admin/mail'/>?cancel=true" class="button" style="text-decoration:none;">
  	<span class="button_left button_cancel"></span>
  	<span class="button_middle">Cancel</span>
  	<span class="button_right"></span>
  </a>
  </div>
</div>
<div class="tabs2"></div>
<div class="page">
<table class="form">	
	<tr>
		<td><span class="required">*</span>To</td>
		<td>
		<select name="to">
		<option value="All Customer">All Customer</option>
		<logic:iterate name="customers" id="customer">
			<option value="${customer.email}">
			${customer.firstName}&nbsp;${customer.lastName}</option>
		</logic:iterate>		
		</select>
		</td>
	</tr>
	<tr>
		<td><span class="required">*</span>Subject</td>
		<td><input type="text" name="subject"/></td>
	</tr>
	<tr>
		<td><span class="required">*</span>Message</td>
	</tr>
</table>
	
<FCK:editor basePath="/view/admin/javascript/fckeditor" instanceName="message" height="500px">
	<jsp:attribute name="value">${message}
	</jsp:attribute>
	<jsp:body>
		<FCK:config SkinPath="skins/silver/"/>
	</jsp:body>
</FCK:editor>			
			
</div>
</form>