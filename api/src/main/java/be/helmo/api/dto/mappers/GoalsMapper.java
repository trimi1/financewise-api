package be.helmo.api.dto.mappers;

import be.helmo.api.dto.GoalsDTO;
import be.helmo.api.infrastructure.model.Objectif;

import java.util.List;
import java.util.stream.Collectors;

public class GoalsMapper {

    public static GoalsDTO toDTO(Objectif goals) {
        return new GoalsDTO(
            goals.getId(),
            goals.getName(),
            goals.getMontant(),
            goals.getDevise().getDevise(),
            goals.getDeadline(),
            goals.getRecommendation()
        );
    }

    public static List<GoalsDTO> toDTOList(List<Objectif> goals) {
        return goals.stream()
                .map(GoalsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
