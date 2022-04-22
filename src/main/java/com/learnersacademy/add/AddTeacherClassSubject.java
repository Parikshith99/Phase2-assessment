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


@WebServlet("/addTeacherClassesSubjectsServlet")
public class AddTeacherClassSubject extends HttpServlet {
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
		String teacherid = request.getParameter("teacherid");
		String classname = request.getParameter("classname");
		String subjectname = request.getParameter("subjectname");
//		int classid = Integer.parseInt(request.getParameter("classid"));
		PrintWriter out = response.getWriter();
		try {
			Statement statement1 = connection.createStatement();
			Statement statement2 = connection.createStatement();
			Statement statement3 = connection.createStatement();
			Statement statement4 = connection.createStatement();
			ResultSet rt = statement1.executeQuery("select TeacherId from teachers where TeacherId='" + teacherid + "'");
			ResultSet rc = statement2.executeQuery("select ClassId from classes where className='" + classname + "'");
			ResultSet rs = statement3.executeQuery("select SubjId from subjects where SubjName='" + subjectname + "'");
			if(rt.next() && rc.next() && rs.next()) {
				ResultSet dv = statement4.executeQuery("select * from teacherclasssubj where teacher='" + rt.getInt(1) + "'and class='" + rc.getInt(1) + "'and subject='" + rs.getInt(1) + "'");
				if(!dv.next()) {
				
				int result = statement3.executeUpdate("insert into teacherclasssubj values  ('" + rt.getInt(1) + "', '" + rc.getInt(1) + "','" + rs.getInt(1) + "')");
				if (result > 0) {
					out.println("<h1>Added successfully</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("teacher_class_subj.html");
					rd.include(request, response);
				} else {
					out.println("<h1>Error creating the class</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("teacher_class_subj.html");
					rd.include(request, response);
				
			}
		}
				else {
					out.println("<h1>Data already present</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("teacher_class_subj.html");
					rd.include(request, response);
				}
		}
			else {
				out.println("<h1>First Create the data mentioned below!...Then try again</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("teacher_class_subj.html");
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
