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


@WebServlet("/addsubjectsServlet")
public class AddSubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init(ServletConfig sc) {
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
		String subjectname = request.getParameter("subjectname");
		String department = request.getParameter("department");

		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();
			// insert into user values ('joe', 'wilson', 'jwil@example.com', 'test1');
			ResultSet rs = statement.executeQuery("select SubjId from subjects where SubjName='" + subjectname + "'");
			if(!rs.next()) {
				int result = statement.executeUpdate("insert into subjects (SubjName,Department) values ('" + subjectname + "', '" + department + "')");
				if (result > 0) {
					out.println("<h1>Subject added</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("subjectform.html");
					rd.include(request, response);
				} else {
					out.println("<h1>Error creating the subject</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("subjectform.html");
					rd.include(request, response);
				
			}
		}
			else {
				out.println("<h1>Subject already present</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("subjectform.html");
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
