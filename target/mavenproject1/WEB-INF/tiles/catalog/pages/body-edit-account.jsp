<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="top">
<h1><bean:message key="text.editaccount"/></h1></div>
<div class="middle">
<b style="margin-bottom: 3px; display: block;"><bean:message key="text.yourpersonal"/></b>
<html:form styleId="edit">
<table class="tableBox">
<tr><td><span class="required">*</span><bean:message key="form.firstName"/></td><td>: <html:text property="firstName"/><html:errors property="firstName"/></td></tr>
<tr><td><span class="required">*</span><bean:message key="form.lastName"/></td><td>: <html:text property="lastName"/><html:errors property="lastName"/></td></tr>
<tr><td><span class="required">*</span><bean:message key="form.email"/></td><td>: <html:text property="email"/><html:errors property="email"/></td></tr>
<tr><td><span class="required">*</span><bean:message key="form.phone"/></td><td>: <html:text property="phone"/><html:errors property="phone"/></td></tr>

<html:hidden property="address"/>
<html:hidden property="city"/>
<html:hidden property="password"/>
<input type="hidden" name="action" value="saveAccount"/>

</table>

<div class="buttons">
<table>
<tr>
<td><a href="#" onclick="edit.submit();" class="button"><span><bean:message key="text.submit"/></span></a>
<a href="<html:rewrite action='/actions/catalog/account'/>?cancel=true" class="button"><span><bean:message key="text.cancel"/></span></a></td>
</tr>
</table>
</div>

</div>
<div class="bottom">&nbsp;</div>
</html:form>