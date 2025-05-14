package com.example.repository;

import com.example.entity.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist, Long> {
}
