package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.DynaValidatorForm;

import com.ecommerce.jpa.CustomerDTO;
import com.ecommerce.jpa.OrderDTO;

import id.ac.bsi.comcart.models.ConfirmedOrderBean;
import id.ac.bsi.comcart.models.OrderBean;

public class ConfirmOrderAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String orderId = (String)((DynaValidatorForm)form).get("orderId");
		String price = (String)((DynaValidatorForm)form).get("price");
		String transferDate = (String)((DynaValidatorForm)form).get("transferDate");
		CustomerDTO customer = (CustomerDTO)request.getSession().getAttribute("customer");
		if(customer!=null){			
			Collection orders = OrderBean.getOrderByCustomer(customer.getCustomerId());
			OrderDTO order;
			for(Iterator it=orders.iterator();it.hasNext();){
				Object o = it.next();
				order = (OrderDTO)o;
				if(orderId.equals(order.getOrderIdCode())){
					ConfirmedOrderBean.addConfirmedOrder(orderId,customer.getCustomerId(),transferDate,convertFloat(price));
					return mapping.findForward("success");		
				}
			}			
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.orderId");
			errors.add("orderId",error);
			saveErrors(request,errors);
			return (mapping.findForward("failed"));
			
		}
		return mapping.findForward("failed");
	}
	private int convertInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}
		return result;
	}
	private float convertFloat(String value){
		float result = 0;
		try{
			result = Float.parseFloat(value);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}
		return result;
	}
}