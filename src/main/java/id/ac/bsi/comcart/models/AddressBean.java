package id.ac.bsi.comcart.models;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import java.util.*;
public class AddressBean{
	public static void updateAddress(AddressDTO address){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE `address` ";
			query += "SET firstname='" + address.getFirstName() + "',";
			query += "lastname='" + address.getLastName() + "',";
			query += "address='" + address.getAddress() + "',";
			query += "zip=" + address.getZip() + ",";
			query += "city='" + address.getCity() + "',";
			query += "country='" + address.getCountry() + "',";
			query += "region='" + address.getRegion() +"'" ;
			query += " WHERE `address-id` =" +address.getAddressId();
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	
	public static void deleteAddress(int id){		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "delete from address where `address-id` = "+id+"";
			int result = stmt.executeUpdate(query);			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static Collection getAddresses(int id){
		Collection addresses = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from address where `customer-id` = "+id+"";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				AddressDTO address = new AddressDTO();
				address.setAddressId(rs.getInt("address-id"));
				address.setCustomerId(rs.getInt("customer-id"));
				address.setAddress(rs.getString("address"));
				address.setCity(rs.getString("city"));
				address.setZip(rs.getInt("zip"));
				address.setCountry(rs.getString("country"));
				address.setRegion(rs.getString("region"));
				address.setFirstName(rs.getString("firstname"));
				address.setLastName(rs.getString("lastname"));
				addresses.add(address);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return addresses;	
	}
	
	public static List<String> getRegionList(String city){
		List<String> regions = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT mp_kabkot.nama_kabkot FROM mp_kabkot "+
					"INNER JOIN mp_prov "+
					"ON mp_prov.id_prov = mp_kabkot.id_prov "+
					"WHERE mp_prov.nama_prov = '"+city+"'";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				regions.add(rs.getString("nama_kabkot"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return regions;	
	}
	
	public static List<String> getSubRegionList(String region){
		List<String> subRegions = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT mp_kec.nama_kec FROM mp_kec "+
					"INNER JOIN mp_kabkot "+
					"ON mp_kabkot.id_kabkot = mp_kec.id_kabkot "+
					"WHERE mp_kabkot.nama_kabkot = '"+region+"'";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				subRegions.add(rs.getString("nama_kec"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return subRegions;	
	}
	
	public static List<String> getCityList(){
		List<String> cities = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select nama_prov from mp_prov";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				cities.add(rs.getString("nama_prov"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return cities;	
	}
	
	public static AddressDTO getAddress(int id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		AddressDTO address = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from address where `address-id` = "+id+"";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				address = new AddressDTO();
				address.setAddressId(rs.getInt("address-id"));
				address.setCustomerId(rs.getInt("customer-id"));
				address.setAddress(rs.getString("address"));
				address.setCity(rs.getString("city"));
				address.setZip(rs.getInt("zip"));
				address.setCountry(rs.getString("country"));
				address.setRegion(rs.getString("region"));
				address.setFirstName(rs.getString("firstname"));
				address.setLastName(rs.getString("lastname"));				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return address;	
	}
	public static Collection createAddress(int customerId, String address, String city,
	                                int zip, String country, String region,
	                                String firstName, String lastName){
		Collection addresses = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "INSERT INTO address (`customer-id`,address,city,zip,country,region,firstname,lastname)"+
					"values('" + customerId + "','" + address + "','" + city + "','" + zip + "'"+
					",'"+ country +"','"+ region +"','"+firstName+"','"+lastName+"')";
			int result = stmt.executeUpdate(query);
			if(result==1){
				query = "SELECT * FROM address WHERE `customer-id` = '" + customerId + "'";
				rs = stmt.executeQuery(query);
				while(rs.next()){
					AddressDTO anAddress = new AddressDTO();
					anAddress.setAddressId(rs.getInt("address-id"));
					anAddress.setCustomerId(rs.getInt("customer-id"));
					anAddress.setAddress(rs.getString("address"));
					anAddress.setCity(rs.getString("city"));
					anAddress.setZip(rs.getInt("zip"));
					anAddress.setCountry(rs.getString("country"));
					anAddress.setRegion(rs.getString("region"));
					anAddress.setFirstName(rs.getString("firstname"));
					anAddress.setLastName(rs.getString("lastname"));
					addresses.add(anAddress);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
	    }
	    return addresses;
	} 
}