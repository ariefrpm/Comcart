package id.ac.bsi.comcart.actions.catalog;
import id.ac.bsi.comcart.models.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
public class AddToCartAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{		
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute("shoppingCart");
		if(shoppingCart==null){
			shoppingCart = new ShoppingCart();
			session.setAttribute("shoppingCart",shoppingCart);
		}
		String id = request.getParameter("id");
		if(id!=null){
			String quantity = request.getParameter("quantity");
			shoppingCart.addItem(id);
			shoppingCart.setNumItems(id, convertInt(quantity));
		}else{
			Enumeration paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName = (String)paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				shoppingCart.setNumItems(paramName, convertInt(paramValue));
			}
			return mapping.findForward("showCart");
		}
		return mapping.findForward("success");
	}
	private int convertInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			result = -1;
		}
		return result;
	}
}