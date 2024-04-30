package com.example.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.api.admin.NewAPI;
import com.example.service.IFilesStorageService;

@Service
public class ThumbnailStorageService implements IFilesStorageService {
	private final Path uploads = Paths.get("D:\\workspace\\spring-eclipse18\\spring-mvc\\uploads");
    
	@Override
	public void save(MultipartFile file) {
		try {
			if (!Files.exists(uploads)) {
	            Files.createDirectories(uploads);
	        }
            Files.copy(file.getInputStream(), this.uploads.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
	}

	@Override
	public Resource load(String filename) {
		try {
            Path file = uploads.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
	}

	@Override
	public String buildFileUrl(MultipartFile file) {
		String url = MvcUriComponentsBuilder
                .fromMethodName(NewAPI.class, "getThumbnail", file.getOriginalFilename())
                .build()
                .toString();

        return url;
	}

}
