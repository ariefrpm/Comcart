package id.ac.bsi.comcart.actions.catalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.CustomerDTO;

public class LoginMenuAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		CustomerDTO customer = (CustomerDTO)request.getSession().getAttribute("customer");
		if(customer==null){
			return mapping.findForward("failed");
		}
		return mapping.findForward("success");
	}
}