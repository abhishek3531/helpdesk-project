package com.helpDesk.user.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.helpDesk.user.entity.Role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
	@NotEmpty
	@Pattern(message="Minimum 2 characters requried",regexp="^(?=.{2,20}$)(?![_.])(?!.*[_.]{2})[A-Za-z._]+(?<![_.])$")
	private String userName;
	private String password;
	private int failureAttempes=0;
	private boolean active = false;
	private String createdBy;
	@CreatedDate
	private Date createdDate;
	private List<Role> role;
	public UserDTO() {
		super();
		
	}
	public UserDTO(String userName, String password, int failureAttempes, boolean active, String createdBy,
			Date createdDate,List<Role> role) {
		super();
		this.userName = userName;
		this.password = password;
		this.failureAttempes = failureAttempes;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.role=role;
	}
	public UserDTO(String userName, String password,String createdBy) {
		super();
		this.userName = userName;
		this.password = password;
		this.createdBy = createdBy;
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFailureAttempes() {
		return failureAttempes;
	}
	public void setFailureAttempes(int failureAttempes) {
		this.failureAttempes = failureAttempes;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	

}
