package com.appointments.appointment;

import com.appointments.user.User;

public class AppointmentDTO {

private int id;

private String date;

private boolean passed;

private String description;

private  User user;

public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}



public String getDate() {
	return date;
}



public void setDate(String date) {
	this.date = date;
}



public boolean isPassed() {
	return passed;
}



public void setPassed(boolean passed) {
	this.passed = passed;
}



public String getDescription() {
	return description;
}



public void setDescription(String description) {
	this.description = description;
}



public User getUser() {
	return user;
}



public void setUser(User user) {
	this.user = user;
}



public AppointmentDTO(String date, boolean passed, String description, User user) {
		this.date = date;
		this.passed = passed;
		this.description = description;
		this.user=user;
	}



public AppointmentDTO(int id, String date, boolean passed, String description, User user) {
	super();
	this.id = id;
	this.date = date;
	this.passed = passed;
	this.description = description;
	this.user = user;
}



public AppointmentDTO() {
	super();
}



}
