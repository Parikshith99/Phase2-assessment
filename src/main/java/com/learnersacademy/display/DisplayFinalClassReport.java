package com.learnersacademy.display;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.learnersacademy.model.FinalClassReport;
import com.learnersacademy.model.FinalClassStudentReport;

@WebServlet("/displayFinalClassReportServlet")
public class DisplayFinalClassReport extends HttpServlet {
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
		 List<FinalClassReport> classreport = new ArrayList<FinalClassReport>();
		 List<FinalClassStudentReport> studentreport = new ArrayList<FinalClassStudentReport>();
			String classname = request.getParameter("className");
			PrintWriter out = response.getWriter();
			try  {
				Statement statement1 = connection.createStatement();
				Statement statement2 = connection.createStatement();
				ResultSet rt = statement1.executeQuery("select te.FirstName,te.LastName,te.TeacherId,sb.SubjName from teacherclasssubj t,classes c,subjects sb,teachers te where t.class=c.ClassId and t.teacher=te.TeacherId and t.subject=sb.SubjId and c.className='" + classname + "'");
				ResultSet rs = statement2.executeQuery("select st.RollNo,st.FirstName,st.LastName from teacherclasssubj t,student st ,classes c where t.class=st.studentClassId and t.class=c.ClassId and c.className='" + classname + "'group by st.RollNo");
				while (rt.next()) {
					FinalClassReport finalclassreport = new FinalClassReport(rt.getString(1),rt.getString(2),rt.getInt(3),rt.getString(4));
					classreport.add(finalclassreport);
				}
				while (rs.next()) {
					FinalClassStudentReport finalstudentreport=new FinalClassStudentReport(rs.getInt(1),rs.getString(2),rs.getString(3));
					studentreport.add(finalstudentreport);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("CurrentSelection", classname);
			request.setAttribute("FinalClassReport", classreport);
			request.setAttribute("FinalStudentReport", studentreport);
			if(classreport.isEmpty()) {
                RequestDispatcher rd = request.getRequestDispatcher("/view_class.jsp");
				
				rd.include(request, response);
				out.println("<h1>No data</h1>");
			}
			else {
				
				RequestDispatcher rd = request.getRequestDispatcher("/view_class.jsp");
				
				rd.forward(request, response);
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
