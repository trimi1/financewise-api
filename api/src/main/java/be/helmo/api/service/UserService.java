package be.helmo.api.service;

import be.helmo.api.dto.LoginUserDto;
import be.helmo.api.dto.RegisterUserDto;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.User;
import be.helmo.api.infrastructure.repository.IRoleRepository;
import be.helmo.api.infrastructure.repository.IUserRepository;
import be.helmo.api.security.AuthenticationResponse;
import be.helmo.api.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final Utils utils;

    public UserService() {
        this.utils = new Utils(new Random());
    }

    public Optional<User> getUser(Integer id) {
        return repository.findById(id);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public void addUser(User user) {
        //TODO : Rajouter le cheking ou voir pour supprimer (Méthode idempotente ?)
        checkRole(user.getRole().getRole());
    }

    public void updateUser(Integer id, User user) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            repository.save(user);
        } else {
            List<String> codes = repository.findAllCodes();
            Optional<Role> role = roleRepository.findByRole("User");
            if(role.isEmpty()) {
                roleRepository.save(new Role("User"));
            }

            role = roleRepository.findByRole("User");
            role.ifPresent(value -> repository.save(new User(user.getLastName(), user.getFirstName(), user.getEmail(), user.getPassword(), this.utils.generateCode(codes), value)));
        }
    }



    public AuthenticationResponse register(RegisterUserDto request) {
        String password = passwordEncoder.encode(request.password());
        List<String> codes = repository.findAllCodes();
        String code = this.utils.generateCode(codes);
        Role role = checkRole("User");
        User user = new User(request.lastName(), request.firstName(), request.email(), password, code, role);
        repository.save(user);
        String tokenResponse = jwtService.generateToken(user);
        return new AuthenticationResponse(tokenResponse);
    }

    public AuthenticationResponse authenticate(LoginUserDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        UserDetails user = repository.findByEmail(request.email()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String tokenResponse = jwtService.generateToken(user);
        return new AuthenticationResponse(tokenResponse);
    }

    private Role checkRole(String role) {
        Optional<Role> roleOptional = roleRepository.findByRole(role);
        if(roleOptional.isEmpty()) {
            roleRepository.save(new Role(role));
        }
        return roleRepository.findByRole(role).get();
    }
}
