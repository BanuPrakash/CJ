package com.cisco.prj.web;

import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();

        resp.setContentType("text/html"); // MIME type
        PrintWriter out = resp.getWriter(); // character stream to client
        // static content
        out.print("<html><body>");
        out.print("<table>");
        out.print("<tr>");
            out.print("<th>ID</th><th>Name</th><th>Price</th>");
        out.print("</tr>");

        for(Product p : products) {
            out.print("<tr>");
                out.print("<td>" + p.getId() + "</td>");
                out.print("<td>" + p.getName() + "</td>");
                out.print("<td>" + p.getPrice() + "</td>");
            out.print("</tr>");
        }

        out.print("</table></body></html>");

    }
}
