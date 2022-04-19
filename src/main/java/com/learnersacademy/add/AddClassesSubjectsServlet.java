package com.learnersacademy.add;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addClassesSubjectsServlet")
public class AddClassesSubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init(ServletConfig sc) {
		System.out.println("initializing addservlet...");
		ServletContext context = sc.getServletContext();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(context.getInitParameter("dbUrl"), 
					context.getInitParameter("dbUser"), 
					context.getInitParameter("dbPassword"));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classname = request.getParameter("classname");
		String subjectname = request.getParameter("subjectname");

		PrintWriter out = response.getWriter();
		try {
			Statement statement1 = connection.createStatement();
			Statement statement2 = connection.createStatement();
			// insert into user values ('joe', 'wilson', 'jwil@example.com', 'test1');
			ResultSet rs = statement1.executeQuery("select SubjId from subjects where SubjName='" + subjectname + "'");
			ResultSet rc = statement2.executeQuery("select ClassId from classes where className='" + classname + "'");
			if(rs.next() && rc.next()) {
				int result = statement2.executeUpdate("insert into classsubject values ('" + rs.getInt(1) + "', '" + rc.getInt(1) + "')");
				if (result > 0) {
					out.println("<h1>Added successfully</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("class_subject.html");
					rd.include(request, response);
				} else {
					out.println("<h1>Error creating the subject</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("class_subject.html");
					rd.include(request, response);
				
			}
		}
			else {
				out.println("<h1>First Create subject name and class name</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("class_subject.html");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
