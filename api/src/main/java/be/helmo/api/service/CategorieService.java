package be.helmo.api.service;

import be.helmo.api.infrastructure.repository.ICategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {

    @Autowired
    private ICategorieRepository categorieRepository;
}
