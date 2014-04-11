package id.ac.bsi.comcart.actions.admin;

import id.ac.bsi.comcart.actions.catalog.ShoppingCart;
import id.ac.bsi.comcart.models.ConfirmedOrderBean;
import id.ac.bsi.comcart.models.CustomerBean;
import id.ac.bsi.comcart.models.OrderBean;
import id.ac.bsi.comcart.models.OrderHistoryBean;
import id.ac.bsi.comcart.models.OrderHistoryDTO;
import id.ac.bsi.comcart.models.OrderProductBean;
import id.ac.bsi.comcart.models.ProductBean;
import id.ac.bsi.comcart.models.StatusBean;
import id.ac.bsi.comcart.models.StatusDTO;
import id.ac.bsi.comcart.utils.Utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.ConfirmedOrderDTO;
import com.ecommerce.jpa.CustomerDTO;
import com.ecommerce.jpa.EnumInboxCategory;
import com.ecommerce.jpa.EnumOrderStatus;
import com.ecommerce.jpa.GCMOrder;
import com.ecommerce.jpa.GCMOrderItem;
import com.ecommerce.jpa.OrderDTO;
import com.ecommerce.jpa.OrderProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class OrderFormAction extends Action{
	
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String id = request.getParameter("id");
		String orderId = request.getParameter("orderId");		
		if(id==null){
			return mapping.findForward("save");
		}
		if(id.equals("cancel")){
			return mapping.findForward("save");
		}
		if(id.equals("edit")){			
			OrderDTO order = OrderBean.getOrderWithStatusCustomerRekening(orderId);
			
//			Collection orderProducts = OrderProductBean.getOrderProducts(orderId);
			ShoppingCart shoppingCart = OrderProductBean.getOrderProductCart(orderId);
			shoppingCart.deliveryPrice = order.getDeliveryPrice();
			
			Collection orderHistories = OrderHistoryBean.getOrderHistories(orderId);
			List<StatusDTO> status = StatusBean.getStatus();
			ConfirmedOrderDTO confirm = null;
			if(orderId != null){
				if(orderId.trim().length() > 0){
					confirm = ConfirmedOrderBean.getConfirmedOrder(orderId);
				}
			}
			
			String expedition = null;
			
			String confirmFlag = confirm==null?null:"flag";
			
			if(order.getStatus().equalsIgnoreCase(EnumOrderStatus.EXPEDITION.getCategory())){
				expedition = EnumOrderStatus.EXPEDITION.getCategory();
			}
			if(order.getStatusId() > EnumOrderStatus.PAYMENT.getCategoryCode()){
				confirmFlag = null;
			}
			
			request.setAttribute("order", order);
			request.setAttribute("histories", orderHistories);
			request.setAttribute("status", status);
			request.setAttribute("confirm", confirm);
			request.setAttribute("confirmFlag", confirmFlag);
			request.setAttribute("expedition", expedition);
			request.setAttribute("shoppingCart", shoppingCart);
			
			return mapping.findForward("success");
		}
		if(id.equals("confirm")){
			String orderIdSave = request.getParameter("orderIdSave");
			
//			OrderHistoryBean.insertOrderHistory(orderIdSave, EnumOrderStatus.EXPEDITION.getCategoryCode(), "");
			
			CustomerDTO customer = CustomerBean.findCustomerRegId(orderIdSave);
			
			List<String> orders = new ArrayList<String>();
			orders.add(orderIdSave);
			
			if(Utils.sendGCMMessage(customer.getEmail(), customer.getRegistrationId(), orderIdSave, EnumOrderStatus.EXPEDITION.getCategoryCode(), null, 5)){
				OrderBean.updateStatusNew(orders, EnumOrderStatus.EXPEDITION.getCategoryCode(), "");
				request.setAttribute("success","success");
			}else{
				request.setAttribute("failed","failed");
			}
			
			return mapping.findForward("save");
		}
		if(id.equals("expedition")){
			String orderIdSave = request.getParameter("orderIdSave");
			List<String> orders = new ArrayList<String>();
			orders.add(orderIdSave);
			
			OrderBean.updateStatusNew(orders, EnumOrderStatus.COMPLETE.getCategoryCode(), "");
//			OrderHistoryBean.insertOrderHistory(orderIdSave, EnumOrderStatus.COMPLETE.getCategoryCode(), "");
			
			request.setAttribute("success","true");
			return mapping.findForward("save");
		}if(id.equals("prescription")){
			String orderIdSave = request.getParameter("orderIdSave");
			String comment = request.getParameter("comment");
//			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			
			OrderDTO order = new OrderDTO();
			order.setOrderId(orderIdSave);
			
			List<OrderProductDTO> orderProducts = new ArrayList<OrderProductDTO>();
			
			float total = 0;
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName = (String)paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				
				if(paramValue.equals(id) || paramValue.equals(orderIdSave) || paramValue.equals(comment)){
				}else{
					OrderProductDTO orderProduct = new OrderProductDTO();
					DecimalFormat format = new DecimalFormat("######");
					Number c = 0;
					try {
						c = (Number) format.parse(paramValue);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					orderProduct.setPrice(c.floatValue());
					orderProduct.setQuantity(1);
					orderProduct.setTotalPrice(c.floatValue());
					orderProduct.setProductId(paramName);
					orderProduct.setSpecial(c.floatValue());
					orderProduct.setOrderId(orderIdSave);
					total += c.floatValue();
					orderProducts.add(orderProduct);
				}
			}
			order.setTotal(total);
			
			
			CustomerDTO customer = CustomerBean.findCustomerRegId(orderIdSave);
			if(Utils.sendGCMMessage(customer.getEmail(), customer.getRegistrationId(), orderIdSave, EnumOrderStatus.NEWPRESCTIPTION.getCategoryCode(), orderProducts, 5)){
				OrderBean.updateOrderPrice(order, orderProducts);
				request.setAttribute("success","success");
			}else{
				request.setAttribute("failed","failed");
			}
			
			return mapping.findForward("save");
		}
		if(id.equals("save")){
			String statusId = request.getParameter("statusId");
			String comment = request.getParameter("comment");
			if(comment==null)comment="";
			String orderIdSave = request.getParameter("orderIdSave");			
			
//			OrderHistoryBean.insertOrderHistory(orderIdSave, Utils.convertInt(statusId), comment);
			List<String> orders = new ArrayList<String>();
			orders.add(orderIdSave);
			
			OrderBean.updateStatusNew(orders, Utils.convertInt(statusId), comment);
			
			Collection orderHistories = OrderHistoryBean.getOrderHistories(orderIdSave);
			OrderHistoryDTO orderHistory;
			int status=0;
			for(Iterator it=orderHistories.iterator();it.hasNext();){
					Object o = it.next();
					orderHistory = (OrderHistoryDTO)o;
					if(orderHistory.getStatusId()>1)status=1;
			}
				
			
			
			if(statusId.equals("2")){
				Collection orderProducts = OrderProductBean.getOrderProducts(orderIdSave);
				OrderProductDTO orderProduct;
				for(Iterator it=orderProducts.iterator();it.hasNext();){
					Object o = it.next();
					orderProduct = (OrderProductDTO)o;
					ProductBean.updateQuantity(orderProduct.getProductId(),orderProduct.getQuantity());
				}
			}
			if((statusId.equals("5")||statusId.equals("6"))&&status==1){				
				Collection orderProducts = OrderProductBean.getOrderProducts(orderIdSave);
				OrderProductDTO orderProduct;
				for(Iterator it=orderProducts.iterator();it.hasNext();){
					Object o = it.next();
					orderProduct = (OrderProductDTO)o;
					ProductBean.cancelQuantity(orderProduct.getProductId(),orderProduct.getQuantity());
				}
			}
			request.setAttribute("success","true");
			return mapping.findForward("save");
		}
		if(id.equals("delete")){
			String[] paramValues = request.getParameterValues("delete");			
			for (int i=0; i<paramValues.length; i++){				
				OrderBean.deleteOrder(paramValues[i]);
			}
			request.setAttribute("success","true");
			return mapping.findForward("save");
		}
		return mapping.findForward("success");
	}
	
	
}