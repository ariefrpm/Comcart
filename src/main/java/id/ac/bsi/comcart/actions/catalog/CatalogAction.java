package id.ac.bsi.comcart.actions.catalog;

import id.ac.bsi.comcart.models.CategoryBean;
import id.ac.bsi.comcart.models.CategoryDTO;
import id.ac.bsi.comcart.models.ProductBean;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ecommerce.jpa.ProductDTO;

public class CatalogAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String id = request.getParameter("catId");
		if(id==null){
			return mapping.findForward("failed");
		}
		String[] ids = id.split("_");
		id = ids[ids.length-1];		
//		int catId = convertInt(id);		
		Collection products = ProductBean.getProduct(id);
		String name = CategoryBean.getCategoryName(id);
		ProductDTO product;
		for(Iterator subIt=products.iterator();subIt.hasNext();){			
			Object subO = subIt.next();
			product = (ProductDTO)subO;
			CategoryDTO category = CategoryBean.getCategory(product.getCategoryId());
			if(category.getParentId().equals("0")){
				product.setCatId(product.getCategoryId()+"");
			}else{
				product.setCatId(category.getParentId()+"_"+product.getCategoryId());	
			}			
		}
		Collection categories = CategoryBean.getSubCategories(id);
		request.setAttribute("products",products);
		request.setAttribute("categoryBodies",categories);
		request.setAttribute("title",name);
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