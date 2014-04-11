<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top"><h1><bean:message key="text.addressbook"/></h1></div>

<div class="middle">
	
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.addressbookentries"/></b>
	<div class="buttons">	
	<table style="font-size: 12px;">
		<form action="<html:rewrite action='/actions/catalog/editAddress'/>" method="post">
		<logic:iterate id="address" name="addresses">
		<tr>
		<td><input type="radio" name="addressId" 
		value="<bean:write name='address' property='addressId'/>" checked="checked">
		<bean:write name="address" property="firstName"/>&nbsp;
		<bean:write name="address" property="lastName"/>,&nbsp;
		<bean:write name="address" property="address"/>,&nbsp;
		<bean:write name="address" property="city"/>
		<logic:notEmpty name="address" property="region">
			,&nbsp;<bean:write name="address" property="region"/>
		</logic:notEmpty>
		<logic:notEmpty name="address" property="country">
			,&nbsp;<bean:write name="address" property="country"/>
		</logic:notEmpty>		
		</input></td>
		</tr>
		</logic:iterate>
		
		<tr>		
		<td align="center">
		
		<input type="submit" name="button" value="edit" class="button">
		<input type="submit" name="button" value="delete" class="button">		
				
		</td>
		</tr>
		</form>
	</table>
	</div>	
	
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.newadderess"/></b>
	<div class="buttons">	
	<table style="font-size: 12px;">
		<html:form action="/actions/catalog/account" styleId="account">
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
		<option>-- <bean:message key="text.select"/> --</option>
		<html:options collection="allCountry" labelName="allCountry" labelProperty="name" name="allCountry" property="countryId"/>

		</html:select>
		
		</td>
		</tr>
		<tr><td><bean:message key="form.region"/></td>
		<td><div id="region"></div></td>
		</tr>		
		<html:hidden property="email" value="user@email.com"/>
		<html:hidden property="phone" value="phone"/>
		<html:hidden property="password" value="password"/>
		<input type="hidden" name="action" value="addAddress"/>
		<tr><td></td>
		<td align="right">
		<a href="#" onclick="account.submit();" class="button"><span><bean:message key="text.addadderess"/></span></a>
		</td>
		</tr>
		</html:form>
	</table>
	</div>
	
</div>

<div class="bottom">&nbsp;</div>