package id.ac.bsi.comcart.actions.catalog;

import id.ac.bsi.comcart.models.ProductBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.ProductDTO;

public class ProductDetailsAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String id = request.getParameter("id");		
		ProductDTO productDetail = ProductBean.getAProduct(id);
		ProductBean.updateView(id);
		request.getSession().setAttribute("productDetail",productDetail);
		return mapping.findForward("success");		
	}
	private int convertInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}
		return result;
	}
}