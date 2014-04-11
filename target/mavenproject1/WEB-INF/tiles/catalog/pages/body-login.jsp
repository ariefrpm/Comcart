<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div class="top">
<h1><bean:message key="text.login"/></h1>
</div>

<div class="middle">

<div style="float: left; display: inline-block; width: 274px;">
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.iamanewcustomer"/></b>
    <div style="background: #F7F7F7; border: 1px solid #DDDDDD; padding: 10px; min-height: 175px;">
     	<bean:message key="text.newcustomer"/><br />
      	<br />
      	<bean:message key="text.bycreating"/><br />
      	<br />
      	<div style="text-align: right;">
      	<a href="<html:rewrite page='/view/catalog/pages/signup.jsp'/>" class="button">
      	<span><bean:message key="text.register"/></span></a></div>
    </div>
</div>

<div style="float: right; display: inline-block; width: 274px;">
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.returningcustomer"/></b>
    <div style="background: #F7F7F7; border: 1px solid #DDDDDD; padding: 10px; min-height: 175px;">
    	<html:form action="/actions/catalog/login" styleId="login">
    	<bean:message key="text.iamreturningcustomer"/><br /><html:errors property="login"/>
    	<br />
    	<b><bean:message key="form.email"/></b><html:text property="email" style="float:right;"/>
    	<br clear="all" /><html:errors property="email"/>
    	<br /><br />
    	<b><bean:message key="form.password"/></b><html:password property="password" style="float:right;"/>
    	<br clear="all"/><html:errors property="password"/>    	
    	<br /><br />
    	<div style="text-align: right;">
    	<a href="<html:rewrite page='/view/catalog/pages/forget.jsp'/>"><bean:message key="text.forgetyourpasswd"/></a>
    	<a href="#" onclick="login.submit();" class="button">
    	<span><bean:message key="text.login"/></span></a></div>
    	</html:form>    	
    </div>
</div>
</div>
<div class="bottom">&nbsp;</div>