package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.*;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	Page<Project> findAll(Pageable pageable);
}
