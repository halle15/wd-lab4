package music.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import music.data.ProductDB;
import music.data.ProductIO;
import music.models.Product;

@WebServlet("/productMaint")
public class ProductMaintenanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final List<Product> prods = ProductDB.getProducts();
    
    public static void main(String[] args) {
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(prods));
    }

    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
    	List<Product> prods = ProductDB.getProducts();
    	
        Gson gson = new Gson();
        String json = gson.toJson(prods);
        
        resp.setContentType("application/json");
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
