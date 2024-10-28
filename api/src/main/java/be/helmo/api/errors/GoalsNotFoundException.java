package be.helmo.api.errors;

public class GoalsNotFoundException extends Exception {
    public GoalsNotFoundException(String message) {
        super(message);
    }
}
