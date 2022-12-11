package music.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import music.models.Product;


public class ProductDB {
	public static void main(String[] args) {
		
		deleteProduct("BO-96");
	}
	
public static void deleteProduct(String code) {
ConnectionPool pool = ConnectionPool.getInstance();
	
	Connection con = null;
	PreparedStatement st = null;
	
	try {
		
		String query = "DELETE FROM products WHERE code = '" + code + "'";	
		
		con = pool.getConnection();
		
		st = con.prepareStatement(query);
		st.execute();
	
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	
	finally {
		if(st != null) {
			try {
				st.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		
		}
		
		if(con != null) {
			pool.freeConnection(con);
		}
		
	}
}

public static void addProduct(Product prod) {
	ConnectionPool pool = ConnectionPool.getInstance();
	
	Connection con = null;
	PreparedStatement st = null;
	
	try {
		String code = prod.getCode();
		String description = prod.getDescription();
		double price = prod.getPrice();
		
		String query = "INSERT INTO products values ('" + code + "', '" + description + "', " + price +")";	
		
		con = pool.getConnection();
		
		st = con.prepareStatement(query);
		st.execute();
	
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	
	finally {
		if(st != null) {
			try {
				st.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		
		}
		
		if(con != null) {
			pool.freeConnection(con);
		}
		
	}
}

public static void editProduct(Product prod) {
	ConnectionPool pool = ConnectionPool.getInstance();
	
	Connection con = null;
	PreparedStatement st = null;
	
	try {
		String code = prod.getCode();
		String description = prod.getDescription();
		double price = prod.getPrice();
		
		String query = "UPDATE products SET "
						+ "code = '" + code + "', description = '" + description + "', price = " + price
						+ " WHERE code = '" + code + "'";	
		System.out.println(query);
		con = pool.getConnection();
		
		st = con.prepareStatement(query);
		st.execute();
	
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	
	finally {
		if(st != null) {
			try {
				st.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		
		}
		
		if(con != null) {
			pool.freeConnection(con);
		}
		
	}
}
	
	
public static List<Product> getProducts(){
	
		List<Product> prods = new ArrayList<Product>();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			String query = "select * from products";	
			
			con = pool.getConnection();
			
			st = con.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				prods.add(new Product(rs.getString(1), rs.getString(2), rs.getDouble(3)));
			}
			
			return prods;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		finally {
			if(st != null) {
				try {
					st.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			
			}
			
			if(con != null) {
				pool.freeConnection(con);
			}
			
		}
		

}
	
public static Product getProduct(String id) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			String query = "select * from products where code = '" + id + "'";
			
			
			con = pool.getConnection();
			
			st = con.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			return new Product(rs.getString(1), rs.getString(2), rs.getDouble(3));
			
		
			
			
		
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		finally {
			if(st != null) {
				try {
					st.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			
			}
			
			if(con != null) {
				pool.freeConnection(con);
			}
			
		}
	}
}
