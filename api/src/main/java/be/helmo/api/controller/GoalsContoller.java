package be.helmo.api.controller;

import be.helmo.api.dto.GoalsDTO;
import be.helmo.api.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalsContoller {
    
    private GoalsService goalService;
    
    @Autowired
    public GoalsContoller(GoalsService goalService) {
        this.goalService = goalService;
    }

    /**
     * Get goals by user id
     * @param idUser user id
     * @return the list of goals from a user
     */
    @GetMapping("/financewise/goals/users/{idUser}")
    public ResponseEntity<List<GoalsDTO>> getGoalsByUser(@PathVariable int idUser) {
        return ResponseEntity.ok(goalService.getGoalsByUserId(idUser));
    }

    /**
     * Add goals to a user
     * @param idUser user id
     * @param goals list of goals
     */
    @PostMapping("/financewise/goals/users/{idUser}")
    public void addGoals(@PathVariable int idUser, @RequestBody List<GoalsDTO> goals) {
        goalService.addGoals(idUser, goals);
    }

    /**
     * Update goals from a user
     * @param idUser user id
     * @param goals list of goals
     */
    @PutMapping("/financewise/goals/users/{idUser}")
    public void updateGoals(@PathVariable int idUser, @RequestBody List<GoalsDTO> goals) {
        goalService.updateGoals(idUser, goals);
    }

    /**
     * Delete goals from a user
     * @param idUser user id
     * @param goals list of goals
     */
    @DeleteMapping("/financewise/goals/users/{idUser}")
    public void deleteGoals(@PathVariable int idUser, @RequestBody List<GoalsDTO> goals) {
        goalService.deleteGoals(idUser, goals);
    }
}
