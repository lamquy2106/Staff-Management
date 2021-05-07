package com.example.demo.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Rooms")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	private String name;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<StaffRoom> staffRooms;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<RoomProject> roomProject;

	public Room() {
		super();
	}

	public Room(Long roomId, String name, Set<StaffRoom> staffRooms, Set<RoomProject> roomProject) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.staffRooms = staffRooms;
		this.roomProject = roomProject;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<StaffRoom> getStaffRooms() {
		return staffRooms;
	}

	public void setStaffRooms(Set<StaffRoom> staffRooms) {
		this.staffRooms = staffRooms;
	}

	public Set<RoomProject> getRoomProject() {
		return roomProject;
	}

	public void setRoomProject(Set<RoomProject> roomProject) {
		this.roomProject = roomProject;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", name=" + name + ", staffRooms=" + staffRooms + ", roomProject="
				+ roomProject + "]";
	}

	
	
}
