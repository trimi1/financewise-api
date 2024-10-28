package be.helmo.api.controller;

import be.helmo.api.errors.UserNotFoundException;
import be.helmo.api.infrastructure.model.User;
import be.helmo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/financewise/users/{id}")
    public ResponseEntity<Map<String, Object>> getUserInformation(@PathVariable int id, @RequestParam(required = false) String fields) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInformations(id, fields));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, Object>() {{
                put("message", e.getMessage());
            }});
        }
    }

    @GetMapping("/financewise/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/financewise/users/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}
