package id.ac.bsi.comcart.models;

import id.ac.bsi.comcart.actions.catalog.ShoppingCart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ecommerce.jpa.OrderProductDTO;
import com.ecommerce.jpa.ProductDTO;

public class OrderProductBean{
	public static Collection getAllOrderProducts(){
		Collection orderProducts = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();			
			query = "select * from `order-product` order by `order-id`, `product-id` desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				OrderProductDTO orderProduct = new OrderProductDTO();
//				orderProduct.setOrderProductId(rs.getInt("order-product-id"));
				orderProduct.setOrderId(rs.getString("order-id"));
				orderProduct.setName(rs.getString("name"));
				orderProduct.setImage(rs.getString("image"));
				orderProduct.setPrice(rs.getFloat("price"));
				orderProduct.setQuantity(rs.getInt("quantity"));
				orderProduct.setTotalPrice(rs.getFloat("total-price"));				
				orderProduct.setProductId(rs.getString("product-id"));
				orderProducts.add(orderProduct);
			}							
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return orderProducts;
	}
	public static Collection getOrderProducts(String orderId){
		Collection orderProducts = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();			
			query = "select * from `order-product` where `order-id`='"+orderId+"'";					
			rs = stmt.executeQuery(query);
			while(rs.next()){
				OrderProductDTO orderProduct = new OrderProductDTO();
				orderProduct.setOrderId(rs.getString("order-id"));
				orderProduct.setName(rs.getString("name"));
				orderProduct.setImage(rs.getString("image"));
				orderProduct.setPrice(rs.getFloat("price"));
				orderProduct.setQuantity(rs.getInt("quantity"));
				orderProduct.setTotalPrice(rs.getFloat("total-price"));				
				orderProduct.setProductId(rs.getString("product-id"));
				orderProducts.add(orderProduct);
			}							
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return orderProducts;
	}
	public static ShoppingCart getOrderProductCart(String orderId){
		ShoppingCart shopCart = new ShoppingCart();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			query = "select * from `order-product` where `order-id`='"+orderId+"'";					
			rs = stmt.executeQuery(query);
			Collection<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setName(rs.getString("name"));
				product.setImage(rs.getString("image"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setProductId(rs.getString("product-id"));
				
				ShoppingCartItem newItem = new ShoppingCartItem(product);
				newItem.setNumItems(product.getQuantity());
				
				items.add(newItem);
			}
			shopCart.setShoppingCartItems(items);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
		return shopCart;
	}
	public static Collection getBestsellersProducts(){
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
			query = "SELECT name, image, price, `product-id`, sum(quantity)as quantity, special "+
					"FROM `order-product` "+
					"group by `product-id` order by quantity desc";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				OrderProductDTO product = new OrderProductDTO();
				product.setProductId(rs.getString("product-id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setSpecial(rs.getFloat("special"));
				product.setQuantity(rs.getInt("quantity"));
				if((rs.getString("image")).equals("")){
					product.setImage("no_image.jpg");
				}else{
					product.setImage(rs.getString("image"));
				}
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
	
	/*public static void addOrderProduct(int orderId, String name, String image, float price, int qty, float totalPrice, String productId, float special){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/ariefrpm");
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			
			query = "INSERT INTO `order-product` (`order-id`,`name`,image,price,quantity,"+
					"`total-price`,`product-id`,special)"+
					" values(" + orderId + ",'" + name + "','" + image + "'"+
					","+price+","+qty+","+totalPrice+",'"+productId+"',"+special+")";
					
			int result = stmt.executeUpdate(query);
							
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{if(rs != null)rs.close();}catch(SQLException e){};
			try{if(stmt != null)stmt.close();}catch(SQLException e){};
			try{if(conn != null)conn.close();}catch(SQLException e){};
		}
	}*/
}