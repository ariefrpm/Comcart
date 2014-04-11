package id.ac.bsi.comcart.models;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import java.util.*;
public class OrderHistoryBean{	
	public static void insertOrderHistory(String orderId, int statusId, String comment){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "insert into `order-history`(`order-id`,`status-id`,`date-added`,comment) "+
					"values ('"+orderId+"',"+statusId+",now(),'"+comment+"')";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static Collection getOrderHistories(String id){
		Collection orderHistories = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM status s INNER JOIN `order-history` c "+
					"ON s.`status-id` = c.`status-id` where `order-id`='"+id+"'";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				OrderHistoryDTO orderHistory = new OrderHistoryDTO();
				orderHistory.setOrderHistoryId(rs.getInt("order-history-id"));
				orderHistory.setOrderId(rs.getString("order-id"));
				orderHistory.setStatusId(rs.getInt("status-id"));
				orderHistory.setDateAdded(rs.getString("date-added"));
				orderHistory.setComment(rs.getString("comment"));
				orderHistory.setStatus(rs.getString("name"));
				orderHistories.add(orderHistory);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return orderHistories;	
	}	
}