package be.helmo.api.errors;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
