package com.learnersacademy.model;

public class Classes {
  private String classname;

	@Override
public String toString() {
	return "Classes [classname=" + classname + "]";
}

	public Classes(String classname) {
		super();
		this.classname = classname;
	}
	
	public String getClassname() {
		return classname;
	}
	
	public void setClassname(String classname) {
		this.classname = classname;
	}
  
}
