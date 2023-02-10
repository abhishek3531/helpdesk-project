package com.helpDesk.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.helpDesk.user.dto.UpdateUserDto;
import com.helpDesk.user.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Where(clause = "active=false")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	private String password;
	private int failureAttempes=0;
	private boolean active = false;
	private String createdBy;
	@CreatedDate
	private Date createdDate;
	
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "userId")
	, inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> role=new ArrayList<>();

	public User(UserDTO userDto) {
		super();
		this.userName=userDto.getUserName();
		this.password=userDto.getPassword();
		this.failureAttempes=userDto.getFailureAttempes();
		this.active=userDto.isActive();
		this.createdBy=userDto.getCreatedBy();
		this.createdDate=userDto.getCreatedDate();
		this.role=userDto.getRole();
			}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(UpdateUserDto updateUserDto) {
		this.userName=updateUserDto.getUserName();
		this.password=updateUserDto.getPassword();
	}

	public User(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", failureAttempes="
				+ failureAttempes + ", active=" + active + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", role=" + role + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = this.role.stream().
				map((role)->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getUsername() {
		
		
		return userName;
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
}
