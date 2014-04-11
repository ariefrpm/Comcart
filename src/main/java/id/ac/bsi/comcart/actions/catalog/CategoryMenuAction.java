package id.ac.bsi.comcart.actions.catalog;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import id.ac.bsi.comcart.models.*;
import java.util.*;
public class CategoryMenuAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		String id = request.getQueryString();
		String subId = "0";
		if(id!=null){
			if(id.indexOf("catId")!=-1){
				int sep = id.indexOf("catId");
				id = id.substring(sep+6);			
				sep = id.lastIndexOf("_");
				if(sep>0){
					subId = id.substring(sep+1);
					id = id.substring(0,sep);	
				}		
			}
		}
//		int catId = convertInt(id);
//		int subCatId = convertInt(subId);
		Collection categories = new ArrayList();		
		Collection parentCategories = CategoryBean.getSubCategories("0");
		CategoryDTO category;
		categories.add("<ul>");
		for(Iterator it=parentCategories.iterator();it.hasNext();){
			Object o = it.next();
			category = (CategoryDTO)o;
			categories.add("<li>");
			String url = request.getContextPath()+"/actions/catalog/catalog.do?catId=";
			
			categories.add("<a href=\""+url+category.getCategoryId()+"\">");
			if(category.getCategoryId().equals(id) && subId.equals("0"))categories.add("<b>");
			categories.add(category.getName());
			if(category.getCategoryId().equals(id) && subId.equals("0"))categories.add("</b>");
			categories.add("</a>");
			
			if(category.getCategoryId().equals(id)){
				Collection subCategories = CategoryBean.getSubCategories(id);
				if(!subCategories.isEmpty())categories.add("<ul>");
				CategoryDTO subCategory;
				for(Iterator subIt=subCategories.iterator();subIt.hasNext();){
					categories.add("<li>");
					Object subO = subIt.next();
					subCategory = (CategoryDTO)subO;
					
					categories.add("<a href=\""+url+category.getCategoryId()+"_"+subCategory.getCategoryId()+"\">");
					if(subCategory.getCategoryId().equals(subId))categories.add("<b>");
					categories.add(subCategory.getName());
					if(subCategory.getCategoryId().equals(subId))categories.add("</b>");
					categories.add("</a>");
						
					categories.add("</li>");
				}
				if(!subCategories.isEmpty())categories.add("</ul>");
			}			
			categories.add("</li>");
		}
		categories.add("</ul>");
		request.setAttribute("categories",categories);
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