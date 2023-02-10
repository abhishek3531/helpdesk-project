package com.helpDesk.category.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helpDesk.category.dtos.GetCategoryDto;
import com.helpDesk.category.dtos.GetChildDto;
import com.helpDesk.category.dtos.categoryDto;
import com.helpDesk.category.dtos.updateCategoryDto;



@Service
public interface CategoryService {

	public void createCategory(categoryDto category);
	
	public GetCategoryDto getCategory(String  code);

	public List<categoryDto> getCategory();

	public void deleteCategory(String code);

	public void updateFields(String code, updateCategoryDto category);
	
	public List<GetChildDto> childrenOfCategory(String code);

}
