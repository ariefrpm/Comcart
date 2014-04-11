package id.ac.bsi.comcart.actions.admin;

import id.ac.bsi.comcart.models.CustomerBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.CustomerDTO;

public class CustomerFormAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
				   
		String id = request.getParameter("id");
		String customerId = request.getParameter("customerId");
		
		if(id==null){
			return mapping.findForward("save");
		}
		if(id.equals("cancel")){
			return mapping.findForward("save");
		}
		if(id.equals("edit")){			
			CustomerDTO customer = CustomerBean.getCustomer(convertInt(customerId));
			request.setAttribute("customer",customer);					
			return mapping.findForward("success");
		}
		if(id.equals("delete")){
			String[] paramValues = request.getParameterValues("delete");			
			for (int i=0; i<paramValues.length; i++){
				CustomerBean.deleteCustomer(convertInt(paramValues[i]));
			}
			request.setAttribute("success","true");
			return mapping.findForward("save");
		}		
		return mapping.findForward("success");
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
}