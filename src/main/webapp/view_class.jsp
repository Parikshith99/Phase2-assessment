<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, com.learnersacademy.model.Classes" %> 
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
 form {margin-left:500px;}
 a{margin-left:45%;}
  td {
  padding: 4px;
}
</style>
<meta charset="ISO-8859-1">
<title>Class Report</title>
</head>
<body>
  <form action="displayFinalClassReportServlet" method="get">
    <table>
      <tr>
        <td>select class Name</td>
        <td> 
		<select name="className">
		<c:forEach var="classroom" items="${classList}">
			<option>${classroom.classname}</option>
		</c:forEach>
		</select>
		</td>
      <td><input type="submit" value="submit" /></td>
      </tr>
    </table>
    <br/><br/>
 </form>
      <h3><a href="view_home.jsp">Go to main menu</a></h3>
 <br/><br/>
  <div style="border:1px solid blue;">
 <h2 style="text-align: center;">Report Of Class: ${CurrentSelection}</h2>
  
 <table border="1" style="margin: 0px auto; width:50%">
		<tr>
		    <th>TeacherID</th>
			<th>TeacherFirstName</th>
			<th>TeacherLastName</th>
			<th>SubjectName</th>
		</tr>
		<c:forEach var="classreport" items="${FinalClassReport}">
			<tr>
			    <td>${classreport.teacherId}</td>
			    <td>${fn:toUpperCase(classreport.teacherFirstName)}</td>
				<td>${fn:toUpperCase(classreport.teacherLastName)}</td>
				<td>${fn:toUpperCase(classreport.subjectname)}</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<table border="1" style="margin: 0px auto; width:50%">
		<tr>
		    <th>StudentRollNo</th>
			<th>StudentFirstName</th>
			<th>StudentLastName</th>
		
		</tr>
		<c:forEach var="Studentreport" items="${FinalStudentReport}">
			<tr>
			    <td>${fn:toUpperCase(Studentreport.studentRollNo)}</td>
			    <td>${fn:toUpperCase(Studentreport.studentFirstName)}</td>
				<td>${fn:toUpperCase(Studentreport.studentLastName)}</td>
				
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	</div>
</body>
</html>