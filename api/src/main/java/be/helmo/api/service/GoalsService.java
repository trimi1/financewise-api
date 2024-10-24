package be.helmo.api.service;

import be.helmo.api.dto.GoalsDTO;
import be.helmo.api.dto.mappers.GoalsMapper;
import be.helmo.api.infrastructure.model.Devise;
import be.helmo.api.infrastructure.model.Objectif;
import be.helmo.api.infrastructure.model.User;
import be.helmo.api.infrastructure.repository.IDeviseRepository;
import be.helmo.api.infrastructure.repository.IObjectifRepository;
import be.helmo.api.infrastructure.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalsService {

    @Autowired
    private IObjectifRepository iObjectifRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    IDeviseRepository deviseRepository;

    public GoalsService() {
    }

    public List<GoalsDTO> getGoalsByUserId(int idUser) {
        List<Objectif> goals = iObjectifRepository.findObjectifsByUser_Id(idUser);
        return GoalsMapper.toDTOList(goals);
    }


    public void addGoals(int idUser, List<GoalsDTO> goals) {
        Optional<User> handleUser = userRepository.findById(idUser);
        if (handleUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = handleUser.get();
        goals.forEach(goal -> {
            Optional<Devise> handleDevise = deviseRepository.findByDevise(goal.devise());
            Devise devise = handleDevise.orElseGet(() -> deviseRepository.save(new Devise(goal.devise())));
            Objectif objectif = new Objectif(goal.name(), goal.montant(), goal.deadline(), devise, goal.recommendation(), user);
            iObjectifRepository.save(objectif);
        });
    }
}
