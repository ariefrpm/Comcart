package id.ac.bsi.comcart.actions.catalog;

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

public class LoginAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		CustomerDTO exist = (CustomerDTO)request.getSession().getAttribute("customer");
		if (exist!=null){     
      		return (mapping.findForward("cancel"));
    	}		
		CustomerDTO customer = CustomerBean.validateCustomer((String)((DynaValidatorForm)form).get("email"),
														  (String)((DynaValidatorForm)form).get("password"));
		if(customer!=null){
			request.getSession().setAttribute("customer",customer);			
			return mapping.findForward("success");
		}else{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.login.invalid");
			errors.add("login",error);
			saveErrors(request, errors);
			return mapping.findForward("failed");
		}		
	}
}