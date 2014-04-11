package id.ac.bsi.comcart.models;

import id.ac.bsi.comcart.utils.ImageUtil;

import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ecommerce.jpa.EnumOrderStatus;
import com.ecommerce.jpa.OrderDTO;
import com.ecommerce.jpa.OrderProductDTO;
import com.ecommerce.jpa.ProductDTO;

public class OrderBean{	
	public static OrderDTO getOrder(String id){		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		OrderDTO order = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM status INNER JOIN `order` ON `status`.`status-id` = `order`.`status-id` where `order`.`order-id`='"+id+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				order = new OrderDTO();
				order.setOrderId(rs.getString("order-id"));
				order.setCustomerId(rs.getInt("customer-id"));
				order.setStatusId(rs.getInt("status-id"));
				order.setRekeningId(rs.getInt("rekening-id"));
				order.setDeliveryFirstName(rs.getString("delivery-firstname"));
				order.setDeliveryLastName(rs.getString("delivery-lastname"));
				order.setDeliveryAddress(rs.getString("delivery-address"));
				order.setDeliveryCity(rs.getString("delivery-city"));
				order.setDeliveryZip(rs.getInt("delivery-zip"));
				order.setDeliveryCountry(rs.getString("delivery-country"));
				order.setDeliveryRegion(rs.getString("delivery-region"));
				order.setComment(rs.getString("comment"));
				order.setTotal(rs.getFloat("total"));
				order.setOrderDate(rs.getString("order-date"));
				order.setStatus(rs.getString("name"));
				order.setOrderIdCode(order.getCustomerId()+""+order.getRekeningId()+""+order.getOrderId());
				order.setNumProducts(getNumProducts(order.getOrderId()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return order;	
	}
	public static Collection getOrders(){
		Collection orders = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT `order`.`order-id`,total,`order-date`,status.name as status,customer.firstname,customer.lastname,"+
					"`delivery-firstname`,`delivery-lastname`,`delivery-address`,`delivery-city`,`delivery-zip`,"+
					"`delivery-country`,`delivery-region`,comment,email,phone,rekening.bank as bank,rekening.rekening,rekening.account, `order`.`delivery-service`, `order`.`delivery-price` "+
					"FROM `order` INNER JOIN "+
					"`status` ON `order`.`status-id` = `status`.`status-id` INNER JOIN "+
					"customer ON `order`.`customer-id` = `customer`.`customer-id` LEFT JOIN "+
					"rekening ON `order`.`rekening-id` = rekening.`rekening-id` order by `order`.`order-id` desc";
			
			rs = stmt.executeQuery(query);
			while(rs.next()){
				OrderDTO order = new OrderDTO();
				order.setOrderId(rs.getString("order-id"));
				order.setDeliveryFirstName(rs.getString("delivery-firstname"));
				order.setDeliveryLastName(rs.getString("delivery-lastname"));
				order.setDeliveryAddress(rs.getString("delivery-address"));
				order.setDeliveryCity(rs.getString("delivery-city"));
				order.setDeliveryZip(rs.getInt("delivery-zip"));
				order.setDeliveryCountry(rs.getString("delivery-country"));
				order.setDeliveryRegion(rs.getString("delivery-region"));
				order.setComment(rs.getString("comment"));
				order.setTotal(rs.getFloat("total"));
				order.setOrderDate(rs.getString("order-date"));				
				order.setStatus(rs.getString("status"));
				order.setCustomerName(rs.getString("firstname")+" "+rs.getString("lastname"));
				order.setCustomerEmail(rs.getString("email"));
				order.setCustomerPhone(rs.getString("phone"));
				order.setBank(rs.getString("bank"));
				order.setRekening(rs.getString("rekening"));
				order.setAccount(rs.getString("account"));
				order.setDeliveryService(rs.getString("delivery-service"));
				order.setDeliveryPrice(rs.getFloat("delivery-price"));
				orders.add(order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return orders;	
	}	
	
	public static OrderDTO getOrderWithStatusCustomerRekening(String id){		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		OrderDTO order = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT `order`.`order-id`,`order`.`status-id`, `order`.total, `order-date`, status.name as status,customer.firstname,customer.lastname, "+
					"`delivery-firstname`,`delivery-lastname`,`delivery-address`,`delivery-city`,`delivery-zip`, "+
					"`delivery-country`,`delivery-region`,comment,email,phone,rekening.bank as bank,rekening.rekening,rekening.account,`order`.`delivery-service`, `order`.`delivery-price`, `order`.`pricing` "+
					"FROM `order` INNER JOIN "+
					"`status` ON `order`.`status-id` = `status`.`status-id` INNER JOIN "+
					"customer ON `order`.`customer-id` = `customer`.`customer-id` LEFT JOIN "+
					"rekening ON `order`.`rekening-id` = `rekening`.`rekening-id` where `order`.`order-id`='"+id+"'";
			
			rs = stmt.executeQuery(query);
			while(rs.next()){
				order = new OrderDTO();
				order.setOrderId(rs.getString("order-id"));				
				order.setDeliveryFirstName(rs.getString("delivery-firstname"));
				order.setDeliveryLastName(rs.getString("delivery-lastname"));
				order.setDeliveryAddress(rs.getString("delivery-address"));
				order.setDeliveryCity(rs.getString("delivery-city"));
				order.setDeliveryZip(rs.getInt("delivery-zip"));
				order.setDeliveryCountry(rs.getString("delivery-country"));
				order.setDeliveryRegion(rs.getString("delivery-region"));
				order.setComment(rs.getString("comment"));
				order.setTotal(rs.getFloat("total"));
				order.setOrderDate(rs.getString("order-date"));				
				order.setStatus(rs.getString("status"));
				order.setStatusId(rs.getInt("status-id"));
				order.setCustomerName(rs.getString("firstname")+" "+rs.getString("lastname"));
				order.setCustomerEmail(rs.getString("email"));
				order.setCustomerPhone(rs.getString("phone"));
				order.setBank(rs.getString("bank"));
				order.setRekening(rs.getString("rekening"));
				order.setAccount(rs.getString("account"));
				order.setDeliveryService(rs.getString("delivery-service"));
				order.setDeliveryPrice(rs.getFloat("delivery-price"));
				order.setPricing(rs.getInt("pricing")==0?false:true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return order;	
	}	
	public static Collection getOrderByCustomer(int id){
		Collection orders = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM status INNER JOIN `order` ON `status`.`status-id` = `order`.`status-id`"+
				 	" where `order`.`customer-id`="+id+
					" order by `order-date` desc";
			
			
			rs = stmt.executeQuery(query);
			while(rs.next()){
				OrderDTO order = new OrderDTO();
				order.setOrderId(rs.getString("order-id"));
				order.setCustomerId(rs.getInt("customer-id"));
				order.setStatusId(rs.getInt("status-id"));
				order.setRekeningId(rs.getInt("rekening-id"));
				order.setDeliveryFirstName(rs.getString("delivery-firstname"));
				order.setDeliveryLastName(rs.getString("delivery-lastname"));
				order.setDeliveryAddress(rs.getString("delivery-address"));
				order.setDeliveryCity(rs.getString("delivery-city"));
				order.setDeliveryZip(rs.getInt("delivery-zip"));
				order.setDeliveryCountry(rs.getString("delivery-country"));
				order.setDeliveryRegion(rs.getString("delivery-region"));
				order.setComment(rs.getString("comment"));
				order.setTotal(rs.getFloat("total"));
				order.setOrderDate(rs.getString("order-date"));
				order.setStatus(rs.getString("name"));
				order.setOrderIdCode(order.getCustomerId()+""+order.getRekeningId()+""+order.getOrderId());
				order.setNumProducts(getNumProducts(order.getOrderId()));
				orders.add(order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return orders;	
	}	
	
	/*public static OrderDTO addOrder(int customerId,int statusId,int bankId,
	String deliveryFirstName,String deliveryLastName,String deliveryAddress,String deliveryCity,
	int deliveryPostalCode,String deliveryCountry,String deliveryRegion,String comment,
	float total){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		String name="";
		OrderDTO order = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			
			query = "INSERT INTO `order` (`customer-id`,`status-id`,`rekening-id`,`delivery-firstname`,"+
					"`delivery-lastname`,`delivery-address`,`delivery-city`,`delivery-zip`,`delivery-country`,"+
					"`delivery-region`,comment,total,`order-date`)"+
					" values(" + customerId + "," + statusId + ""+
					","+bankId+",'"+deliveryFirstName+"','"+deliveryLastName+"'"+
					",'"+deliveryAddress+"','"+deliveryCity+"',"+deliveryPostalCode+""+
					",'"+deliveryCountry+"','"+deliveryRegion+"','"+comment+"',"+total+""+
					",now())";
			int result = stmt.executeUpdate(query);
			if(result==1){
				query = "SELECT * FROM `order`";
				rs = stmt.executeQuery(query);
				if(rs.last()){
					order = new OrderDTO();
					order.setOrderId(rs.getInt("order-id"));
					order.setCustomerId(rs.getInt("customer-id"));
					order.setStatusId(rs.getInt("status-id"));
					order.setRekeningId(rs.getInt("rekening-id"));
					order.setDeliveryFirstName(rs.getString("delivery-firstname"));
					order.setDeliveryLastName(rs.getString("delivery-lastname"));
					order.setDeliveryAddress(rs.getString("delivery-address"));
					order.setDeliveryCity(rs.getString("delivery-city"));
					order.setDeliveryZip(rs.getInt("delivery-zip"));
					order.setDeliveryCountry(rs.getString("delivery-country"));
					order.setDeliveryRegion(rs.getString("delivery-region"));
					order.setComment(rs.getString("comment"));
					order.setTotal(rs.getFloat("total"));
					order.setOrderDate(rs.getString("order-date"));
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return order;	
	}*/
	
	
	public static String addOrderComplete(OrderDTO order, Collection<OrderProductDTO> products) throws SQLException{
		
		String headerQuery = "INSERT INTO `order` " +
						     "(`order-id`,`customer-id`,`status-id`,`rekening-id`,`delivery-firstname`,`delivery-lastname`,`delivery-address`,`delivery-city`,`delivery-zip`,`delivery-country`,`delivery-region`,comment,total,`order-date`, `delivery-subregion`, `delivery-phone1`, `delivery-phone2`, `delivery-latitude`, `delivery-longitude`, `delivery-accuracy`, `delivery-price`, `delivery-service`) " +
						     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String detailQuery = "INSERT INTO `order-product` " +
						 	 "(`order-id`, `name`, image, price, quantity, `total-price`, `product-id`, special) " +
						 	 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String historyQuery = "INSERT INTO `order-history` " +
						  	  "(`order-id`, `status-id`, `date-added`, comment) " +
						 	  "VALUES (?, ?, ?, ?)";
		
		PreparedStatement insertHeader = null;
		PreparedStatement insertDetail = null;
		PreparedStatement insertHistory = null;
		Connection conn = null;
		
		String retVal = "Failed";
		Timestamp now = new Timestamp(new Date().getTime());
		String orderId = generateOrderNumber();
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			insertHeader = conn.prepareStatement(headerQuery);
			insertDetail = conn.prepareStatement(detailQuery);
			insertHistory = conn.prepareStatement(historyQuery);
			
			insertHeader.setString(1, orderId);
			insertHeader.setInt(2, CustomerBean.findCustomerId(order.getCustomerEmail()));
			insertHeader.setInt(3, order.getStatusId());
			insertHeader.setInt(4, RekeningBean.getRekeningIdByRekening(order.getRekening()));
			insertHeader.setString(5, order.getDeliveryFirstName());
			insertHeader.setString(6, order.getDeliveryLastName());
			insertHeader.setString(7, order.getDeliveryAddress());
			insertHeader.setString(8, order.getDeliveryCity());
			insertHeader.setInt(9, order.getDeliveryZip());
			insertHeader.setString(10, order.getDeliveryCountry());
			insertHeader.setString(11, order.getDeliveryRegion());
			insertHeader.setString(12, order.getComment());
			insertHeader.setFloat(13, order.getTotal());
			insertHeader.setTimestamp(14, now);
			insertHeader.setString(15, order.getDeliverySubRegion());
			insertHeader.setString(16, order.getDeliveryPhone1());
			insertHeader.setString(17, order.getDeliveryPhone2());
			insertHeader.setDouble(18, order.getDeliveryLatitude());
			insertHeader.setDouble(19, order.getDeliveryLongitude());
			insertHeader.setDouble(20, order.getDeliveryAccuracy());
			insertHeader.setFloat(21, order.getDeliveryPrice());
			insertHeader.setString(22, order.getDeliveryService());
			
			insertHeader.executeUpdate();
			
			for(OrderProductDTO product : products){
				insertDetail.setString(1, orderId);
				insertDetail.setString(2, product.getName());
				insertDetail.setString(3, product.getImage());
				insertDetail.setFloat(4, product.getPrice());
				insertDetail.setInt(5, product.getQuantity());
				insertDetail.setFloat(6, product.getTotalPrice());
				insertDetail.setString(7, product.getProductId());
				insertDetail.setFloat(8, product.getSpecial());
				insertDetail.executeUpdate();
			}
			
			insertHistory.setString(1, orderId);
			insertHistory.setInt(2, order.getStatusId());
			insertHistory.setTimestamp(3, now);
			insertHistory.setString(4, "");
			insertHistory.executeUpdate();
			
			conn.commit();
			retVal = "Success | "+orderId;
		} catch (Exception e ) {
			retVal = e.getMessage();
			e.printStackTrace();
			if (conn != null) {
			   try {
			       System.err.print("Transaction is being rolled back");
			       conn.rollback();
			   } catch(SQLException excep) {
			       excep.printStackTrace();
			   }
			}
		} finally {
			if (insertHeader != null) {
				insertHeader.close();
			}
			if (insertDetail != null) {
				insertDetail.close();
			}
			if (insertHistory != null) {
				insertHistory.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	
	public static String updateOrderPrice(OrderDTO order, List<OrderProductDTO> products) throws SQLException{
		
		String headerQuery = "UPDATE `order` " +
						     "SET total = ?, pricing = ? " +
						     "WHERE `order-id` = ? ";
		
		String detailQuery = "UPDATE `order-product` " +
						 	 "SET price = ?, quantity = ?, `total-price` = ?, special = ? " +
						 	 "WHERE `order-id` = ? AND `product-id` = ?";
		
		PreparedStatement insertHeader = null;
		PreparedStatement insertDetail = null;
		Connection conn = null;
		
		String retVal = "Failed";
//		Timestamp now = new Timestamp(new Date().getTime());
//		String orderId = generateOrderNumber();
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			insertHeader = conn.prepareStatement(headerQuery);
			insertDetail = conn.prepareStatement(detailQuery);
			
			insertHeader.setFloat(1, order.getTotal());
			insertHeader.setInt(2, 0);
			insertHeader.setString(3, order.getOrderId());
			insertHeader.executeUpdate();
			
			for(OrderProductDTO product : products){
				insertDetail.setFloat(1, product.getPrice());
				insertDetail.setInt(2, product.getQuantity());
				insertDetail.setFloat(3, product.getTotalPrice());
				insertDetail.setFloat(4, product.getSpecial());
				insertDetail.setString(5, product.getOrderId());
				insertDetail.setString(6, product.getProductId());
				insertDetail.executeUpdate();
			}
			
			
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
			       excep.printStackTrace();
			   }
			}
		} finally {
			if (insertHeader != null) {
				insertHeader.close();
			}
			if (insertDetail != null) {
				insertDetail.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	
	public static boolean addOrderCompleteWeb(OrderDTO order, Collection<ShoppingCartItem> products) throws SQLException{
		
		String headerQuery = "INSERT INTO `order` " +
						     "(`order-id`,`customer-id`,`status-id`,`rekening-id`,`delivery-firstname`,`delivery-lastname`,`delivery-address`,`delivery-city`,`delivery-zip`,`delivery-country`,`delivery-region`,comment,total,`order-date`) " +
						     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String detailQuery = "INSERT INTO `order-product` " +
						 	 "(`order-id`, `name`, image, price, quantity, `total-price`, `product-id`, special) " +
						 	 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String historyQuery = "INSERT INTO `order-history` " +
						  	  "(`order-id`, `status-id`, `date-added`, comment) " +
						 	  "VALUES (?, ?, ?, ?)";
		
		PreparedStatement insertHeader = null;
		PreparedStatement insertDetail = null;
		PreparedStatement insertHistory = null;
		Connection conn = null;
		
		boolean retVal = false;
		Timestamp now = new Timestamp(new Date().getTime());
		String orderId = generateOrderNumber();
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			insertHeader = conn.prepareStatement(headerQuery);
			insertDetail = conn.prepareStatement(detailQuery);
			insertHistory = conn.prepareStatement(historyQuery);
			
			insertHeader.setString(1, orderId);
			insertHeader.setInt(2, order.getCustomerId());
			insertHeader.setInt(3, order.getStatusId());
			insertHeader.setInt(4, order.getRekeningId());
			insertHeader.setString(5, order.getDeliveryFirstName());
			insertHeader.setString(6, order.getDeliveryLastName());
			insertHeader.setString(7, order.getDeliveryAddress());
			insertHeader.setString(8, order.getDeliveryCity());
			insertHeader.setInt(9, order.getDeliveryZip());
			insertHeader.setString(10, order.getDeliveryCountry());
			insertHeader.setString(11, order.getDeliveryRegion());
			insertHeader.setString(12, order.getComment());
			insertHeader.setFloat(13, order.getTotal());
			insertHeader.setTimestamp(14, now);
			insertHeader.executeUpdate();
			
			for(ShoppingCartItem product : products){
				insertDetail.setString(1, orderId);
				insertDetail.setString(2, product.getItemName());
				insertDetail.setString(3, product.getImage());
				insertDetail.setFloat(4, product.getPrice());
				insertDetail.setInt(5, product.getNumItems());
				insertDetail.setFloat(6, product.getTotalPrice());
				insertDetail.setString(7, product.getItemId());
				insertDetail.setFloat(8, product.getSpecial());
				insertDetail.executeUpdate();
			}
			
			insertHistory.setString(1, orderId);
			insertHistory.setInt(2, order.getStatusId());
			insertHistory.setTimestamp(3, now);
			insertHistory.setString(4, "");
			insertHistory.executeUpdate();
			
			conn.commit();
			retVal = true;
		} catch (Exception e ) {
			retVal = false;
			e.printStackTrace();
			if (conn != null) {
			   try {
			       System.err.print("Transaction is being rolled back");
			       conn.rollback();
			   } catch(SQLException excep) {
			       excep.printStackTrace();
			   }
			}
		} finally {
			if (insertHeader != null) {
				insertHeader.close();
			}
			if (insertDetail != null) {
				insertDetail.close();
			}
			if (insertHistory != null) {
				insertHistory.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	
	
	
	public static String addOrderPrescription(String email, List<Image> img, String path) throws SQLException{
		
		String headerQuery = "INSERT INTO `order` " +
						     "(`order-id`,`customer-id`,`status-id`,`pricing`,`order-date`) " +
						     "VALUES (?, ?, ?, ?, ?)";
		
		String detailQuery = "INSERT INTO `order-product` " +
						 	 "(`order-id`, `name`, image, `product-id`,`quantity`) " +
						 	 "VALUES (?, ?, ?, ?, ?)";
		
		String historyQuery = "INSERT INTO `order-history` " +
						  	  "(`order-id`, `status-id`, `date-added`, comment) " +
						 	  "VALUES (?, ?, ?, ?)";
		
		PreparedStatement insertHeader = null;
		PreparedStatement insertDetail = null;
		PreparedStatement insertHistory = null;
		Connection conn = null;
		
		String retVal = "Failed";
		Timestamp now = new Timestamp(new Date().getTime());
		String orderId = generateOrderNumber();
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);

			insertHeader = conn.prepareStatement(headerQuery);
			insertDetail = conn.prepareStatement(detailQuery);
			insertHistory = conn.prepareStatement(historyQuery);
			
			insertHeader.setString(1, orderId);
			insertHeader.setInt(2, CustomerBean.findCustomerId(email));
			insertHeader.setInt(3, EnumOrderStatus.NEWPRESCTIPTION.getCategoryCode());
			insertHeader.setInt(4, 1);
			insertHeader.setTimestamp(5, now);
			insertHeader.executeUpdate();
			
			int i = 1;
			for(Image image : img){
				String productName = orderId+"-"+i;
				insertDetail.setString(1, orderId);
				insertDetail.setString(2, productName);
				insertDetail.setString(3, productName+"."+ImageUtil.IMAGE_JPG);
				insertDetail.setString(4, productName);
				insertDetail.setInt(5, 1);
				insertDetail.executeUpdate();
				
				ImageUtil thumb = new ImageUtil(image);
//	        	thumb.getThumbnail(35, ImageUtil.VERTICAL);
//	        	thumb.saveThumbnail(new File(path+"/small"+productName), ImageUtil.IMAGE_JPG);
//	        	thumb.getThumbnail(100, ImageUtil.VERTICAL);
//	        	thumb.saveThumbnail(new File(path+"/thumb"+productName), ImageUtil.IMAGE_JPG);
	        	thumb.getOriginal();
	        	thumb.saveThumbnail(new File(path+"/med"+productName+"."+ImageUtil.IMAGE_JPG), ImageUtil.IMAGE_JPG);
	        	
				i++;
			}
			
			insertHistory.setString(1, orderId);
			insertHistory.setInt(2, EnumOrderStatus.NEWPRESCTIPTION.getCategoryCode());
			insertHistory.setTimestamp(3, now);
			insertHistory.setString(4, "");
			insertHistory.executeUpdate();
			
			conn.commit();
			retVal = "Success";
//			retVal = "Success | "+orderId;
		} catch (Exception e ) {
			retVal = e.getMessage();
			e.printStackTrace();
			if (conn != null) {
			   try {
			       System.err.print("Transaction is being rolled back");
			       conn.rollback();
			   } catch(SQLException excep) {
			       excep.printStackTrace();
			   }
			}
		} finally {
			if (insertHeader != null) {
				insertHeader.close();
			}
			if (insertDetail != null) {
				insertDetail.close();
			}
			if (insertHistory != null) {
				insertHistory.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	
	public static void deleteOrder(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "delete `order` where `order-id`='"+id+"'";			
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	private static int getNumProducts(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		int result = 0;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select count(`product-id`) as jumlah from `order-product` where `order-id`='"+id+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()){				
				result = rs.getInt("jumlah");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return result;	
	}
	
	public static String updateStatusNew(List<String> orders, int statusId, String comment) throws SQLException{
		String orderQuery = "UPDATE `order` " +
							 "SET `status-id` = ? " +
						 	 "WHERE `order-id` = ?";
		
		String historyQuery = "INSERT INTO `order-history` " +
						  	  "(`order-id`, `status-id`, `date-added`, comment) " +
						 	  "VALUES (?, ?, ?, ?)";
		
		PreparedStatement updateOrder = null;
		PreparedStatement updateHistory = null;
		
		Connection conn = null;
		
		String retVal = "Failed";
		Timestamp now = new Timestamp(new Date().getTime());
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			updateOrder = conn.prepareStatement(orderQuery);
			updateHistory = conn.prepareStatement(historyQuery);
			
			for(String orderId : orders){
				updateOrder.setInt(1, statusId);
				updateOrder.setString(2, orderId);
				updateOrder.executeUpdate();
				
				updateHistory.setString(1, orderId);
				updateHistory.setInt(2, statusId);
				updateHistory.setTimestamp(3, now);
				updateHistory.setString(4, comment);
				updateHistory.executeUpdate();
			}
			
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
			      excep.printStackTrace();
			  }
			}
		} finally {
			if (updateOrder != null) {
				updateOrder.close();
			}
			if (updateHistory != null) {
				updateHistory.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	
	public static String updatePrescriptionConfirm(OrderDTO order, Collection<OrderProductDTO> products) throws SQLException{
		
		String deleteHeaderQuery = "DELETE FROM `order` WHERE `order-id` = ? AND `customer-id` = ?";
		String deleteDetailQuery = "DELETE FROM `order-product` WHERE `product-id` = ? AND `order-id` = ?";
		
		String headerQuery = "INSERT INTO `order` " +
						     "(`order-id`,`customer-id`,`status-id`,`rekening-id`,`delivery-firstname`,`delivery-lastname`,`delivery-address`,`delivery-city`,`delivery-zip`,`delivery-country`,`delivery-region`,comment,total,`order-date`, `delivery-subregion`, `delivery-phone1`, `delivery-phone2`, `delivery-latitude`, `delivery-longitude`, `delivery-accuracy`, `delivery-price`, `delivery-service`) " +
						     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String detailQuery = "INSERT INTO `order-product` " +
						 	 "(`order-id`, `name`, image, price, quantity, `total-price`, `product-id`, special) " +
						 	 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String historyQuery = "INSERT INTO `order-history` " +
						  	  "(`order-id`, `status-id`, `date-added`, comment) " +
						 	  "VALUES (?, ?, ?, ?)";
		
		PreparedStatement insertHeader = null;
		PreparedStatement insertDetail = null;
		PreparedStatement insertHistory = null;
		PreparedStatement deleteHeader = null;
		PreparedStatement deleteDetail = null;
		
		Connection conn = null;
		
		String retVal = "Failed";
		Timestamp now = new Timestamp(new Date().getTime());
		String orderId = generateOrderNumber();
		int custId = CustomerBean.findCustomerId(order.getCustomerEmail());
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			insertHeader = conn.prepareStatement(headerQuery);
			insertDetail = conn.prepareStatement(detailQuery);
			insertHistory = conn.prepareStatement(historyQuery);
			deleteHeader = conn.prepareStatement(deleteHeaderQuery);
			deleteDetail = conn.prepareStatement(deleteDetailQuery);
			
			insertHeader.setString(1, orderId);
			insertHeader.setInt(2, custId);
			insertHeader.setInt(3, order.getStatusId());
			insertHeader.setInt(4, RekeningBean.getRekeningIdByRekening(order.getRekening()));
			insertHeader.setString(5, order.getDeliveryFirstName());
			insertHeader.setString(6, order.getDeliveryLastName());
			insertHeader.setString(7, order.getDeliveryAddress());
			insertHeader.setString(8, order.getDeliveryCity());
			insertHeader.setInt(9, order.getDeliveryZip());
			insertHeader.setString(10, order.getDeliveryCountry());
			insertHeader.setString(11, order.getDeliveryRegion());
			insertHeader.setString(12, order.getComment());
			insertHeader.setFloat(13, order.getTotal());
			insertHeader.setTimestamp(14, now);
			insertHeader.setString(15, order.getDeliverySubRegion());
			insertHeader.setString(16, order.getDeliveryPhone1());
			insertHeader.setString(17, order.getDeliveryPhone2());
			insertHeader.setDouble(18, order.getDeliveryLatitude());
			insertHeader.setDouble(19, order.getDeliveryLongitude());
			insertHeader.setDouble(20, order.getDeliveryAccuracy());
			insertHeader.setFloat(21, order.getDeliveryPrice());
			insertHeader.setString(22, order.getDeliveryService());
			insertHeader.executeUpdate();
			
			for(OrderProductDTO product : products){
				
				deleteHeader.setString(1, product.getOrderId());
				deleteHeader.setInt(2, custId);
				deleteHeader.executeUpdate();
				
				deleteDetail.setString(1, product.getProductId());
				deleteDetail.setString(2, product.getOrderId());
				deleteDetail.executeUpdate();
				
				insertDetail.setString(1, orderId);
				insertDetail.setString(2, product.getName());
				insertDetail.setString(3, product.getImage());
				insertDetail.setFloat(4, product.getPrice());
				insertDetail.setInt(5, product.getQuantity());
				insertDetail.setFloat(6, product.getTotalPrice());
				insertDetail.setString(7, product.getProductId());
				insertDetail.setFloat(8, product.getSpecial());
				insertDetail.executeUpdate();
			}
			
			
			
			insertHistory.setString(1, orderId);
			insertHistory.setInt(2, order.getStatusId());
			insertHistory.setTimestamp(3, now);
			insertHistory.setString(4, "");
			insertHistory.executeUpdate();
			
			conn.commit();
			retVal = "Success | "+orderId;
		} catch (Exception e ) {
			retVal = e.getMessage();
			e.printStackTrace();
			if (conn != null) {
			  try {
			      System.err.print("Transaction is being rolled back");
			      conn.rollback();
			  } catch(SQLException excep) {
			      excep.printStackTrace();
			  }
			}
		} finally {
			if (insertHeader != null) {
				insertHeader.close();
			}
			if (insertDetail != null) {
				insertDetail.close();
			}
			if (insertHistory != null) {
				insertHistory.close();
			}
			if(conn != null){
				conn.setAutoCommit(true);
			}
		}
		return retVal;
	}
	
	private static String generateOrderNumber(){
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY)==0?1:now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE)==0?1:now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND)==0?1:now.get(Calendar.SECOND);

		int sec = hour * minute * second;

		String ret = new SimpleDateFormat("yyDDD", Locale.US).format(now.getTime());
		return "IN"+ret+""+String.format("%05d", sec);
	}
}