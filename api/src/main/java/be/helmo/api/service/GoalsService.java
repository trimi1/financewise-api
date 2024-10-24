package be.helmo.api.service;

import be.helmo.api.dto.GoalsDTO;
import be.helmo.api.dto.mappers.GoalsMapper;
import be.helmo.api.infrastructure.model.Devise;
import be.helmo.api.infrastructure.model.Objectif;
import be.helmo.api.infrastructure.model.User;
import be.helmo.api.infrastructure.repository.IDeviseRepository;
import be.helmo.api.infrastructure.repository.IGoalsRepository;
import be.helmo.api.infrastructure.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalsService {

    @Autowired
    private IGoalsRepository iGoalsRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    IDeviseRepository deviseRepository;

    public GoalsService() {
    }

    public List<GoalsDTO> getGoalsByUserId(int idUser) {
        List<Objectif> goals = iGoalsRepository.findObjectifsByUser_Id(idUser);
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
            iGoalsRepository.save(objectif);
        });
    }

    public void updateGoals(int idUser, List<GoalsDTO> goals) {
        List<GoalsDTO> toCreate = goals.stream().filter(goal -> goal.id() <= 0).toList();
        List<GoalsDTO> toUpdate = goals.stream().filter(goal -> goal.id() > 0).toList();
        addGoals(idUser, toCreate);


    }

    public void deleteGoals(int idUser, List<GoalsDTO> goals) {
    }
}
