<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.*, com.learnersacademy.model.Student" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View all the students details</title>
</head>
<body>
<h3><a style="margin-left:40%;" href="view_home.jsp"> Go to main menu</a></h3>
<br/><br/>
<table border="1" style="margin: 0px auto; width:50%">
		<tr>
		    <th>Roll Number</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>className</th>
		</tr>
		<c:forEach var="student" items="${studentsList}">
			<tr>
			    <td>${fn:toUpperCase(student.rollNo)}</td>
				<td>${fn:toUpperCase(student.firstName)}</td>
				<td>${fn:toUpperCase(student.lastName)}</td>
				<td>${fn:toUpperCase(student.className)}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>