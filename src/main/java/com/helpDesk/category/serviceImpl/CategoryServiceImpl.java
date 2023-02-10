package com.helpDesk.category.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpDesk.ExceptionHandler.ChildNotFoundException;
import com.helpDesk.ExceptionHandler.CodeException;
import com.helpDesk.ExceptionHandler.ResourceNotFoundException;
import com.helpDesk.ExceptionHandler.resourceAlreadyExist;
import com.helpDesk.category.dtos.GetCategoryDto;
import com.helpDesk.category.dtos.GetChildDto;
import com.helpDesk.category.dtos.categoryDto;
import com.helpDesk.category.dtos.updateCategoryDto;
import com.helpDesk.category.entity.Category;
import com.helpDesk.category.repository.CategoryRepo;
import com.helpDesk.category.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryReposatiory;

	@Autowired
	GetCategoryDto getCategoryDto;

	// CREATE CATEGORY
	@Override
	public void createCategory(categoryDto categorydto) {
		Category category = new Category(categorydto);
		String categoyName = category.getName();
		Category findCategory = categoryReposatiory.findByName(categoyName);
		if (findCategory != null) {
			throw new resourceAlreadyExist(categoyName);
		}
		Category categoryByCode = categoryReposatiory.findByCode(category.getCode().toUpperCase());
		if (category.getCode() == null || categoryByCode != null) {
			throw new CodeException(category.getCode());
		} else {
			category.setCode(category.getCode().toUpperCase());
		}

		if (category.getParent() != null) {
			String name = category.getParent().getName();
			Category parent = categoryReposatiory.findByName(name);
			if (parent == null) {
				throw new ResourceNotFoundException(name);
			} else {
				category.setParent(parent);
			}
		}
		categoryReposatiory.save(category);

	}

	// Update Fields
	@Override
	public void updateFields(String code, updateCategoryDto Category) {
		Category category = new Category(Category);
		Category updateCategory = categoryReposatiory.findByCode(code.toUpperCase());
		if (updateCategory == null || updateCategory.getFlag() == true) {
			throw new ResourceNotFoundException(code);
		}
		String CategoryName = category.getName();
		if (CategoryName != null) {
			Category categoryByCode = categoryReposatiory.findByCode(Category.getCode().toUpperCase());
			Category categoryByName = categoryReposatiory.findByName(category.getName());
			if (categoryByCode != null && categoryByName != null) {
				throw new resourceAlreadyExist(CategoryName);
			} else {
				updateCategory.setName(CategoryName);
			}
		}
		if (category.getParent() != null) {
			String parentName = category.getParent().getName();
			Category parent = categoryReposatiory.findByName(parentName);
			if (parent == null) {
				throw new ResourceNotFoundException(parentName);
			} else {
				updateCategory.setParent(parent);
			}
		}
		Category categoryByCode = categoryReposatiory.findByCode(Category.getCode().toUpperCase());
		if (category.getCode() == null || categoryByCode != null) {
			throw new CodeException(code);
		}
		updateCategory.setCode(category.getCode().toUpperCase());
		categoryReposatiory.save(updateCategory);

	}

	// Get Category By name
	public GetCategoryDto getCategory(String code) {
		Category category = categoryReposatiory.findByCode(code.toUpperCase());
		if (category == null) {
			throw new ResourceNotFoundException(code);
		}
		return new GetCategoryDto(category.getName(), category.getCode(), category.getParent());
	}

	// GET ALL CATEGORIES
	@Override
	public List<categoryDto> getCategory() {
		List<Category> categories = categoryReposatiory.findAllCategories();
		List<categoryDto> dtoCategories = categories.stream().map(category -> getCategoryDto.categoryTODto(category))
				.collect(Collectors.toList());
		return dtoCategories;
	}

	// Fetch Children of category
	@Override
	public List<GetChildDto> childrenOfCategory(String code) {

		List<GetChildDto> getCategoryDto = new ArrayList<>();
		Category findByName = categoryReposatiory.findByCode(code.toUpperCase());
		if (findByName == null) {
			throw new ResourceNotFoundException(code);
		}
		List<Category> childList = categoryReposatiory.getChild(findByName.getId());
		if (childList.isEmpty()) {
			throw new ChildNotFoundException(code);
		}
		for (Category category : childList) {
			getCategoryDto.add(new GetChildDto(category.getName(), category.getCode()));
		}

		return getCategoryDto;
	}

	// DELETE CATEGORY
	public void deleteCategory(String code) {

		Category category = categoryReposatiory.findByCode(code.toUpperCase());
		if (category == null) {
			throw new ResourceNotFoundException(code);
		} else {
			category.setFlag(true);
			categoryReposatiory.save(category);
			List<Category> childList = categoryReposatiory.getChild(category.getId());
			for (Category child : childList) {
				if (child != null) {
					deleteCategory(child.getName());
				}
			}
		}
	}

}