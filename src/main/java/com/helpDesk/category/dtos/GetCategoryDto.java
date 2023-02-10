package com.helpDesk.category.dtos;

import org.springframework.stereotype.Component;

import com.helpDesk.category.entity.Category;

import lombok.Data;

@Data
@Component
public class GetCategoryDto {
	

	private String name;
	private String code;
	private Category parent;
	public GetCategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCategoryDto(String name,String code,Category parent) {
		super();
		this.name=name;
		this.code=code;
		this.parent=parent;
	}
	
	public GetCategoryDto(String name,String code)
	{
		this.name=name;
		this.code=code;
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
	

	@Override
	public String toString() {
		return "GetCategoryDto [name=" + name + ", code=" + code + "]";
	}
	
	public categoryDto categoryTODto(Category category)
	{
		categoryDto dto = new categoryDto();
		dto.setCode(category.getCode());
		dto.setName(category.getName());
		dto.setFlag(category.getFlag());
		dto.setParent(category.getParent());
		return dto;
	}
	
}
