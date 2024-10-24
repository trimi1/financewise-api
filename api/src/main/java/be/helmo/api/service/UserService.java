package be.helmo.api.service;

import be.helmo.api.dto.AuthenticationResponseDTO;
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
    private IUserRepository userRepository;

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
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private Role checkRole(String role) {
        Optional<Role> roleOptional = roleRepository.findByRole(role);
        if(roleOptional.isEmpty()) {
            roleRepository.save(new Role(role));
        }
        return roleRepository.findByRole(role).get();
    }

    private int getUserIdByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(User::getId).orElse(-1);
    }

    public AuthenticationResponseDTO register(RegisterUserDto request) {
        String password = passwordEncoder.encode(request.password());
        List<String> codes = userRepository.findAllCodes();
        String code = this.utils.generateCode(codes);
        Role role = checkRole("User");
        User user = new User(request.lastName(), request.firstName(), request.email(), password, code, role);
        userRepository.save(user);
        int idUser = getUserIdByEmail(request.email());
        String tokenResponse = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(tokenResponse, 0L, idUser);
    }

    public AuthenticationResponseDTO authenticate(LoginUserDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String tokenResponse = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(tokenResponse, 0L, user.getId());
    }

    public void updateUser(Integer id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.save(user);
        } else {
            List<String> codes = userRepository.findAllCodes();
            Optional<Role> role = roleRepository.findByRole("User");
            if(role.isEmpty()) {
                roleRepository.save(new Role("User"));
            }

            role = roleRepository.findByRole("User");
            role.ifPresent(value -> userRepository.save(new User(user.getLastName(), user.getFirstName(), user.getEmail(), user.getPassword(), this.utils.generateCode(codes), value)));
        }
    }
}
