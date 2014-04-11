package id.ac.bsi.comcart.actions.admin;
import id.ac.bsi.comcart.models.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
public class CategoryListAction extends Action{
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
		Collection categories = new ArrayList();		
		Collection parentCategories = CategoryBean.getSubCategories("0");
		CategoryDTO category;
		for(Iterator it=parentCategories.iterator();it.hasNext();){
			Object o = it.next();
			category = (CategoryDTO)o;
			categories.add(category);
			Collection subCategories = CategoryBean.getSubCategories(category.getCategoryId());
			CategoryDTO subCategory;
			for(Iterator subIt=subCategories.iterator();subIt.hasNext();){
				Object subO = subIt.next();
				subCategory = (CategoryDTO)subO;
				subCategory.setName(category.getName()+" > "+subCategory.getName());
				categories.add(subCategory);
			}
		}
		request.setAttribute("categories",categories);
		return mapping.findForward("success");
	}
}