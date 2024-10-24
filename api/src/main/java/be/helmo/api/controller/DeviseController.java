package be.helmo.api.controller;

import be.helmo.api.dto.DeviseDTO;
import be.helmo.api.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviseController {

    private DeviseService deviseService;

    @Autowired
    public DeviseController(DeviseService deviseService) {
        this.deviseService = deviseService;
    }

    @GetMapping("/financewise/devises")
    public ResponseEntity<List<DeviseDTO>> getAllDevise() {
        return ResponseEntity.ok(deviseService.getDevises());
    }
}
