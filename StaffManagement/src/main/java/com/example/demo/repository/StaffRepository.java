package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Staff;
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
	@Query("SELECT e FROM Staff e WHERE e.userName = ?1 and e.password = ?2")
	  List<Staff> findByLogin(String userName, String password);
}
