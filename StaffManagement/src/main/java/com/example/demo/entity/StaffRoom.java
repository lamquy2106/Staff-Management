package com.example.demo.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "StaffRooms")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StaffRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long srId;
	
	@Enumerated(EnumType.ORDINAL)
	private EPosition position;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room", nullable = false)
	@JsonIgnore
	private Room room;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff", nullable = false)
	@JsonIgnore
	private Staff staff;

	public StaffRoom() {
		super();
	}

	public StaffRoom(Long srId, EPosition position, Room room, Staff staff) {
		super();
		this.srId = srId;
		this.position = position;
		this.room = room;
		this.staff = staff;
	}

	public Long getSrId() {
		return srId;
	}

	public void setSrId(Long srId) {
		this.srId = srId;
	}

	public EPosition getPosition() {
		return position;
	}

	public void setPosition(EPosition position) {
		this.position = position;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "StaffRoom [srId=" + srId + ", position=" + position + ", room=" + room + ", staff=" + staff + "]";
	}

	
	
}
