package be.helmo.api.controller;

import be.helmo.api.dto.GoalsDTO;
import be.helmo.api.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/financewise/goals")
public class GoalsContoller {
    
    private final GoalsService goalService;
    
    @Autowired
    public GoalsContoller(GoalsService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<List<GoalsDTO>> getGoalsByUser(@PathVariable int idUser) {
        return ResponseEntity.ok(goalService.getGoalsByUserId(idUser));
    }

    @PostMapping("/users/{idUser}")
    public void addGoals(@PathVariable int idUser, @RequestBody List<GoalsDTO> goals) {
        goalService.addGoals(idUser, goals);
    }



}
