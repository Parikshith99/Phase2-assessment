package com.learnersacademy.validate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ValidateServlet")
public class AdminValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
		
			if (username.equals("admin") && password.equals("admin")) {
				// successfull login
				
				RequestDispatcher rd = request.getRequestDispatcher("/view_home.jsp");
				rd.forward(request, response);
			} else {
				// unsuccessful login - incorrect email or incorrect password
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<p>Login failed</p>");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
