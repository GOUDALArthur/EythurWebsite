package com.example.repository;

import com.example.entity.RaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceCategoryRepository extends JpaRepository<RaceCategory, String> {

}
