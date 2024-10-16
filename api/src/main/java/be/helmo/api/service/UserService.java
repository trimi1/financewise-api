package be.helmo.api.service;

import be.helmo.api.infrastructure.model.Utilisateur;
import be.helmo.api.infrastructure.repository.IUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUtilisateurRepository repository;

    public Optional<Utilisateur> getUser(Integer id) {
        return repository.findById(id);
    }
}
