package com.christoph.elasticsearch.controller;

import com.christoph.elasticsearch.document.Vehicle;
import com.christoph.elasticsearch.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public void save(@RequestBody final Vehicle vehicle) {
        vehicleService.index(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable final String id) {
        return vehicleService.getById(id);
    }
}

