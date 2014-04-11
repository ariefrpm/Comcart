package id.ac.bsi.comcart.actions.catalog;
import id.ac.bsi.comcart.models.*;
import id.ac.bsi.comcart.utils.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import java.util.*;
public class ForgetAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String email = (String)((DynaValidatorForm)form).get("email");		
		String password = CustomerBean.checkEmail(email);
		if(password==null||password.equals("")){
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.email.invalid");
			errors.add("login",error);
			saveErrors(request, errors);
			return mapping.findForward("failed");
		}			
		SendMail.sendMail("your password is "+password,"Forget Password",email,"arief@onepiece.com","127.0.0.1");
		return mapping.findForward("success");
	}	
}