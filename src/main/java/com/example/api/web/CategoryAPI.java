package com.example.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryDTO;
import com.example.service.ICategoryService;

@RestController
public class CategoryAPI {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/api/category")
	public List<CategoryDTO> getAll(){
		return categoryService.getAll();
	}
}
