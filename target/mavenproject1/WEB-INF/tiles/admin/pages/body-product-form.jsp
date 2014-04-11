<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html:form action="/actions/admin/productForm" styleId="productForms" method="post" enctype="multipart/form-data">
<div class="heading">
  <h1>Products</h1>
  <div class="buttons">
  <a href="#" onclick="productForms.submit()" class="button" style="text-decoration:none">
  	<span class="button_left button_save"></span>
  	<span class="button_middle">Save</span>
  	<span class="button_right"></span>
  </a>
  <a href="<html:rewrite action='/actions/admin/productForm'/>?id=cancel" class="button" style="text-decoration:none;">
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
		<td><span class="required">*</span>Product Name</td>
		<td><html:text property="name"/><html:errors property="name"/></td>
	</tr>
	<tr>
		<td><span class="required">*</span>Category</td>
		<td>
			<html:select property="categoryId">
			<option value="">-- Select --</option>
			<html:options collection="categories" labelName="categories" labelProperty="name" name="categories" property="categoryId"/>
			</html:select>
			<html:errors property="categoryId"/>
		</td>
	</tr>
	<tr>
		<td><span class="required">*</span>Price</td>
		<td><html:text property="price"/><html:errors property="price"/></td>
	</tr>
	<tr>
		<td>Special Price</td>
		<td><html:text property="special"/></td>
	</tr>
	<tr>
		<td>Quantity</td>
		<td><html:text property="quantity" size="5"/></td>
	</tr>
	<tr>
		<td>Status</td>
		<td>		
			<html:select property="status">
				<html:option value="0">Disable</html:option>
				<html:option value="1">Enable</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td>Image</td>
		<td><html:file property="theFile" /><br/><img src="<html:rewrite page='/images/thumb${imageName}'/>"></td>		
	</tr>
	<tr>
		<td>Description</td>		
	</tr>
	<input type="hidden" name="id" value="save"/>
	</table>
<FCK:editor basePath="/view/admin/javascript/fckeditor" instanceName="description" height="500px">
	<jsp:attribute name="value">${description}
	</jsp:attribute>
	<jsp:body>
		<FCK:config SkinPath="skins/silver/"/>
	</jsp:body>
</FCK:editor>			
			
</div>
</html:form>