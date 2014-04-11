<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" 
		  href="<html:rewrite page='/view/catalog/stylesheet/layout.css'/>"/>
	<script type="text/javascript" src="<html:rewrite page='/view/catalog/javascript/javascript.js'/>">
	</script>
	<title><tiles:getAsString name="title"/></title>
</head>
<body>
<div id="container">
  <div id="header"><tiles:insert attribute="header"/></div>
  <div id="breadcrumb"></div>
  <div id="column_left"><tiles:insert attribute="left-menu"/></div>
  <div id="content"><tiles:insert attribute="body"/></div>
  <div id="column_right"><tiles:insert attribute="right-menu"/></div>  
  <div id="footer"><tiles:insert attribute="footer"/></div>
</div>
</body>
</html>