package com.example.demo.Repository;

import com.example.demo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LocalisationRepository extends JpaRepository<Location, Long> {
}