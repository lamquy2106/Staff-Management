package com.example.demo.entity;
//
//import java.util.Set;
//
//import javax.persistence.*;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "Violations")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//public class Violation {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long violationId;
//	
//	private String violationName;
//	
//	@OneToMany(mappedBy = "violation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<TimeKeeping> timeKeepings;
//
//	public Violation() {
//		super();
//	}
//
//	public Violation(Long violationId, String violationName, Set<TimeKeeping> timeKeepings) {
//		super();
//		this.violationId = violationId;
//		this.violationName = violationName;
//		this.timeKeepings = timeKeepings;
//	}
//
//	public Long getViolationId() {
//		return violationId;
//	}
//
//	public void setViolationId(Long violationId) {
//		this.violationId = violationId;
//	}
//
//	public String getViolationName() {
//		return violationName;
//	}
//
//	public void setViolationName(String violationName) {
//		this.violationName = violationName;
//	}
//
//	public Set<TimeKeeping> getTimeKeepings() {
//		return timeKeepings;
//	}
//
//	public void setTimeKeepings(Set<TimeKeeping> timeKeepings) {
//		this.timeKeepings = timeKeepings;
//	}
//
//	@Override
//	public String toString() {
//		return "Violation [violationId=" + violationId + ", violationName=" + violationName + ", timeKeepings="
//				+ timeKeepings + "]";
//	}
//
//	
//	
//}
