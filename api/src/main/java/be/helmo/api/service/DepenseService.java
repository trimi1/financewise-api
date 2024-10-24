package be.helmo.api.service;

import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private IGoalsRepository objectifRepository;

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

    public List<Depense> getDepensesByCategorie(int id, String name) {
        return depenseRepository.findByUser_IdAndCategorie_Name(id, name);
    }

    public List<Depense> getDepensesByUserId(int id) {
        return depenseRepository.findByUser_Id(id);
    }
}
