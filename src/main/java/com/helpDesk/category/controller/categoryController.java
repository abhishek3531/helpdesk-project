package com.helpDesk.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpDesk.category.dtos.GetCategoryDto;
import com.helpDesk.category.dtos.GetChildDto;
import com.helpDesk.category.dtos.categoryDto;
import com.helpDesk.category.dtos.updateCategoryDto;
import com.helpDesk.category.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class categoryController {

	@Autowired
	CategoryService categoryService;

	// CREATE CATEGORY
	@PostMapping("/")
	public ResponseEntity<?> createCategory(@Valid @RequestBody categoryDto category) {
		 categoryService.createCategory(category);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	//Get Category By name
	@GetMapping("/{code}")
	public ResponseEntity<GetCategoryDto> GetByName(@PathVariable String code)
	{
		GetCategoryDto category = categoryService.getCategory(code);
		return new ResponseEntity<GetCategoryDto>(category, HttpStatus.OK);
	}
	// GET ALL CATEGORIES
	@GetMapping("/")
	public ResponseEntity<List<categoryDto>> getCategories() {
		List<categoryDto> category = categoryService.getCategory();
		return new ResponseEntity<List<categoryDto>>(category, HttpStatus.OK);
	}

	// fetching child of a category
	@GetMapping("/{code}/child")
	public ResponseEntity<List<GetChildDto>> getChild(@PathVariable("code") String code) {
		
		List<GetChildDto> childrenOfCategory = categoryService.childrenOfCategory(code);
		return new ResponseEntity<List<GetChildDto>>(childrenOfCategory,HttpStatus.OK);
	}

	// Update Fields
	@PatchMapping("/{code}")
	public ResponseEntity<?> update(@PathVariable String code, @Valid @RequestBody updateCategoryDto category) {
		categoryService.updateFields(code, category);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Delete Category
	@DeleteMapping("/{code}")
	public ResponseEntity<?> deleteCategory(@PathVariable String code) {
		categoryService.deleteCategory(code);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
