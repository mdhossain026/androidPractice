package com.hossain.blood.search.model;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * @author hossain
	 * @ Date: 28 Jan 2014
	 *  Message Entity class
	 */
	private static final long serialVersionUID = -1545547495831121755L;
	
	private String bloodGroup;
	private String patName;
	private String diseaseName;
	private String patLocation;
	private String contactNo;
	
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getPatLocation() {
		return patLocation;
	}
	public void setPatLocation(String patLocation) {
		this.patLocation = patLocation;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
	

}
