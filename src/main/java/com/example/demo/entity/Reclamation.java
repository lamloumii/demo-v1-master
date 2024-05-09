package com.example.demo.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reclamation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReclamation;

    @Column( name = "description", nullable = false)
    private String Description;

    @ManyToMany
    @JoinTable(
            name = "reclamation_file",
            joinColumns = @JoinColumn(name = "reclamation_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private List<File> files;

//
//    @OneToMany(mappedBy = "reclamation")
//    private List<File> files = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;


    public static List<Location> getLocations(List<Reclamation> reclamations) {
        List<Location> locations = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if (reclamation.getLocation() != null) {
                locations.add(reclamation.getLocation());
            }
        }
        return locations;
    }

}
