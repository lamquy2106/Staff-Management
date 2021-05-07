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
public class RoomProjectController {
	@Autowired
	RoomProjectRepository rpRepo;
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	@PostMapping("project/{proId}/room/{roomId}/roomproject")
	public ResponseEntity<Map<String, Object>> createRoomProject(@PathVariable Long proId,@PathVariable Long roomId, @RequestBody @Valid RoomProject roomProject, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder devErrorMsg = new StringBuilder();
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError objectError : allErrors) {
				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
			}
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setErrorCode("ERR-1400");
			errorDetails.setErrorMessage("Invalid RoomProject data received");
			errorDetails.setDevErrorMessage(devErrorMsg.toString());
			
			Map<String, Object> response = new HashMap<>();
			response.put("errorCode", errorDetails);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(!proRepo.existsById(proId) || !roomRepo.existsById(roomId)) {
			throw new ResourceNotFoundException("No are found with project id= " + proId + " and room id= " + roomId);
		}
		else {
			Project _project = proRepo.findById(proId).orElse(null);
			Room _room = roomRepo.findById(roomId).orElse(null);
			roomProject.setProject(_project);
			roomProject.setRoom(_room);
		}
		RoomProject saveRoomProject = rpRepo.save(roomProject);
		Map<String, Object> response = new HashMap<>();
		response.put("errorCode", 1);
		response.put("RoomProject", saveRoomProject);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/roomproject")
	public ResponseEntity<Map<String, Object>> listRoomProject(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size){
		try {
			List<RoomProject> roomProject = new ArrayList<RoomProject>();
			Pageable paging = PageRequest.of(page, size);
			
			Page<RoomProject> pageTuts;
			pageTuts = rpRepo.findAll(paging);
			
			roomProject = pageTuts.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("roomProject", roomProject);
			response.put("currentPage", pageTuts.getNumber());
		    response.put("totalItems", pageTuts.getTotalElements());
		    response.put("totalPages", pageTuts.getTotalPages());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/roomproject/{id}")
	public ResponseEntity<Map<String, Object>> getRp(@PathVariable("id") Long id) {
		try {
			Long proId;
			Long roomId;
			Optional<RoomProject> rpData = rpRepo.findById(id);
				if (rpData.isPresent()) 
					
				{
					RoomProject _rp = rpData.get();
					proId = _rp.getProject().getProId();
					roomId = _rp.getRoom().getRoomId();
					Map<String, Object> response = new HashMap<>();
				      response.put("roomProject", rpData);
				      response.put("proId", proId);
				      response.put("roomId", roomId);
				      response.put("errorCode", 1);
				      return new ResponseEntity<>(response, HttpStatus.OK);
				}
				else {
					throw new ResourceNotFoundException("Invalid roomProject data");
				}
		      
		    } catch (Exception e) {
		    	Map<String, Object> response = new HashMap<>();
		    	response.put("errorCode", 0);
		      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PutMapping("/project/{proId}/room/{roomId}/roomproject/{rpId}")
	public ResponseEntity<Map<String, Object>> updateRoomProject(@PathVariable Long proId,@PathVariable Long roomId, @PathVariable("rpId") Long rpId, @RequestBody @Valid RoomProject roomProject, BindingResult result){
		if(result.hasErrors()) {
			throw new IllegalArgumentException("Invalod RoomProject data");
		}
		if(proRepo.existsById(proId) && roomRepo.existsById(roomId)) {
//			Staff _staff = staffRepo.findById(staffId).orElse(null);
//			Violation _violation = vioRepo.findById(vioId).orElse(null);
			Optional<RoomProject> roomProjectData = rpRepo.findById(rpId);
			if(roomProjectData.isPresent())
			{
				RoomProject _roomProject = roomProjectData.get();
				_roomProject.setStatus(roomProject.getStatus());
				_roomProject.setReceivedDate(roomProject.getReceivedDate());
				_roomProject.setFinishDate(roomProject.getFinishDate());
				
				RoomProject saveRoomProject = rpRepo.save(_roomProject);
				
				Map<String, Object> response = new HashMap<>();
				response.put("RoomProject", saveRoomProject);
				response.put("errorCode", 1);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				throw new ResourceNotFoundException("Invalid RoomProject data");
			}
		}
		throw new ResourceNotFoundException("Invalid RoomProject id");
	}
	
	@DeleteMapping("/roomproject/{id}")
	public ResponseEntity<Map<String, Object>> deletetRoomProject(@PathVariable("id") Long id) {
//		TimeKeeping timeKeeping = tkRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No TimeKeeping found with id=" + id));
		try {
			rpRepo.deleteById(id);
			Map<String, Object> response = new HashMap<>();  
			response.put("message", "Delete Success");
			response.put("errorCode", 1);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new PostDeletionException("RoomProject with id=" + id + " can't be deleted");
		}
	}

}
