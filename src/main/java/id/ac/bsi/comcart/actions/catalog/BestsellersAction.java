package id.ac.bsi.comcart.actions.catalog;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import id.ac.bsi.comcart.models.*;
import java.util.*;
public class BestsellersAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{		
		Collection bestsellersProduct = OrderProductBean.getBestsellersProducts();
		request.setAttribute("bestsellers",bestsellersProduct);
		return mapping.findForward("success");
	}
}