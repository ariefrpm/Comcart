package id.ac.bsi.comcart.actions.admin;

import java.util.Collection;
import java.util.Iterator;

import id.ac.bsi.comcart.models.AdminDTO;
import id.ac.bsi.comcart.models.CustomerBean;
import id.ac.bsi.comcart.utils.SendMail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.CustomerDTO;

public class MailAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		AdminDTO admin = (AdminDTO)request.getSession().getAttribute("admin");
		if (admin==null){			
      		return (mapping.findForward("admin"));
    	}
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		String cancel = request.getParameter("cancel");
		
		Collection customers = CustomerBean.getCustomers();
		request.setAttribute("customers",customers);
		
		if(to==null||subject==null||message==null||cancel!=null){
			return mapping.findForward("success");
		}
		
		if(to.equals("All Customer")){			
			CustomerDTO customer;
			for(Iterator it=customers.iterator();it.hasNext();){
				Object o = it.next();
				customer = (CustomerDTO)o;
				SendMail.sendMail(message,subject,customer.getEmail(),"arief@onepiece.com","127.0.0.1");
			}
		}else{
			CustomerDTO customer = CustomerBean.getCustomer(convertInt(to));
			SendMail.sendMail(message,subject,customer.getEmail(),"arief@onepiece.com","127.0.0.1");	
		}	
			
		request.setAttribute("success","true");
		return mapping.findForward("success");
	}
	private int convertInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			result = 1;
		}
		return result;
	}
}