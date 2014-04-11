package id.ac.bsi.comcart.utils;
import id.ac.bsi.comcart.models.*;
import org.apache.struts.action.*;
import org.apache.struts.config.ModuleConfig;
import javax.servlet.*;
import java.util.*;
public class StartupManager implements PlugIn{
	ServletContext sc; 
	public void init(ActionServlet arg0, ModuleConfig arg1)
	throws ServletException{
		sc = arg0.getServletContext();		
		
		Collection countries = CountryBean.getAllCountry();
		sc.setAttribute("allCountry",countries);
		
		Collection rekenings = RekeningBean.getRekenings();
		sc.setAttribute("rekenings",rekenings);
	}
	public void destroy(){
	}
}