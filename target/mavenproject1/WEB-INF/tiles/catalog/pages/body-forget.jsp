<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="top">
<h1><bean:message key="text.forgetyourpasswd"/></h1>
</div>

<div class="middle">
<div style="float: left; display: inline-block; width: 274px;">
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.inputyouremail"/></b>
    <div style="background: #F7F7F7; border: 1px solid #DDDDDD; padding: 10px; min-height: 120px;">
    	
    	<html:form action="/actions/catalog/forget" styleId="forget">
    	<html:errors property="login"/>
    	<br />
    	<b><bean:message key="form.email"/></b><br />
    	<html:text property="email"/><a href="#" onclick="forget.submit();" class="button">
    	<span><bean:message key="text.send"/></span></a>
    	<br /><html:errors property="email"/>    	
    	<html:hidden property="password" value="password"/>
    	</html:form>    	
    </div>
</div>
</div>
<div class="bottom">&nbsp;</div>