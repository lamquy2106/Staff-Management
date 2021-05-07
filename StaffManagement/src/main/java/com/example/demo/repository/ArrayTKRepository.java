package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TKArray;

public interface ArrayTKRepository extends JpaRepository<TKArray, Long>{
	Page<TKArray> findAll(Pageable pageable);
	@Query("SELECT e FROM TKArray e WHERE month(e.dateInfo) = ?1 and year(e.dateInfo) = ?2")
	  List<TKArray> findByMonthAndYear(int month, int year);
}
