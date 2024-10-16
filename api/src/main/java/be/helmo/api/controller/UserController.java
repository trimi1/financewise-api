package be.helmo.api.controller;

import be.helmo.api.infrastructure.model.Utilisateur;
import be.helmo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Utilisateur getUser(@RequestParam Integer id) {
        System.out.println("id = " + id);
        Optional<Utilisateur> user = userService.getUser(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }
}
