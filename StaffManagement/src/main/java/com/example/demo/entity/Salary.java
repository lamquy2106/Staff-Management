package com.example.demo.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Salarys")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long salaryId;
	
	private Double basicSalary;
	
	private Double rewardMoney;
	
	private Double prepayMoney;
	
	private Double subsidize;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "staff", nullable = false)
	@JsonIgnore
	private Staff staff;

	public Salary() {
		super();
	}

	public Salary(Long salaryId, Double basicSalary, Double rewardMoney, Double prepayMoney, Double subsidize,
			Staff staff) {
		super();
		this.salaryId = salaryId;
		this.basicSalary = basicSalary;
		this.rewardMoney = rewardMoney;
		this.prepayMoney = prepayMoney;
		this.subsidize = subsidize;
		this.staff = staff;
	}

	public Long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
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

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", basicSalary=" + basicSalary + ", rewardMoney=" + rewardMoney
				+ ", prepayMoney=" + prepayMoney + ", subsidize=" + subsidize + ", staff=" + staff + "]";
	}

	
}
