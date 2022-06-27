package com.entity;

import java.util.ArrayList;
import java.util.Date;

public class Base implements Cloneable {

	
	public String eid = "";
	public String name = "";
	public Integer salary;
	public Date joinDate;
	public String JD = "";
	public String role = "";
	
	public ArrayList<String> response = new ArrayList<String>();             
		
//	add newly
	public Integer id = 0;;
	
	public Base(Integer id,String eid, String name, Integer salary, String role, Date joinDate,  ArrayList<String> response) {
		System.out.println("called JD with DATE");
		
		this.eid = eid;
		this.name = name;
		this.salary = salary;
		this.joinDate = joinDate;
		this.role = role;
		this.response = response;
		this.id = id;
	}
	
	public Base(Integer id,String eid, String name, Integer salary, String role, String JD,  ArrayList<String> response) {
		System.out.println("called JD with string");
		this.eid = eid;
		this.name = name;
		this.salary = salary;
		this.JD = JD;
		this.role = role;
		this.response = response;
		this.id = id;
	}
	
	public Base() {
		
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getJD() {
		return JD;
	}
	public void setJD(String jD) {
		JD = jD;
	}

	
	public ArrayList<String> getResponse() {
		return response;
	}
	public void setResponse(ArrayList<String> response) {
		this.response = response;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSalary() {
		return salary;
	}
	
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static Base clone(Base list) {
		try {
			return (Base) list.clone();
		} catch (CloneNotSupportedException e) {
			// not possible
		}
		return null;
	}
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((eid == null) ? 0 : eid.hashCode());
//		return result;
//	}
	
}
