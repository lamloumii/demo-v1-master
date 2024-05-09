package com.example.demo.Controller;

import com.example.demo.service.LocalisationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Location;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@AllArgsConstructor
public class LocalisationController {

    private LocalisationService locationService;

    @GetMapping("/gelAll")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/get/{id}")
    public Optional<Location> getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @DeleteMapping("/DeleteLocation/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location locationDetails) {
        Location updatedLocation = locationService.updateLocation(id, locationDetails);
        return ResponseEntity.ok(updatedLocation);
    }
}