package com.example.demo.controller;
import com.example.demo.entity.Reclamation;
import com.example.demo.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.Location;
import java.util.List;


@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {
    private final ReclamationService reclamationService;

    @Autowired
    public ReclamationController(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    @GetMapping("/gelAll")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable Long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id)
                .orElseThrow(() -> new RuntimeException("Reclamation not found with id " + id));
        return ResponseEntity.ok(reclamation);
    }

    @PostMapping("/createReclamation")
    public Reclamation createReclamation(@RequestBody Reclamation reclamation) {

        Reclamation savedReclamation = reclamationService.saveReclamation(reclamation);
        return ResponseEntity.ok(savedReclamation).getBody();
    }

    @DeleteMapping("/deleteReclamation/{id}")
    public ResponseEntity<?> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.ok().build();
    }


}
