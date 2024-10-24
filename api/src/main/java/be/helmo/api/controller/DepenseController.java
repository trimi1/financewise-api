package be.helmo.api.controller;

import be.helmo.api.dto.DepenseDTO;
import be.helmo.api.dto.mappers.DepenseMapper;
import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DepenseController {

    private DepenseService depenseService;

    @Autowired
    public DepenseController(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    @GetMapping("/financewise/depenses/{id}")
    public ResponseEntity<Depense> getDepenseById(@PathVariable int id) {
        Optional<Depense> depense = depenseService.getDepenseById(id);
        return ResponseEntity.ok(depense.get());
    }

    @GetMapping("/financewise/depenses/users/{email}")
    public ResponseEntity<List<DepenseDTO>> getUserDepenses(@PathVariable String email, @RequestParam(required = false) String fields) {
        List<Depense> depenses = depenseService.getDepensesByUser(email);
        List<DepenseDTO> depenseDTO = DepenseMapper.toDTOList(depenses);
        return ResponseEntity.ok(depenseDTO);
    }

    @GetMapping("/financewise/depenses/users/{email}/categorie/{cat_name}")
    public ResponseEntity<List<DepenseDTO>> getUserDepensesByCategory(@PathVariable String email, @PathVariable String cat_name, @RequestParam(required = false) String fields) {
        List<Depense> depenses = depenseService.getDepensesByCategorie(email, cat_name);
        List<DepenseDTO> depenseDTO = DepenseMapper.toDTOList(depenses);
        return ResponseEntity.ok(depenseDTO);
    }
}
