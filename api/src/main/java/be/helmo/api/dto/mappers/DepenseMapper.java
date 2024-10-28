package be.helmo.api.dto.mappers;

import be.helmo.api.dto.CategoryDTO;
import be.helmo.api.dto.DepenseDTO;
import be.helmo.api.dto.GoalsDTO;
import be.helmo.api.infrastructure.model.Depense;

import java.util.List;
import java.util.stream.Collectors;

public class DepenseMapper {
    public static DepenseDTO toDTO(Depense depense) {
        CategoryDTO categorieDTO = depense.getCategorie() == null ? null : new CategoryDTO(depense.getCategorie().getId(), depense.getCategorie().getName(), depense.getCategorie().getMontantMax(), depense.getCategorie().getDevise().getDevise());
        GoalsDTO goalsDTO = depense.getObjectif() == null ? null : new GoalsDTO(depense.getObjectif().getId(), depense.getObjectif().getName(), depense.getObjectif().getMontant(), depense.getDevise().getDevise(), depense.getObjectif().getDeadline(), depense.getObjectif().getRecommendation());
        return new DepenseDTO(depense.getId(), depense.getName(), depense.getMontant(), depense.getDevise().getDevise(), depense.getDate(), categorieDTO, goalsDTO);
    }

    public static List<DepenseDTO> toDTOList(List<Depense> depenses) {
        return depenses.stream()
                .map(DepenseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
