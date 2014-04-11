package id.ac.bsi.comcart.actions.catalog;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class GetRegion extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList list = new ArrayList();
		try{
			list.add("<select name=\"region\">");
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/comcartDB");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM region where country_id="+id;
			rs = stmt.executeQuery(query);
			while(rs.next()){				
				list.add("<option value=\""+rs.getString("name")+"\">"+rs.getString("name")+"</option>");
			}
			list.add("</select>");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(rs != null)stmt.close();}catch(SQLException e){};
			try{if(rs != null)conn.close();}catch(SQLException e){};
		}
		for(int i=0; i<list.size(); i++){
			out.println((String)list.get(i));
		}
	}
}