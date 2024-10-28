package be.helmo.api.controller;

import be.helmo.api.dto.DepenseDTO;
import be.helmo.api.dto.mappers.DepenseMapper;
import be.helmo.api.errors.PutRequestFailedException;
import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/financewise/depenses/users/{id}")
    public ResponseEntity<List<DepenseDTO>> getUserDepenses(@PathVariable int id, @RequestParam(required = false) String fields) {
        List<Depense> depenses = depenseService.getDepensesByUserId(id);
        List<DepenseDTO> depenseDTO = DepenseMapper.toDTOList(depenses);
        return ResponseEntity.ok(depenseDTO);
    }

    @PutMapping("/financewise/depenses/users/{id}")
    public ResponseEntity<String> updateDepenses(@PathVariable int id, @RequestBody List<DepenseDTO> depenses) {
        try {
            depenseService.reactPutRequest(id, depenses);
            return ResponseEntity.status(HttpStatus.OK).body("Dépenses mises à jour avec succès.");
        } catch (PutRequestFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la mise à jour des dépenses.");
        }
    }

    @DeleteMapping("/financewise/depenses/users/{id}")
    public void deleteDepense(@PathVariable int id, @RequestBody List<DepenseDTO> depenses) {
        depenseService.deleteDepense(id, depenses);
    }
}
