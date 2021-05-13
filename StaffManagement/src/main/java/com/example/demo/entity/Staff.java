package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Staffs")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long staffId;
	
	private String staffCode;
	
	private String staffFirstName;
	
	private String staffLastName;
	
	@Enumerated(EnumType.ORDINAL)
	private EGender gender;
	
	private Date birthDay;
	
	private Double staffPhone;
	
	private String staffAddress;
	
	private String staffEmail;
	
	private Double staffCMND;
	
	private Date staffStarDay;
	
	@ManyToMany(fetch = FetchType.LAZY) 
	@JoinTable(	name = "staffRoles", 
				joinColumns = @JoinColumn(name = "staffId"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@Enumerated(EnumType.ORDINAL)
	private EStaffStatus staffStatus;
//	
//	@Enumerated(EnumType.ORDINAL)
//	private ERole role;
	
	private String userName;
	
	private String password;
	
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TimeKeeping> timeKeepings;
	
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Salary> salarys;
	
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<StaffRoom> staffRooms;

	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Project> project;

	public Staff() {
		super();
	}

	public Staff(Long staffId, String staffCode, String staffFirstName, String staffLastName, EGender gender,
			Double staffPhone, String staffAddress, Double staffCMND, Date staffStarDay,
			Set<com.example.demo.entity.Role> roles, EStaffStatus staffStatus, String userName, String staffEmail, String password,
			Set<TimeKeeping> timeKeepings, Set<Salary> salarys, Set<StaffRoom> staffRooms, Set<Project> project) {
		super();
		this.staffId = staffId;
		this.staffCode = staffCode;
		this.staffFirstName = staffFirstName;
		this.staffLastName = staffLastName;
		this.gender = gender;
		this.staffPhone = staffPhone;
		this.staffAddress = staffAddress;
		
		this.staffCMND = staffCMND;
		this.staffStarDay = staffStarDay;
		this.roles = roles;
		this.staffStatus = staffStatus;
		
		this.timeKeepings = timeKeepings;
		this.salarys = salarys;
		this.staffRooms = staffRooms;
		this.project = project;
		this.userName = userName;
		this.staffEmail = staffEmail;
		this.password = password;
	}
	
	

	public Staff(String staffCode, String staffFirstName, String staffLastName, EGender gender,Date birthDay, Double staffPhone,
			String staffAddress, Double staffCMND, Date staffStarDay, EStaffStatus staffStatus,
			String userName, String staffEmail, String password) {
		super();
		this.staffCode = staffCode;
		this.staffFirstName = staffFirstName;
		this.staffLastName = staffLastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.staffPhone = staffPhone;
		this.staffAddress = staffAddress;
		this.staffEmail = staffEmail;
		this.staffCMND = staffCMND;
		this.staffStarDay = staffStarDay;
		this.staffStatus = staffStatus;
		this.userName = userName;
		this.password = password;
	}

	public Staff( String userName, String staffEmail, String password) {
		super();
		
		this.userName = userName;
		this.staffEmail = staffEmail;
		this.password = password;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public Double getStaffPhone() {
		return staffPhone;
	}

	public void setStaffPhone(Double staffPhone) {
		this.staffPhone = staffPhone;
	}

	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public Double getStaffCMND() {
		return staffCMND;
	}

	public void setStaffCMND(Double staffCMND) {
		this.staffCMND = staffCMND;
	}

	public Date getStaffStarDay() {
		return staffStarDay;
	}

	public void setStaffStarDay(Date staffStarDay) {
		this.staffStarDay = staffStarDay;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public EStaffStatus getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(EStaffStatus staffStatus) {
		this.staffStatus = staffStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<TimeKeeping> getTimeKeepings() {
		return timeKeepings;
	}

	public void setTimeKeepings(Set<TimeKeeping> timeKeepings) {
		this.timeKeepings = timeKeepings;
	}

	public Set<Salary> getSalarys() {
		return salarys;
	}

	public void setSalarys(Set<Salary> salarys) {
		this.salarys = salarys;
	}

	public Set<StaffRoom> getStaffRooms() {
		return staffRooms;
	}

	public void setStaffRooms(Set<StaffRoom> staffRooms) {
		this.staffRooms = staffRooms;
	}

	public Set<Project> getProject() {
		return project;
	}

	public void setProject(Set<Project> project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffCode=" + staffCode + ", staffFirstName=" + staffFirstName
				+ ", staffLastName=" + staffLastName + ", gender=" + gender + ", staffPhone=" + staffPhone
				+ ", staffAddress=" + staffAddress + ", staffEmail=" + staffEmail + ", staffCMND=" + staffCMND
				+ ", staffStarDay=" + staffStarDay + ", roles=" + roles + ", staffStatus=" + staffStatus + ", userName="
				+ userName + ", password=" + password + ", timeKeepings=" + timeKeepings + ", salarys=" + salarys
				+ ", staffRooms=" + staffRooms + ", project=" + project + "]";
	}

	
}
