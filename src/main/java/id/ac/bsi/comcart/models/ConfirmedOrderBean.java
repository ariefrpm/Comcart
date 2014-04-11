package id.ac.bsi.comcart.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ecommerce.jpa.ConfirmedOrderDTO;
import com.ecommerce.jpa.EnumOrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
public class ConfirmedOrderBean{
	public static void addConfirmedOrder(String orderId, int customerId, String transferDate, float price){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "insert into `confirmed-order`(`order-id`,`customer-id`,`transfer-date`,price) values ('"+orderId+"',"+customerId+",'"+transferDate+"',"+price+")";
			int result = stmt.executeUpdate(query);			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static String addConfirmedOrder(ConfirmedOrderDTO confirmedOrder) throws SQLException{
		String orderQuery = "UPDATE `order` " +
					     	 "SET `status-id` = ?, `rekening-id` = ? " +
					     	 "WHERE `order-id` = ?";
		
		String confirmedQuery = "INSERT INTO `confirmed-order` " +
					 	 	 "(`order-id`, `customer-id`, `transfer-date`, price, `transfer-account-bank`, `transfer-account-name`, `transfer-account-number`) " +
					 	 	 "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		String statusQuery = "INSERT INTO `order-history` " +
					  	     "(`order-id`, `status-id`, `date-added`, comment) " +
					 	     "VALUES (?, ?, ?, ?)";
		
		PreparedStatement updateOrder = null;
		PreparedStatement insertConfirmed = null;
		PreparedStatement insertStatus = null;
		Connection conn = null;
		
		String retVal = "Failed";
		Timestamp now = new Timestamp(new Date().getTime());
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			updateOrder = conn.prepareStatement(orderQuery);
			insertConfirmed = conn.prepareStatement(confirmedQuery);
			insertStatus = conn.prepareStatement(statusQuery);
			
			updateOrder.setInt(1, EnumOrderStatus.PAYMENT.getCategoryCode());
			updateOrder.setInt(2, confirmedOrder.getRekeningId());
			updateOrder.setString(3, confirmedOrder.getOrderId());
			updateOrder.executeUpdate();
			
			insertConfirmed.setString(1, confirmedOrder.getOrderId());
			insertConfirmed.setInt(2, CustomerBean.findCustomerId(confirmedOrder.getCustomerEmail()));
			insertConfirmed.setString(3, confirmedOrder.getTransferDate());
			insertConfirmed.setFloat(4, confirmedOrder.getPrice());
			insertConfirmed.setString(5, confirmedOrder.getAccountBank());
			insertConfirmed.setString(6, confirmedOrder.getAccountName());
			insertConfirmed.setString(7, confirmedOrder.getAccountNumber());
			insertConfirmed.executeUpdate();
			
			insertStatus.setString(1, confirmedOrder.getOrderId());
			insertStatus.setInt(2, EnumOrderStatus.PAYMENT.getCategoryCode());
			insertStatus.setTimestamp(3, now);
			insertStatus.setString(4, "");
			insertStatus.executeUpdate();
			
			conn.commit();
			retVal = "Success";
		} catch (Exception e ) {
			retVal = e.getMessage();
			e.printStackTrace();
			if (conn != null) {
			  try {
			      System.err.print("Transaction is being rolled back");
			      conn.rollback();
			  } catch(SQLException excep) {
				  retVal = excep.getMessage();
			      excep.printStackTrace();
			  }
			}
		} finally {
			if (updateOrder != null) {
				updateOrder.close();
			}
			if (insertConfirmed != null) {
				insertConfirmed.close();
			}
			if (insertStatus != null) {
				insertStatus.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	public static Collection getConfirmedOrders(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		Collection confirmedOrders = new ArrayList();
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from `confirmed-order` order by `confirmed-order-id` desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ConfirmedOrderDTO confirmed = new ConfirmedOrderDTO();
				confirmed.setConfirmedOrderId(rs.getInt("confirmed-order-id"));
				confirmed.setOrderId(rs.getString("order-id"));
				confirmed.setTransferDate(rs.getString("transfer-date"));
				confirmed.setPrice(rs.getFloat("price"));
				confirmedOrders.add(confirmed);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return confirmedOrders;
	}
	public static ConfirmedOrderDTO getConfirmedOrder(String orderId){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		ConfirmedOrderDTO confirmed = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from `confirmed-order` WHERE `order-id` = '"+orderId+"'";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				confirmed = new ConfirmedOrderDTO();
				confirmed.setConfirmedOrderId(rs.getInt("confirmed-order-id"));
				confirmed.setOrderId(rs.getString("order-id"));
				confirmed.setTransferDate(rs.getString("transfer-date"));
				confirmed.setPrice(rs.getFloat("price"));
				
				confirmed.setAccountBank(rs.getString("transfer-account-bank"));
				confirmed.setAccountName(rs.getString("transfer-account-name"));
				confirmed.setAccountNumber(rs.getString("transfer-account-number"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return confirmed;
	}
}