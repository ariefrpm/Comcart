package id.ac.bsi.comcart.models;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

import com.ecommerce.jpa.GCMMessage;

import java.util.*;
public class StatusBean{
	public static List<StatusDTO> getStatus(){
		List<StatusDTO> status = new ArrayList<StatusDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM status";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				StatusDTO stat = new StatusDTO();
				stat.setStatusId(rs.getInt("status-id"));
				stat.setName(rs.getString("name"));
				status.add(stat);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(rs != null)stmt.close();}catch(SQLException e){};
			try{if(rs != null)conn.close();}catch(SQLException e){};
		}
		return status;	
	}
	public static String getInboxDetail(int inboxId){  //I KNOW ITS WEIRD
		String status = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT `inbox_detail` FROM notification WHERE inbox_id = "+inboxId;
			rs = stmt.executeQuery(query);
			while(rs.next()){
				status = rs.getString("inbox_detail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(rs != null)stmt.close();}catch(SQLException e){};
			try{if(rs != null)conn.close();}catch(SQLException e){};
		}
		return status;	
	}
}