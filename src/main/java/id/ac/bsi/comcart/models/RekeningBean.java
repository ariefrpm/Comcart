package id.ac.bsi.comcart.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ecommerce.jpa.RekeningDTO;

public class RekeningBean{
	public static RekeningDTO getRekening(int id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		RekeningDTO rekening = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from rekening where `rekening-id` = "+id+"";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				rekening = new RekeningDTO();
				rekening.setRekeningId(rs.getInt("rekening-id"));
				rekening.setBank(rs.getString("bank"));
				rekening.setImage(rs.getString("image"));
				rekening.setRekening(rs.getString("rekening"));
				rekening.setAccount(rs.getString("account"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return rekening;	
	}
	public static int getRekeningIdByRekening(String rekening){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		int rekeningId = 0;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select `rekening-id` from rekening where `rekening` = '"+rekening+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				rekeningId = rs.getInt("rekening-id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return rekeningId;	
	}
	public static Collection getRekenings(){
		Collection rekenings = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from rekening";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				RekeningDTO rekening = new RekeningDTO();
				rekening.setRekeningId(rs.getInt("rekening-id"));
				rekening.setBank(rs.getString("bank"));
				rekening.setImage(rs.getString("image"));
				rekening.setRekening(rs.getString("rekening"));
				rekening.setAccount(rs.getString("account"));
				rekenings.add(rekening);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return rekenings;	
	}
	public static List<String> getAllBank(){
		List<String> banks = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select bank_name from m_bank";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				banks.add(rs.getString("bank_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return banks;	
	}
	public static void insertRekening(String bank, String rekening, String image, String account){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "insert into rekening(bank,rekening,image,account) values ('"+bank+"','"+rekening+"','"+image+"','"+account+"')";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void updateRekening(int id, String bank, String rekening, String image, String account){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE rekening ";
			query += "SET bank='" + bank + "',";
			query += "rekening='" + rekening + "',";
			if(!image.equals("")){
				query += "image='" + image + "',";	
			}			
			query += "account='" + account + "'";
			query += " WHERE `rekening-id` =" +id;
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void deleteRekening(int id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "delete from rekening where `rekening-id`="+id+"";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
}