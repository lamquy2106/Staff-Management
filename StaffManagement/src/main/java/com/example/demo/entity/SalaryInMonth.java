package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SalaryInMonths")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SalaryInMonth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long salaryId;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateInfo;
	
	private Double basicSalary;
	
	private Double rewardMoney;
	
	private Double prepayMoney;
	
	private Double subsidize;
	
	private Double totalSalary;

	public SalaryInMonth() {
		super();
	}

	public SalaryInMonth(Long salaryId, String firstName, String lastName, Date dateInfo, Double basicSalary,
			Double rewardMoney, Double prepayMoney, Double subsidize, Double totalSalary) {
		super();
		this.salaryId = salaryId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateInfo = dateInfo;
		this.basicSalary = basicSalary;
		this.rewardMoney = rewardMoney;
		this.prepayMoney = prepayMoney;
		this.subsidize = subsidize;
		this.totalSalary = totalSalary;
	}

	public Long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	public Double getPrepayMoney() {
		return prepayMoney;
	}

	public void setPrepayMoney(Double prepayMoney) {
		this.prepayMoney = prepayMoney;
	}

	public Double getSubsidize() {
		return subsidize;
	}

	public void setSubsidize(Double subsidize) {
		this.subsidize = subsidize;
	}

	public Double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}

	@Override
	public String toString() {
		return "SalaryInMonth [salaryId=" + salaryId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateInfo=" + dateInfo + ", basicSalary=" + basicSalary + ", rewardMoney=" + rewardMoney
				+ ", prepayMoney=" + prepayMoney + ", subsidize=" + subsidize + ", totalSalary=" + totalSalary + "]";
	}

	
}
