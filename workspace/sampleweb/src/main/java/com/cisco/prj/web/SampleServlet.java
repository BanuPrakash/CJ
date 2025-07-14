package com.cisco.prj.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sample")
public class SampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html"); // MIME type
        PrintWriter out = resp.getWriter(); // character stream to client
//        ServletOutputStream out = resp.getOutputStream(); // binary
        out.print("<html><body>");
        out.print("Hello from SampleServlet!!!");
        out.print("</body></html>");
    }
}
