package id.ac.bsi.comcart.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ecommerce.jpa.ProductDTO;

public class ProductBean{	
	
	public static Collection getSearch(String value){
		Collection products = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			
//			DataSource ds=(DataSource) context. lookup("java:comp/env/jdbc/ariefrpm");
//			conn = ds.getConnection();
			
			stmt = conn.createStatement();
			query = "select * from product where name like \"%"+value+"%\"";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	public static Collection getProduct(String id){
		Collection products = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from product where `category-id`='"+id+"' and status=1";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	public static Collection getProduct(String id, int from, int to){
		Collection products = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			query = "select * from product "+ 
					"where `category-id` = ? and status = 1 "+ 
					"ORDER BY `name` "+
					"LIMIT ?, ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setInt(2, from);
			stmt.setInt(3, to-from);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	
	public static Collection getProductByCategory(String id, int from, int to){
		Collection products = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			query = "SELECT product.`product-id`, product.`name`, product.price, product.quantity, product.`status`, product.special, product.principal, product.uom, "+ 
					"product.image, product.description, category.`name` as categoryname "+
					"FROM product "+
					"INNER JOIN category "+
					"ON product.`category-id` = category.`category-id` "+
					"WHERE category.`name` = ? AND `status` = 1 "+
					"ORDER BY product.`name` "+
					"LIMIT ?, ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setInt(2, from);
			stmt.setInt(3, to-from);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("categoryname"));
				product.setName(rs.getString("name"));
				product.setPrincipal(rs.getString("principal"));
				product.setUom(rs.getString("uom"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			System.out.print("e "+e.getMessage());
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	
	public static Collection getAllProducts(){
		Collection products = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from product order by `date-added` desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	
	public static Collection getMostViewedProducts(){
		Collection products = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "SELECT * FROM product order by viewed desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setViewed(rs.getInt("viewed"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	public static Collection getLatestProducts(){
		Collection products = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from product where status=1 order by `date-added` desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				
				product.setDescription(rs.getString("description"));
				products.add(product);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return products;	
	}
	
	public static ProductDTO getAProduct(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		ProductDTO product=null;
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from product where `product-id`='"+id+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				product = new ProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setCategoryId(rs.getString("category-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setStatus(rs.getInt("status"));
				product.setSpecial(rs.getFloat("special"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
				product.setDescription(rs.getString("description"));
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return product;	
	}
	public static void insertProduct(ProductDTO product){//TODO
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "insert into `product`(`category-id`,name,price,quantity,image,description,`date-added`,viewed,`status`,special) values "+
					"('"+product.getCategoryId()+"','"+product.getName()+"',"+product.getPrice()+","+
					""+product.getQuantity()+",'"+product.getImage()+"','"+product.getDescription()+"',now(),0,"+product.getStatus()+","+product.getSpecial()+")";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void updateQuantity(String id, int qty){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "update product set quantity=quantity-"+qty+" where `product-id`='"+id+"'";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}	
	}
	public static void cancelQuantity(String id, int qty){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "update product set quantity=quantity+"+qty+" where `product-id`='"+id+"'";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void updateProduct(ProductDTO product){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE `product` ";
			query += "SET name='" + product.getName() + "',";
			query += "`category-id`='" + product.getCategoryId() + "',";
			query += "price=" + product.getPrice() + ",";
			query += "quantity=" + product.getQuantity() + ",";
			if(!product.getImage().equals("")){
				query += "image='" + product.getImage() + "',";	
			}			
			query += "description='" + product.getDescription() + "',";
			query += "`status`=" + product.getStatus() + ",";
			query += "special=" + product.getSpecial() + "";
			query += " WHERE `product-id` ='" +product.getProductId()+"'";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void deleteProduct(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "delete from `product` where `product-id`='"+id+"'";
			int result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}
	public static void updateView(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";		
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "UPDATE product SET viewed=viewed+1 WHERE `product-id` ='" +id+"'";
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