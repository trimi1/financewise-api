package be.helmo.api.service;

import be.helmo.api.dto.InvestmentDTO;
import be.helmo.api.infrastructure.model.Depense;
import be.helmo.api.infrastructure.model.Objectif;
import be.helmo.api.infrastructure.repository.IDepenseRepository;
import be.helmo.api.infrastructure.repository.IGoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {
    @Autowired
    private IGoalsRepository goalsRepository;

    @Autowired
    private IDepenseRepository depenseRepository;

    public InvestmentService(){
    }

    public List<InvestmentDTO> getInvestmentsByUserId(int userId, String categoryName){
        List<InvestmentDTO> investmentDTOS;
        List<Depense> depenses = depenseRepository.findByUser_Id(userId);
        depenses = depenseRepository.findByCategorie_Name(categoryName);
        //depenses =
        List<Objectif> goals = goalsRepository.findObjectifsByUser_Id(userId);
//        for(Objectif goal : goals){
//            investmentDTOS.add(new InvestmentDTO(
//                    goal.getName(),
//                    goal.,todo: avoir le total des dépenses d'une catégorie, d'un objectif donné.
//                    goal.getMontant(),
//                    goal.getDeadline(),
//                    goal.getRecommendation()));
//        }

//        return investmentDTOS;
        return null;
    }
}
