package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TimeKeeping;
@Repository
public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Long>{
	@Query("SELECT e FROM TimeKeeping e WHERE month(e.tkDay) = ?1 and year(e.tkDay) = ?2 order by e.staff")
	  List<TimeKeeping> findByLike(int month, int year);
}
