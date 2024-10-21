package be.helmo.api.controller;

import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DepenseController {

    private DepenseService depenseService;

    @Autowired
    public DepenseController(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    @GetMapping("/depenses/{id}")
    public ResponseEntity<Depense> getDepenseById(@PathVariable int id) {
        Optional<Depense> depense = depenseService.getDepenseById(id);
        return ResponseEntity.ok(depense.get());
    }

    @GetMapping("/depenses")
    public ResponseEntity<List<Depense>> getAllDepenses() {
        return ResponseEntity.ok(depenseService.getDepenses());
    }

    @GetMapping("/users/{id}/depenses")
    public ResponseEntity<List<Depense>> getUserDepenses(@PathVariable int id) {
        return ResponseEntity.ok(depenseService.getDepensesByUser(id));
    }

    @GetMapping("/users/{id}/depenses/categorie/{cat_name}")
    public ResponseEntity<List<Depense>> getUserDepensesByCategory(@PathVariable int id, @PathVariable String cat_name) {
        return ResponseEntity.ok(depenseService.getDepensesByCategorie(id, cat_name));
    }

}
