<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="top"><h1><bean:message key="text.checkoutinfo"/></h1></div>

<div class="middle">
	<form action="<html:rewrite action='/actions/catalog/checkout'/>" id="checkout" method="post">
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.deliveryaddress"/></b>
	<div class="buttons">	
	<table style="font-size: 12px;">
		
		<logic:iterate id="address" name="addresses">
		<tr>
		<td><input type="radio" name="delivery" value="<bean:write name='address' property='addressId'/>" checked="checked">
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
		
	</table>
	</div>
	
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.prefferedbank"/></b>
	<div class="buttons">	
	<table style="font-size: 12px;">
		
		<logic:iterate id="rekening" name="rekenings">
		<tr>
		<td><input type="radio" name="rekening" value="<bean:write name='rekening' property='rekeningId'/>" checked="checked">
		<img src="<html:rewrite page='/images/small${rekening.image}'/>" border="0"/>&nbsp;
		<bean:write name="rekening" property="account"/>&nbsp;
		No.Rek&nbsp;<bean:write name="rekening" property="rekening"/>
		</input></td>
		</tr>
		</logic:iterate>
		<input type="hidden" name="id" value="checkoutConfirmation"/>
	</table>
	</div>	
	</form>
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.newadderess"/></b>
	<div class="buttons">	
	<table style="font-size: 12px;">
		
		<html:form action="/actions/catalog/addAddress" styleId="addAddress">
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
		<html:select property="country">
		<option>-- Select --</option>
		<html:options collection="allCountry" labelName="allCountry" labelProperty="name" name="allCountry" property="name"/>

		</html:select>
		</td>
		</tr>
		<tr><td><bean:message key="form.region"/></td>
		<td><html:text property="region"/></td>
		</tr>		
		<html:hidden property="email" value="user@email.com"/>
		<html:hidden property="phone" value="phone"/>
		<html:hidden property="password" value="password"/>
		<tr><td></td>
		<td align="right">
		<a href="#" onclick="addAddress.submit();" class="button"><span><bean:message key="text.addadderess"/></span></a>
		</td>
		</tr>
		</html:form>
	</table>
	</div>
	
	<b style="margin-bottom: 3px; display: block;"><bean:message key="text.addcomment"/></b>
    <div style="background: #F7F7F7; border: 1px solid #DDDDDD; padding: 10px; margin-bottom: 10px;">            
      <textarea rows="3" cols="40" name="comment">${comment}</textarea>
    </div>
    
    <div class="buttons">
	<table>
		<tr>		
		<td align="right"><a href="#" onclick="checkout.submit();" class="button">
		<span><bean:message key="text.continue"/></span></a></td>		
		</tr>
	</table>
	</div>	
</div>

<div class="bottom">&nbsp;</div>