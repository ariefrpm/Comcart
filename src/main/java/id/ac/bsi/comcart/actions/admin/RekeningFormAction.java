package id.ac.bsi.comcart.actions.admin;
import id.ac.bsi.comcart.models.RekeningBean;
import id.ac.bsi.comcart.models.RekeningFormBean;
import id.ac.bsi.comcart.utils.ImageUtil;

import java.awt.Image;
import java.io.File;

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

import com.ecommerce.jpa.RekeningDTO;
public class RekeningFormAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{		
		RekeningFormBean myForm = (RekeningFormBean)form;
		String bank = myForm.getBank();
		String rekening = myForm.getRekening();
		String image = "";
		String account = myForm.getAccount();
		FormFile myFile = myForm.getTheFile();		
		
		String id = request.getParameter("id");
		String rekeningId = request.getParameter("rekeningId");		
		
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
			RekeningDTO rekeningDto = RekeningBean.getRekening(convertInt(rekeningId));
			RekeningFormBean rekeningForm = new RekeningFormBean();
			rekeningForm.setBank(rekeningDto.getBank());
			rekeningForm.setRekening(rekeningDto.getRekening());
			rekeningForm.setImage(rekeningDto.getImage());
			rekeningForm.setAccount(rekeningDto.getAccount());
			
			request.setAttribute(mapping.getAttribute(), rekeningForm);
			request.setAttribute("imageName",rekeningForm.getImage());
			
			request.getSession().setAttribute("update",rekeningId);			
			return mapping.findForward("success");
		}
		if(id.equals("save")){
			ActionErrors errors = new ActionErrors();
			if((bank==null||bank.equals(""))||
			   (rekening==null||rekening.equals(""))||
			   (account==null||account.equals(""))){
			   	if(bank==null||bank.equals("")){
			   		ActionMessage error = new ActionMessage("error.bank");
					errors.add("bank",error);	
			   	}
			   	if(rekening==null||rekening.equals("")){
			   		ActionMessage error = new ActionMessage("error.rekening");
					errors.add("rekening",error);	
			   	}
			   	if(account==null||account.equals("")){
			   		ActionMessage error = new ActionMessage("error.account");
					errors.add("account",error);	
			   	}
				
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
//		        	thumb.getThumbnail(100, ImageUtil.VERTICAL);
//		        	thumb.saveThumbnail(new File(filePath+"/thumb"+fileName), ImageUtil.IMAGE_JPEG);
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
				RekeningBean.updateRekening(convertInt(update),bank,rekening,image,account);				
				request.getSession().removeAttribute("update");
			}else{
				RekeningBean.insertRekening(bank,rekening,image,account);
			}
			request.setAttribute("success","true");		
			return mapping.findForward("save");
		}
		if(id.equals("delete")){
			String[] paramValues = request.getParameterValues("delete");			
			for (int i=0; i<paramValues.length; i++){
				RekeningBean.deleteRekening(convertInt(paramValues[i]));
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