<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
	<link rel="stylesheet" href="<html:rewrite page='/view/catalog/stylesheet/layout.css'/>" />
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%">
	<tr><td><tiles:insert attribute="cart-menu"/></td></tr>
	<tr><td><tiles:insert attribute="bestsellers"/></td></tr>
</table>
</body>
</html>