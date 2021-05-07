package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.*;

@Repository
public interface UserRepository extends JpaRepository<Staff, Long> {
	Optional<Staff> findByUserName(String userName);

	Boolean existsByUserName(String userName);
	Boolean existsByStaffEmail(String staffEmail);
}
