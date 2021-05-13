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
public class StaffRoomController {
	@Autowired
	StaffRoomRepository srRepo;
	
	@Autowired
	StaffRepository staffRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	@PostMapping("staff/{staffId}/room/{roomId}/staffroom")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> createStaffRoom(@PathVariable Long staffId,@PathVariable Long roomId, @RequestBody @Valid StaffRoom staffRoom, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder devErrorMsg = new StringBuilder();
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError objectError : allErrors) {
				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
			}
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setErrorCode("ERR-1400");
			errorDetails.setErrorMessage("Invalid StaffRoom data received");
			errorDetails.setDevErrorMessage(devErrorMsg.toString());
			
			Map<String, Object> response = new HashMap<>();
			response.put("errorCode", errorDetails);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(!staffRepo.existsById(staffId) || !roomRepo.existsById(roomId)) {
			throw new ResourceNotFoundException("No are found with staff id= " + staffId + " and room id= " + roomId);
		}
		else {
			Staff _staff = staffRepo.findById(staffId).orElse(null);
			Room _room = roomRepo.findById(roomId).orElse(null);
			staffRoom.setStaff(_staff);
			staffRoom.setRoom(_room);
		}
		StaffRoom saveStaffRoom = srRepo.save(staffRoom);
		Map<String, Object> response = new HashMap<>();
		response.put("errorCode", 1);
		response.put("StaffRoom", saveStaffRoom);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/staffroom")
	public ResponseEntity<Map<String, Object>> listStaffRoom(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size){
		try {
			List<StaffRoom> staffRoom = new ArrayList<StaffRoom>();
			Pageable paging = PageRequest.of(page, size);
			
			Page<StaffRoom> pageTuts;
			pageTuts = srRepo.findAll(paging);
			
			staffRoom = pageTuts.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("staffRoom", staffRoom);
			response.put("currentPage", pageTuts.getNumber());
		    response.put("totalItems", pageTuts.getTotalElements());
		    response.put("totalPages", pageTuts.getTotalPages());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/staffroom/{id}")
	public ResponseEntity<Map<String, Object>> getSr(@PathVariable("id") Long id) {
		try {
			Long staffId;
			Long roomId;
			Optional<StaffRoom> srData = srRepo.findById(id);
				if (srData.isPresent()) 
					
				{
					StaffRoom _sr = srData.get();
					staffId = _sr.getStaff().getStaffId();
					roomId = _sr.getRoom().getRoomId();
					Map<String, Object> response = new HashMap<>();
				      response.put("staffRoom", srData);
				      response.put("staffId", staffId);
				      response.put("roomId", roomId);
				      response.put("errorCode", 1);
				      return new ResponseEntity<>(response, HttpStatus.OK);
				}
				else {
					throw new ResourceNotFoundException("Invalid staffRoom data");
				}
		      
		    } catch (Exception e) {
		    	Map<String, Object> response = new HashMap<>();
		    	response.put("errorCode", 0);
		      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PutMapping("/staff/{staffId}/room/{roomId}/staffroom/{srId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> updateStaffRoom(@PathVariable Long staffId,@PathVariable Long roomId, @PathVariable("srId") Long srId, @RequestBody @Valid StaffRoom staffRoom, BindingResult result){
		if(result.hasErrors()) {
			throw new IllegalArgumentException("Invalod StaffRoom data");
		}
		if(staffRepo.existsById(staffId) && roomRepo.existsById(roomId)) {
//			Staff _staff = staffRepo.findById(staffId).orElse(null);
//			Violation _violation = vioRepo.findById(vioId).orElse(null);
			Optional<StaffRoom> staffRoomData = srRepo.findById(srId);
			if(staffRoomData.isPresent())
			{
				StaffRoom _staffRoom = staffRoomData.get();
				_staffRoom.setPosition(staffRoom.getPosition());
				
				StaffRoom saveStaffRoom = srRepo.save(_staffRoom);
				
				Map<String, Object> response = new HashMap<>();
				response.put("StaffRoom", saveStaffRoom);
				response.put("errorCode", 1);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				throw new ResourceNotFoundException("Invalid StaffRoom data");
			}
		}
		throw new ResourceNotFoundException("Invalid Staff id");
	}
	
	@DeleteMapping("/staffroom/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	public ResponseEntity<Map<String, Object>> deletetStaffRoom(@PathVariable("id") Long id) {
//		TimeKeeping timeKeeping = tkRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No TimeKeeping found with id=" + id));
		try {
			srRepo.deleteById(id);
			Map<String, Object> response = new HashMap<>();  
			response.put("message", "Delete Success");
			response.put("errorCode", 1);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new PostDeletionException("StaffRoom with id=" + id + " can't be deleted");
		}
	}

}
