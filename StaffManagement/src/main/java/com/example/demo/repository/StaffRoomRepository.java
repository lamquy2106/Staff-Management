package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StaffRoom;

@Repository
public interface StaffRoomRepository extends JpaRepository<StaffRoom, Long>{

}
