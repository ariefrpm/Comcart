package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;

import id.ac.bsi.comcart.models.AddressBean;
import id.ac.bsi.comcart.models.CountryBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.ecommerce.jpa.CustomerDTO;

public class AddAddressAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		CustomerDTO customer = (CustomerDTO)request.getSession().getAttribute("customer");		
		DynaValidatorForm myForm = (DynaValidatorForm)form;
		String firstName = (String)myForm.get("firstName");
		String lastName = (String)myForm.get("lastName");
		String address = (String)myForm.get("address");
		int zip = convertInt((String)myForm.get("zip"));
		String city = (String)myForm.get("city");		
		String country = (String)myForm.get("country");
		country = CountryBean.getCountryName(country);
		if(country==null||country.equals("-- Select --"))country="";
		String region = (String)myForm.get("region");
		if(region==null)region="";		
		
		if(customer!=null){			
			Collection addresses = AddressBean.createAddress(customer.getCustomerId(),address,
			city,zip,country,region,firstName,lastName);
			request.setAttribute("addresses",addresses);
			myForm.set("firstName","");
			myForm.set("lastName","");
			myForm.set("address","");
			myForm.set("zip","");
			myForm.set("city","");
			myForm.set("region","");
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failed");
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