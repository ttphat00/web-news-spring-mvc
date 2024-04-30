package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.dto.CategoryDTO;

public interface ICategoryService {
	Map<String, String> findAll();
	List<CategoryDTO> getAll();
}
