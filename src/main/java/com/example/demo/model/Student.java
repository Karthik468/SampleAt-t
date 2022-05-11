package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="students")
public class Student {
	@Id
	private int sid;
	private String sname;
	private String sbranch;
	private String scgpa;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int sid, String sname, String sbranch, String scgpa) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sbranch = sbranch;
		this.scgpa = scgpa;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSbranch() {
		return sbranch;
	}
	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}
	public String getScgpa() {
		return scgpa;
	}
	public void setScgpa(String scgpa) {
		this.scgpa = scgpa;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sbranch=" + sbranch + ", scgpa=" + scgpa + "]";
	}
	
	
}
