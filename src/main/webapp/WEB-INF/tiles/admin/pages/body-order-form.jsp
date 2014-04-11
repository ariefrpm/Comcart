<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="<html:rewrite page='/resource/magnific-popup.css'/>" ></link>
<script src="<html:rewrite page='/resource/jquery.min.js'/>"></script> 
<script src="<html:rewrite page='/resource/jquery.magnific-popup.js'/>"></script> 
<style>
	.image-link {
	  cursor: -webkit-zoom-in;
	  cursor: -moz-zoom-in;
	  cursor: zoom-in;
	}
</style>
<script>
$(document).ready(function() {

	$('.image-popup-no-margins').magnificPopup({
		type: 'image',
		closeOnContentClick: true,
		closeBtnInside: false,
		fixedContentPos: true,
		mainClass: 'mfp-no-margins mfp-with-zoom',
		image: {
			verticalFit: false
		},
		zoom: {
			enabled: true,
			duration: 300
		}
	});

});
</script>

<div id="order">

<form action="<html:rewrite action='/actions/admin/orderForm'/>" id="orderForm">

<input type="hidden" name="orderIdSave" value="${order.orderId}"/>
<div class="heading">
  <h1>Orders</h1>
  <div class="buttons">
  <a href="<html:rewrite action='/actions/admin/orderList'/>" class="button" style="text-decoration:none">
  	<span class="button_left button_back"></span>
  	<span class="button_middle">Back</span>
  	<span class="button_right"></span>
  </a>  
  </div>
</div>

<table>
      <thead>
        <tr>
          <td align="center" colspan="3">Order Details</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="25%"><b>Order Id</b></td>
          <td width="25%"><b>Date Added</b></td>
          <td width="25%"><b>Payment Transfer</b></td>          
        </tr>
        <tr>
          <td valign="top">${order.orderId}</td>
          <td valign="top">${order.orderDate}</td>
          <td valign="top">Bank&nbsp;${order.bank}<br>Rek.&nbsp;${order.rekening}</td>
        </tr>
      </tbody>
