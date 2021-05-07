package com.example.demo.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.Staff;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.payload.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserAuthController {
	
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

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getUserName() + " " + loginRequest.getPassword());
System.out.println(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()).getPrincipal());
Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		System.out.println(1);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		System.out.println(userDetails.getUsername());
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getStaffEmail(), 
												 roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
		Staff staff = new Staff(signUpRequest.getUserName(), 
				 signUpRequest.getStaffEmail(),
				 encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		System.out.println(strRoles);
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_STAFF)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "staff":
					Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(staffRole);

					break;
				case "manager":
					Role managerRole= roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(managerRole);

					break;
				}
			});
		}
		System.out.println(roles.isEmpty());
		staff.setRoles(roles);
		
		System.out.println(staff.getRoles().toString());

		userRepository.save(staff);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
