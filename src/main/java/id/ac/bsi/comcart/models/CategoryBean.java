package id.ac.bsi.comcart.models;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import java.util.*;
public class CategoryBean{
	public static CategoryDTO getCategory(String id){
		Collection categories = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		CategoryDTO category = null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from category where `category-id`='"+id+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				category = new CategoryDTO();
				category.setCategoryId(rs.getString("category-id"));
				category.setName(rs.getString("name"));
				category.setParentId(rs.getString("parent-id"));
				category.setDescription(rs.getString("description"));
				if((rs.getString("image")).equals("")){
					category.setImage("no_image.jpg");
				}else{
					category.setImage(rs.getString("image"));	
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return category;	
	}
	public static String getCategoryName(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		String name="";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select name from category where `category-id` = '"+id+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				name = rs.getString("name");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return name;	
	}
	public static Collection getCategories(){
		Collection categories = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from category";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				CategoryDTO category = new CategoryDTO();
				category.setCategoryId(rs.getString("category-id"));
				category.setName(rs.getString("name"));
				category.setParentId(rs.getString("parent-id"));
				category.setDescription(rs.getString("description"));
				if((rs.getString("image")).equals("")){
					category.setImage("no_image.jpg");
				}else{
					category.setImage(rs.getString("image"));	
				}
				categories.add(category);	
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return categories;	
	}
	
	public static List<String> getTitleCategories(){
		List<String> categories = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select `name` from category ORDER BY `order`";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				categories.add(rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return categories;
	}
	
	public static Collection getSubCategories(String id){
		Collection categories = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from category where `parent-id`='"+id+"'";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				CategoryDTO category = new CategoryDTO();
				category.setCategoryId(rs.getString("category-id"));
				category.setName(rs.getString("name"));
				category.setParentId(rs.getString("parent-id"));
				category.setDescription(rs.getString("description"));
				if((rs.getString("image")).equals("")){
					category.setImage("no_image.jpg");
				}else{
					category.setImage(rs.getString("image"));	
				}				
				categories.add(category);						
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return categories;	
	}
	public static void insertCategory(String name, String parentId, String image, String description){//TODO
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "insert into category(`name`,`parent-id`,image,description) values ('"+name+"','"+parentId+"','"+image+"','"+description+"')";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void updateCategory(String id, String name, String parentId, String image, String description){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE `category` ";
			query += "SET `name`='" + name + "',";
			query += "`parent-id`='" + parentId + "',";
			if(!image.equals("")){
				query += "image='" + image + "',";	
			}			
			query += "description='" + description + "'";
			query += " WHERE `category-id` ='" +id+"'";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void deleteCategory(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "delete from `category` where `category-id`='"+id+"'";
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