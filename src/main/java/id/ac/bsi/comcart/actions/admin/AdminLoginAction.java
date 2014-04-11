package id.ac.bsi.comcart.actions.admin;
import id.ac.bsi.comcart.models.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.validator.*;
public class AdminLoginAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		AdminDTO exist = (AdminDTO)request.getSession().getAttribute("admin");
		if (exist!=null){
      		return (mapping.findForward("cancel"));
    	}
    	String name = request.getParameter("name");
    	String password = request.getParameter("password");
    	if((name.length()==0||name==null)||(password.length()==0||password==null)){
    		request.setAttribute("warning","true");
    		return mapping.findForward("failed");
    	}
		AdminDTO admin = AdminBean.validateAdmin(name,password);
		if(admin!=null){
			request.getSession().setAttribute("admin",admin);
			return mapping.findForward("success");
		}else{
			request.setAttribute("warning","true");
    		return mapping.findForward("failed");
		}		
	}
}