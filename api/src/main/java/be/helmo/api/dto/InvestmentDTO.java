package be.helmo.api.dto;

import java.time.LocalDateTime;

public record InvestmentDTO(String goalName, double totDepenses, double financialGoal, LocalDateTime deadline, String recommendation) {

}
