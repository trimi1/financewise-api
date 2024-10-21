package be.helmo.api.controller;

import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Depense getDepenseById(@PathVariable int id) {
        Optional<Depense> depense = depenseService.getDepenseById(id);
        return depense.get();
    }

    @GetMapping("/depenses")
    public List<Depense> getAllDepenses() {
        return depenseService.getDepenses();
    }
}
