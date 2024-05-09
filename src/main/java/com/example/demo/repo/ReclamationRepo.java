package com.example.demo.repo;

import com.example.demo.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation,Long > {
}
