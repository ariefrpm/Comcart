<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:form action="/actions/admin/categoryForm" styleId="categoryForm" enctype="multipart/form-data">
<div class="heading">
  <h1>Category</h1>
  <div class="buttons">
  <a href="#" onclick="categoryForm.submit()" class="button" style="text-decoration:none">
  	<span class="button_left button_save"></span>
  	<span class="button_middle">Save</span>
  	<span class="button_right"></span>
  </a>
  <a href="<html:rewrite action='/actions/admin/categoryForm'/>?id=cancel" class="button" style="text-decoration:none;">
  	<span class="button_left button_cancel"></span>
  	<span class="button_middle">Cancel</span>
  	<span class="button_right"></span>
  </a>
  </div>
</div>
<div class="tabs"></div>
<div class="page">
<table class="form">	
	<tr>
		<td><span class="required">*</span>Category Name</td>
		<td><html:text property="name"/><html:errors property="name"/></td>		
	</tr>
	<tr>
		<td>Parent Category</td>
		<td>
		<html:select property="parent">
			<option value="0">-- Select --</option>
			<html:options collection="categories" labelName="categories" labelProperty="name" name="categories" property="categoryId"/>
		</html:select>
		</td>
	</tr>
	<tr>
		<td>Image</td>
		<td><html:file property="theFile" /><br/><img src="<html:rewrite page='/images/thumb${imageName}'/>"></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><html:textarea property="description" cols="35" rows="3"/></td>
	</tr>
	<input type="hidden" name="id" value="save"/>
</table>
</div>
</html:form>