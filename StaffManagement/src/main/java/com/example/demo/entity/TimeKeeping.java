package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TimeKeepings")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TimeKeeping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tkId;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tkDay = new Date();
	
	@Enumerated(EnumType.ORDINAL)
	private ETKStatus tkStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff", nullable = false)
	@JsonIgnore
	private Staff staff;

	public TimeKeeping() {
		super();
	}

	public TimeKeeping(Long tkId, Date tkDay, ETKStatus tkStatus, Staff staff) {
		super();
		this.tkId = tkId;
		this.tkDay = tkDay;
		this.tkStatus = tkStatus;
		this.staff = staff;
	}

	public Long getTkId() {
		return tkId;
	}

	public void setTkId(Long tkId) {
		this.tkId = tkId;
	}

	public Date getTkDay() {
		return tkDay;
	}

	public void setTkDay(Date tkDay) {
		this.tkDay = tkDay;
	}

	public ETKStatus getTkStatus() {
		return tkStatus;
	}

	public void setTkStatus(ETKStatus tkStatus) {
		this.tkStatus = tkStatus;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "TimeKeeping [tkId=" + tkId + ", tkDay=" + tkDay + ", tkStatus=" + tkStatus + ", staff=" + staff + "]";
	}

	

	
}
