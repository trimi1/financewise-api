package be.helmo.api.dto.mappers;

import be.helmo.api.dto.DeviseDTO;
import be.helmo.api.infrastructure.model.Devise;

import java.util.List;
import java.util.stream.Collectors;

public class DeviseMapper {
    public static DeviseDTO toDTO(Devise devise) {
        return new DeviseDTO(devise.getIdDevise(), devise.getDevise());
    }

    public static List<DeviseDTO> toDTOList(List<Devise> devises) {
        return devises.stream().map(DeviseMapper::toDTO).collect(Collectors.toList());
    }
}
