package id.ac.bsi.comcart.actions.catalog;

import java.util.Collection;
import java.util.Iterator;

import id.ac.bsi.comcart.models.CategoryBean;
import id.ac.bsi.comcart.models.CategoryDTO;
import id.ac.bsi.comcart.models.ProductBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.ProductDTO;

public class HomeAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{		
		Collection products = ProductBean.getLatestProducts();
		ProductDTO product;
		for(Iterator subIt=products.iterator();subIt.hasNext();){			
			Object subO = subIt.next();
			product = (ProductDTO)subO;
			CategoryDTO category = CategoryBean.getCategory(product.getCategoryId());
			product.setCatId(category.getParentId()+"_"+product.getCategoryId());
		}
		request.setAttribute("products",products);
		return mapping.findForward("success");
	}
}