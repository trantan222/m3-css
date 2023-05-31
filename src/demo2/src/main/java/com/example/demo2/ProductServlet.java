package com.example.demo2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product",""})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action ==null) {
            action = "";
        }
        switch (action) {
            default:
                showList(request,response);
        }
    }
    private void calculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("descrise");
        int price = Integer.parseInt(request.getParameter("price"));
        int percent = Integer.parseInt(request.getParameter("percent"));
        double Discount =  (price * percent * 0.01);
        request.setAttribute("name",name);
        request.setAttribute("price",price);
        request.setAttribute("percent",percent);
        request.setAttribute("Discount",Discount);
        request.getRequestDispatcher("/calculate/result.jsp").forward(request,response);
    }
    protected void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.sendRedirect("/calculate/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        calculate(request,response);
    }
}
