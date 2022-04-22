package com.learnersacademy.model;

public class FinalClassStudentReport {
	private int studentRollNo;
	  private String studentFirstName;
	  private String studentLastName;
	public FinalClassStudentReport(int studentRollNo, String studentFirstName, String studentLastName) {
		super();
		this.studentRollNo = studentRollNo;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
	}
	public int getStudentRollNo() {
		return studentRollNo;
	}
	public void setStudentRollNo(int studentRollNo) {
		this.studentRollNo = studentRollNo;
	}
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	@Override
	public String toString() {
		return "FinalClassStudentReport [studentRollNo=" + studentRollNo + ", studentFirstName=" + studentFirstName
				+ ", studentLastName=" + studentLastName + "]";
	}
	
	  
}
