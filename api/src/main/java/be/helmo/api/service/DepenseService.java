package be.helmo.api.service;

import be.helmo.api.dto.DepenseDTO;
import be.helmo.api.infrastructure.model.*;
import be.helmo.api.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepenseService {

    @Autowired
    private IDepenseRepository depenseRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IDeviseRepository deviseRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IGoalsRepository goalsRepository;

    public DepenseService() {
    }

    public Optional<Depense> getDepenseById(int id) {
        return depenseRepository.findById(id);
    }

    private void addDepenses(int id, List<DepenseDTO> depenses) {
        Optional<User> handleUser = userRepository.findById(id);
        if(handleUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = handleUser.get();
        for(DepenseDTO depenseDTO : depenses) {
            Optional<Devise> handleDevise = deviseRepository.findByDevise(depenseDTO.devise());
            Optional<Category> handleCategory = categoryRepository.findById(depenseDTO.categorie().idCategory());
            Optional<Objectif> handleObjectif = goalsRepository.findById(depenseDTO.objectif().id());
            Depense depense = new Depense(depenseDTO.name(), depenseDTO.montant(), handleDevise.get(), depenseDTO.date(), handleCategory.get(), handleObjectif.get(), user);
            depenseRepository.save(depense);
        }
    }

    public void updateDepense(Integer id, List<DepenseDTO> depense) {
        List<DepenseDTO> toCreate = depense.stream().filter(d -> d.id() <= 0).toList();
        List<DepenseDTO> toUpdate = depense.stream().filter(d -> d.id() > 0).toList();

        addDepenses(id, toCreate);

        for(DepenseDTO depenseDTO : toUpdate) {
            Optional<Depense> handleDepense = depenseRepository.findById(depenseDTO.id());
            if(handleDepense.isPresent()) {
                Depense dataDepense = handleDepense.get();
                if(dataDepense.getUser().getId() == id) {
                    dataDepense.setDate(depenseDTO.date());
                    dataDepense.setName(depenseDTO.name());
                    dataDepense.setDevise(deviseRepository.findByDevise(depenseDTO.devise()).get());
                    dataDepense.setCategorie(categoryRepository.findById(depenseDTO.categorie().idCategory()).get());
                    dataDepense.setObjectif(goalsRepository.findById(depenseDTO.objectif().id()).get());
                    depenseRepository.save(dataDepense);
                }
            }
        }
    }


    public List<Depense> getDepensesByUserId(int id) {
        return depenseRepository.findByUser_Id(id);
    }

    public void deleteDepense(int id, List<DepenseDTO> depenses) {
        Optional<User> handleUser = userRepository.findById(id);
        if(handleUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = handleUser.get();
        for(DepenseDTO depenseDTO : depenses) {
            Optional<Depense> handleDepense = depenseRepository.findById(depenseDTO.id());
            if(handleDepense.isPresent()) {
                Depense depense = handleDepense.get();
                if(depense.getUser().getId() == user.getId()) {
                    depenseRepository.delete(depense);
                }
            }
        }
    }
}
