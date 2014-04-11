package id.ac.bsi.comcart.models;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
public class AdminBean{
	public static AdminDTO validateAdmin(String name, String password){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		AdminDTO admin = null;
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM admin WHERE name = '"+
					  name + "' " + "AND password = '" + password + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()){				
				admin = new AdminDTO();
				admin.setAdminId(rs.getInt("admin-id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(rs != null)stmt.close();}catch(SQLException e){};
			try{if(rs != null)conn.close();}catch(SQLException e){};
		}
		return admin;
	}
}