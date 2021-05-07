package com.example.demo.payload.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;

import com.example.demo.entity.EGender;
import com.example.demo.entity.EStaffStatus;
import com.example.demo.entity.Role;


public class SignupRequest {
	
private String staffCode;
	
	private String staffFirstName;
	
	private String staffLastName;
	
	@Enumerated(EnumType.ORDINAL)
	private EGender gender;
	
	private Double staffPhone;
	
	private String staffAddress;
	
	private Double staffCMND;
	
	private Date staffStarDay;

	
	@Enumerated(EnumType.ORDINAL)
	private EStaffStatus staffStatus;
	
	@NotBlank
    @Size(min = 3, max = 20)
    private String userName;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String staffEmail;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

	public String getStaffCode() {
		return staffCode;
	}

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public EGender getGender() {
		return gender;
	}

	public Double getStaffPhone() {
		return staffPhone;
	}

	public String getStaffAddress() {
		return staffAddress;
	}

	public Double getStaffCMND() {
		return staffCMND;
	}

	public Date getStaffStarDay() {
		return staffStarDay;
	}

	public EStaffStatus getStaffStatus() {
		return staffStatus;
	}

	public String getUserName() {
		return userName;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public Set<String> getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	
}
