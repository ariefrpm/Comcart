<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<ul id="nav" style="display: none;">

	<li id="catalog"><a class="top">Catalog</a>
	<ul>
		<li><a href="<html:rewrite action='/actions/admin/categoryList'/>">Category</a></li>
		<li><a href="<html:rewrite action='/actions/admin/productList'/>">Products</a></li>
	</ul>
	</li>
		
	<li id="customer"><a class="top">Customers</a>
	<ul>
		<li><a href="<html:rewrite action='/actions/admin/customerList'/>">Customer</a></li>
		<li><a href="<html:rewrite action='/actions/admin/mail'/>">Mail</a></li>
	</ul>
	</li>
	
	<li><a class="top">Orders</a>
	<ul>
	   <li><a href="<html:rewrite action='/actions/admin/orderList'/>">Order</a></li>
       <li><a href="<html:rewrite action='/actions/admin/confirmOrderList'/>">Confirmed Order</a></li>
       <li><a href="<html:rewrite action='/actions/admin/rekeningList'/>">Rekening</a></li>
	</ul>
	</li>
		
	<li id="report"><a class="top">Report</a>
    <ul>
      <li><a href="<html:rewrite action='/actions/admin/report'/>?id=viewed">Products Viewed</a></li>
      <li><a href="<html:rewrite action='/actions/admin/report'/>?id=purchased">Products Purchased</a></li>
    </ul>
    </li>
    
</ul>

<script type="text/javascript"><!--
$(document).ready(function() {
	$('#nav').superfish({
		hoverClass	 : 'sfHover',
		pathClass	 : 'overideThisToUse',
		delay		 : 0,
		animation	 : {opacity: 'show', height: 'show'},
		speed		 : 'normal',
		autoArrows   : false,
		dropShadows  : false, 
		disableHI	 : false, /* set to true to disable hoverIntent detection */
		onInit		 : function(){},
		onBeforeShow : function(){},
		onShow		 : function(){},
		onHide		 : function(){}
	});
	
	$('#nav').css('display', 'block');
});
//--></script>
