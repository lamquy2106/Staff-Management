package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SalaryInMonth;

@Repository
public interface SalaryInMonthRepository  extends JpaRepository<SalaryInMonth , Long>{
	@Query("SELECT e FROM SalaryInMonth e WHERE month(e.dateInfo) = ?1 and year(e.dateInfo) = ?2")
	  List<SalaryInMonth> findSalaryByMonthAndYear(int month, int year);
}
