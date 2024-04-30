package com.example.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.NewDTO;
import com.example.service.INewService;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {
	@Autowired
	private INewService newService;

	@PostMapping(value = "/api/new")
	public NewDTO createNew(@ModelAttribute NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO updateNew(@RequestBody NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@DeleteMapping("/api/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
	
	@GetMapping("/api/new/thumbnail/{filename:.+}")
    public ResponseEntity<Resource> getThumbnail(@PathVariable String filename){
        Resource file = newService.getThumbnail(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
