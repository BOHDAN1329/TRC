package com.example.trc.controller;

import com.example.trc.entity.Fuel;
import com.example.trc.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuels")
public class FuelController {

    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping
    public List<Fuel> getAllFuels() {
        return fuelService.getAllFuels();
    }

    @PostMapping
    public Fuel createFuel(@RequestBody Fuel fuel) {
        return fuelService.saveFuel(fuel);
    }

    @GetMapping("/search")
    public List<Fuel> searchFuels(@RequestParam(value = "type") String type) {
        return fuelService.getFuelsByType(type);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fuel> updateFuelPrice(@PathVariable long id, @RequestBody Fuel fuel) {
        try {
            return ResponseEntity.ok(fuelService.updateFuel(id, fuel));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuel(@PathVariable long id) {
        fuelService.deleteFuel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
