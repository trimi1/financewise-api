package be.helmo.api.errors;

public class PutRequestFailedException extends Exception {
    public PutRequestFailedException(String message) {
        super(message);
    }
}
