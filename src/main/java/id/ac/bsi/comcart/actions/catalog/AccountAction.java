package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;

import id.ac.bsi.comcart.models.AddressBean;
import id.ac.bsi.comcart.models.CountryBean;
import id.ac.bsi.comcart.models.CustomerBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

import com.ecommerce.jpa.CustomerDTO;


public class AccountAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{		
		CustomerDTO customer = (CustomerDTO)request.getSession().getAttribute("customer");
		String action = request.getParameter("action");
		String cancel = request.getParameter("cancel");
		
		if(customer==null){
			return mapping.findForward("failed");
		}
		if(cancel!=null){     
      		return (mapping.findForward("cancel"));
    	}
    	if(action==null){
    		return mapping.findForward("failed");
    	}
    	
		DynaActionForm myForm = (DynaActionForm)form;		
		String firstName = (String)myForm.get("firstName");
		String lastName = (String)myForm.get("lastName");
		String email = (String)myForm.get("email");
		String phone = (String)myForm.get("phone");
		String address = (String)myForm.get("address");
		String city = (String)myForm.get("city");
		String zip = (String)myForm.get("zip");		
		String country = (String)myForm.get("country");
		country = CountryBean.getCountryName(country);
    	if(country==null||country.equals("-- Select --"))country="";
    	String region = (String)myForm.get("region");
		String password = (String)myForm.get("password");
		String newPassword = (String)myForm.get("newPassword");
		String newPassword2 = (String)myForm.get("newPassword2");
		
    	
		if(action.equals("editAccount")){			
			myForm.set("firstName",customer.getFirstName());
			myForm.set("lastName",customer.getLastName());
			myForm.set("email",customer.getEmail());
			myForm.set("phone",customer.getPhone());
			myForm.set("address","address");
			myForm.set("city","city");
			myForm.set("password","password");
			return mapping.findForward("editAccount");
		}
		if(action.equalsIgnoreCase("saveAccount")){			
			ActionErrors errors = myForm.validate(mapping, request);
			if(errors.isEmpty()){
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setEmail(email);
				customer.setPhone(phone);				
				CustomerBean.updateCustomer(customer);
				return mapping.findForward("success");
			}else{
				saveErrors(request, errors);
				return mapping.findForward("failed");
			}			
		}
		if(action.equals("changePassword")){
			return mapping.findForward("changePassword");			
		}
		if(action.equals("savePassword")){
			ActionErrors errors = new ActionErrors();
			if(password.equals("")||newPassword.equals("")||newPassword2.equals("")){
				ActionMessage error = new ActionMessage("error.password");
				if(password.equals(""))errors.add("password",error);
				if(newPassword.equals(""))errors.add("newPassword",error);
				if(newPassword2.equals(""))errors.add("newPassword2",error);
				saveErrors(request,errors);
				return (mapping.findForward("failedPassword"));	
			}
			if(newPassword.equals(newPassword2)){
				if(customer.getPassword().equals(password)){
					customer.setPassword(newPassword);					
					CustomerBean.updateCustomerPassword(customer);
					return mapping.findForward("success");	
				}else{
					ActionMessage error = new ActionMessage("error.signup.passMismatch");
					errors.add("password",error);
					saveErrors(request,errors);
					return (mapping.findForward("failedPassword"));
				}
			}else{
				ActionMessage error = new ActionMessage("error.signup.passMismatch");
				errors.add("newPassword2",error);
				saveErrors(request,errors);
				return (mapping.findForward("failedPassword"));	
			}
		}
		if(action.equals("modifyAddress")){			
			Collection addresses = AddressBean.getAddresses(customer.getCustomerId());
			request.getSession().setAttribute("addresses",addresses);
			return mapping.findForward("showAddresses");
		}		
		if(action.equals("addAddress")){
			ActionErrors errors = myForm.validate(mapping, request);
    		if(errors.isEmpty()){				
				Collection addresses = AddressBean.createAddress(customer.getCustomerId(),
				address, city, convertInt(zip), country, region, firstName, lastName);			
				request.setAttribute("addresses",addresses);
						
				myForm.set("firstName","");
				myForm.set("lastName","");
				myForm.set("address","");
				myForm.set("zip","");
				myForm.set("city","");
				myForm.set("region","");
				myForm.set("country","");
			
				return mapping.findForward("showAddresses");
			}else{
				saveErrors(request, errors);
				return mapping.findForward("showAddresses");
			}
		}
		
		return mapping.findForward("success");
	}
	private int convertInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			result = 0;
		}
		return result;
	}
}