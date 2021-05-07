package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.repository.*;


@RestController
@RequestMapping(value = "/api")
public class LoginController {
	
	@Autowired
	StaffRepository staffRepo;
	
	@PostMapping("/login/{userName}/{password}")
	public ResponseEntity<Map<String, Object>> login(@PathVariable("userName") String userName , @PathVariable("password") String password){
		try {
			List<Staff> staffData = staffRepo.findByLogin(userName, password);
			
			Map<String, Object> response = new HashMap<>();
			response.put("Login" , staffData);
			response.put("errorCode", 1);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
	    	response.put("errorCode", 0);
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
