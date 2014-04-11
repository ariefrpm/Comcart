package id.ac.bsi.comcart.actions.admin;
import id.ac.bsi.comcart.models.*;
import id.ac.bsi.comcart.utils.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.util.*;
import java.io.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import org.apache.struts.upload.FormFile;
public class CategoryFormAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{		
		CategoryFormBean myForm = (CategoryFormBean)form;
		String name = myForm.getName();
		String parent = myForm.getParent();
		String image = "";
		String description = myForm.getDescription();
		FormFile myFile = myForm.getTheFile();
		
		String id = request.getParameter("id");
		String categoryId = request.getParameter("categoryId");
		
		Collection categories = CategoryBean.getSubCategories("0");
		request.setAttribute("categories",categories);
		if(id==null){
			return mapping.findForward("save");
		}
		if(id.equals("insert")){
			return mapping.findForward("success");
		}
		if(id.equals("cancel")){
			return mapping.findForward("save");
		}
		if(id.equals("edit")){
			CategoryDTO category = CategoryBean.getCategory(categoryId);
			CategoryFormBean categoryForm = new CategoryFormBean();
			categoryForm.setName(category.getName());
			categoryForm.setParent(""+category.getParentId());
			categoryForm.setImage(category.getImage());
			categoryForm.setDescription(category.getDescription());
			
			request.setAttribute(mapping.getAttribute(), categoryForm);
			request.setAttribute("imageName",categoryForm.getImage());
			
			request.getSession().setAttribute("update",categoryId);			
			return mapping.findForward("success");
		}
		if(id.equals("save")){			
			if(name==null||name.equals("")){
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.name");
				errors.add("name",error);
				saveErrors(request,errors);
				return mapping.findForward("failed");
			}
			
			String fileName = myFile.getFileName();
			image = fileName;
    		String filePath = getServlet().getServletContext().getRealPath("/") +"images";    
    		if(!fileName.equals("")){          	
        		File fileToCreate = new File(filePath, fileName);        
        		if(!fileToCreate.exists()){
        			Image imageF = ImageIO.read(myFile.getInputStream());
		        	ImageUtil thumb = new ImageUtil(imageF);
		        	thumb.getThumbnail(35, ImageUtil.VERTICAL);
		        	thumb.saveThumbnail(new File(filePath+"/small"+fileName), ImageUtil.IMAGE_JPEG);		        	
		        	thumb.getThumbnail(100, ImageUtil.VERTICAL);
		        	thumb.saveThumbnail(new File(filePath+"/thumb"+fileName), ImageUtil.IMAGE_JPEG);
		        	thumb.getThumbnail(250, ImageUtil.HORIZONTAL);
		        	thumb.saveThumbnail(new File(filePath+"/med"+fileName), ImageUtil.IMAGE_JPEG);
        		}
    		}
    		
			String update = (String)request.getSession().getAttribute("update");
			if(update!=null){
				CategoryBean.updateCategory(update, name, parent, image, description);
				request.getSession().removeAttribute("update");
			}else{				
				CategoryBean.insertCategory(name, parent, image, description);
			}
			request.setAttribute("success","true");		
			return mapping.findForward("save");
		}
		if(id.equals("delete")){
			String[] paramValues = request.getParameterValues("delete");			
			for (int i=0; i<paramValues.length; i++){
				CategoryBean.deleteCategory(paramValues[i]);
			}
			request.setAttribute("success","true");
			return mapping.findForward("save");
		}
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