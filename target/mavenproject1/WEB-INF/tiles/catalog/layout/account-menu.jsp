<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="box">
<div class="top"><img src="<html:rewrite page='/view/catalog/images/icon_account.png'/>" alt="" />
<bean:message key="text.account"/></div>
<div id="category" class="middle">	
	<h3 style="font-size:12px; border-bottom:solid 1px #DEDEDE;">
	<bean:write name="customer" property="firstName"/>&nbsp;
	<bean:write name="customer" property="lastName"/><br>
	</h3>
	<ul>
	<li><a href="<html:rewrite action='/actions/catalog/account'/>?action=editAccount"><bean:message key="text.editaccount"/></a></li>
	<li><a href="<html:rewrite action='/actions/catalog/account'/>?action=changePassword"><bean:message key="text.changepassword"/></a></li>
	<li><a href="<html:rewrite action='/actions/catalog/account'/>?action=modifyAddress"><bean:message key="text.modifyaddress"/></a></li>
	<li><a href="<html:rewrite action='/actions/catalog/orderHistory'/>"><bean:message key="text.vieworder"/></a></li>
	<li><a href="<html:rewrite page='/view/catalog/pages/confirm-order.jsp'/>"><bean:message key="text.confirmorder"/></a></li>
	</ul>
	
	<br />
	
	<a href="<html:rewrite action='/actions/catalog/logout'/>" class="button">
	<span><bean:message key="text.logout"/></span></a>
	
</div>
<div class="bottom">&nbsp;</div>
</div>

</div>
