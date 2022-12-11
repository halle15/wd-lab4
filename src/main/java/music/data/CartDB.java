package music.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import music.models.CartEntry;
import music.models.Product;

public class CartDB {
	public static void main(String[] args) {
		
		editProduct(new CartEntry(new Product("shist", "shitty ass shist", 199.99), 1));
	}
	
	public static void deleteProduct(String code) {
		ConnectionPool pool = ConnectionPool.getInstance();
			
			Connection con = null;
			PreparedStatement st = null;
			
			try {
				
				String query = "DELETE FROM cart WHERE code = '" + code + "'";	
				
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
	public static void addProduct(CartEntry prod) {
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			String code = prod.getProduct().getCode();
			String description = prod.getProduct().getDescription();
			double price = prod.getProduct().getPrice();
			int qty = prod.getQty();
			
			String query = "INSERT INTO cart values ('" + code + "', '" + description + "', " + price +", " + qty + ")";	
			
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
	public static void editProduct(CartEntry prod) {
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			String code = prod.getProduct().getCode();
			String description = prod.getProduct().getDescription();
			double price = prod.getProduct().getPrice();
			int qty = prod.getQty();
			
			String query = "UPDATE cart SET "
							+ "code = '" + code + "', description = '" + description + "', price = " + price + ", qty = " + qty
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
	
	public static List<CartEntry> getProducts(){
		
		List<CartEntry> prods = new ArrayList<CartEntry>();
		
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			String query = "select * from cart";	
			
			con = pool.getConnection();
			
			st = con.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				prods.add(new CartEntry(new Product(rs.getString(1), rs.getString(2), rs.getDouble(3)), rs.getInt(4)));
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
public static CartEntry getProduct(String id) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			String query = "select * from cart where code = '" + id + "'";
			
			
			con = pool.getConnection();
			
			st = con.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			return new CartEntry(new Product(rs.getString(1), rs.getString(2), rs.getDouble(3)), rs.getInt(4));
			
		
			
			
		
			
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
