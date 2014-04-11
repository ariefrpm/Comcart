package id.ac.bsi.comcart.actions.admin;
import id.ac.bsi.comcart.models.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
public class ProductListAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		AdminDTO admin = (AdminDTO)request.getSession().getAttribute("admin");
		if (admin==null){
      		return (mapping.findForward("admin"));
    	}
		request.getSession().removeAttribute("update");		
		Collection products = ProductBean.getAllProducts();
		request.setAttribute("products",products);		
		return mapping.findForward("success");
	}
}