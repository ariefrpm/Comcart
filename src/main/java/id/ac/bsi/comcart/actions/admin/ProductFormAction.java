package id.ac.bsi.comcart.actions.admin;

import id.ac.bsi.comcart.models.CategoryBean;
import id.ac.bsi.comcart.models.CategoryDTO;
import id.ac.bsi.comcart.models.ProductBean;
import id.ac.bsi.comcart.models.ProductFormBean;
import id.ac.bsi.comcart.utils.ImageUtil;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.ecommerce.jpa.ProductDTO;
public class ProductFormAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		ProductFormBean myForm = (ProductFormBean)form;
		String name = myForm.getName();
		String categoryId = myForm.getCategoryId();
		String price = myForm.getPrice();
		String quantity = myForm.getQuantity();		
		String description = myForm.getDescription();
		String status = myForm.getStatus();
		String special = myForm.getSpecial();
		
		FormFile myFile = myForm.getTheFile();        
    	
//    	String image = "";
    
		String id = request.getParameter("id");
		String productId = request.getParameter("productId");
		
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
			ProductDTO product = ProductBean.getAProduct(productId);
			ProductFormBean productForm = new ProductFormBean();
			productForm.setName(product.getName());
			productForm.setCategoryId(""+product.getCategoryId());
			productForm.setPrice(""+product.getPrice());
			productForm.setSpecial(""+product.getSpecial());
			if(product.getSpecial()!=0){
				productForm.setPrice(""+product.getSpecial());
				productForm.setSpecial(""+product.getPrice());
			}			
			productForm.setQuantity(""+product.getQuantity());
			productForm.setImage(product.getImage());
			productForm.setDescription(product.getDescription());
			productForm.setStatus(""+product.getStatus());			
			
			request.setAttribute(mapping.getAttribute(), productForm);
			request.setAttribute("imageName",productForm.getImage());
			request.setAttribute("description",productForm.getDescription());
			request.getSession().setAttribute("update",productId);	
					
			return mapping.findForward("success");
		}
		if(id.equals("save")){
			
			String update = (String)request.getSession().getAttribute("update");
			
			if(name==null||name.equals("")||
			   categoryId==null||categoryId.equals("")||
			   price==null||price.equals("")){
					ActionErrors errors = new ActionErrors();   	
					if(name==null||name.equals("")){				
						ActionMessage error = new ActionMessage("error.name");
						errors.add("name",error);						
					}
					if(categoryId==null||categoryId.equals("")){				
						ActionMessage error = new ActionMessage("error.category");
						errors.add("categoryId",error);
					}
					if(price==null||price.equals("")){				
						ActionMessage error = new ActionMessage("error.price");
						errors.add("price",error);
					}
					saveErrors(request,errors);
					return mapping.findForward("failed");	
			}
			
//			String fileName = myFile.getFileName();
			String fileName = update + ImageUtil.IMAGE_JPG;
			
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
			
			ProductDTO product = new ProductDTO();
			product.setProductId(update);
			product.setName(name);
			product.setCategoryId(categoryId);
			product.setPrice(convertFloat(price));
			product.setSpecial(convertFloat(special));
			if(product.getSpecial()>0){
				product.setPrice(convertFloat(special));
				product.setSpecial(convertFloat(price));				
			}
			product.setQuantity(convertInt(quantity));
			product.setImage(fileName);
			product.setDescription(description);
			product.setStatus(convertInt(status));
			
				
			if(update!=null){				
				ProductBean.updateProduct(product);
				request.getSession().removeAttribute("update");
			}else{				
				ProductBean.insertProduct(product);
			}
			request.setAttribute("success","true");		
			return mapping.findForward("save");
		}
		if(id.equals("delete")){
			String[] paramValues = request.getParameterValues("delete");			
			for (int i=0; i<paramValues.length; i++){
				ProductBean.deleteProduct(paramValues[i]);				
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
	private float convertFloat(String value){
		float result = 0;
		try{
			result = Float.parseFloat(value);
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}
		return result;
	}
}