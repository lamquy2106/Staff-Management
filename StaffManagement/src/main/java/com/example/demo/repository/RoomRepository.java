package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	@Query("SELECT e FROM Room e WHERE e.name like ?1")
	  List<Room> findByNameLike(String name);
}
