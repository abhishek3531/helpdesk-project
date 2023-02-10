package com.helpDesk.ExceptionHandler;

public class resourceAlreadyExist extends RuntimeException {

   
	private static final long serialVersionUID = 1L;
	private String name;
	
	public resourceAlreadyExist(String name) {
		super(String.format("Category Already Exist With Name :%s",name));
		this.name = name;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
