package be.helmo.api.infrastructure.repository;

import be.helmo.api.app.ApiApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApiApplication.class)
public class ICategorieRepositoryTests {
    @Autowired
    private ICategorieRepository repository;

    @Test
    public void should_save_categorie() {

    }
}
