package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;

import id.ac.bsi.comcart.models.OrderBean;
import id.ac.bsi.comcart.models.OrderHistoryBean;
import id.ac.bsi.comcart.models.OrderProductBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.CustomerDTO;
import com.ecommerce.jpa.OrderDTO;

public class OrderHistoryAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String orderId = request.getParameter("id");
		CustomerDTO customer = (CustomerDTO)request.getSession().getAttribute("customer");		
		if(orderId!=null){			
			OrderDTO order = OrderBean.getOrder(orderId);
			Collection orderProducts = OrderProductBean.getOrderProducts(orderId);
			Collection orderHistory = OrderHistoryBean.getOrderHistories(orderId);						
			if(customer.getCustomerId()!=order.getCustomerId())return mapping.findForward("failed");			
			request.setAttribute("order",order);
			request.setAttribute("orderProducts",orderProducts);
			request.setAttribute("orderHistory",orderHistory);			
			return mapping.findForward("orderInvoice");
		}		
		Collection orders = OrderBean.getOrderByCustomer(customer.getCustomerId());
		request.setAttribute("orders",orders);
		return mapping.findForward("success");
	}
}