<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<div class="box">
	<div class="top">
	<img src="<html:rewrite page='/view/catalog/images/icon_bestsellers.png'/>" alt="" /><bean:message key="text.best"/>
	</div>
	<div class="middle">	
	<logic:notEmpty name="bestsellers">
    <table cellpadding="0" cellspacing="0" style="width: 100%;font-size:12px;">
      <logic:iterate id="product" name="bestsellers" length="4">
      <tr>
        <td valign="top" align="center">
        	<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=<bean:write name='product' property='productId'/>" style="text-decoration:none;">
        		<img class="small" src="<html:rewrite page='/images/small${product.image}'/>" alt="" border="0" />
        	</a>
        </td>
        <td valign="top">
        	<a href="<html:rewrite action='/actions/catalog/productDetails'/>?id=<bean:write name='product' property='productId'/>">
        		<bean:write name="product" property="name"/>
        	</a>
          	<br />
          	<c:if test="${product.special==0}">
				<span style="color: #900; font-weight: bold;">
					$${product.price}
				</span>
			</c:if>
			<c:if test="${product.special!=0}">
				<strike>$${product.special}</strike>
				<span style="color: #900; font-weight: bold;">
				$${product.price}
				</span>
			</c:if>
        </td>
      </tr>
      </logic:iterate>
    </table>
    </logic:notEmpty>
	</div>
	<div class="bottom">&nbsp;</div>
</div>