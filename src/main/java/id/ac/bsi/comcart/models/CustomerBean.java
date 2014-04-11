package id.ac.bsi.comcart.models;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ecommerce.jpa.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
public class CustomerBean{
	public static CustomerDTO getCustomer(int id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		CustomerDTO customer = null;
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from customer where `customer-id`="+id+"");
			if(rs.next()){
				customer = new CustomerDTO();
				customer.setCustomerId(rs.getInt("customer-id"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setDateCreated(rs.getString("date-created"));
				customer.setNumLogins(rs.getInt("num-logins"));
				customer.setLastLogin(rs.getString("last-login"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customer;
	}
	public static Collection getCustomers(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		Collection customers = new ArrayList();
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from customer order by `date-created` desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				CustomerDTO customer = new CustomerDTO();
				customer.setCustomerId(rs.getInt("customer-id"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setDateCreated(rs.getString("date-created"));
				customer.setNumLogins(rs.getInt("num-logins"));
				customer.setLastLogin(rs.getString("last-login"));
				customers.add(customer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customers;
	}
	public static CustomerDTO findCustomer(String email){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		CustomerDTO customer = null;
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from customer where email='"+email+"'");
			if(rs.next()){
				customer = new CustomerDTO();
				customer.setCustomerId(rs.getInt("customer-id"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setDateCreated(rs.getString("date-created"));
				customer.setNumLogins(rs.getInt("num-logins"));
				customer.setLastLogin(rs.getString("last-login"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customer;
	}
	public static int findCustomerId(String email){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int customer = 0;
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select `customer-id` from customer where email='"+email+"'");
			if(rs.next()){
				customer = rs.getInt("customer-id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customer;
	}
	
	public static CustomerDTO findCustomerRegId(String orderId){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		CustomerDTO customer = new CustomerDTO();
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT customer.`registration-id`, customer.`email` "+ 
								   "FROM customer "+
					   			   "INNER JOIN `order` "+
					   			   "ON `order`.`customer-id` = customer.`customer-id` "+
					   			   "WHERE `order`.`order-id` = '"+orderId+"'");
			if(rs.next()){
				customer.setRegistrationId(rs.getString("registration-id"));
				customer.setEmail(rs.getString("email"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customer;
	}
	
	public static String findCustomerRegIdByEmail(String email){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
//		CustomerDTO customer = new CustomerDTO();
		String regId = "";
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT customer.`registration-id`, customer.`email` "+ 
								   "FROM customer "+
					   			   "WHERE `customer`.`email` = '"+email+"'");
			if(rs.next()){
				regId = rs.getString("registration-id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return regId;
	}
	
	public static void updateCustomerPassword(CustomerDTO customer){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE customer ";
			query += "SET password='" + customer.getPassword() + "'";
			query += " WHERE `customer-id` =" +customer.getCustomerId();
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void updateCustomer(CustomerDTO customer){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE customer ";
			query += "SET firstname='" + customer.getFirstName() + "',";
			query += "lastname='" + customer.getLastName() + "',";
			query += "email='" + customer.getEmail() + "',";
			query += "phone='" + customer.getPhone() + "'";
			query += " WHERE `customer-id` =" +customer.getCustomerId();
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void deleteCustomer(int id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "delete from customer WHERE `customer-id` ="+id;
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static CustomerDTO validateCustomer(String email, String password){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		CustomerDTO customer = null;
		String query = "";
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM customer WHERE email = '"+
					  email + "' " + "AND password = '" + password + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()){				
				customer = new CustomerDTO();
				customer.setCustomerId(rs.getInt("customer-id"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setDateCreated(rs.getString("date-created"));
				
				query = "UPDATE customer SET `last-login`=now(),`num-logins`=`num-logins`+1 "+
						"where `customer-id`=" + customer.getCustomerId();
				int result = stmt.executeUpdate(query);				
				
				customer.setNumLogins(rs.getInt("num-logins"));
				customer.setLastLogin(rs.getString("last-login"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customer;
	}
	public static CustomerDTO validateCustomer(String email, String password, String regId){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		CustomerDTO customer = null;
		String query = "";
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM customer WHERE email = '"+
					  email + "' " + "AND password = '" + password + "'";
			rs = stmt.executeQuery(query);
			
			customer = new CustomerDTO();
			
			if(rs.next()){			
				
				customer.setCustomerId(rs.getInt("customer-id"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setDateCreated(rs.getString("date-created"));
				customer.setBirthDay(rs.getDate("birthday"));
				customer.setGender(rs.getInt("gender"));
				customer.setImage(rs.getString("image"));
				
				customer.setLoginMessage("success");
				
				query = "UPDATE customer SET `last-login` = now(), `num-logins` = `num-logins`+1, `registration-id` = '"+regId+"' "+
						"WHERE `customer-id`=" + customer.getCustomerId();
				int result = stmt.executeUpdate(query);
				if(result == 1){
					customer.setLoginMessage("success");
				}else{
					customer.setLoginMessage("Update login failed");
				}
//				customer.setNumLogins(rs.getInt("num-logins"));
//				customer.setLastLogin(rs.getString("last-login"));
				
			}else{
				customer.setLoginMessage("Wrong email or password");
			}
		}catch(Exception e){
			customer.setLoginMessage("Error "+e.getMessage());
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return customer;
	}
	public static CustomerDTO createCustomer(String firstName, String lastName, String email, String phone, String password){
		CustomerDTO customer = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "INSERT INTO customer (firstname, lastname, password, email, phone, `date-created`, `num-logins`, `last-login`)"+
					"values('" + firstName + "','" + lastName + "','" + password + "','" + email + "'"+
					",'"+phone+"',now(),1,now())";
			int result = stmt.executeUpdate(query);
			if(result==1){
				query = "SELECT * FROM customer WHERE email = '" + email + "' "+
						 "AND password = '" + password + "'";
				rs = stmt.executeQuery(query);
				if(rs.next()){
					customer = new CustomerDTO();
					customer.setCustomerId(rs.getInt("customer-id"));
					customer.setFirstName(rs.getString("firstname"));
					customer.setLastName(rs.getString("lastname"));
					customer.setPassword(rs.getString("password"));
					customer.setEmail(rs.getString("email"));
					customer.setPhone(rs.getString("phone"));
					customer.setDateCreated(rs.getString("date-created"));
					customer.setNumLogins(rs.getInt("num-logins"));
					customer.setLastLogin(rs.getString("last-login"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
	    }
	    return customer;
	}
	
	public static boolean createCustomer(CustomerDTO customer){
//		CustomerDTO customer = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "";
		boolean ret = false;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			
			query = "INSERT INTO customer (firstname, lastname, password, email, phone, `date-created`, `num-logins`, `last-login`, `registration-id`, birthday, gender, image)"+
					"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			Date now = new Date();
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getPassword());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getPhone());
			stmt.setDate(6, new java.sql.Date(now.getTime()));
			stmt.setInt(7, 1);
			stmt.setDate(8, new java.sql.Date(now.getTime()));
			stmt.setString(9, customer.getRegistrationId());
			stmt.setDate(10, new java.sql.Date(customer.getBirthDay().getTime()));
			stmt.setInt(11, customer.getGender());
			stmt.setString(12, customer.getImage());
			
			int result = stmt.executeUpdate(query);
			if(result==1){
				ret = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
	    }
	    return ret;
	}
	
	public static String createCustomerMobile(CustomerDTO customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "";
		String ret = "Failed";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			query = "INSERT INTO customer (firstname, lastname, password, email, phone, `date-created`, `num-logins`, `last-login`, `registration-id`, birthday, gender, image) "+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			Date now = new Date();
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getPassword());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getPhone());
			stmt.setDate(6, new java.sql.Date(now.getTime()));
			stmt.setInt(7, 1);
			stmt.setDate(8, new java.sql.Date(now.getTime()));
			stmt.setString(9, customer.getRegistrationId());
			if(customer.getBirthDay() != null){
				stmt.setDate(10, new java.sql.Date(customer.getBirthDay().getTime()));
			}else{
				stmt.setDate(10, null);
			}
			stmt.setInt(11, customer.getGender());
			stmt.setString(12, customer.getImage());
			
			int result = stmt.executeUpdate();
			if(result==1){
				ret = "Success";
			}else{
				ret = "Failed result :"+result;
			}
			conn.commit();
		}catch(Exception e){
			ret = e.getMessage();
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
	    }
	    return ret;
	}
	
	public static String updateCustomerMobileWithPass(CustomerDTO customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "";
		String ret = "Failed";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			query = "UPDATE customer " +
					"SET firstname = ?, password = ?, phone = ?, birthday = ?, gender = ?, image = ?) "+
					"WHERE email = ?";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getPassword());
			stmt.setString(3, customer.getPhone());
			stmt.setDate(4, new java.sql.Date(customer.getBirthDay().getTime()));
			stmt.setInt(5, customer.getGender());
			stmt.setString(6, customer.getImage());
			stmt.setString(7, customer.getEmail());
			
			int result = stmt.executeUpdate();
			if(result==1){
				ret = "Success";
			}else{
				ret = "Failed result :"+result;
			}
			conn.commit();
		}catch(Exception e){
			ret = e.getMessage();
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
	    }
	    return ret;
	}
	
	public static String updateCustomerMobileWithoutPass(CustomerDTO customer){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "";
		String ret = "Failed";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			query = "UPDATE customer " +
					"SET firstname = ?, phone = ?, birthday = ?, gender = ?, image = ?) "+
					"WHERE email = ?";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getPhone());
			stmt.setDate(3, new java.sql.Date(customer.getBirthDay().getTime()));
			stmt.setInt(4, customer.getGender());
			stmt.setString(5, customer.getImage());
			stmt.setString(6, customer.getEmail());
			
			int result = stmt.executeUpdate();
			if(result==1){
				ret = "Success";
			}else{
				ret = "Failed result :"+result;
			}
			conn.commit();
		}catch(Exception e){
			ret = e.getMessage();
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
	    }
	    return ret;
	}
	
	
	public static String checkEmail(String email){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String password = "";
		String query = "";
		try{			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM customer WHERE email = '"+ email + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()){				
				password = rs.getString("password");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return password;
	}
}