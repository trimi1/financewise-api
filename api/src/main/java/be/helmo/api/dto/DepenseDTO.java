package be.helmo.api.dto;

import java.time.LocalDateTime;

public record DepenseDTO(int id, String name, double montant, String devise, LocalDateTime date, CategoryDTO categorie, GoalsDTO objectif, Integer idUser) {

}
