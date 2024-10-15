package be.helmo.infrastructures.repository;

import be.helmo.infrastructures.config.ConfigTest;
import be.helmo.infrastructures.model.Categorie;
import be.helmo.infrastructures.model.Devise;
import be.helmo.infrastructures.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ConfigTest.class)
public class ICategorieRepositoryTests {
    @Autowired
    private ICategorieRepository repository;

    @Test
    public void should_save_categorie() {

    }
}
