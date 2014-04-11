<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<div style="text-align: center; height: 30px;">
<A HREF="<html:rewrite page='/view/catalog/pages/about.jsp'/>"><bean:message key="text.about"/></A>
<A HREF="<html:rewrite page='/view/catalog/pages/contact.jsp'/>"><bean:message key="text.contact"/></A>
<A HREF="<html:rewrite page='/view/catalog/pages/terms.jsp'/>"><bean:message key="text.terms"/></A>
</div>