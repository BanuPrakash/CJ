package com.cisco.prj.web;

import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String URI = req.getRequestURI();
        String METHOD = req.getMethod();

        if(URI.endsWith("sample.do") && METHOD.equalsIgnoreCase("GET")) {
            out.print("<html><body>");
            out.print("Hello from SampleServlet!!!");
            out.print("</body></html>");
        } else if (URI.endsWith("products.do")){
             if(METHOD.equalsIgnoreCase("get")) {
                 ProductDao productDao = new ProductDaoJdbcImpl();
                 List<Product> products = productDao.getProducts();
                 req.setAttribute("products", products);
                 req.getRequestDispatcher("list.jsp").forward(req, resp);
                 return ;
             } else if(METHOD.equalsIgnoreCase("post")) {
                 Product product = Product.builder().
                         name(req.getParameter("name")).
                         price(Double.parseDouble(req.getParameter("price"))).
                         build();
                 ProductDao productDao = new ProductDaoJdbcImpl();
                 productDao.addProduct(product);
                 resp.sendRedirect("index.jsp?msg=Product Added!!!");
                 return;
             }
        } else if(URI.endsWith("login.do")) {
            // read email and password, authenticate
            HttpSession session = req.getSession(); // create a session if not exist, if exists get the session
            session.setMaxInactiveInterval(60*30);
            session.setAttribute("user", req.getParameter("email"));
            resp.sendRedirect("index.jsp");
        } else if (URI.endsWith("logout.do")) {
            HttpSession session = req.getSession(false); // get existing session
            session.invalidate(); // destroy session object
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
