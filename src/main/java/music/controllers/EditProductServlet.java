package music.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.data.ProductDB;
import music.data.ProductIO;
import music.models.Product;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("productCode");
        req.setAttribute("isNew", code == null);

        if (code != null) {
        	Product p = ProductDB.getProduct(code);
            req.setAttribute("product", ProductDB.getProduct(code));
        }

        req.getRequestDispatcher("/editProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        boolean isNew = Boolean.parseBoolean(req.getParameter("isNew"));
        String code = req.getParameter("code");
        String desc = req.getParameter("description");

        double price;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        }
        catch(Exception e) {
            price = 0.0;
        }

        Product p = new Product();
        p.setCode(code);
        p.setDescription(desc);
        p.setPrice(price);

        if (code != null && code.length() != 0 &&
                desc != null && desc.length() != 0 &&
                price != 0) {

            if (isNew) {
                ProductDB.addProduct(p);
            }
            else {
                ProductDB.editProduct(p);
            }

            resp.sendRedirect("productMaint.html");
            return;
        }

        req.setAttribute("product", p);
        req.setAttribute("isNew", isNew);
        req.setAttribute("error", "Invalid entry");
        req.getRequestDispatcher("/editProduct.jsp").forward(req, resp);
    }

}
