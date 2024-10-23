package be.helmo.api.service;

import be.helmo.api.infrastructure.model.Categorie;
import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.infrastructure.repository.*;
import be.helmo.api.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DepenseService {

    @Autowired
    private IDepenseRepository depenseRepository;
    @Autowired
    private IDeviseRepository deviseRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository utilisateurRepository;
    @Autowired
    private ICategorieRepository categorieRepository;
    @Autowired
    private IObjectifRepository objectifRepository;

    public DepenseService() {
    }

    public Optional<Depense> getDepenseById(int id) {
        return depenseRepository.findById(id);
    }

    public void addDepense(Depense depense) {
        depenseRepository.save(depense);
    }

    public void updateDepense(Integer id, Depense depense) {
        Optional<Depense> depenseOptional = depenseRepository.findById(id);
        if (depenseOptional.isPresent()) {
            depenseRepository.save(depense);
        } else {

        }
    }

    public List<Depense> getDepenses() {
        return depenseRepository.findAll();
    }

    public void deleteDepense(int id) {
        depenseRepository.deleteById(id);
    }

    public List<Depense> getDepensesByCategorie(String email, String name) {
        return depenseRepository.findByUser_EmailAndCategorie_Name(email, name);
    }

    public List<Depense> getDepensesByUser(String email) {
        return depenseRepository.findByUser_Email(email);
    }
}
