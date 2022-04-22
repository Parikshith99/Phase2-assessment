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
import javax.servlet.http.HttpSession;

import com.learnersacademy.model.Classes;


@WebServlet("/displayClassUtilServlet")
public class DisplayClassUtil extends HttpServlet {
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          List<Classes> c = new ArrayList<Classes>();
		
		try  {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select className from classes");
			while (rs.next()) {
				Classes clasobj = new Classes(rs.getString(1));
				c.add(clasobj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		HttpSession session=request.getSession();
		session.setAttribute("classList", c);
        RequestDispatcher rd = request.getRequestDispatcher("/view_class.jsp");
		
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
