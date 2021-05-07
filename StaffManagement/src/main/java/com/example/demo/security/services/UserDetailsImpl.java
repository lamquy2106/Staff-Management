package com.example.demo.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;	

import com.example.demo.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.entity.Staff;
import com.example.demo.security.services.UserDetailsImpl;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;

	private String staffEmail;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String userName, String staffEmail, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.userName = userName;
		this.staffEmail = staffEmail;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Staff staff) {
		List<GrantedAuthority> authorities = staff.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				staff.getStaffId(), 
				staff.getUserName(), 
				staff.getStaffEmail(),
				staff.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
