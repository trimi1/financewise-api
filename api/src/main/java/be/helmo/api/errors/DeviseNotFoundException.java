package be.helmo.api.errors;

public class DeviseNotFoundException extends Exception {
    public DeviseNotFoundException(String message) {
        super(message);
    }
}
