package com.example.service;

import java.util.List;

import org.springframework.core.io.Resource;

import com.example.dto.NewDTO;

public interface INewService {
	List<NewDTO> findAll(int page, int limit);
	int totalItem();
	NewDTO findById(long id);
	NewDTO save(NewDTO dto);
	void delete(long[] ids);
	Resource getThumbnail(String filename);
}
