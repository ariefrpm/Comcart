package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;
import java.util.Iterator;

import id.ac.bsi.comcart.models.AddressBean;
import id.ac.bsi.comcart.models.AddressDTO;
import id.ac.bsi.comcart.models.OrderBean;
import id.ac.bsi.comcart.models.OrderHistoryBean;
import id.ac.bsi.comcart.models.OrderProductBean;
import id.ac.bsi.comcart.models.RekeningBean;
import id.ac.bsi.comcart.models.ShoppingCartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.CustomerDTO;
import com.ecommerce.jpa.EnumOrderStatus;
import com.ecommerce.jpa.OrderDTO;
import com.ecommerce.jpa.RekeningDTO;

public class CheckOutAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		HttpSession session = request.getSession();
		CustomerDTO customer = (CustomerDTO)session.getAttribute("customer");
		String id = request.getParameter("id");
		if(id==null) return mapping.findForward("cart");
		if(customer==null) return mapping.findForward("failed");
		
		if(id.equals("checkout")){
			Collection addresses = AddressBean.getAddresses(customer.getCustomerId());			
			session.setAttribute("addresses",addresses);
			Collection rekenings = RekeningBean.getRekenings();
			session.setAttribute("rekenings",rekenings);
			return mapping.findForward("delivery");	
		}
		if(id.equals("delivery")){
			String deliveryAddressId = request.getParameter("delivery");		
			String rekeningId = request.getParameter("rekening");
			String comment = request.getParameter("comment");			
			
			AddressDTO deliveryAddress = AddressBean.getAddress(convertInt(deliveryAddressId));
			if(deliveryAddress.getCustomerId()!=customer.getCustomerId())
				return mapping.findForward("failed");			
			RekeningDTO rekening = RekeningBean.getRekening(convertInt(rekeningId));
			
			
			session.setAttribute("comment",comment);
			session.setAttribute("rekening",rekening);			
			session.setAttribute("deliveryAddress",deliveryAddress);
			return mapping.findForward("order");	
		}
		if(id.equals("order")){			
			String agree = request.getParameter("agree");
			String comment = (String)session.getAttribute("comment");
			if(comment==null)comment="";			
			AddressDTO deliveryAddress = (AddressDTO)session.getAttribute("deliveryAddress");
			RekeningDTO rekening = (RekeningDTO)session.getAttribute("rekening");
			ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
						
			if(agree==null){
				request.setAttribute("failed","You Have To Agree With Terms And Conditions ");
				return mapping.findForward("order");
			}			
			
			if(customer==null||deliveryAddress==null||rekening==null||shoppingCart==null){
				return mapping.findForward("failed");
			}	
		
			/*OrderDTO order = OrderBean.addOrder(customer.getCustomerId(),1,rekening.getRekeningId(),
			deliveryAddress.getFirstName(),deliveryAddress.getLastName(),deliveryAddress.getAddress(),
			deliveryAddress.getCity(),deliveryAddress.getZip(),deliveryAddress.getCountry(),
			deliveryAddress.getRegion(),comment,shoppingCart.getGrandTotal());
			
			if(order==null){
				return mapping.findForward("failed");
			}
			
			OrderHistoryBean.insertOrderHistory(order.getOrderId(),1,"");
			Collection products = shoppingCart.getShoppingCartItems();
			ShoppingCartItem product;
			for(Iterator it=products.iterator();it.hasNext();){
				Object o = it.next();
				product = (ShoppingCartItem)o;				
				OrderProductBean.addOrderProduct(order.getOrderId(),product.getItemName(),
				product.getImage(),product.getPrice(),product.getNumItems(),product.getTotalPrice(),
				product.getItemId(),product.getSpecial());
			}*/
			
			Collection<ShoppingCartItem> products = shoppingCart.getShoppingCartItems();
			
			OrderDTO order = new OrderDTO();
			order.setCustomerId(customer.getCustomerId());
			order.setStatusId(EnumOrderStatus.NEW.getCategoryCode());
			order.setRekeningId(rekening.getRekeningId());
			order.setDeliveryFirstName(deliveryAddress.getFirstName());
			order.setDeliveryLastName(deliveryAddress.getLastName());
			order.setDeliveryAddress(deliveryAddress.getAddress());
			order.setDeliveryCity(deliveryAddress.getCity());
			order.setDeliveryZip(deliveryAddress.getZip());
			order.setDeliveryCountry(deliveryAddress.getCountry());
			order.setDeliveryRegion(deliveryAddress.getRegion());
			order.setComment(comment);
			order.setTotal(shoppingCart.getGrandTotal());
			
			boolean isSuccess = OrderBean.addOrderCompleteWeb(order, products);
			if(!isSuccess){
				return mapping.findForward("failed");
			}
			
			session.removeAttribute("addresses");
			session.removeAttribute("deliveryAddress");
			session.removeAttribute("rekening");
			session.removeAttribute("comment");
			session.removeAttribute("shoppingCart");
			return mapping.findForward("input-order");
		}
		return mapping.findForward("cart");
	}
	private int convertInt(String value){
		int result=0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			result = 0;
		}
		return result;
	}
}