package be.helmo.api.controller;

import be.helmo.api.infrastructure.model.Utilisateur;
import be.helmo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public Utilisateur getUserById(@PathVariable Integer id) {
        System.out.println("id = " + id);
        Optional<Utilisateur> user = userService.getUser(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    @GetMapping("/users")
    public List<Utilisateur> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody Utilisateur user) {
        userService.addUser(user);
    }

    @PostMapping("/users/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody Utilisateur user) {
        userService.updateUser(id, user);
    }
}
