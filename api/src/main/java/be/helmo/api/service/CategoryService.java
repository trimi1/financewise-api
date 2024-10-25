package be.helmo.api.service;

import be.helmo.api.dto.CategoryDTO;
import be.helmo.api.infrastructure.model.Category;
import be.helmo.api.infrastructure.model.Devise;
import be.helmo.api.infrastructure.model.User;
import be.helmo.api.infrastructure.repository.ICategoryRepository;
import be.helmo.api.infrastructure.repository.IDeviseRepository;
import be.helmo.api.infrastructure.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDeviseRepository deviseRepository;

    public CategoryService() {
    }

    public List<Category> getAllUsersCategories(int idUser) {
        return categoryRepository.findByUser_Id(idUser);
    }

    public void addCategories(int idUser, List<CategoryDTO> categories){
        User user = getUser(idUser);
        categories.forEach(category -> {
            if (deviseRepository.findByDevise(category.devise()).isEmpty()){//TODO: si devise existante dans la db, alors l'ajouter à la catégorie, sinon renvoyer une erreur (faire pareil pour les autres classes)
                throw new RuntimeException();//TODO: exception personnalisé? car la c'est pas adapté. --> Créer un package pour y mettre les exceptions si oui.
            } else {
                Devise devise = deviseRepository.findByDevise(category.devise()).get();
                Category categorie = new Category(category.name(), category.montantMax(), devise, user);
                categoryRepository.save(categorie);
            }
        });
    }

    public void updateCategories(int idUser, List<CategoryDTO> categories) {
        List<CategoryDTO> toCreate = categories.stream().filter(category -> category.idCategory() < 0).toList();
        List<CategoryDTO> toUpdate = categories.stream().filter(category -> category.idCategory() > 0).toList();
        addCategories(idUser, toCreate);
        toUpdate.forEach(category -> {
            Optional<Category> handleCategory = categoryRepository.findById(category.idCategory());
            if (handleCategory.isPresent()) {
                Category categorie = handleCategory.get();
                if (categorie.getUser().getId() == idUser) {
                    categorie.setName(category.name());
                    categorie.setMontantMax(category.montantMax());
                    categorie.setDevise(deviseRepository.findByDevise(category.devise()).orElseGet(() -> deviseRepository.save(new Devise(category.devise()))));
                    categoryRepository.save(categorie);
                }
            }
        });
    }

    public void deleteCategories(int idUser, List<CategoryDTO> categories) {
        User user = getUser(idUser);
        categories.forEach(category -> {
            Optional<Category> handleCategory = categoryRepository.findById(category.idCategory());
            if (handleCategory.isPresent()) {
                Category categorie = handleCategory.get();
                if (categorie.getUser().getId() == user.getId()) {
                    categoryRepository.delete(categorie);
                }
            }
        });
    }

    /**
     * Get the user by its idCategory
     * @param idUser the idCategory of the user
     * @return the user
     */
    private User getUser(int idUser) {
        Optional<User> handleUser = userRepository.findById(idUser);
        if (handleUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return handleUser.get();
    }
}
