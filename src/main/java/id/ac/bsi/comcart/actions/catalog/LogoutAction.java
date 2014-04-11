package id.ac.bsi.comcart.actions.catalog;
import javax.servlet.http.*;
import org.apache.struts.action.*;
public class LogoutAction extends Action{
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception{
		request.getSession().invalidate();
		return (mapping.findForward("success"));
	}
}