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

    /**
     * Get goals by user id
     * @param idUser user id
     * @return the list of goals from a user
     */
    @GetMapping("/users/{idUser}")
    public ResponseEntity<List<GoalsDTO>> getGoalsByUser(@PathVariable int idUser) {
        return ResponseEntity.ok(goalService.getGoalsByUserId(idUser));
    }

    /**
     * Add goals to a user
     * @param idUser user id
     * @param goals list of goals
     */
    @PostMapping("/users/{idUser}")
    public void addGoals(@PathVariable int idUser, @RequestBody List<GoalsDTO> goals) {
        goalService.addGoals(idUser, goals);
    }



}
