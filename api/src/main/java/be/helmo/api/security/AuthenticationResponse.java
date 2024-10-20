package be.helmo.api.security;


public class AuthenticationResponse {

    private String token;

    private long expirationIn;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setExpirationIn(long expirationIn) {
        this.expirationIn = expirationIn;
    }

    public long getExpirationIn() {
        return this.expirationIn;
    }


}
