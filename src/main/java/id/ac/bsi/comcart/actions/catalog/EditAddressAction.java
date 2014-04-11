package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;

import id.ac.bsi.comcart.models.AddressBean;
import id.ac.bsi.comcart.models.AddressDTO;
import id.ac.bsi.comcart.models.CountryBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.ecommerce.jpa.CustomerDTO;

public class EditAddressAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		
		CustomerDTO customer = (CustomerDTO)request.getSession().getAttribute("customer");
		String button = request.getParameter("button");
		String addressId = request.getParameter("addressId");
		String cancel = request.getParameter("cancel");
				
		if(cancel!=null){     
      		return (mapping.findForward("showAddress"));
    	}
    	
    	DynaActionForm myForm = (DynaActionForm)form;		
		String firstName = (String)myForm.get("firstName");
		String lastName = (String)myForm.get("lastName");
		String address = (String)myForm.get("address");
		String city = (String)myForm.get("city");
		String zip = (String)myForm.get("zip");
		String region = (String)myForm.get("region");
		String country = (String)myForm.get("country");
		country = CountryBean.getCountryName(country);
    	if(country==null||country.equals("-- Select --"))country="";
		
    	if((button!=null)&&(button.equals("save"))){
    		ActionErrors errors = myForm.validate(mapping, request);
    		if(errors.isEmpty()){    			
    			int id = (Integer)request.getSession().getAttribute("addressId");
    			AddressDTO addressDto = new AddressDTO();
    			addressDto.setAddressId(id);
    			addressDto.setFirstName(firstName);
    			addressDto.setLastName(lastName);
    			addressDto.setAddress(address);
    			addressDto.setZip(convertInt(zip));
    			addressDto.setCity(city);
    			addressDto.setCountry(country);
    			addressDto.setRegion(region);    			
				AddressBean.updateAddress(addressDto);
				Collection addresses = AddressBean.getAddresses(customer.getCustomerId());
				request.setAttribute("addresses",addresses);
				request.getSession().removeAttribute("addressId");
								
				myForm.set("firstName","");
				myForm.set("lastName","");
				myForm.set("address","");
				myForm.set("zip","");
				myForm.set("city","");
				myForm.set("region","");
				myForm.set("country","");
			
				return mapping.findForward("save");
    		}else{
				saveErrors(request, errors);
				return mapping.findForward("failed");
			}	
    	}
		if((button!=null)&&(button.equals("edit"))){			
			AddressDTO addressDto = AddressBean.getAddress(convertInt(addressId));
			if(addressDto.getCustomerId()!=customer.getCustomerId()){
				return mapping.findForward("showAddress");
			}			
			myForm.set("firstName",addressDto.getFirstName());
			myForm.set("lastName",addressDto.getLastName());
			myForm.set("address",addressDto.getAddress());
			myForm.set("zip",""+addressDto.getZip());
			if(addressDto.getZip()==0)	myForm.set("zip","");				
			myForm.set("city",addressDto.getCity());			
			myForm.set("country",CountryBean.getCountryId(addressDto.getCountry()));
			myForm.set("region",addressDto.getRegion());
			myForm.set("password",customer.getPassword());
			myForm.set("email",customer.getEmail());
			myForm.set("phone",customer.getPhone());
			request.getSession().setAttribute("addressId",addressDto.getAddressId());
			return mapping.findForward("edit");
		}
		if((button!=null)&&(button.equals("delete"))){			
			AddressBean.deleteAddress(convertInt(addressId));
			Collection addresses = AddressBean.getAddresses(customer.getCustomerId());
			request.setAttribute("addresses",addresses);			
			return mapping.findForward("delete");
		}
		return mapping.findForward("showAddress");    	
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