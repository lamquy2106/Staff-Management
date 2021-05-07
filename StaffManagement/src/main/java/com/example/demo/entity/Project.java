package com.example.demo.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Projects")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long proId;
	
	private String proName;
	
	private Double proPrice;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff", nullable = false)
	@JsonIgnore
	private Staff staff;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<RoomProject> roomProject;

	public Project() {
		super();
	}

	public Project(Long proId, String proName, Double proPrice, Staff staff, Set<RoomProject> roomProject) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.proPrice = proPrice;
		this.staff = staff;
		this.roomProject = roomProject;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Double getProPrice() {
		return proPrice;
	}

	public void setProPrice(Double proPrice) {
		this.proPrice = proPrice;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Set<RoomProject> getRoomProject() {
		return roomProject;
	}

	public void setRoomProject(Set<RoomProject> roomProject) {
		this.roomProject = roomProject;
	}

	@Override
	public String toString() {
		return "Project [proId=" + proId + ", proName=" + proName + ", proPrice=" + proPrice + ", staff=" + staff
				+ ", roomProject=" + roomProject + "]";
	}

	
	
}
