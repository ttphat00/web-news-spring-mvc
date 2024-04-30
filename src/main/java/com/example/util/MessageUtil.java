package com.example.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

	public Map<String, String> getMessage(String message){
		Map<String, String> result = new HashMap<>();
		
		switch(message) {
		case "update_success":
			result.put("message", "Updated success");
			result.put("alert", "success");
			break;
		case "insert_success":
			result.put("message", "Created success");
			result.put("alert", "success");
			break;
		case "delete_success":
			result.put("message", "Deleted success");
			result.put("alert", "success");
			break;
		case "error_system":
			result.put("message", "Error system");
			result.put("alert", "danger");
			break;
		default:
			break;
		}
		
		return result;
	}
}
