package id.ac.bsi.comcart.actions.catalog;
import id.ac.bsi.comcart.models.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
public class SearchAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String query = request.getParameter("query");
		if(query.trim().equals(""))return mapping.findForward("failed");
		
		Collection searchProducts = ProductBean.getSearch(query);
		request.setAttribute("searchProducts",searchProducts);
		return mapping.findForward("success");
	}
}