package be.helmo.api.dto.mappers;

import be.helmo.api.dto.CategorieDTO;
import be.helmo.api.dto.DepenseDTO;
import be.helmo.api.dto.ObjectifDTO;
import be.helmo.api.infrastructure.model.Depense;

import java.util.List;
import java.util.stream.Collectors;

public class DepenseMapper {
    public static DepenseDTO toDTO(Depense depense) {
        CategorieDTO categorieDTO = new CategorieDTO(depense.getCategorie().getName(), depense.getCategorie().getMontantMax(), depense.getCategorie().getDevise().getDevise());
        ObjectifDTO objectifDTO = new ObjectifDTO(depense.getObjectif().getName());
        return new DepenseDTO(depense.getName(), depense.getMontant(), depense.getDate().toLocalDate(), categorieDTO, objectifDTO);
    }

    public static List<DepenseDTO> toDTOList(List<Depense> depenses) {
        return depenses.stream()
                .map(DepenseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
