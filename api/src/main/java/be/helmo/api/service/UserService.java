package be.helmo.api.service;

import be.helmo.api.dto.AuthenticationResponseDTO;
import be.helmo.api.dto.LoginUserDto;
import be.helmo.api.dto.RegisterUserDto;
import be.helmo.api.errors.CurrencyNotFoundException;
import be.helmo.api.errors.RoleNotFoundException;
import be.helmo.api.errors.UserNotFoundException;
import be.helmo.api.errors.UserNotSavedException;
import be.helmo.api.infrastructure.model.Category;
import be.helmo.api.infrastructure.model.Devise;
import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.User;
import be.helmo.api.infrastructure.repository.ICategoryRepository;
import be.helmo.api.infrastructure.repository.IDeviseRepository;
import be.helmo.api.infrastructure.repository.IRoleRepository;
import be.helmo.api.infrastructure.repository.IUserRepository;
import be.helmo.api.tools.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private ICategoryRepository categoryRepository;

    @Autowired
    IDeviseRepository deviseRepository;

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

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private Role getDefaultRole() throws RoleNotFoundException {
        return roleRepository.findByRole("User").orElseThrow(()-> new RoleNotFoundException("Role not found"));
    }

    private Devise getDefaultDevise() throws CurrencyNotFoundException {
        return deviseRepository.findByDevise("Euro").orElseThrow(() -> new CurrencyNotFoundException("Currency not found"));
    }

    @Transactional(rollbackOn = UserNotSavedException.class)
    public AuthenticationResponseDTO register(RegisterUserDto request) throws UserNotSavedException {
        try {
            String password = passwordEncoder.encode(request.password());
            List<String> codes = userRepository.findAllCodes();
            String code = this.utils.generateCode(codes);
            Role role = getDefaultRole();
            Devise devise = getDefaultDevise();
            User userSaved = userRepository.save(new User(request.lastName(), request.firstName(), request.email(), password, code, role));
            categoryRepository.save(new Category("Economie", null, devise, userSaved));
            categoryRepository.save(new Category("Investissement", null, devise, userSaved));
            String tokenResponse = jwtService.generateToken(userSaved);
            return new AuthenticationResponseDTO(tokenResponse, 0L, userSaved.getId(),"");
        } catch (DataAccessException e) {
            throw new UserNotSavedException("Database error: " + e.getMessage());
        } catch (RoleNotFoundException | CurrencyNotFoundException e) {
            throw new UserNotSavedException("Required role or currency not found: " + e.getMessage());
        } catch (Exception e) {
            throw new UserNotSavedException("User not saved: " + e.getMessage());
        }
    }

    public AuthenticationResponseDTO authenticate(LoginUserDto request) throws UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new UserNotFoundException("User not found"));
        String tokenResponse = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(tokenResponse, 0L, user.getId(),"");
    }

    public Map<String, Object> getUserInformations(int id, String fields) throws UserNotFoundException {
        Map<String, Object> informations = new HashMap<>();
        User user = getUserById(id);
        if(fields != null) {
            String[] fieldArray = fields.split(",");
            for (String field : fieldArray) {
                switch (field.trim()) {
                    case "-id":
                        informations.put("id", user.getId());
                        break;
                    case "lastName":
                        informations.put("lastName", user.getLastName());
                        break;
                    case "firstName":
                        informations.put("firstName", user.getFirstName());
                        break;
                    case "email":
                        informations.put("email", user.getEmail());
                        break;
                    case "code":
                        informations.put("code", user.getCode());
                        break;
                    case "role":
                        informations.put("role", user.getRole().getNameRole());
                        break;
                }
            }
        }
        return informations;
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
