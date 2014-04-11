<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="top">
<h1><bean:message key="text.changepassword"/></h1></div>
<div class="middle">
<b style="margin-bottom: 3px; display: block;"><bean:message key="text.modifyyourcurrentpasswd"/></b>
<html:form styleId="editPassword">
<table class="tableBox">
<tr><td><span class="required">*</span><bean:message key="text.yourcurrentpasswd"/></td><td>: <html:password property="password"/><html:errors property="password"/></td></tr>
<tr><td><span class="required">*</span><bean:message key="text.yournewpasswd"/></td><td>: <html:password property="newPassword"/><html:errors property="newPassword"/></td></tr>
<tr><td><span class="required">*</span><bean:message key="text.retypeyournewpasswd"/></td><td>: <html:password property="newPassword2"/><html:errors property="newPassword2"/></td></tr>

<html:hidden property="firstName"/>
<html:hidden property="lastName"/>
<html:hidden property="email"/>
<html:hidden property="phone"/>
<html:hidden property="address"/>
<html:hidden property="city"/>
<html:hidden property="password"/>
<input type="hidden" name="action" value="savePassword"/>

</table>

<div class="buttons">
<table>
<tr>
<td><a href="#" onclick="editPassword.submit();" class="button"><span><bean:message key="text.submit"/></span></a>
<a href="<html:rewrite action='/actions/catalog/account'/>?cancel=true" class="button"><span><bean:message key="text.cancel"/></span></a></td>
</tr>
</table>
</div>

</div>
<div class="bottom">&nbsp;</div>
</html:form>