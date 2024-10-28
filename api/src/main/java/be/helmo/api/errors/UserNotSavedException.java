package be.helmo.api.errors;

public class UserNotSavedException extends Exception {
    public UserNotSavedException(String message) {
        super(message);
    }
}
