package com.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.CategoryConverter;
import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import com.example.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public Map<String, String> findAll() {
		Map<String, String> result = new HashMap<>();

		List<CategoryEntity> entities = categoryRepo.findAll();
		for (CategoryEntity entity : entities) {
			result.put(entity.getCode(), entity.getName());
		}

		return result;
	}

	@Override
	public List<CategoryDTO> getAll() {
		List<CategoryDTO> result = new ArrayList<>();
		
		List<CategoryEntity> entities = categoryRepo.findAll();
		for (CategoryEntity entity : entities) {
			CategoryDTO dto = categoryConverter.toDto(entity);
			result.add(dto);
		}

		return result;
	}

}
