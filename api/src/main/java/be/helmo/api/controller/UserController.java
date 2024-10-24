package be.helmo.api.controller;

import be.helmo.api.infrastructure.model.User;
import be.helmo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable int id, @RequestParam(required = false) String fields) {
        Optional<User> handleUser = userService.getUser(id);
        if(handleUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        User user = handleUser.get();
        if(fields != null) {
            String[] fieldArray = fields.split(",");
            for (String field : fieldArray) {
                switch (field.trim()) {
                    case "lastName":
                        response.put("lastName", user.getLastName());
                        break;
                    case "firstName":
                        response.put("firstName", user.getFirstName());
                        break;
                    case "email":
                        response.put("email", user.getEmail());
                        break;
                    case "code":
                        response.put("code", user.getCode());
                        break;
                    case "role":
                        response.put("role", user.getRole());
                        break;
                }
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(Map.of("user", user));
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
