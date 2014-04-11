<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top"><h1><bean:message key="text.edityouraddress"/></h1></div>

<div class="middle">

<b style="margin-bottom: 3px; display: block;"><bean:message key="text.edityouraddress"/></b>
<html:form action="/actions/catalog/editAddress" styleId="edit">
<div class="buttons">
<table style="font-size: 12px;">
<tr>
<td><span class="required">*</span><bean:message key="form.firstName"/></td>
<td align="left"><html:text property="firstName"/>
<html:errors property="firstName"/></td>
</tr>
<tr>
<td><span class="required">*</span><bean:message key="form.lastName"/></td>
<td align="left"><html:text property="lastName"/>
<html:errors property="lastName"/></td>
</tr>
<tr>
<td><span class="required">*</span><bean:message key="form.address"/></td>
<td align="left"><html:textarea property="address" cols="35" rows="3"/>
<html:errors property="address"/></td>
</tr>
<tr>
<td><bean:message key="form.zip"/></td>
<td align="left">
<html:text property="zip"/>
<html:errors property="zip"/>
</td>
</tr>
<tr>
<td><span class="required">*</span><bean:message key="form.city"/></td>
<td align="left"><html:text property="city"/>
<html:errors property="city"/></td>
</tr>
<tr>
<td><bean:message key="form.country"/></td>
<td align="left">

<html:select property="country" onchange="getRegion(this.value);">
<option>-- <bean:message key="text.select"/> --</option>
<html:options collection="allCountry" labelName="allCountry" labelProperty="name" name="allCountry" property="countryId"/>

</html:select>

</td>
</tr>
<tr><td><bean:message key="form.region"/></td>
<td><div id="region"></div></td>
</tr>

<html:hidden property="password"/>
<html:hidden property="email"/>
<html:hidden property="phone"/>
<input type="hidden" name="button" value="save"/>
</table>
</div>


<div class="buttons">
<table>
<tr>
<td><a href="#" onclick="edit.submit();" class="button"><span><bean:message key="text.submit"/></span></a>
<a href="<html:rewrite action='/actions/catalog/editAddress'/>?cancel=true" class="button"><span><bean:message key="text.cancel"/></span></a></td>
</tr>
</table>
</div>
</html:form>
</div>

<div class="bottom">&nbsp;</div>