package id.ac.bsi.comcart.actions.admin;
import id.ac.bsi.comcart.models.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
public class ReportAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		AdminDTO admin = (AdminDTO)request.getSession().getAttribute("admin");
		if (admin==null){			
      		return (mapping.findForward("admin"));
    	}
		String id = request.getParameter("id");
		if(id.equals("viewed")){			
			Collection viewedProducts = ProductBean.getMostViewedProducts();	
			request.setAttribute("viewedProducts",viewedProducts);
		}
		if(id.equals("purchased")){
			Collection orderProducts = OrderProductBean.getBestsellersProducts();	
			request.setAttribute("orderProducts",orderProducts);
		}	
		return mapping.findForward("success");
	}
}