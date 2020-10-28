package com.spring.boot.hrservice.service;

import java.time.LocalDate;
import java.util.List;



public class HRPersonalProfile {
	private String fname;
	private String lname;
	private LocalDate birthdate;
	private String address;
	private List<OccupationServices> services;
	private int id;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OccupationServices> getServices() {
		return services;
	}

	public void setServices(List<OccupationServices> services) {
		this.services = services;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
