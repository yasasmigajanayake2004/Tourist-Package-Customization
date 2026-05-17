package servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

import model.*;
import service.ReviewService;

@WebServlet({"/add-review","/update-review","/delete-review"})

public class ReviewServlet extends HttpServlet {

    private ReviewService rs = new ReviewService();

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User acc = (User) req.getSession().getAttribute("account");

        if (acc == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String path = req.getServletPath();
        boolean admin = !acc.getRole().equals("CUSTOMER");
