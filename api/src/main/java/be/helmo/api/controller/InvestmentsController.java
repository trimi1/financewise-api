package be.helmo.api.controller;

import be.helmo.api.dto.InvestmentDTO;
import be.helmo.api.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvestmentsController {
    private InvestmentService investmentService;

    @Autowired
    public InvestmentsController(InvestmentService investmentService){
        this.investmentService = investmentService;
    }

    @GetMapping("/financewise/users/{iduser}/categories/{categoryName}")
    public ResponseEntity<List<InvestmentDTO>> getInvestmentsByUser(@PathVariable int idUser, @PathVariable String categoryName){
        return ResponseEntity.ok(investmentService.getInvestmentsByUserId(idUser, categoryName));
    }
}
