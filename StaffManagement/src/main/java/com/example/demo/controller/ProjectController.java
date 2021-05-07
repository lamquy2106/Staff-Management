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
public class ProjectController {
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	StaffRepository staffRepo;
	
	@PostMapping("/staff/{staffId}/project")
	public ResponseEntity<Map<String, Object>> createProject(@PathVariable Long staffId,@RequestBody @Valid Project project, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder devErrorMsg = new StringBuilder();
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError objectError : allErrors) {
				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
			}
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setErrorCode("ERR-1400");
			errorDetails.setErrorMessage("Invalid project data received");
			errorDetails.setDevErrorMessage(devErrorMsg.toString());
			Map<String, Object> response = new HashMap<>();
			response.put("errorCode", errorDetails);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(!staffRepo.existsById(staffId)) {
			throw new ResourceNotFoundException("No are found with id= " + staffId);
		}
		else {
			Staff _staff = staffRepo.findById(staffId).orElse(null);
			project.setStaff(_staff);
		}
		Project saveProject = projectRepo.save(project);
		Map<String, Object> response = new HashMap<>();
		response.put("errorCode", 1);
		response.put("project", saveProject);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/project")
	public ResponseEntity<Map<String, Object>> listProject(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size){
		try {
			List<Project> project = new ArrayList<Project>();
			Pageable paging = PageRequest.of(page, size);
			
			Page<Project> pageTuts;
			pageTuts = projectRepo.findAll(paging);
			
			project = pageTuts.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("project", project);
			response.put("currentPage", pageTuts.getNumber());
		    response.put("totalItems", pageTuts.getTotalElements());
		    response.put("totalPages", pageTuts.getTotalPages());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/project/{id}")
	public ResponseEntity<Map<String, Object>> getProject(@PathVariable("id") Long id) {
		try {
			Long staffId;
			Optional<Project> projectData = projectRepo.findById(id);
				if (projectData.isPresent()) 
					
				{
					Project _project = projectData.get();
					staffId = _project.getStaff().getStaffId();
					Map<String, Object> response = new HashMap<>();
				      response.put("project", projectData);
				      response.put("staffId", staffId);
				      response.put("errorCode", 1);
				      return new ResponseEntity<>(response, HttpStatus.OK);
				}
				else {
					throw new ResourceNotFoundException("Invalid project data");
				}
		      
		    } catch (Exception e) {
		    	Map<String, Object> response = new HashMap<>();
		    	response.put("errorCode", 0);
		    	return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PutMapping("/staff/{staffId}/project/{proId}")
	public ResponseEntity<Map<String, Object>> updateProject(@PathVariable Long staffId,@PathVariable("proId") Long proId, @RequestBody @Valid Project project, BindingResult result){
		if(result.hasErrors()) {
			throw new IllegalArgumentException("Invalod project data");
		}
		if(staffRepo.existsById(staffId)) {
			Optional<Project> projectData = projectRepo.findById(proId);
			if(projectData.isPresent())
			{
				Project _project = projectData.get();
				_project.setProName(project.getProName());
				_project.setProPrice(project.getProPrice());
				
				
				Project saveProject = projectRepo.save(_project);
				
				Map<String, Object> response = new HashMap<>();
				response.put("project", saveProject);
				response.put("errorCode", 1);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				throw new ResourceNotFoundException("Invalid project data");
			}
		}
		throw new ResourceNotFoundException("Invalid Staff id");
	}
	
	@DeleteMapping("/project/{id}")
	public ResponseEntity<Map<String, Object>> deletetProject(@PathVariable("id") Long id) {
//		Project project = projectRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No Project found with id=" + id));
		try {
			projectRepo.deleteById(id);
			Map<String, Object> response = new HashMap<>();  
			response.put("message", "Delete Success");
			response.put("errorCode", 1);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new PostDeletionException("Project with id=" + id + " can't be deleted");
		}
	}
}
