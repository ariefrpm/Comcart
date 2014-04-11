<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<div class="top"><h1><bean:message key="text.confirmorder"/></h1></div>

<div class="middle">

<div class="buttons">
<table style="font-size: 12px;">
<html:form action="/actions/catalog/confirmOrder" styleId="confirmId">
<tr>
<td><span class="required">*</span><bean:message key="text.orderid"/></td>
<td><html:text property="orderId"/><html:errors property="orderId"/></td>
</tr>

<tr>
<td><span class="required">*</span><bean:message key="form.price"/></td>
<td><html:text property="price"/><html:errors property="price"/></td>
</tr>

<tr>
<td><span class="required">*</span><bean:message key="text.transferdate"/></td>
<td><html:text property="transferDate"/><html:errors property="transferDate"/></td>
</tr>

<tr>
<td><a href="#" onclick="confirmId.submit()" class="button"><span><bean:message key="text.submit"/></span></a></td>
</tr>

</html:form>
</table>
</div>

</div>

<div class="bottom">&nbsp;</div>