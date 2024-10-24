package be.helmo.api.dto;

public record AuthenticationResponseDTO(String token, long expirationIn, int idUser) {
}
