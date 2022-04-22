package com.learnersacademy.model;

public class FinalClassReport {

  private String teacherFirstName;
  private String teacherLastName;
  private int teacherId;
  private String subjectname;
  
public FinalClassReport(String teacherFirstName, String teacherLastName, int teacherId, String subjectname) {
	super();
	this.teacherFirstName = teacherFirstName;
	this.teacherLastName = teacherLastName;
	this.teacherId = teacherId;
	this.subjectname = subjectname;
	
}
public String getTeacherFirstName() {
	return teacherFirstName;
}
public void setTeacherFirstName(String teacherFirstName) {
	this.teacherFirstName = teacherFirstName;
}
public String getTeacherLastName() {
	return teacherLastName;
}
public void setTeacherLastName(String teacherLastName) {
	this.teacherLastName = teacherLastName;
}
public int getTeacherId() {
	return teacherId;
}
public void setTeacherId(int teacherId) {
	this.teacherId = teacherId;
}
public String getSubjectname() {
	return subjectname;
}
public void setSubjectname(String subjectname) {
	this.subjectname = subjectname;
}



}
