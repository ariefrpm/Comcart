<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top">
<h1><bean:message key="text.createaccount"/></h1></div>

<div class="middle">

<html:form action="/actions/catalog/signup" styleId="signup">
<b style="margin-bottom: 3px; display: block;"><bean:message key="text.yourpersonal"/></b>
<div class="buttons">
<table style="font-size:12px;">
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
<td><span class="required">*</span><bean:message key="form.email"/></td>
<td align="left"><html:text property="email"/>
<html:errors property="email"/></td>
</tr>
<tr>
<td><span class="required">*</span><bean:message key="form.phone"/></td>
<td align="left"><html:text property="phone"/>
<html:errors property="phone"/></td>
</tr>
</table>
</div>


<b style="margin-bottom: 3px; display: block;"><bean:message key="text.youraddress"/></b>
<div class="buttons">
<table style="font-size:12px;">
<tr>
<td><span class="required">*</span><bean:message key="form.address"/></td>
<td align="left"><html:textarea property="address" cols="35" rows="3"/>
<html:errors property="address"/></td>
</tr>
<tr>
<td><bean:message key="form.zip"/></td>
<td align="left"><html:text property="zip"/>
<html:errors property="zip"/></td>
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
<option>-- Select --</option>
<html:options collection="allCountry" labelName="allCountry" labelProperty="name" name="allCountry" property="countryId"/>
</html:select>
</td>
</tr>
<tr><td><bean:message key="form.region"/></td>
<td><div id="region"></div></td>
</tr>
</table>
</div>

<b style="margin-bottom: 3px; display: block;"><bean:message key="text.yourpassword"/></b>
<div class="buttons">
<table style="font-size:12px;">
<tr>
<td><span class="required">*</span><bean:message key="form.password"/></td>
<td align="left"><html:password property="password"/>
<html:errors property="password"/></td>
</tr>
<tr>
<td><span class="required">*</span><bean:message key="form.repeatedPassword"/></td>
<td align="left"><html:password property="password2"/>
<html:errors property="password2"/></td>
</tr>
</table>
</div>


<div class="buttons">
<table>
<tr>
<td><a href="#" onclick="signup.submit();" class="button"><span><bean:message key="text.submit"/></span></a>
<a href="#" onclick="signup.cancel();" class="button"><span><bean:message key="text.cancel"/></span></a></td>
</tr>
</table>
</div>

</div>
<div class="bottom">&nbsp;</div>
</html:form>