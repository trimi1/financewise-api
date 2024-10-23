package be.helmo.api.dto;

import java.time.LocalDate;

public record DepenseDTO(int id, String name, double montant, String devise, LocalDate date, CategorieDTO categorie, ObjectifDTO objectif) {

}
