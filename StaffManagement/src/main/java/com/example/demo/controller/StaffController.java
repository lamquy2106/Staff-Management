package com.example.demo.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.*;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.entity.Role;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StaffController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	StaffRepository staffRepo;
	
//	@PostMapping("/staff")
//	public ResponseEntity<Map<String, Object>> createStaff( @RequestBody @Valid Staff staff, BindingResult result) {
//		if(result.hasErrors()) {
//			StringBuilder devErrorMsg = new StringBuilder();
//			List<ObjectError> allErrors = result.getAllErrors();
//			for(ObjectError objectError : allErrors) {
//				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
//			}
//			ErrorDetails errorDetails = new ErrorDetails();
//			errorDetails.setErrorCode("ERR-1400");
//			errorDetails.setErrorMessage("Invalid staff data received");
//			errorDetails.setDevErrorMessage(devErrorMsg.toString());
//			
//			Map<String, Object> response = new HashMap<>();
//			response.put("errorCode", errorDetails);
//			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		}
//		Staff saveStaff = staffRepo.save(staff);
//		Map<String, Object> response = new HashMap<>();
//		response.put("errorCode", 1);
//		response.put("staff", saveStaff);
//		return new ResponseEntity<>(response, HttpStatus.CREATED);
//	}
	@PostMapping("/staff")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("âsgklạhg");
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByStaffEmail(signUpRequest.getStaffEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Staff staff = new Staff(
				signUpRequest.getStaffCode(),
				signUpRequest.getStaffFirstName(),
				signUpRequest.getStaffLastName(),
				signUpRequest.getGender(),
				signUpRequest.getStaffPhone(),
				signUpRequest.getStaffAddress(),
				signUpRequest.getStaffCMND(),
				signUpRequest.getStaffStarDay(),
				signUpRequest.getStaffStatus(),
				signUpRequest.getUserName(), 
				 signUpRequest.getStaffEmail(),
				 encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		System.out.println(signUpRequest.getRole().toString());
		if (strRoles.isEmpty()) {
			System.out.println("jfgálkgjáljgh");
			Role userRole = roleRepository.findByName(ERole.ROLE_STAFF)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "staff":
					Role cashierRole= roleRepository.findByName(ERole.ROLE_STAFF)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(cashierRole);

					break;
				case "manager":
					Role chefRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(chefRole);

					break;
				}
			});
		}

		staff.setRoles(roles);

		userRepository.save(staff);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/staff")
	public ResponseEntity<Map<String, Object>> listStaff(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size){
		try {
			List<Staff> staff = new ArrayList<Staff>();
			Pageable paging = PageRequest.of(page, size);
			
			Page<Staff> pageTuts;
			pageTuts = staffRepo.findAll(paging);
			
			staff = pageTuts.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("staff", staff);
			response.put("currentPage", pageTuts.getNumber());
		    response.put("totalItems", pageTuts.getTotalElements());
		    response.put("totalPages", pageTuts.getTotalPages());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/staff/{id}")
	public Staff staff(@PathVariable("id") Long id) {
		return staffRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No staff found with id=" + id));
	}
	
	@PutMapping("staff/{staffId}")
	public ResponseEntity<Map<String, Object>> updateStaff( @PathVariable("staffId") Long staffId, @RequestBody @Valid Staff staff, BindingResult result){
		if(result.hasErrors()) {
			throw new IllegalArgumentException("Invalod staff data");
		}
		if(staffRepo.existsById(staffId)) {
			Optional<Staff> staffData = staffRepo.findById(staffId);
			if(staffData.isPresent())
			{
				Staff _staff = staffData.get();
				_staff.setStaffCode(staff.getStaffCode());
				_staff.setStaffFirstName(staff.getStaffFirstName());
				_staff.setStaffLastName(staff.getStaffLastName());
				_staff.setStaffPhone(staff.getStaffPhone());
				_staff.setStaffAddress(staff.getStaffAddress());
				_staff.setStaffEmail(staff.getStaffEmail());
				_staff.setStaffCMND(staff.getStaffCMND());
				_staff.setStaffStarDay(staff.getStaffStarDay());
				_staff.setStaffStatus(staff.getStaffStatus());
				_staff.setRoles(staff.getRoles());
				_staff.setUserName(staff.getUserName());
				_staff.setPassword(staff.getPassword());
				
				Staff saveStaff = staffRepo.save(_staff);
				
				Map<String, Object> response = new HashMap<>();
				response.put("staff", saveStaff);
				response.put("errorCode", 1);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				throw new ResourceNotFoundException("Invalid Staff data");
			}
		}
		throw new ResourceNotFoundException("Invalid staff id");
	}
	
	@DeleteMapping("/staff/{id}")
	public ResponseEntity<Map<String, Object>> deletetStaff(@PathVariable("id") Long id) {
//		Staff staff = staffRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No Staff found with id=" + id));
		try {
			staffRepo.deleteById(id);
			Map<String, Object> response = new HashMap<>();  
			response.put("message", "Delete Success");
			response.put("errorCode", 1);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new PostDeletionException("Staff with id=" + id + " can't be deleted");
		}
	}
}
