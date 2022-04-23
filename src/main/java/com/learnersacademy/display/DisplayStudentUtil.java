package com.learnersacademy.display;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learnersacademy.model.Student;


@WebServlet("/displayStudentUtilServlet")
public class DisplayStudentUtil extends HttpServlet {
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> students = new ArrayList<Student>();
		
		try  {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select RollNo,FirstName,LastName,className from student,classes where studentClassId=ClassId  order by studentClassId,RollNo");
			while (rs.next()) {
				Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
				students.add(student);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("studentsList", students);
        RequestDispatcher rd = request.getRequestDispatcher("/view_students.jsp");
		
		rd.forward(request, response);
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
