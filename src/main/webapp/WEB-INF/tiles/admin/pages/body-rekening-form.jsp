<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:form action="/actions/admin/rekeningForm" styleId="rekeningForm" enctype="multipart/form-data">
<div class="heading">
  <h1>Rekening</h1>
  <div class="buttons">
  <a href="#" onclick="rekeningForm.submit()" class="button" style="text-decoration:none">
  	<span class="button_left button_save"></span>
  	<span class="button_middle">Save</span>
  	<span class="button_right"></span>
  </a>
  <a href="<html:rewrite action='/actions/admin/rekeningForm'/>?id=cancel" class="button" style="text-decoration:none;">
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
		<td><span class="required">*</span>Bank Name</td>
		<td><html:text property="bank"/><html:errors property="bank"/></td>		
	</tr>
	<tr>
		<td>Rekening Number</td>
		<td><html:text property="rekening"/><html:errors property="rekening"/></td>		
	</tr>
	<tr>
		<td>Account</td>
		<td><html:text property="account"/><html:errors property="account"/></td>
	</tr>
	<tr>
		<td>Image</td>
		<td><html:file property="theFile" /><br/><img src="<html:rewrite page='/images/thumb${imageName}'/>"></td>
	</tr>	
	<input type="hidden" name="id" value="save"/>
</table>
</div>
</html:form>