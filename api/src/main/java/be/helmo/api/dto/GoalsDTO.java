package be.helmo.api.dto;

import java.time.LocalDateTime;

public record GoalsDTO(int id, String name, double montant, String devise, LocalDateTime deadline, String recommendation) {
}