</table>
    <br />
    <table>
      <thead>
        <tr>
          <td align="center" colspan="4">Customer Details</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="25%"><b>Name</b></td>
          <td width="25%"><b>E-Mail</b></td>
          <td width="25%"><b>Telephone</b></td>
          <td width="25%"><b>Delivery Address</b></td>
        </tr>
        <tr>
          <td valign="top">${order.customerName}</td>
          <td valign="top">${order.customerEmail}</td>
          <td valign="top">${order.customerPhone}</td>
          <td valign="top">${order.deliveryFirstName}&nbsp;${order.deliveryLastName}<br>
              ${order.deliveryAddress}<br>${order.deliveryCity}<br>
              ${order.deliveryRegion}<br>${order.deliveryCountry}
         </td>
        </tr>
      </tbody>
    </table>
        
    <br />
    <table>
      <thead>
        <tr>
          <td align="center" colspan="5">Products</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td align="center"><b>Image</b></td>
          <td><b>Product</b></td>          
          <td align="right"><b>Quantity</b></td>
          <td align="right"><b>Unit Price</b></td>
          <td align="right"><b>Total</b></td>
        </tr>
        <logic:iterate id="product" name="shoppingCart" property="shoppingCartItems">
	        <tr>
	        	  <td>
		        	  <a class="image-popup-no-margins image-link" href="<html:rewrite page='/images/med${product.image}'/>">
						<img src="<html:rewrite page='/images/med${product.image}'/>" border="0" width="107" height="75"/>
				 	  </a>
			 	  </td>
		          <td>${product.itemName}</td>
		          <td align="right">${product.numItems}</td>
		          <td align="right">
		          		<c:choose>
						    <c:when test="${order.pricing}">
						        <input style="text-align: right;" type="text" name="${product.itemId}" size="13" value="${product.price}" onchange="change()" id="${product.itemId}">
						    </c:when>
						    <c:otherwise>
						        ${product.price}
						    </c:otherwise>
						</c:choose>
		          </td>
		          <td align="right"><div id="tot${product.itemId}">${product.totalPrice}</div></td>
	        </tr>
        </logic:iterate>
        <tr>
          <td align="right" colspan="3"><b>${order.deliveryService}</b></td>
          <td align="right"></td>
          <td align="right">
          		<script>
	          		function change(){
	          			var tot = 0;
	          		  	<logic:iterate id="product" name="shoppingCart" property="shoppingCartItems">
	          		  		var el1 = document.getElementById("tot${product.itemId}");
	          		    	var el2 = document.getElementById("${product.itemId}");
	          		    	el1.innerHTML = el2.value;
	          		    	tot = tot + parseInt(el2.value);
					    </logic:iterate>
					    var deliv = document.getElementById("deliv");
					    var gr = document.getElementById("grTot");
					    gr.innerHTML = tot + parseInt(deliv.innerHTML);
	          		}
				</script>
			<div id="deliv">${order.deliveryPrice}</div>
		</td>
        </tr>
        <tr>
          <td align="right" colspan="3">
          	<b>Total</b>	
          </td>
          <td align="center"></td>
          <td align="right"><div id="grTot">${shoppingCart.grandTotal}</div></td>
        </tr>        
      </tbody>
    </table>
    <br />
    <logic:notEmpty name="order" property="comment">
    <table>
      <thead>
        <tr>
          <td align="center">Order Comment</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${order.comment}</td>
        </tr>
      </tbody>
    </table>
    <br />
    </logic:notEmpty>
    <logic:notEmpty name="histories">
    <table>
      <thead>
        <tr>
          <td align="center" colspan="3">Order History</td>
        </tr>
      </thead>
      <tbody>
      	<tr>
          <td><b>Date</b></td>
          <td><b>Status</b></td>
          <td><b>Action</b></td>
        </tr>
        <logic:iterate id="history" name="histories">
        <tr>
          <td>${history.dateAdded}</td>
          <td>${history.status}</td>
          <td>
	          <c:if test="${not empty confirmFlag && history.status == 'Payment'}">
	          	<a onclick="orderForm.submit()" class="button">
	          		<input type="hidden" name="id" value="confirm"/>
		        	<span class="button_left button_save"></span>
		        	<span class="button_middle">Accept</span>
		        	<span class="button_right"></span>
	        	</a>
	        	<a onclick="<html:rewrite action='/actions/admin/orderList'/>" class="button" style="margin-left: 5px;">
	        		<input type="hidden" name="id" value="expedition"/>
		        	<span class="button_left button_cancel"></span>
		        	<span class="button_middle">Reject</span>
		        	<span class="button_right"></span>
	        	</a>
	          </c:if>
	          
	          <c:if test="${not empty expedition && history.status == expedition}">
	          	<a onclick="orderForm.submit()" class="button">
	          		<input type="hidden" name="id" value="expedition"/>
		        	<span class="button_left button_save"></span>
		        	<span class="button_middle">Complete</span>
		        	<span class="button_right"></span>
	        	</a>
	        	<a onclick="<html:rewrite action='/actions/admin/orderList'/>" class="button" style="margin-left: 5px;">
		        	<span class="button_left button_cancel"></span>
		        	<span class="button_middle">Cancel</span>
		        	<span class="button_right"></span>
	        	</a>
	          </c:if>
          <td>
        </tr>
        <tr>
        	<c:if test="${not empty confirm && history.status == 'Payment'}">
        		<td></td>
        		<td>Bank&nbsp;${confirm.accountBank}</br>an.&nbsp;${confirm.accountName}</br>Rek.&nbsp;${confirm.accountNumber}</br><b>Amount: &nbsp;${confirm.price}</b></td>
        		<td></td>
        	</c:if>
        	
        </tr>
        </logic:iterate>
      </tbody>
    </table>
    </logic:notEmpty>
    <br />
    <c:if test="${order.pricing}">
	    <table>
	      <thead>
	        <tr>
	          <td align="center" colspan="1">Update Order</td>
	        </tr>
	      </thead>
	      <tbody>
	        <tr>
	          <td><b>Save prescription</b></td>          
	        </tr>
	        <%-- <tr>
	          <td><select name="statusId">
	              <logic:iterate id="stat" name="status">              
	              <option value="<bean:write name="stat" property="statusId"/>">
	              <bean:write name="stat" property="name"/></option>
	              </logic:iterate>
	            </select></td>          
	        </tr> --%>
	        <tr>
	          <td colspan="2"><b>Comments</b></td>
	        </tr>
	        <tr>
	          <td colspan="2"><textarea name="comment" cols="40" rows="3" style="width: 99%"></textarea></td>
	        </tr>
	        <tr>
	        <td align="right" colspan="2">
	        	<a onclick="orderForm.submit()" class="button">
	        		<input type="hidden" name="id" value="prescription"/>
		        	<span class="button_left button_save"></span>
		        	<span class="button_middle">Save</span>
		        	<span class="button_right"></span>
	        	</a>
	        	<a onclick="<html:rewrite action='/actions/admin/orderList'/>" class="button" style="margin-left: 5px;">
		        	<span class="button_left button_cancel"></span>
		        	<span class="button_middle">Cancel</span>
		        	<span class="button_right"></span>
	        	</a>
	        </td>
	        </tr>
	      </tbody>
	    </table>
    </c:if>
	</form>
</div>
