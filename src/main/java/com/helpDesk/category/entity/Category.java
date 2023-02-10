package com.helpDesk.category.entity;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helpDesk.category.dtos.categoryDto;
import com.helpDesk.category.dtos.updateCategoryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@Where(clause = "flag=false")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @Column(length = 64)
    private String name;

    private boolean Flag=false;
	
	@ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
	
	private String code;
	

	public Category(Integer id, String name, boolean Flag, Category parent) {
		super();
		this.id = id;
		this.name = name;
		this.Flag = Flag;
		this.parent = parent;
		
	}
	
    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
    public Category(String name,String code) {
        this.name = name;
        this.code=code;
    }
    public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

   
 // Mapper categoryDto to Category
    public Category(categoryDto category) {
    	this.code=category.getCode();
    	this.name=category.getName();
    	this.parent=category.getParent();
    }
    
 // Mapper updateCategoryDto to Category
    public Category(updateCategoryDto category) {
		this.code=category.getCode();
    	this.name= category.getName();
    	this.parent=category.getParent();
	}
   
	

	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Category getParent() {
        return parent;
    }
 
    public void setParent(Category parent) {
        this.parent = parent;
    }
	public boolean getFlag() {
	return Flag;
}

public void setFlag(boolean Flag) {
	this.Flag = Flag;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

	@Override
	public String toString() {
		return " [ name=" + name + ", parent=" + parent + "]";
	}
	
}