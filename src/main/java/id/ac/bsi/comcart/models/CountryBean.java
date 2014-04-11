package id.ac.bsi.comcart.models;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import java.util.*;
import javax.servlet.http.*;
public class CountryBean{
	public static Collection getAllCountry(){
		Collection countries = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from country";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				CountryDTO country = new CountryDTO();
				country.setCountryId(rs.getInt("country_id"));
				country.setName(rs.getString("name"));
				countries.add(country);	
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return countries;	
	}
	public static String getCountryName(String id){		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		String result = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select name from country where country_id="+id;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				result = rs.getString("name");	
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
	public static String getCountryId(String name){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		String result = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select country_id from country where name="+name;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				result = rs.getString("country_id");
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
}