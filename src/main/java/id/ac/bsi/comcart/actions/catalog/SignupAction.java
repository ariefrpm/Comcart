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
import org.apache.struts.validator.DynaValidatorForm;

import com.ecommerce.jpa.CustomerDTO;

public class SignupAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		DynaValidatorForm myForm = (DynaValidatorForm)form;		
		String firstName = (String)myForm.get("firstName");
		String lastName = (String)myForm.get("lastName");
		String email = (String)myForm.get("email");
		String phone = (String)myForm.get("phone");
		
		String address = (String)myForm.get("address");
		String city = (String)myForm.get("city");
		
		int zip = convertInt((String)myForm.get("zip"));
			
		String country = (String)myForm.get("country");
		country = CountryBean.getCountryName(country);
		if(country==null||country.equals("-- Select --"))country="";		
		String region = (String)myForm.get("region");
		if(region==null)region="";
		
		String password = (String)myForm.get("password");
		String password2 = (String)myForm.get("password2");		
		
		if(password2.equals(password)){			
			CustomerDTO customer = CustomerBean.createCustomer(firstName, lastName, email, 
			phone, password);			
			Collection addresses = AddressBean.createAddress(customer.getCustomerId(),address,city,
			zip,country,region,firstName,lastName);
			if(customer!=null){
				request.getSession().setAttribute("customer",customer);
				return mapping.findForward("success");
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.signup.exists");
				errors.add("signup",error);
				saveErrors(request,errors);
				return (mapping.findForward("failed"));
			}
		}else{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.signup.passMismatch");
			errors.add("password2",error);
			saveErrors(request,errors);
			return (mapping.findForward("failed"));
		}
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