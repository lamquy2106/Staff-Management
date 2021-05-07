package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RoomProjects")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class RoomProject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rpId;
	
	@Enumerated(EnumType.ORDINAL)
	private EStatusProject status;
	
	private Date receivedDate;
	
	private Date finishDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room", nullable = false)
	@JsonIgnore
	private Room room;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "project", nullable = false)
	@JsonIgnore
	private Project project;

	public RoomProject() {
		super();
	}

	public RoomProject(Long rpId, EStatusProject status, Date receivedDate, Date finishDate, Room room,
			Project project) {
		super();
		this.rpId = rpId;
		this.status = status;
		this.receivedDate = receivedDate;
		this.finishDate = finishDate;
		this.room = room;
		this.project = project;
	}

	public Long getRpId() {
		return rpId;
	}

	public void setRpId(Long rpId) {
		this.rpId = rpId;
	}

	public EStatusProject getStatus() {
		return status;
	}

	public void setStatus(EStatusProject status) {
		this.status = status;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "RoomProject [rpId=" + rpId + ", status=" + status + ", receivedDate=" + receivedDate + ", finishDate="
				+ finishDate + ", room=" + room + ", project=" + project + "]";
	}

	
	
}
