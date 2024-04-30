package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.converter.NewConverter;
import com.example.dto.NewDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.NewEntity;
import com.example.repository.CategoryRepository;
import com.example.repository.NewRepository;
import com.example.service.IFilesStorageService;
import com.example.service.INewService;

@Service
public class NewService implements INewService {
	@Autowired
	private NewRepository newRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private NewConverter newConverter;
	@Autowired
	private IFilesStorageService filesStorageService;

	@Override
	public List<NewDTO> findAll(int page, int limit) {
		List<NewDTO> models = new ArrayList<>();
		
		Pageable pageable = new PageRequest(page - 1, limit);
		
		List<NewEntity> entities = newRepo.findAll(pageable).getContent();
		for(NewEntity entity : entities) {
			NewDTO dto = newConverter.toDto(entity);
			models.add(dto);
		}
		
		return models;
	}

	@Override
	public int totalItem() {
		return (int) newRepo.count();
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepo.findOne(id);
		return newConverter.toDto(entity);
	}

	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		NewEntity newEntity = new NewEntity();
		newEntity = newConverter.toEntity(dto);
		
		if(dto.getFile() != null) {
			filesStorageService.save(dto.getFile());
	        String url = filesStorageService.buildFileUrl(dto.getFile());
	        newEntity.setThumbnail(url);
		}
		
		if(dto.getId() != null) {
			NewEntity oldNewEntity = newRepo.findOne(dto.getId());
			newEntity = newConverter.toEntity(oldNewEntity, dto);
		}
		
		CategoryEntity category = categoryRepo.findOneByCode(dto.getCategoryCode());
		newEntity.setCategory(category);
		
		return newConverter.toDto(newRepo.save(newEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			newRepo.delete(id);
		}
	}

	@Override
	public Resource getThumbnail(String filename) {
		return filesStorageService.load(filename);
	}

}
