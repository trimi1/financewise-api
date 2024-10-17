package be.helmo.api.service;

import be.helmo.api.infrastructure.model.Role;
import be.helmo.api.infrastructure.model.Utilisateur;
import be.helmo.api.infrastructure.repository.IRoleRepository;
import be.helmo.api.infrastructure.repository.IUtilisateurRepository;
import be.helmo.api.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private IUtilisateurRepository repository;

    @Autowired
    private IRoleRepository roleRepository;

    private final Utils utils;

    public UserService() {
        this.utils = new Utils(new Random());
    }

    public Optional<Utilisateur> getUser(Integer id) {
        return repository.findById(id);
    }

    public List<Utilisateur> getUsers() {
        return repository.findAll();
    }

    public void addUser(Utilisateur user) {
        //TODO : Rajouter le cheking ou voir pour supprimer (Méthode idempotente ?)
        repository.save(user);
    }

    public void updateUser(Integer id, Utilisateur user) {
        Optional<Utilisateur> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            repository.save(user);
        } else {
            List<String> codes = repository.findAllCodes();
            Optional<Role> role = roleRepository.findByRole("User");
            if(role.isEmpty()) {
                roleRepository.save(new Role("User"));
            }

            role = roleRepository.findByRole("User");
            role.ifPresent(value -> repository.save(new Utilisateur(user.getNom(), user.getPrenom(), user.getEmail(), user.getMotDePasse(), this.utils.generateCode(codes), value)));
        }
    }
}
