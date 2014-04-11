package id.ac.bsi.comcart.actions.catalog;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.*;

public class LocaleAction extends Action {
    
	public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            String language = request.getParameter("method");
            if(language.equals("english")){
            	setLocale(request, Locale.ENGLISH);
            	return mapping.findForward("success");	
            }
            if(language.equals("indonesian")){
            	setLocale(request, Locale.ITALIAN);
            	return mapping.findForward("success");	
            }
            return mapping.findForward("success");	
            
    }
    protected void setLocale(HttpServletRequest request, Locale locale){
    	HttpSession session = request.getSession(true);
        session.setAttribute("org.apache.struts.action.LOCALE",locale);
    }
}