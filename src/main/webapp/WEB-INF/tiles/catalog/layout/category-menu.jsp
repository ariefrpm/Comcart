<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<body>
<div class="box">
<div class="top"><img src="<html:rewrite page='/view/catalog/images/icon_category.png'/>" alt="" />
<bean:message key="text.category"/></div>
<div id="category" class="middle">
	<logic:iterate id="category" name="categories">
		${category}
	</logic:iterate>
</div>
<div class="bottom">&nbsp;</div>
</div>
</body>
</html>