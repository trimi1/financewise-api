package be.helmo.api.service;

import be.helmo.api.dto.DepenseDTO;
import be.helmo.api.errors.*;
import be.helmo.api.infrastructure.model.*;
import be.helmo.api.infrastructure.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Depense> getDepensesByUserIdAndByCategory(int userId, String categoryFields) {//todo: vaudrait mieux géré en cas de pépin (exception)
        List<Depense> depenses = new ArrayList<>();

        String[] categoriesArray = categoryFields.split("&");
        for (String category : categoriesArray) {
            List<Depense> depensesByCategory = depenseRepository.findByUser_IdAndCategorie_Name(userId, category);
            depenses.addAll(depensesByCategory);
        }

        return depenses;
    }

    private User getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private Devise getDeviseByName(String devise) throws CurrencyNotFoundException {
        return deviseRepository.findByDevise(devise).orElseThrow(() -> new CurrencyNotFoundException("Currency not found : " + devise));
    }

    private Category getCategoryById(int id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found : " + id));
    }

    private Objectif getGoalsById(int id) throws CategoryNotFoundException {
        return goalsRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found : " + id));
    }

    public void addDepenses(int id, List<DepenseDTO> depenses) throws DepenseNotSavedException {
        try {
            User user = getUserById(id);
            for(DepenseDTO depenseDTO : depenses) {
                Devise devise = getDeviseByName(depenseDTO.devise());
                Category category = depenseDTO.categorie() == null ? null : getCategoryById(depenseDTO.categorie().idCategory());
                Objectif objectif = depenseDTO.objectif() == null ? null : getGoalsById(depenseDTO.objectif().id());
                Depense depense = new Depense(depenseDTO.name(), depenseDTO.montant(), devise, depenseDTO.date(), category, objectif, user);
                depenseRepository.save(depense);
            }
        } catch (Exception e) {
            throw new DepenseNotSavedException("Depense not added : " + e.getMessage());
        }
    }

    private void updateDepense(List<DepenseDTO> depenses) throws DepensesNotUpdatedException {
        try {
            for(DepenseDTO depenseDTO : depenses) {
                Depense depense = depenseRepository.findById(depenseDTO.id()).orElseThrow(() -> new DepenseNotFoundException("Depense not found : " + depenseDTO.id()));
                depense.setName(depenseDTO.name());
                depense.setMontant(depenseDTO.montant());
                depense.setDate(depenseDTO.date());
                depense.setDevise(getDeviseByName(depenseDTO.devise()));
                depense.setCategorie(depenseDTO.categorie() == null ? null : getCategoryById(depenseDTO.categorie().idCategory()));
                depense.setObjectif(depenseDTO.objectif() == null ? null : getGoalsById(depenseDTO.objectif().id()));
                depenseRepository.save(depense);
            }
        } catch (Exception e) {
            throw new DepensesNotUpdatedException("Depense not updated : " + e.getMessage());
        }
    }

    @Transactional(rollbackOn = PutRequestFailedException.class)
    public void reactPutRequest(int id, List<DepenseDTO> depenses) throws PutRequestFailedException {
        try {
            List<DepenseDTO> toCreate = depenses.stream().filter(d -> d.id() <= 0).toList();
            List<DepenseDTO> toUpdate = depenses.stream().filter(d -> d.id() > 0).toList();
            addDepenses(id, toCreate);
            updateDepense(toUpdate);
        } catch (Exception e) {
            throw new PutRequestFailedException(e.getMessage());
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
