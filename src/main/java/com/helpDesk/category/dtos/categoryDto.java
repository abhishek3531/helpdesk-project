package com.helpDesk.category.dtos;

import com.helpDesk.category.entity.Category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class categoryDto {

	@NotEmpty
	@Pattern(regexp="^(?=.{2,20}$)(?![_.])(?!.*[_.]{2})[A-Za-z._]+(?<![_.])$")
	private String name;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[0-9])"+ "(?=.*[a-z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{2,20}$")
	private String code;
	private Category parent;
	private boolean Flag;
	
	
	public categoryDto(categoryDto categoryDto) {
		super();
		this.name=categoryDto.getName();
	}
	
	public categoryDto(
			 String name,
			String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public boolean getFlag() {
		return Flag;
	}
	public void setFlag(boolean b) {
		Flag = b;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public categoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "categoryDto [name=" + name + ", code=" + code + ", parent=" + parent + ", Flag=" + Flag + "]";
	}

	

}
