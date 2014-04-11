<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="<html:rewrite page='/view/admin/stylesheet/layout.css'/>" />
	<script type="text/javascript" src="<html:rewrite page='/view/admin/javascript/jquery/jquery-1.3.2.min.js'/>"></script>	
	<script type="text/javascript" src="<html:rewrite page='/view/admin/javascript/jquery/superfish/js/superfish.js'/>"></script>	
	<title><tiles:getAsString name="title"/></title>
</head>
<body>
<div id="header"><tiles:insert attribute="header"/></div>
<div id="menu"><tiles:insert attribute="menu"/></div>
<div id="container">
  <div id="column_left"></div>
  <div id="content">    
    <div class="breadcrumb"></div>
    <tiles:insert attribute="body"/>
  </div>
  <div id="column_right"></div>
</div>
<div id="footer"><tiles:insert attribute="footer"/></div>
</body>
</body>
</html>