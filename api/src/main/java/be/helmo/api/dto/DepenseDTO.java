package be.helmo.api.dto;

import java.time.LocalDate;

public record DepenseDTO(String name, double montant, LocalDate date, CategorieDTO categorie, ObjectifDTO objectif) {

}
