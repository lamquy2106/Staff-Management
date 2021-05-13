package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

@RestController
@RequestMapping(value = "/api")
public class RoomController {
	@Autowired
	RoomRepository roomRepo;
	
	@PostMapping("/room")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> createRoom(@RequestBody @Valid Room room, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder devErrorMsg = new StringBuilder();
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError objectError : allErrors) {
				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
			}
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setErrorCode("ERR-1400");
			errorDetails.setErrorMessage("Invalid Room data received");
			errorDetails.setDevErrorMessage(devErrorMsg.toString());
			Map<String, Object> response = new HashMap<>();
			response.put("errorCode", errorDetails);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		Room saveRoom = roomRepo.save(room);
		Map<String, Object> response = new HashMap<>();
		response.put("errorCode", 1);
		response.put("Room", saveRoom);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/room")
	public ResponseEntity<Map<String, Object>> listRoom(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size){
		try {
			List<Room> room = new ArrayList<Room>();
			Pageable paging = PageRequest.of(page, size);
			
			Page<Room> pageTuts;
			pageTuts = roomRepo.findAll(paging);
			
			room = pageTuts.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("room", room);
			response.put("currentPage", pageTuts.getNumber());
		    response.put("totalItems", pageTuts.getTotalElements());
		    response.put("totalPages", pageTuts.getTotalPages());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/room/{id}")
	public Room room(@PathVariable("id") Long id) {
		return roomRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No IngredientCategory found with id=" + id));
	}
	
	@PutMapping("room/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> updateRoom(@PathVariable("id") Long id, @RequestBody @Valid Room room, BindingResult result){
		if(result.hasErrors()) {
			throw new IllegalArgumentException("Invalod IngredientCategory data");
		}
		if(roomRepo.existsById(id)) {
			Optional<Room> roomData = roomRepo.findById(id);
			if(roomData.isPresent())
			{
				Room _room = roomData.get();
				_room.setName(room.getName());
				
				Room saveRoom = roomRepo.save(_room);
				
				Map<String, Object> response = new HashMap<>();
				response.put("Room", saveRoom);
				response.put("errorCode", 1);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				throw new ResourceNotFoundException("Invalid Room Status data");
			}
		}
		throw new ResourceNotFoundException("Invalid Room Status id");
	}
	
	@DeleteMapping("/room/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> deletetRoom(@PathVariable("id") Long id) {
//		Violation violation = violationRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No violation found with id=" + id));
		try {
			roomRepo.deleteById(id);
			Map<String, Object> response = new HashMap<>();  
			response.put("message", "Delete Success");
			response.put("errorCode", 1);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new PostDeletionException("Room with id=" + id + " can't be deleted");
		}
	}
	
	@GetMapping("/allsalary/{n}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> allSalary(@PathVariable("n") String n){
		try {
			if(n != "") {
			System.out.println("dit me may");
				List<Room> roomData = roomRepo.findByNameLike("%"+n+"%");
//			String roomData = "a";
				Map<String, Object> response = new HashMap<>();
			      response.put("salary", roomData);
			      response.put("errorCode", 1);
			      return new ResponseEntity<>(response, HttpStatus.OK);
			
			}
			else {
				System.out.println("data input fail");
				throw new ResourceNotFoundException("Invalid Room Status data");
			}
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
	    	response.put("errorCode", 0);
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
