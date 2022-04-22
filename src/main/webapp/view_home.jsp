<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<style>
ul   { padding-left: 80px;}
a{text-decoration: none;}
}
</style>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>


  <h1 style="text-align:center">Welcome Admin</h1>
  <br/><br/>
  <ul>
  <li><h3><a href="subjectform.html">Set up master list of all subjects</a></h3></li>
  <li> <h3><a href="teachersform.html">Set up master list of all teachers</a></h3></li>
  <li><h3><a href="classesform.html">Set up master list of all classes</a></h3></li>
  <li><h3><a href="class_subject.html">Assign classes for subjects from the master list</a></h3></li>
  <li><h3><a href="teacher_class_subj.html">Assign teachers to a class for a subject </a></h3></li>
  <li><h3><a href="displayStudentUtilServlet">Get a master list of students</a></h3></li>
  <li>  <h3><a href="displayClassUtilServlet">View class report</a></h3></li>
  </ul>
  <br/><br/>
<h3 style="text-align:center"><a href="Login.html">Logout</a></h3>

 </body>
</html>