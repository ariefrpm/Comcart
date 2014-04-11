<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
	
<form action="<html:rewrite action='/actions/catalog/login'/>" id="login">
<div class="box">
<div class="top"><img src="<html:rewrite page='/view/catalog/images/icon_login.png'/>" alt="" />
<bean:message key="text.login"/></div>
<div class="middle">
	<label><bean:message key="form.email"/></label><br>
	<input type="text" name="email" size="21"/><br>
	<label><bean:message key="form.password"/></label><br>
	<input type="password" name="password" size="21"/><br>
	
	<div class="spacer"></div>
	<a href="#" onclick="login.submit();" class="button"><span><bean:message key="text.login"/></span></a>
	<a href="<html:rewrite page='/view/catalog/pages/signup.jsp'/>" class="button"><span><bean:message key="text.signup"/></span></a>
</div>
<div class="bottom">&nbsp;</div>
</div>
</form>