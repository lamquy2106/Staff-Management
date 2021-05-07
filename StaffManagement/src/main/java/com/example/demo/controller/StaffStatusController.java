package com.example.demo.controller;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.entity.*;
//import com.example.demo.exception.*;
//import com.example.demo.repository.*;
//
//
//@RestController
//@RequestMapping(value = "/api")
public class StaffStatusController {
//
//	@Autowired
//	StaffStatusRepository staffStatusrepo;
//	
//	@PostMapping("/staffstatus")
//	public ResponseEntity<Map<String, Object>> createStaffStatus(@RequestBody @Valid StaffStatus staffStatus, BindingResult result) {
//		if(result.hasErrors()) {
//			StringBuilder devErrorMsg = new StringBuilder();
//			List<ObjectError> allErrors = result.getAllErrors();
//			for(ObjectError objectError : allErrors) {
//				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
//			}
//			ErrorDetails errorDetails = new ErrorDetails();
//			errorDetails.setErrorCode("ERR-1400");
//			errorDetails.setErrorMessage("Invalid staffStatus data received");
//			errorDetails.setDevErrorMessage(devErrorMsg.toString());
//			Map<String, Object> response = new HashMap<>();
//			response.put("errorCode", errorDetails);
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//		StaffStatus saveStaffStatus = staffStatusrepo.save(staffStatus);
//		Map<String, Object> response = new HashMap<>();
//		response.put("errorCode", 1);
//		response.put("staffStatus", saveStaffStatus);
//		return new ResponseEntity<>(response, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/staffstatus")
//	public ResponseEntity<Map<String, Object>> listStaffStatus(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "100") int size){
//		try {
//			List<StaffStatus> staffStatus = new ArrayList<StaffStatus>();
//			Pageable paging = PageRequest.of(page, size);
//			
//			Page<StaffStatus> pageTuts;
//			pageTuts = staffStatusrepo.findAll(paging);
//			
//			staffStatus = pageTuts.getContent();
//			
//			Map<String, Object> response = new HashMap<>();
//			response.put("staffStatus", staffStatus);
//			response.put("currentPage", pageTuts.getNumber());
//		    response.put("totalItems", pageTuts.getTotalElements());
//		    response.put("totalPages", pageTuts.getTotalPages());
//		    
//		    return new ResponseEntity<>(response, HttpStatus.OK);
//		}
//		catch(Exception e){
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@GetMapping("/staffstatus/{id}")
//	public StaffStatus staffStatus(@PathVariable("id") Long id) {
//		return staffStatusrepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No IngredientCategory found with id=" + id));
//	}
//	
//	@PutMapping("staffstatus/{id}")
//	public ResponseEntity<Map<String, Object>> updateStaffStatus(@PathVariable("id") Long id, @RequestBody @Valid StaffStatus staffStatus, BindingResult result){
//		if(result.hasErrors()) {
//			throw new IllegalArgumentException("Invalod StaffStatus data");
//		}
//		if(staffStatusrepo.existsById(id)) {
//			Optional<StaffStatus> staffStatusData = staffStatusrepo.findById(id);
//			if(staffStatusData.isPresent())
//			{
//				StaffStatus _staffStatus = staffStatusData.get();
//				_staffStatus.setName(staffStatus.getName());
//				StaffStatus saveStaffStatus = staffStatusrepo.save(_staffStatus);
//				
//				Map<String, Object> response = new HashMap<>();
//				response.put("staffStatus", saveStaffStatus);
//				response.put("errorCode", 1);
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			}
//			else
//			{
//				throw new ResourceNotFoundException("Invalid Staff Status data");
//			}
//		}
//		throw new ResourceNotFoundException("Invalid Staff Status id");
//	}
//	
//	@DeleteMapping("/staffstatus/{id}")
//	public ResponseEntity<Map<String, Object>> deletetStaffStatus(@PathVariable("id") Long id) {
////		StaffStatus staffStatus = staffStatusrepo.findById(id)
////				.orElseThrow(() -> new ResourceNotFoundException("No StaffStatus found with id=" + id));
//		try {
//			staffStatusrepo.deleteById(id);
//			Map<String, Object> response = new HashMap<>();  
//			response.put("message", "Delete Success");
//			response.put("errorCode", 1);
//			return  new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			throw new PostDeletionException("StaffStatus with id=" + id + " can't be deleted");
//		}
//	}
}
