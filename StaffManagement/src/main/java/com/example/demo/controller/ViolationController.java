package com.example.demo.controller;
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
public class ViolationController {
//	@Autowired
//	ViolationRepository violationRepo;
//	
//	@PostMapping("/violation")
//	public ResponseEntity<Map<String, Object>> createViolation(@RequestBody @Valid Violation violation, BindingResult result) {
//		if(result.hasErrors()) {
//			StringBuilder devErrorMsg = new StringBuilder();
//			List<ObjectError> allErrors = result.getAllErrors();
//			for(ObjectError objectError : allErrors) {
//				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
//			}
//			ErrorDetails errorDetails = new ErrorDetails();
//			errorDetails.setErrorCode("ERR-1400");
//			errorDetails.setErrorMessage("Invalid violation data received");
//			errorDetails.setDevErrorMessage(devErrorMsg.toString());
//			Map<String, Object> response = new HashMap<>();
//			response.put("errorCode", errorDetails);
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//		Violation saveViolation = violationRepo.save(violation);
//		Map<String, Object> response = new HashMap<>();
//		response.put("errorCode", 1);
//		response.put("violation", saveViolation);
//		return new ResponseEntity<>(response, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/violation")
//	public ResponseEntity<Map<String, Object>> listViolation(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "100") int size){
//		try {
//			List<Violation> violation = new ArrayList<Violation>();
//			Pageable paging = PageRequest.of(page, size);
//			
//			Page<Violation> pageTuts;
//			pageTuts = violationRepo.findAll(paging);
//			
//			violation = pageTuts.getContent();
//			
//			Map<String, Object> response = new HashMap<>();
//			response.put("violation", violation);
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
//	@GetMapping("/violation/{id}")
//	public Violation violation(@PathVariable("id") Long id) {
//		return violationRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No IngredientCategory found with id=" + id));
//	}
//	
//	@PutMapping("violation/{id}")
//	public ResponseEntity<Map<String, Object>> updateViolation(@PathVariable("id") Long id, @RequestBody @Valid Violation violation, BindingResult result){
//		if(result.hasErrors()) {
//			throw new IllegalArgumentException("Invalod IngredientCategory data");
//		}
//		if(violationRepo.existsById(id)) {
//			Optional<Violation> violationData = violationRepo.findById(id);
//			if(violationData.isPresent())
//			{
//				Violation _violation = violationData.get();
//				_violation.setViolationName(violation.getViolationName());
//				Violation saveViolation = violationRepo.save(_violation);
//				
//				Map<String, Object> response = new HashMap<>();
//				response.put("violation", saveViolation);
//				response.put("errorCode", 1);
//				return new ResponseEntity<>(response, HttpStatus.OK);
//			}
//			else
//			{
//				throw new ResourceNotFoundException("Invalid vilation Status data");
//			}
//		}
//		throw new ResourceNotFoundException("Invalid violation Status id");
//	}
//	
//	@DeleteMapping("/violation/{id}")
//	public ResponseEntity<Map<String, Object>> deletetViolation(@PathVariable("id") Long id) {
////		Violation violation = violationRepo.findById(id)
////				.orElseThrow(() -> new ResourceNotFoundException("No violation found with id=" + id));
//		try {
//			violationRepo.deleteById(id);
//			Map<String, Object> response = new HashMap<>();  
//			response.put("message", "Delete Success");
//			response.put("errorCode", 1);
//			return  new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			throw new PostDeletionException("Violation with id=" + id + " can't be deleted");
//		}
//	}
}
