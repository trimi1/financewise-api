package be.helmo.api.errors;

public class DepenseNotFoundException extends Exception {
    public DepenseNotFoundException(String message) {
        super(message);
    }
}
