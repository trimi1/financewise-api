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
    private ICategoryRepository categorieRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDeviseRepository deviseRepository;

    public CategoryService() {
    }

    public Optional<Category> getCategorieById(int id) {
        return categorieRepository.findById(id);
    }

    public List<Category> getAllUsersCategories(int id) {
        return categorieRepository.findByUser_Id(id);
    }

    public void addCategories(int idUser, List<CategoryDTO> categories){

        User user = GetUser(idUser);
        categories.forEach(category -> {
            Optional<Devise> handleDevise = deviseRepository.findByDevise(category.devise());
            //TODO: si devise existante dans la db, alors l'ajouter à la catégorie, sinon renvoyer une erreur
            //Category categorie = new Category(category.name(), category.montantMax(), devise, user);
            //categorieRepository.save(categorie);
        });
    }

    public void updateCategories(int idUser, List<CategoryDTO> categories) {
        GetUser(idUser);
        //TODO: update categories
    }

    public void deleteCategories(int idUser, List<CategoryDTO> categories) {
        GetUser(idUser);
        //TODO: delete categories
    }

    /**
     * Get the user by its id
     * @param idUser the id of the user
     * @return the user
     */
    private User GetUser(int idUser) {
        Optional<User> handleUser = userRepository.findById(idUser);
        if (handleUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return handleUser.get();
    }
}
