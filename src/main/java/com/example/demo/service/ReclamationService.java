package com.example.demo.service;


import com.example.demo.entity.Location;
import com.example.demo.entity.Reclamation;
import com.example.demo.repo.ReclamationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService {
    private final ReclamationRepo reclamationRepository;

    @Autowired
    public ReclamationService(ReclamationRepo reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }

    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }

//    public Reclamation saveReclamation(Reclamation reclamation) {
//        return reclamationRepository.save(reclamation);
//    }

    public Reclamation saveReclamation(Reclamation reclamation) {
        if (reclamation.getDescription() == null || reclamation.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        return reclamationRepository.save(reclamation);
    }

    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }


}

